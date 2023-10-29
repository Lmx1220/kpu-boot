<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import { cloneDeep } from 'lodash-es'
import { ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'
import type { FormSchemaExt } from '@/api/modules/common/formValidateService'
import { getValidateRuleObj } from '@/api/modules/common/formValidateService'
import { ActionEnum, VALIDATE_API } from '@/enums/commonEnum'
import yesOrNoEnum from '@/enums/common/yesOrNoEnum'
import { enumComponentProps, dictComponentProps } from '@/util/common'
import type { FormConfig } from '#/global'
import crud${table.entityName}, { Api } from '@/api/modules/${table.plusModuleName}/${table.entityName?uncap_first}'

export interface Props {
  id?: string
  type?: ActionEnum
  currentData: { [key: string]: any }
  parentData?: { [key: string]: any }
}

const props = withDefaults(defineProps<Props>(), {
  id: '',
  type: ActionEnum.ADD,
  currentData: () => ({
    id: '',
  }),
})

function customFormSchemaRules(_: ActionEnum): Partial<FormSchemaExt>[] {
  return []
}

const { t } = useI18n()

const current = computed(() => props.currentData)

const defaultForm = {

}

const data = ref<FormConfig>({
  loading: false,
  form: {
    ...defaultForm,
  },
  rules: {

  },
})

const form = ref<FormInstance>()

watch(() => [props.id, props.type, props.currentData.id], () => {
  form.value?.resetFields()
  Object.assign(data.value.form, cloneDeep(current.value))
})

watch(() => props.type, async (value: ActionEnum, oldValue: ActionEnum) => {
  if (value !== oldValue) {
    if (value !== ActionEnum.VIEW) {
      const validateApi = Api[VALIDATE_API[unref(value)]]
      await getValidateRuleObj(validateApi, customFormSchemaRules(value)).then(async (rules) => {
        rules && (data.value.rules = rules)
        setTimeout(() => {
          form.value?.clearValidate()
        }, 200)
      })
    }
  }
})

defineExpose({
  reset() {
    form.value?.resetFields()
    data.value.form = props.currentData
  },
  submit(callback: any) {
    form.value?.validate(async (valid) => {
      if (valid) {
        if (props.type !== ActionEnum.VIEW) {
          if (props.type === ActionEnum.EDIT) {
            await crud${table.entityName}.update(data.value.form)
          }
          else {
            await crud${table.entityName}.save(data.value.form)
          }
          ElMessage.success({
            message: t(`common.tips.${r'${props.type}'}Success`),
            center: true,
          })
          callback && callback()
        }
        else {
          callback && callback(false)
        }
      }
    })
  },
})
</script>

<template>
  <div v-loading="data.loading">
    <el-form ref="form" :model="data.form" :rules="data.rules" label-suffix="：" label-width="120px">
      <el-row>
        <el-col>
          <el-form-item label="上级节点" prop="parentName">
            <el-input v-model="data.form.parentName" disabled placeholder="请输入" />
          </el-form-item>
        </el-col>
      <#list fields as field>
        <#if field.isEdit && !field.isLogicDeleteField>
        <el-col>
          <el-form-item prop="${field.javaField}" :label="t('${table.plusModuleName}.${table.entityName?uncap_first}.${field.javaField}')">
            <<#if field.component?starts_with("Api")>${field.component}<#elseif field.component?ends_with("TimePicker")>ElTimePicker<#elseif field.component?ends_with("Picker")>ElDatePicker<#elseif field.component=="IconPicker">${field.component}<#elseif field.component=="InputTextArea" || field.component == "InputPassword" >ElInput<#else>El${field.component}</#if> v-model="data.form.${field.javaField}"
              <#if field.component=="InputTextArea">
              type="textarea"
              <#elseif field.component=="InputPassword">
              type="password"
              </#if>
              <#if field.javaType =="LocalDateTime">
              type="datetime" value-format="YYYY-MM-DD HH:mm:ss"
              <#elseif field.javaType =="LocalDate">
              type="date" value-format="YYYY-MM-DD"
              <#elseif field.javaType =="LocalTime">
              format="HH:mm:ss"
              <#elseif field.enumStr?? && field.enumStr?trim != ''>
  <#--                     生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',-->
  <#--                     请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！-->
  <#--                     ...enumDef.componentProps(${table.plusApplicationName?cap_first}EnumEnum.${field.javaType}),-->
              v-bind="enumComponentProps('${field.javaType}')"
              :placeholder="t('common.chooseText')"
              <#elseif field.dictType?? && field.dictType?trim != ''>
                <#if field.dictType?contains('"')>
              v-bind="dictComponentProps('${field.dictType}')"
              :placeholder="t('common.chooseText')"
                <#else>
                  <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
                  <#assign dt=field.dictType?substring(dotIndex?number) />
  <#--                      // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';-->
  <#--                      // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！-->
  <#--                      // ...dictDef.componentProps(${table.plusApplicationName?cap_first}DictEnum.${field.dictType!?replace(".","_")}),-->
              v-bind="dictComponentProps('${dt!?upper_case}')"
              :placeholder="t('common.chooseText')"
                </#if>
              <#else>
              :placeholder="t('common.inputText')"
              </#if>
              :disabled="props.type === 'view'"
            <#if field.javaType == "Boolean" && field.component == 'RadioGroup'>
            >
              <el-radio v-for="(item, index) in yesOrNoEnum.enum()" :key="index" :label="item?.value">
                {{ item?.label }}
              </el-radio>
            </El${field.component}>
            <#else>
            />
          </#if>
          </el-form-item>
        </el-col>
        </#if>
      </#list>
      </el-row>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
// scss
</style>
