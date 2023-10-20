import type { <#if table.tplType != TPL_TREE>BasicColumn, </#if>FormSchema } from '/@/components/Table'
import { ActionEnum } from '/@/enums/biz'
import type { FormSchemaExt } from '/@/api/common/formValidateService'
import { ${table.plusApplicationName?cap_first}DictEnum, ${table.plusApplicationName?cap_first}EnumEnum } from '/~/${table.plusApplicationName}/enums/biz'

import { useI18n } from '/@/hooks/web/useI18n'
import { actionOn, enumDef, dictDef, stateDef, yesOrNoDef, datetimeDef } from '/@/cuedes/utils/schema'

const { t } = useI18n();

<#if table.tplType != TPL_TREE>
// 列表页字段
export const columns = (): BasicColumn[] => {
  return [
<#list fields as field>
<#if field.isList && !field.isLogicDeleteField>
    {
      title: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.meta.${field.javaField}'),
<#if field.echoStr?? && field.echoStr?trim != ''>
      dataIndex: ['echoMap', '${field.javaField}'],
      key: '${field.javaField}',
<#else >
      dataIndex: '${field.javaField}',
</#if>
<#if field.width?? && field.width?trim != ''>
      width: ${field.width},
</#if>
<#if field.indexHelpMessage?? && field.indexHelpMessage?trim != ''>
    helpMessage: '${field.indexHelpMessage}',
</#if>
    },
</#if>
</#list>
<#if table.tplType == TPL_TREE>
    {
      title: t('meta.sortValue'),
      dataIndex: 'sortValue',
      width: 80,
    },
</#if>
    {
      title: t('meta.createdTime'),
      dataIndex: 'createdTime',
      sorter: true,
      width: 160,
    },
  ]
}

// 列表页搜索表单字段
export const searchFormSchema = (): FormSchema[] => {
  return [
<#list fields as field>
<#if field.isQuery && !field.isLogicDeleteField>
    {
      label: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.meta.${field.javaField}'),
      field: '${field.javaField}',
      component: '${field.component}',
<#if field.javaType =="LocalDateTime">
      componentProps: datetimeDef.componentProps('YYYY-MM-DD HH:mm:ss'),
<#elseif field.javaType =="LocalDate">
      componentProps: datetimeDef.componentProps('YYYY-MM-DD'),
<#elseif field.javaType =="LocalTime">
      componentProps: datetimeDef.componentProps('HH:mm:ss'),
<#elseif field.javaType =="Boolean">
      componentProps: {
        ...yesOrNoDef.componentProps(),
      },
<#elseif field.enumStr?? && field.enumStr?trim != ''>
      componentProps: {
        // 生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',
        // 请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！
        // ...enumDef.componentProps(${table.plusApplicationName?cap_first}EnumEnum.${field.javaType}),
        ...enumDef.componentProps('${field.javaType}'),
      },
<#elseif field.dictType?? && field.dictType?trim != ''>
      componentProps: {
    <#if field.dictType?contains('"')>
        ...dictDef.componentProps(${field.dictType}),
    <#else>
        <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
        <#assign dt=field.dictType?substring(dotIndex?number) />
        // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';
        // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！
        // ...dictDef.componentProps(${table.plusApplicationName?cap_first}DictEnum.${field.dictType!?replace(".","_")}),
        ...dictDef.componentProps('${dt!?upper_case}'),
    </#if>
      },
<#else></#if>
      colProps: { span: 6 }
    },
</#if>
</#list>
    {
      field: 'createTimeRange',
      label: t('meta.createdTime'),
      component: 'RangePicker',
      colProps: { span: 6 }
    },
  ]
}
</#if>

// 编辑页字段
export const editFormSchema = (type: Ref<ActionEnum>): FormSchema[] => {
  const { on_view } = actionOn(type)

  return [
    { label: 'ID', field: 'id', component: 'Input', show: false },
<#if table.tplType == TPL_TREE>
    { label: 'parentId', field: 'parentId', component: 'Input', defaultValue: 0, show: false },

    {
      label: t('meta.tree_.parent'),
      field: 'parentName',
      component: 'Input',
      defaultValue: '根节点',
      dynamicDisabled: true
    },
<#else>

</#if>
<#list fields as field>
<#if field.isEdit && !field.isLogicDeleteField>
    {
      label: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.meta.${field.javaField}'),
      field: '${field.javaField}',
      component: '${field.component}',
<#if field.editDefValue?? && field.editDefValue?trim != ''>
    <#if field.editDefValue?is_number || field.editDefValue?is_boolean || field.editDefValue == 'true' || field.editDefValue == 'false'>
      defaultValue: ${field.editDefValue},
    <#else>
      defaultValue: '${field.editDefValue}',
    </#if>
</#if>
<#if field.editHelpMessage?? && field.editHelpMessage?trim != ''>
      helpMessage: '${field.editHelpMessage}',
</#if>
<#if field.javaType =="LocalDateTime">
      componentProps: datetimeDef.componentProps('YYYY-MM-DD HH:mm:ss'),
<#elseif field.javaType =="LocalDate">
      componentProps: datetimeDef.componentProps('YYYY-MM-DD'),
<#elseif field.javaType =="LocalTime">
      componentProps: datetimeDef.componentProps('HH:mm:ss'),
<#elseif field.javaType =="Boolean">
      componentProps: {
        ...yesOrNoDef.componentProps(),
      },
<#elseif field.enumStr?? && field.enumStr?trim != ''>
      componentProps: {
        // 生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',
        // 请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！
        // ...enumDef.componentProps(${table.plusApplicationName?cap_first}EnumEnum.${field.javaType}),
        ...enumDef.componentProps('${field.javaType}'),
      },
<#elseif field.dictType?? && field.dictType?trim != ''>
      componentProps: {
    <#if field.dictType?contains('"')>
        ...dictDef.componentProps(${field.dictType}),
    <#else>
        <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
        <#assign dt=field.dictType?substring(dotIndex?number) />
        // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';
        // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！
        // ...dictDef.componentProps(${table.plusApplicationName?cap_first}DictEnum.${field.dictType!?replace(".","_")}),
        ...dictDef.componentProps('${dt!?upper_case}'),
    </#if>
      }
<#else></#if>
    },
</#if>
</#list>
<#if table.tplType == TPL_TREE>
    {
      label: t('meta.sortValue'),
      field: 'sortValue',
      component: 'InputNumber',
    },
</#if>
  ]
}

// 前端自定义表单验证规则
export const customFormSchemaRules = (_): Partial<FormSchemaExt>[] => {
  return [];
}
