-- SQL脚本仅需执行一次，重复执行会生成多条数据，请谨慎手动执行！
<#setting number_format="#">
<#assign menuCode = "${table.plusModuleName}:${table.entityName?uncap_first}"/>
-- 创建菜单
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`,`redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`,`group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`,`tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`,`updated_time`) VALUES (${menuId}, '', '${table.menuName}',<#if table.popupType != POPUP_TYPE_JUMP>'${plusName}${table.entityName}List'<#else>''</#if>,<#if table.popupType != POPUP_TYPE_JUMP>'20'<#else>'10'</#if>, '', b'0', '','/${table.plusModuleName}/${table.entityName?uncap_first}',<#if table.popupType != POPUP_TYPE_JUMP>'${table.plusModuleName}/${table.entityName?uncap_first}/index.vue'<#else>'Layout'</#if>,b'1', 3, '${table.menuIcon!''}', '', '',NULL, NULL, NULL, ${parentMenuId}, 1, '/${parentMenuId}/','{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":true,\"breadcrumb\":true,\"activeMenu\":\"\",\"cache\":[],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);


<#if table.popupType == POPUP_TYPE_JUMP>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${menuListId}, '', '${table.menuName}列表', '${plusName}${table.entityName}List', '20', '', b'0', '', '', '${table.plusModuleName}/${table.entityName?uncap_first}/index.vue', b'1', 1, '', '', '', NULL, NULL, NULL, 1, 3, '/${parentMenuId}/${menuId}/','{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":false,\"activeMenu\":\"/${table.plusModuleName}/${table.entityName?uncap_first}\",\"cache\":[\"${plusName}${table.entityName?uncap_first}Edit\"],\"noCache\":[],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${menuEditId}, '', '${table.menuName}修改', '${plusName}${table.entityName}Edit', '20', '', b'0', '', '/${table.plusModuleName}/${table.entityName?uncap_first}/edit/:id', '${table.plusModuleName}/${table.entityName?uncap_first}/index.vue', b'1', 1, '', '', '', NULL, NULL, NULL, 1, 3, '/${parentMenuId}/${menuId}/','{\"defaultOpened\":false,\"permanent\":false,\"auth\":[],\"sidebar\":false,\"breadcrumb\":true,\"activeMenu\":\"/${table.plusModuleName}/${table.entityName?uncap_first}\",\"cache\":true,\"noCache\":[\"${plusName}${table.entityName?uncap_first}List\"],\"badge\":\"\",\"link\":\"\",\"iframe\":\"\",\"copyright\":false,\"paddingBottom\":\"0px\"}',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
-- 创建按钮
<#if table.addShow>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${buttonAddId},'<#if table.addAuth?? && table.addAuth?trim != ''>${table.addAuth}<#else>${menuCode}:add</#if>','新增',  '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL, ${menuId}, 1, '/${parentMenuId}/${menuId}/', '',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);</#if>
<#if table.editShow>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${buttonEditId},'<#if table.editAuth?? && table.editAuth?trim != ''>${table.editAuth}<#else>${menuCode}:edit</#if>','编辑',  '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL, ${menuId}, 2, '/${parentMenuId}/${menuId}/', '',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.copyShow>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${buttonCopyId},'<#if table.copyAuth?? && table.copyAuth?trim != ''>${table.copyAuth}<#else>${menuCode}:copy</#if>','复制',  '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL, ${menuId}, 3, '/${parentMenuId}/${menuId}/', '',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.deleteShow>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${buttonDeleteId},'<#if table.deleteAuth?? && table.deleteAuth?trim != ''>${table.deleteAuth}<#else>${menuCode}:delete</#if>','删除',  '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL, ${menuId}, 4, '/${parentMenuId}/${menuId}/', '',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.viewShow>
INSERT INTO c_resource (`id`, `code`, `title`, `name`, `resource_type`, `remarks`, `is_general`, `redirect`, `path`, `component`, `state`, `sort_value`, `icon`, `active_icon`, `group_`, `data_scope`, `custom_class`, `is_def`, `parent_id`, `tree_grade`, `tree_path`, `meta_json`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES (${buttonViewId},'<#if table.viewAuth?? && table.viewAuth?trim != ''>${table.viewAuth}<#else>${menuCode}:view</#if>','查看',  '', '30', '', b'0', NULL, NULL, '', b'1', 1, '', NULL, '', NULL, NULL, NULL, ${menuId}, 5, '/${parentMenuId}/${menuId}/', '',${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>,  ${createdBy},<#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>


-- 给内置的租户授权, 请注意：内置租户以外的租户，需要自行在【角色管理】-【应用资源授权】中给其他租户授权！
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrMenuId}, ${roleId}, ${menuId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
<#if table.popupType == POPUP_TYPE_JUMP>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrMenuListId}, ${roleId}, ${menuListId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_time`, `created_by`, updated_by, updated_time) VALUES (${rrMenuEditId}, ${roleId}, ${menuEditId},  ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.addShow>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrButtonAddId}, ${roleId}, ${buttonAddId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.editShow>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrButtonEditId}, ${roleId}, ${buttonEditId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.copyShow>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrButtonCopyId}, ${roleId}, ${buttonCopyId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.deleteShow>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrButtonDeleteId}, ${roleId}, ${buttonDeleteId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.viewShow>
INSERT INTO c_role_resource (`id`, `role_id`, `resource_id`, `created_by`, `created_time`, updated_by, updated_time) VALUES (${rrButtonViewId}, ${roleId}, ${buttonViewId}, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>

-- 字典
<#list dictList as dict>
<#assign dictId=uidGenerator.getUid()/>
INSERT INTO c_dict(id, parent_id, parent_key, classify, key_, name, state, remarks, sort_value, icon, css_style, css_class, created_by, created_time, updated_by, updated_time)
VALUES (${dictId}, 0, '', '20', '${dict.key}', '${dict.value}', 1, '${dict.str!}', 1, '', '', '', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);

<#list dict.itemList as item>
INSERT INTO c_dict(id, parent_id, parent_key, classify, key_, name, state, remarks, sort_value, icon, css_style, css_class, created_by, created_time, updated_by, updated_time)
VALUES (${uidGenerator.getUid()}, ${dictId}, '${dict.key}', '20', '${item.key}', '${item.value}', 1, '', 1, '', '', '', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#list>

-- 删除数据，用于测试
-- delete from c_dict where id in (${dictId}) or parent_id in (${dictId});
</#list>

-- 删除数据，用于测试
/*
delete from c_resource where id in (${menuId}, ${buttonAddId}, ${buttonEditId}, ${buttonCopyId}, ${buttonDeleteId}, ${buttonViewId});
delete from c_role_resource where id in (${rrMenuId}, ${rrMenuListId}, ${rrMenuEditId}, ${rrButtonAddId}, ${rrButtonEditId}, ${rrButtonCopyId}, ${rrButtonDeleteId}, ${rrButtonViewId});
*/
