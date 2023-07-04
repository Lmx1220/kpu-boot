/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lmx.kpu.oauth.granter;

import cn.lmx.basic.base.R;
import cn.lmx.basic.context.ContextConstants;
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.jwt.TokenUtil;
import cn.lmx.basic.jwt.model.AuthInfo;
import cn.lmx.basic.utils.SpringUtils;
import cn.lmx.basic.utils.StrHelper;
import cn.lmx.kpu.authority.dto.auth.LoginParamDTO;
import cn.lmx.kpu.authority.dto.auth.Online;
import cn.lmx.kpu.authority.entity.auth.User;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import cn.lmx.kpu.authority.service.auth.OnlineService;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.common.properties.SystemProperties;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.oauth.event.LoginEvent;
import cn.lmx.kpu.oauth.event.model.LoginStatusDTO;
import cn.lmx.kpu.tenant.service.TenantService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static cn.lmx.kpu.oauth.granter.RefreshTokenGranter.GRANT_TYPE;

/**
 * RefreshTokenGranter
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component(GRANT_TYPE)
public class RefreshTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "refresh_token";

    public RefreshTokenGranter(TokenUtil tokenUtil, UserService userService, TenantService tenantService, AppendixService appendixService,
                               ApplicationService applicationService, DatabaseProperties databaseProperties,
                               OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService,
                databaseProperties, onlineService, systemProperties, appendixService);
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        String grantType = loginParam.getGrantType();
        String refreshTokenStr = loginParam.getRefreshToken();
        if (StrHelper.isAnyBlank(grantType, refreshTokenStr) || !GRANT_TYPE.equals(grantType)) {
            return R.fail("加载用户信息失败");
        }
        // 2.检测client是否可用
        R<String[]> checkClient = checkClient();
        if (!checkClient.getIsSuccess()) {
            return R.fail(checkClient.getMsg());
        }

        AuthInfo authInfo = tokenUtil.parseRefreshToken(refreshTokenStr);

        if (!ContextConstants.REFRESH_TOKEN_KEY.equals(authInfo.getTokenType())) {
            return R.fail("refreshToken无效，无法加载用户信息");
        }

        User user = userService.getByIdCache(authInfo.getUserId());

        if (user == null || !user.getState()) {
            String msg = "您已被禁用！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(authInfo.getUserId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }


        AuthInfo newAuth = createToken(user);
        Online online = getOnline(checkClient.getData()[0], newAuth);

        //成功登录事件
        LoginStatusDTO.success(user.getId(), online);
        onlineService.save(online);
        return R.success(newAuth);

    }
}
