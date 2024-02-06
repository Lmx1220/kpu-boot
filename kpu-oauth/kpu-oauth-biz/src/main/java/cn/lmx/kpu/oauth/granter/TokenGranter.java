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
import cn.lmx.kpu.oauth.vo.param.LoginParamVO;
import cn.lmx.kpu.oauth.vo.result.LoginResultVO;

/**
 * 授予token接口
 *
 * @author Dave Syer
 * @author lmx
 * @date 2024/02/07  02:04
 */
public interface TokenGranter {

    /**
     * 获取用户信息
     *
     * @param loginParam 授权参数
     * @return LoginDTO
     */
    R<LoginResultVO> login(LoginParamVO loginParam);

    /**
     * 退出
     *
     * @param token 用户token
     * @return
     */
    R<Boolean> logout(String token);

    /**
     * 切换企业和机构
     *
     * @param companyId companyId
     * @param deptId    deptId
     * @return cn.lmx.kpu.oauth.vo.result.LoginResultVO
     * @author lmx
     * @date 2024/02/07  02:04 PM
     * @create [2024/02/07  02:04 PM ] [lmx] [初始创建]
     */
    LoginResultVO switchOrg(Long companyId, Long deptId);

}
