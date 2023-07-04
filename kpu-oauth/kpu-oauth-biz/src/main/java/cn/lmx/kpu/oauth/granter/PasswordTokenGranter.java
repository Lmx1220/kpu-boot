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
import cn.lmx.basic.database.properties.DatabaseProperties;
import cn.lmx.basic.jwt.TokenUtil;
import cn.lmx.basic.jwt.model.AuthInfo;
import cn.lmx.kpu.authority.dto.auth.LoginParamDTO;
import cn.lmx.kpu.authority.service.auth.ApplicationService;
import cn.lmx.kpu.authority.service.auth.OnlineService;
import cn.lmx.kpu.authority.service.auth.UserService;
import cn.lmx.kpu.common.properties.SystemProperties;
import cn.lmx.kpu.file.service.AppendixService;
import cn.lmx.kpu.tenant.service.TenantService;
import org.springframework.stereotype.Component;

import static cn.lmx.kpu.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

/**
 * 账号密码登录获取token
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Component(GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    public PasswordTokenGranter(TokenUtil tokenUtil, UserService userService, TenantService tenantService, AppendixService appendixService,
                                ApplicationService applicationService, DatabaseProperties databaseProperties,
                                OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties, onlineService, systemProperties, appendixService);
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}
