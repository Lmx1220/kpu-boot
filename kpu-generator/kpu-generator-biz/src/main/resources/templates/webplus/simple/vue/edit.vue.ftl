<template>
<#if table.popupType == POPUP_TYPE_MODAL>
  <BasicModal
    v-bind="$attrs"
    :keyboard="true"
    :title="t(`title.${r'${type}'}`)"
    @ok="handleSubmit"
    @register="registerEditor"
  >
  <#elseif table.popupType == POPUP_TYPE_DRAWER>
  <BasicDrawer
    v-bind="$attrs"
    :title="t(`title.${r'${type}'}`)"
    showFooter
    width="50%"
    @ok="handleSubmit"
    @register="registerEditor"
  >
</#if>
    <BasicForm @register="registerForm" />
<#if table.popupType == POPUP_TYPE_MODAL>
  </BasicModal>
<#elseif table.popupType == POPUP_TYPE_DRAWER>
  </BasicDrawer>
</#if>
</template>

<script lang="ts">
  import { defineComponent, ref, unref } from 'vue'
  <#if table.popupType == POPUP_TYPE_MODAL>
  import { BasicModal, useModalInner } from '/@/components/Modal'
  <#elseif table.popupType == POPUP_TYPE_DRAWER>
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer'
  </#if>
  import { BasicForm, useForm } from '/@/components/Form/index'

  import { useI18n } from '/@/hooks/web/useI18n'
  import { useMessage } from '/@/hooks/web/useMessage'
  import { useHabitSetting } from '/@/hooks/setting/useHabitSetting'

  import { ActionEnum, VALIDATE_API } from '/@/enums/biz'
  import { editFormSchema, customFormSchemaRules } from './schema'
  import { getValidateRules } from '/@/api/common/formValidateService'
  import { Api, save, update } from '/~/${table.plusApplicationName}/api/${table.plusModuleName}/${table.entityName?uncap_first}'

  export default defineComponent({
    name: '编辑${table.menuName}',
    components: { <#if table.popupType == POPUP_TYPE_MODAL>BasicModal<#elseif table.popupType == POPUP_TYPE_DRAWER>BasicDrawer<#else></#if>, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const { t } = useI18n()
      const { createMessage } = useMessage()
      const { getMaskClosable } = useHabitSetting()
      const type = ref<ActionEnum>(ActionEnum.ADD)

      const [registerForm, { setFieldsValue, resetFields, updateSchema, validate, resetSchema }] = useForm({
        name: '${table.entityName}Edit',
        labelWidth: 100,
        schemas: editFormSchema(type),
        showActionButtonGroup: false,
        actionColOptions: { span: 23 },
        baseColProps: { span: 24 },
        disabled: _ => unref(type) === ActionEnum.VIEW
      })

      <#if table.popupType == POPUP_TYPE_MODAL>
      const [registerEditor, { setModalProps: setEditorProps, closeModal: closeEditor }] = useModalInner(
        async (data) => {
      <#elseif table.popupType == POPUP_TYPE_DRAWER>
      const [registerEditor, { setDrawerProps: setEditorProps, closeDrawer: closeEditor }] = useDrawerInner(
        async (data) => {
      <#else></#if>
          setEditorProps({ confirmLoading: false, maskClosable: unref(getMaskClosable) })
          type.value = data?.type || ActionEnum.ADD
          await resetFields()
          await resetSchema(editFormSchema(type))
          const doing = unref(type)

          // <--赋值
          if (doing !== ActionEnum.ADD) {
            const record = { record = {} } = data
            await setFieldsValue(record)
          }
          // 赋值-->

          if (doing !== ActionEnum.VIEW) {
            const validateApi = Api[VALIDATE_API[doing]]
            await getValidateRules(validateApi, customFormSchemaRules(type)).then(async (rules) => {
              rules && rules.length > 0 && (await updateSchema(rules))
            })
          }
        }
      )

      async function handleSubmit() {
        try {
          setEditorProps({ confirmLoading: true })

          const doing = unref(type)
          if (doing !== ActionEnum.VIEW) {
            const params = await validate()
            if (doing === ActionEnum.EDIT) {
              await update(params)
            } else {
              params.id = null
              await save(params)
            }
            createMessage.success(t(`success.${r'${'}doing${r'}'}`))
          }

          closeEditor()
          emit('success')
        } finally {
          setEditorProps({ confirmLoading: false });
        }
      }

      return {
        t,
        registerEditor,
        registerForm,
        type,
        handleSubmit
      }
    }
  })
</script>
