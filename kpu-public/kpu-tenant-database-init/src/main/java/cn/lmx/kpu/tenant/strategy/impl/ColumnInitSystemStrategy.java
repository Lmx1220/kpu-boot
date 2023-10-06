package cn.lmx.kpu.tenant.strategy.impl;

import cn.hutool.core.map.MapUtil;
import cn.lmx.basic.context.ContextUtil;
import cn.lmx.kpu.authority.entity.auth.*;
import cn.lmx.kpu.authority.entity.common.Dict;
import cn.lmx.kpu.authority.entity.common.Parameter;
import cn.lmx.kpu.authority.enumeration.auth.ApplicationAppTypeEnum;
import cn.lmx.kpu.authority.enumeration.auth.ResourceTypeEnum;
import cn.lmx.kpu.authority.service.auth.*;
import cn.lmx.kpu.authority.service.common.DictService;
import cn.lmx.kpu.authority.service.common.ParameterService;
import cn.lmx.kpu.common.constant.ParameterKey;
import cn.lmx.kpu.model.constant.EchoDictItem;
import cn.lmx.kpu.model.enumeration.base.RoleCategoryEnum;
import cn.lmx.kpu.tenant.dto.TenantConnectDTO;
import cn.lmx.kpu.tenant.strategy.InitSystemStrategy;
import com.baidu.fsg.uid.UidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 初始化规则:
 * 手动创建数据
 *
 * @author lmx
 * @date 2023/7/4 14:27
 */
@Service("COLUMN")
@Slf4j
@RequiredArgsConstructor
public class ColumnInitSystemStrategy implements InitSystemStrategy {
    private static final String NOTICE = "notice";
    private static final String TODO = "todo";
    private static final String DONE = "done";
    private static final String STARTED = "started";
    private static final String ORG = "org";
    private static final String STATION = "station";
    private static final String USER = "user";
    private static final String MENU = "menu";
    private static final String ROLE = "role";
    private static final String DICTIONARY = "dictionary";
    private static final String AREA = "area";
    private static final String PARAMETER = "parameter";
    private static final String APPLICATION = "application";
    private static final String ONLINE = "online";
    private static final String OPT_LOG = "optLog";
    private static final String LOGIN_LOG = "loginLog";
    private static final String SMS = "sms";
    private static final String SMS_TEMPLATE = "smsTemplate";
    private static final String MSG = "msg";
    private static final String ATTACHMENT = "attachment";
    private final ResourceService resourceService;
    private final RoleService roleService;
    private final RoleResourceService roleResourceService;
    private final ApplicationService applicationService;
    private final DictService dictionaryService;
    private final ParameterService parameterService;
    private final UidGenerator uidGenerator;
    private final UserService userService;

    /**
     * 我*，这种方式太脑残了，但想不出更好的方式初始化数据，希望各位大神有好的初始化方法记得跟我说声！！！
     * 写这段代码写得想去si ~~~
     * <p>
     * 不能用 SCHEMA 模式的初始化脚本方法： 因为id 会重复，租户编码会重复！
     *
     * @param tenantConnect 待初始化租户编码
     * @return 是否陈成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initConnect(TenantConnectDTO tenantConnect) {
        String tenant = tenantConnect.getTenant();
        // 初始化数据
        //1, 生成并关联 ID TENANT
        ContextUtil.setTenant(tenant);

        // 菜单 资源 角色 角色_资源 字典 参数
        List<Resource> menuList = new ArrayList<>();
        Map<String, Long> menuMap = MapUtil.newHashMap();
        boolean menuFlag = initResource(menuList, menuMap);

        List<Resource> resourceList = new ArrayList<>();
        boolean resourceFlag = initResourc(resourceList, menuMap);

        // 角色
        Long roleId = uidGenerator.getUid();
        boolean roleFlag = initRole(roleId);

        // 资源权限
        boolean roleResourceFlag = initRoleResource(menuList, resourceList, roleId);

        // 字典
        initDict();

        //参数
        initParameter();

        initApplication();

        // 内置超级管理员
        initSuperUser();

        return menuFlag && resourceFlag && roleFlag && roleResourceFlag;
    }

    private boolean initApplication() {
        List<Application> list = new ArrayList<>();
        list.add(Application.builder().clientId("kpu_web").clientSecret("kpu_web_secret").website("https://boot.lmx.top/kpu-web/").name("微服务快速开发管理后台").appType(ApplicationAppTypeEnum.PC).state(true).build());
        list.add(Application.builder().clientId("kpu_web_plus").clientSecret("kpu_web_plus_secret").website("https://boot.lmx.top/").name("微服务快速开发管理后台").appType(ApplicationAppTypeEnum.PC).state(true).build());
        return applicationService.saveBatch(list);
    }

    private boolean initSuperUser() {
        User user = User.builder()
                .username("kpuAdmin").nickName("内置超级管理员").password("kpu")
                .readonly(true).sex("1").avatar("cnrhVkzwxjPwAaCfPbdc.png")
                .state(true).passwordErrorNum(0)
                .build();
        return userService.initUser(user);
    }

    private boolean initParameter() {
        List<Parameter> list = new ArrayList<>();
        list.add(Parameter.builder().key(ParameterKey.LOGIN_POLICY).name("登录策略").value(ParameterKey.LoginPolicy.MANY.name()).remarks("ONLY_ONE:一个用户只能登录一次; MANY:用户可以任意登录; ONLY_ONE_CLIENT:一个用户在一个应用只能登录一次").state(true).readonly(true).build());
        return parameterService.saveBatch(list);
    }

    private boolean initRoleResource(List<Resource> menuList, List<Resource> resourceList, Long roleId) {
        List<RoleResource> roleResourceList = new ArrayList<>();
        menuList.forEach(item ->
                roleResourceList.add(RoleResource.builder().resourceId(item.getId()).roleId(roleId).build())
        );
        resourceList.forEach(item ->
                roleResourceList.add(RoleResource.builder().resourceId(item.getId()).roleId(roleId).build())
        );
        return roleResourceService.saveBatch(roleResourceList);
    }

    private boolean initRole(Long roleId) {
        Role role = Role.builder().id(roleId).name("超级管理员").code
                ("SUPER_ADMIN").remarks("内置超级管理员").category(RoleCategoryEnum.FUNCTION.getCode()).readonly(true).build();
        return roleService.getSuperManager().save(role);
    }

    private boolean initResourc(List<Resource> resourceList, Map<String, Long> menuMap) {
        Long orgId = menuMap.get(ORG);
        resourceList.add(Resource.builder().code
                ("authority:org:add").name("新增").id(orgId).build());
        resourceList.add(Resource.builder().code
                ("authority:org:delete").name("删除").id(orgId).build());
        resourceList.add(Resource.builder().code
                ("authority:org:edit").name("编辑").id(orgId).build());
        resourceList.add(Resource.builder().code
                ("authority:org:view").name("查看").id(orgId).build());
        resourceList.add(Resource.builder().code
                ("authority:org:import").name("导入").id(orgId).build());
        resourceList.add(Resource.builder().code
                ("authority:org:export").name("导出").id(orgId).build());

        Long stationId = menuMap.get(STATION);
        resourceList.add(Resource.builder().code
                ("authority:station:add").name("新增").id(stationId).build());
        resourceList.add(Resource.builder().code
                ("authority:station:delete").name("删除").id(stationId).build());
        resourceList.add(Resource.builder().code
                ("authority:station:edit").name("编辑").id(stationId).build());
        resourceList.add(Resource.builder().code
                ("authority:station:view;authority:org:view").name("查看").id(stationId).build());
        resourceList.add(Resource.builder().code
                ("authority:station:import").name("导入").id(stationId).build());
        resourceList.add(Resource.builder().code
                ("authority:station:export").name("导出").id(stationId).build());

        Long userId = menuMap.get(USER);
        resourceList.add(Resource.builder().code
                ("authority:user:add").name("新增").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:delete").name("删除").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:edit").name("编辑").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:view;authority:station:view;authority:org:view").name("查看").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:updateState").name("修改状态").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:resetPassword").name("重置密码").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:import").name("导入").id(userId).build());
        resourceList.add(Resource.builder().code
                ("authority:user:export").name("导出").id(userId).build());

        Long msgId = menuMap.get(MSG);
        resourceList.add(Resource.builder().code
                ("msg:msg:add;authority:user:view;authority:role:view").name("新增").id(msgId).build());
        resourceList.add(Resource.builder().code
                ("msg:msg:delete").name("删除").id(msgId).build());
        resourceList.add(Resource.builder().code
                ("msg:msg:edit;authority:user:view;authority:role:view").name("编辑").id(msgId).build());
        resourceList.add(Resource.builder().code
                ("msg:msg:view").name("查看").id(msgId).build());
        resourceList.add(Resource.builder().code
                ("msg:msg:mark").name("标记已读").id(msgId).build());
        resourceList.add(Resource.builder().code
                ("msg:msg:export").name("导出").id(msgId).build());

        Long smsTemplateId = menuMap.get(SMS_TEMPLATE);
        resourceList.add(Resource.builder().code
                ("msg:smsTemplate:add").name("新增").id(smsTemplateId).build());
        resourceList.add(Resource.builder().code
                ("msg:smsTemplate:delete").name("删除").id(smsTemplateId).build());
        resourceList.add(Resource.builder().code
                ("msg:smsTemplate:edit").name("编辑").id(smsTemplateId).build());
        resourceList.add(Resource.builder().code
                ("msg:smsTemplate:view").name("查看").id(smsTemplateId).build());

        Long smsManageId = menuMap.get(SMS);
        resourceList.add(Resource.builder().code
                ("msg:sms:add").name("新增").id(smsManageId).build());
        resourceList.add(Resource.builder().code
                ("msg:sms:delete").name("删除").id(smsManageId).build());
        resourceList.add(Resource.builder().code
                ("msg:sms:edit").name("编辑").id(smsManageId).build());
        resourceList.add(Resource.builder().code
                ("msg:sms:view;msg:smsTemplate:view").name("查看").id(smsManageId).build());

        Long fileId = menuMap.get(ATTACHMENT);
        resourceList.add(Resource.builder().code
                ("file:attachment:add").name("新增").id(fileId).build());
        resourceList.add(Resource.builder().code
                ("file:attachment:delete").name("删除").id(fileId).build());
        resourceList.add(Resource.builder().code
                ("file:attachment:edit").name("编辑").id(fileId).build());
        resourceList.add(Resource.builder().code
                ("file:attachment:view").name("查看").id(fileId).build());
        resourceList.add(Resource.builder().code
                ("file:attachment:download").name("下载").id(fileId).build());

        Long id = menuMap.get(MENU);
        resourceList.add(Resource.builder().code
                ("authority:menu:add").name("新增").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:menu:delete").name("删除").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:menu:edit").name("编辑").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:menu:view;authority:resource:view").name("查看").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:menu:import").name("导入").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:menu:export").name("导出").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:resource:add").name("添加").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:resource:delete").name("删除").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:resource:edit").name("编辑").id(id).build());
        resourceList.add(Resource.builder().code
                ("authority:resource:view").name("查看").id(id).build());

        Long roleId = menuMap.get(ROLE);
        resourceList.add(Resource.builder().code
                ("authority:role:add;authority:org:view").name("新增").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:delete").name("删除").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:edit;authority:org:view").name("编辑").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:view").name("查看").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:config;authority:user:view").name("配置").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:auth;authority:menu:view;authority:resource:view").name("授权").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:import").name("导入").id(roleId).build());
        resourceList.add(Resource.builder().code
                ("authority:role:export").name("导出").id(roleId).build());


        Long dictionaryId = menuMap.get(DICTIONARY);
        resourceList.add(Resource.builder().code
                ("authority:dictionary:add").name("新增").id(dictionaryId).build());
        resourceList.add(Resource.builder().code
                ("authority:dictionary:delete").name("删除").id(dictionaryId).build());
        resourceList.add(Resource.builder().code
                ("authority:dictionary:edit").name("编辑").id(dictionaryId).build());
        resourceList.add(Resource.builder().code
                ("authority:dictionary:view").name("查看").id(dictionaryId).build());

        Long areaId = menuMap.get(AREA);
        resourceList.add(Resource.builder().code
                ("authority:area:add").name("新增").id(areaId).build());
        resourceList.add(Resource.builder().code
                ("authority:area:delete").name("删除").id(areaId).build());
        resourceList.add(Resource.builder().code
                ("authority:area:edit").name("编辑").id(areaId).build());
        resourceList.add(Resource.builder().code
                ("authority:area:view").name("查看").id(areaId).build());

        Long parameterId = menuMap.get(PARAMETER);
        resourceList.add(Resource.builder().code
                ("authority:parameter:add").name("新增").id(parameterId).build());
        resourceList.add(Resource.builder().code
                ("authority:parameter:delete").name("删除").id(parameterId).build());
        resourceList.add(Resource.builder().code
                ("authority:parameter:edit").name("编辑").id(parameterId).build());
        resourceList.add(Resource.builder().code
                ("authority:parameter:view").name("查看").id(parameterId).build());

        Long optLogId = menuMap.get(OPT_LOG);
        resourceList.add(Resource.builder().code
                ("authority:optLog:delete").name("删除").id(optLogId).build());
        resourceList.add(Resource.builder().code
                ("authority:optLog:view").name("查看").id(optLogId).build());

        Long loginLogId = menuMap.get(LOGIN_LOG);
        resourceList.add(Resource.builder().code
                ("authority:loginLog:delete").name("删除").id(loginLogId).build());
        resourceList.add(Resource.builder().code
                ("authority:loginLog:view").name("查看").id(loginLogId).build());

        Long onlineId = menuMap.get(ONLINE);
        resourceList.add(Resource.builder().code
                ("authority:online:delete").name("删除").id(onlineId).build());
        resourceList.add(Resource.builder().code
                ("authority:online:view").name("查看").id(onlineId).build());

        Long applicationId = menuMap.get(APPLICATION);
        resourceList.add(Resource.builder().code
                ("authority:application:add").name("新增").id(applicationId).build());
        resourceList.add(Resource.builder().code
                ("authority:application:delete").name("删除").id(applicationId).build());
        resourceList.add(Resource.builder().code
                ("authority:application:edit").name("编辑").id(applicationId).build());
        resourceList.add(Resource.builder().code
                ("authority:application:view").name("查看").id(applicationId).build());

        return resourceService.saveBatch(resourceList);
    }

    private boolean initResource(List<Resource> menuList, Map<String, Long> menuMap) {
        Long workbenchId = uidGenerator.getUid();
        Long organizationId = uidGenerator.getUid();
        Long resourcesId = uidGenerator.getUid();
        Long activitiId = uidGenerator.getUid();
        Long systemId = uidGenerator.getUid();
        Long gatewayId = uidGenerator.getUid();
        // 1级菜单
        menuList.add(Resource.builder().id(workbenchId).resourceType(ResourceTypeEnum.MENU.getCode()).title("工作台").path("/workbench").component("Layout").icon("fa fa-tachometer").sortValue(20).readonly(true).build());
        menuList.add(Resource.builder().id(organizationId).resourceType(ResourceTypeEnum.MENU.getCode()).title("组织管理").path("/org").component("Layout").icon("fa fa-users").sortValue(30).readonly(true).build());
        menuList.add(Resource.builder().id(resourcesId).resourceType(ResourceTypeEnum.MENU.getCode()).title("资源中心").path("/resources").component("Layout").icon("fa fa-cloud").sortValue(40).readonly(true).build());
        menuList.add(Resource.builder().id(activitiId).resourceType(ResourceTypeEnum.MENU.getCode()).title("流程管理").path("/activiti").component("Layout").icon("fa fa-retweet").sortValue(50).readonly(true).build());
        menuList.add(Resource.builder().id(systemId).resourceType(ResourceTypeEnum.MENU.getCode()).title("系统设置").path("/system").component("Layout").icon("fa fa-gears").sortValue(60).readonly(true).build());
        menuList.add(Resource.builder().id(gatewayId).resourceType(ResourceTypeEnum.MENU.getCode()).title("网关管理").path("/gateway").component("Layout").icon("fa fa-sort-amount-asc").sortValue(70).readonly(true).build());

        // 工作台
        Long noticeId = uidGenerator.getUid();
        menuMap.put(NOTICE, noticeId);
        menuList.add(Resource.builder().id(noticeId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(workbenchId).title("通知公告").path("/workbench/notice").component("kpu/workbench/notice/index").sortValue(10).readonly(true).build());
        Long todoId = uidGenerator.getUid();
        menuMap.put(TODO, todoId);
        menuList.add(Resource.builder().id(todoId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(workbenchId).title("待我审批").path("/workbench/todo").component("kpu/workbench/todo/index").sortValue(20).readonly(true).build());
        Long doneId = uidGenerator.getUid();
        menuMap.put(DONE, doneId);
        menuList.add(Resource.builder().id(doneId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(workbenchId).title("我已审批").path("/workbench/done").component("kpu/workbench/done/index").sortValue(30).readonly(true).build());
        Long startedId = uidGenerator.getUid();
        menuMap.put(STARTED, startedId);
        menuList.add(Resource.builder().id(startedId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(workbenchId).title("我发起的").path("/workbench/started").component("kpu/workbench/started/index").sortValue(40).readonly(true).build());

        // 组织管理
        Long orgId = uidGenerator.getUid();
        menuMap.put(ORG, orgId);
        menuList.add(Resource.builder().id(orgId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(organizationId).title("机构管理").path("/org/org").component("kpu/org/org/index").sortValue(10).readonly(true).build());
        Long stationId = uidGenerator.getUid();
        menuMap.put(STATION, stationId);
        menuList.add(Resource.builder().id(stationId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(organizationId).title("岗位管理").path("/org/station").component("kpu/org/station/index").sortValue(20).readonly(true).build());
        Long userId = uidGenerator.getUid();
        menuMap.put(USER, userId);
        menuList.add(Resource.builder().id(userId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(organizationId).title("用户管理").path("/org/user").component("kpu/org/user/index").sortValue(30).readonly(true).build());

        // 资源中心
        Long msgId = uidGenerator.getUid();
        menuMap.put(MSG, msgId);
        menuList.add(Resource.builder().id(msgId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(resourcesId).title("消息中心").path("/resources/msg").component("kpu/resources/msg/index").sortValue(10).readonly(true).build());
        Long smsTemplateId = uidGenerator.getUid();
        menuMap.put(SMS_TEMPLATE, smsTemplateId);
        menuList.add(Resource.builder().id(smsTemplateId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(resourcesId).title("短信模版").path("/resources/smsTemplate").component("kpu/resources/smsTemplate/index").sortValue(20).readonly(true).build());
        Long smsId = uidGenerator.getUid();
        menuMap.put(SMS, smsId);
        menuList.add(Resource.builder().id(smsId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(resourcesId).title("短信中心").path("/resources/sms").component("kpu/resources/sms/index").sortValue(30).readonly(true).build());
        Long attachmentId = uidGenerator.getUid();
        menuMap.put(ATTACHMENT, attachmentId);
        menuList.add(Resource.builder().id(attachmentId).resourceType(ResourceTypeEnum.MENU.getCode()).parentId(resourcesId).title("附件管理").path("/resources/attachment").component("kpu/resources/attachment/index").sortValue(40).readonly(true).build());

        // 流程管理
        Long leaveId = uidGenerator.getUid();
        Long reId = uidGenerator.getUid();
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(activitiId).title("流程部署").path("/activiti/deploymentManager").component("kpu/activiti/deploymentManager/index").sortValue(10).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(activitiId).title("模型管理").path("/activiti/modelManager").component("kpu/activiti/modelManager/index").sortValue(20).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(leaveId).parentId(activitiId).title("请假流程").path("/activiti/leave").component("Layout").sortValue(30).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(reId).parentId(activitiId).title("报销流程").path("/activiti/reimbursement").component("kpu/activiti/reimbursement/Index").sortValue(40).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(leaveId).title("请假管理").path("/activiti/leave/instant").component("kpu/activiti/leave/instantManager/index").sortValue(1).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(leaveId).title("请假任务").path("/activiti/leave/ruTask").component("kpu/activiti/leave/ruTask/index").sortValue(2).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(reId).title("报销管理").path("/activiti/reimbursement/instantManager").component("kpu/activiti/reimbursement/instantManager/index").sortValue(1).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(reId).title("报销任务").path("/activiti/reimbursement/ruTask").component("kpu/activiti/reimbursement/ruTask/index").sortValue(2).readonly(true).build());

        // 系统管理
        Long id = uidGenerator.getUid();
        menuMap.put(MENU, id);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(id).parentId(systemId).title("菜单管理").path("/system/menu").component("kpu/system/menu/index").sortValue(10).readonly(true).build());
        Long roleId = uidGenerator.getUid();
        menuMap.put(ROLE, roleId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(roleId).parentId(systemId).title("角色管理").path("/system/role").component("kpu/system/role/index").sortValue(20).readonly(true).build());
        Long dictionaryId = uidGenerator.getUid();
        menuMap.put(DICTIONARY, dictionaryId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(dictionaryId).parentId(systemId).title("字典管理").path("/system/dictionary").component("kpu/system/dictionary/index").sortValue(30).readonly(true).build());
        Long areaId = uidGenerator.getUid();
        menuMap.put(AREA, areaId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(areaId).parentId(systemId).title("地区管理").path("/system/area").component("kpu/system/area/index").sortValue(40).readonly(true).build());
        Long parameterId = uidGenerator.getUid();
        menuMap.put(PARAMETER, parameterId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(parameterId).parentId(systemId).title("参数管理").path("/system/parameter").component("kpu/system/parameter/index").sortValue(50).readonly(true).build());
        Long optLogId = uidGenerator.getUid();
        menuMap.put(OPT_LOG, optLogId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(optLogId).parentId(systemId).title("操作日志").path("/system/optLog").component("kpu/system/optLog/index").sortValue(60).readonly(true).build());
        Long loginLogId = uidGenerator.getUid();
        menuMap.put(LOGIN_LOG, loginLogId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(loginLogId).parentId(systemId).title("登录日志").path("/system/loginLog").component("kpu/system/loginLog/index").sortValue(70).readonly(true).build());
        Long onlineId = uidGenerator.getUid();
        menuMap.put(ONLINE, onlineId);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(onlineId).parentId(systemId).title("在线用户").path("/system/online").component("kpu/system/online/index").sortValue(80).readonly(true).build());
        Long applicationApi = uidGenerator.getUid();
        menuMap.put(APPLICATION, applicationApi);
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).id(applicationApi).parentId(systemId).title("应用管理").path("/system/application").component("kpu/system/application/index").sortValue(90).readonly(true).build());

        // 网关管理
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(gatewayId).title("限流规则").path("/gateway/ratelimiter").component("kpu/gateway/ratelimiter/index").sortValue(10).readonly(true).build());
        menuList.add(Resource.builder().resourceType(ResourceTypeEnum.MENU.getCode()).parentId(gatewayId).title("阻止访问").path("/gateway/blocklist").component("kpu/gateway/blocklist/index").sortValue(20).readonly(true).build());
        return resourceService.saveBatch(menuList);
    }


    private boolean initDict() {
        List<Dict> dictionaryList = new ArrayList<>();
        Integer nationSort = 1;
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("01").name("汉族").sortValue(nationSort++).build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("02").name("壮族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("03").name("满族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("04").name("回族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("05").name("苗族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("06").name("维吾尔族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("07").name("土家族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("08").name("彝族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("09").name("蒙古族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("10").name("藏族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("11").name("布依族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("12").name("侗族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("13").name("瑶族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("14").name("朝鲜族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("15").name("白族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("16").name("哈尼族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("17").name("哈萨克族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("18").name("黎族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("19").name("傣族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("20").name("畲族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("21").name("傈僳族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("22").name("仡佬族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("23").name("东乡族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("24").name("高山族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("25").name("拉祜族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("26").name("水族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("27").name("佤族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("28").name("纳西族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("29").name("羌族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("30").name("土族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("31").name("仫佬族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("32").name("锡伯族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("33").name("柯尔克孜族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("34").name("达斡尔族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("35").name("景颇族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("36").name("毛南族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("37").name("撒拉族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("38").name("塔吉克族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("39").name("阿昌族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("40").name("普米族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("41").name("鄂温克族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("42").name("怒族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("43").name("京族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("44").name("基诺族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("45").name("德昂族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("46").name("保安族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("47").name("俄罗斯族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("48").name("裕固族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("49").name("乌兹别克族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("50").name("门巴族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("51").name("鄂伦春族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("52").name("独龙族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("53").name("塔塔尔族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("54").name("赫哲族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("55").name("珞巴族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("56").name("布朗族").sortValue(nationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.NATION).name("民族")
                .key("57").name("其他").sortValue(nationSort++)
                .build());

        Integer positionStatusSort = 1;
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.POSITION_STATUS).name("职位状态")
                .key("01").name("在职").sortValue(positionStatusSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.POSITION_STATUS).name("职位状态")
                .key("02").name("请假").sortValue(positionStatusSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.POSITION_STATUS).name("职位状态")
                .key("03").name("离职").sortValue(positionStatusSort++)
                .build());

        Integer educationSort = 1;
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("01").name("小学").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("02").name("中学").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("03").name("高中").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("04").name("专科").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("05").name("本科").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("06").name("硕士").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("07").name("博士").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("08").name("博士后").sortValue(educationSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.EDUCATION).name("学历")
                .key("20").name("其他").sortValue(educationSort++)
                .build());

        Integer areaLevelSort = 1;
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.AREA_LEVEL).name("行政区划")
                .key("01").name("国家").sortValue(areaLevelSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.AREA_LEVEL).name("行政区划")
                .key("02").name("省份/直辖市").sortValue(areaLevelSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.AREA_LEVEL).name("行政区划")
                .key("03").name("地市").sortValue(areaLevelSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.AREA_LEVEL).name("行政区划")
                .key("04").name("区县").sortValue(areaLevelSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.AREA_LEVEL).name("行政区划")
                .key("05").name("乡镇").sortValue(areaLevelSort++)
                .build());


        Integer orgTypeSort = 1;
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.ORG_TYPE).name("机构类型")
                .key("01").name("单位").sortValue(orgTypeSort++)
                .build());
        dictionaryList.add(Dict.builder().parentKey(EchoDictItem.ORG_TYPE).name("机构类型")
                .key("02").name("部门").sortValue(orgTypeSort++)
                .build());
        return dictionaryService.saveBatch(dictionaryList);
    }

    @Override
    public boolean reset(String tenant) {
        //TODO 待实现
        // 1，清空所有表的数据
        // 2，重新初始化 tenant
        // 3，重新初始化 业务数据
        //        init(tenant);
        return true;
    }

    @Override
    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
        // 清空所有表中当前租户的数据
        //TODO 待实现
        // 1,查询系统中的所有表
        // 删除该租户的所有数据
        return true;
    }
}
