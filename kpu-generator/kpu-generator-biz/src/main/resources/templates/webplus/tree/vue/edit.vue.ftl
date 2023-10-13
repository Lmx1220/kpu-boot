<template>
    <div class="bg-white m-4 ml-2 overflow-hidden">
        <Card :title="t(`title.${r'${'}type}`)" :bordered="false">
            <BasicForm @register="registerForm"/>
            <div v-if="type !== ActionEnum.VIEW" class="flex justify-center">
                <a-button @click="resetFields">{{ t('title.reset') }}</a-button>
                <a-button :loading="confirmLoading" class="!ml-4" type="primary" @click="handleSubmit">
                    {{ t('title.save') }}
                </a-button>
            </div>
        </Card>
    </div>
</template>

<script lang="ts">
    import {defineComponent, ref, unref} from 'vue'
    import {Card} from 'ant-design-vue'
    import {BasicForm, useForm} from '/@/components/Form'

    import {useI18n} from '/@/hooks/web/useI18n'
    import {useMessage} from '/@/hooks/web/useMessage'

    import {ActionEnum, VALIDATE_API} from '/@/enums/commonEnum'
    import {customFormSchemaRules, editFormSchema} from './schema.data'
    import {getValidateRules} from '/@/api/common/formValidateService'
    import {Api, save, update} from '/~/'

    export default defineComponent({
        name: '${table.entityName}Edit',
        components: {BasicForm, Card},
        emits: ['success'],
        setup(_, {emit}) {
            const {t} = useI18n()
            const {createMessage} = useMessage()
            const type = ref(ActionEnum.VIEW)
            const confirmLoading = ref(false)

            const [registerForm, {setFieldsValue, resetFields, updateSchema, validate}] = useForm({
                labelWidth: 100,
                schemas: editFormSchema(type),
                showActionButtonGroup: false,
                actionColOptions: {span: 23},
                baseColProps: {span: 24}
            })

            async function handleSubmit() {
                try {
                    confirmLoading.value = true

                    const doing = unref(type)
                    if (doing !== ActionEnum.VIEW) {
                        const params = await validate()
                        if (doing === ActionEnum.Edit) {
                            await update(params)
                        } else {
                            params.id = undefined
                            await save(params)
                        }
                        createMessage.success(t(`success.${r'${'}doing${r'}'}`))
                    }

                    type.value = ActionEnum.ADD
                    await resetFields()
                    emit('success')
                } finally {
                    confirmLoading.value = false
                }
            }

            // 设置回显数据
            async function setData(data: Recordable) {
                type.value = data?.type
                await resetFields()
                await resetSchema(editFormSchema(type))
                const doing = unref(type)

                // <--赋值
                const {record = {}, parent = {}} = data
                record['parentName'] = parent?.${table.treeName}
                record['parentId'] = parent?.id

                await setFieldsValue(record)
                // 赋值-->

                if (doing !== ActionEnum.VIEW) {
                    const validateApi = Api[VALIDATE_API[doing]]
                    await getValidateRules(validateApi, customFormSchemaRules(type)).then(async (rules) => {
                        rules && rules.length > 0 && (await updateSchema(rules))
                    })
                }
            }

            return {
                t,
                registerForm,
                resetFields,
                handleSubmit,
                setData,
                confirmLoading,
                ActionEnum
            }
        }
    })
</script>
