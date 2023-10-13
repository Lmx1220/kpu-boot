<template>
    <PageWrapper contentFullHeight dense>
        <BasicTable @register="registerTable">
            <template #toolbar>
                <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
                    <a-button type="primary" @click="handleImport">{{ t('title.import') }}</a-button>
                    <a-button type="primary" @click="handleExport">{{ t('title.export') }}</a-button>
                </#if>
                <#if table.deleteShow>
                    <a-button
                            <#if table.deleteAuth?? && table.deleteAuth != ''>
                                v-hasAnyPermission="['${table.deleteAuth}']"
                            </#if>
                            color="error"
                            preIcon="ant-design:delete-outlined"
                            type="primary"
                            @click="handleBatchDelete"
                    >{{ t('title.delete') }}
                    </a-button
                    >
                </#if>
                <#if table.addShow>
                    <a-button
                            <#if table.addAuth?? && table.addAuth != '' >
                                v-hasAnyPermission="['${table.addAuth}']"
                            </#if>
                            preIcon="ant-design:plus-outlined"
                            type="primary"
                            @click="handleAdd"
                    >{{ t('common.title.add') }}
                    </a-button
                    >
                </#if>
            </template>
            <template #bodyCell="{ column, record }">
                <template v-if="column.dataIndex === 'action'">
                    <TableAction
                            :actions="[
              <#if table.viewShow>
              {
                tooltip: t('title.view'),
                icon: 'ant-design:search-outlined',
                onClick: handleView.bind(null, record),
                <#if table.viewAuth?? && table.viewAuth != ''>
                auth: '${table.viewAuth}'
                </#if>
              },
              </#if>
              <#if table.deleteShow>
              {
                tooltip: t('title.delete'),
                icon: 'ant-design:delete-outlined',
                color: 'error',
                popConfirm: {
                  title: t('confirm.delete'),
                  confirm: handleDelete.bind(null, record),
                },
                <#if table.deleteAuth?? && table.deleteAuth != ''>
                auth: '${table.deleteAuth}'
                </#if>
              },
              </#if>
              <#if table.editShow>
              {
                tooltip: t('title.edit'),
                icon: 'ant-design:edit-outlined',
                onClick: handleEdit.bind(null, record),
                <#if table.editAuth?? && table.editAuth != ''>
                auth: '${table.editAuth}'
                </#if>
              },
              </#if>
              <#if table.copyShow>
              {
                tooltip: t('title.copy'),
                icon: 'ant-design:copy-outlined',
                onClick: handleCopy.bind(null, record),
                <#if table.copyAuth?? && table.copyAuth != ''>
                auth: '${table.copyAuth}'
                </#if>
              },
              </#if>
            ]"
                            :stopButtonPropagation="true"
                    />
                </template>
            </template>
        </BasicTable>
        <Editor @register="registerEditor" @success="handleSuccess"/>
        <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
            <PreviewExcelModel
                    width="70%"
                    @register="exportRegister"
                    @success="handleExportSuccess"
                    :exportApi="exportFile"
                    :previewApi="exportPreview"
            />
            <ImpExcelModel
                    @register="importRegister"
                    @success="handleImportSuccess"
                    :api="importFile"
                    templateHref=""
            />
        </#if>
    </PageWrapper>
</template>

<script lang="ts">
    import {defineComponent} from 'vue'
    import {BasicTable, TableAction, useTable} from '/@/components/Table'
    import {PageWrapper} from '/@/components/Page'
    import Editor from './Edit.vue'
    import {useI18n} from '/@/hooks/web/useI18n'
    import {useMessage} from '/@/hooks/web/useMessage'
    import {useModal} from '/@/components/Modal'
    import {useDrawer} from '/@/components/Drawer'
    import {useRouter} from 'vue-router'
    import {ImpExcelModel, PreviewExcelModel} from '/@/components/Poi'
    import {exportFile, exportPreview, importFile, page, PermCode, remove} from '/~/'
    import {columns, searchFormSchema} from './schema'

    <#if table.popupType == POPUP_TYPE_MODAL || table.popupType == POPUP_TYPE_DRAWER >

    </#if>

    <#if table.popupType == POPUP_TYPE_MODAL || table.superClass == SUPER_CLASS_SUPER_POI_CLASS>

    </#if>
    <#if table.popupType == POPUP_TYPE_DRAWER>

    <#elseif table.popupType == POPUP_TYPE_JUMP>

    </#if>
    <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>

    </#if>

    <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>

    <#else>

    </#if>

    export default defineComponent({
        // 若需要开启页面缓存，请将此参数跟菜单名保持一致
        name: '${table.menuName}',
        components: {
            BasicTable,
            PageWrapper,
            TableAction,
            <#if table.popupType == POPUP_TYPE_MODAL || table.popupType == POPUP_TYPE_DRAWER >
            Editor,
            </#if>
            <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
            ImpExcelModel,
            PreviewExcelModel,
            </#if>
        },
        setup(_, {emit}) {
            const {t} = useI18n()
            const {createMessage} = useMessage()

            <#if table.popupType == POPUP_TYPE_MODAL>
            const [registerEditor, {openModal: openEditor}] = useModal()
            <#elseif table.popupType == POPUP_TYPE_DRAWER>
            const [registerEditor, {openDrawer: openEditor}] = useDrawer()
            <#elseif table.popupType == POPUP_TYPE_JUMP>
            const {replace} = useRouter()
            const menuName = '编辑${table.menuName}'
            </#if>

            // 表格
            const [registerTable, {
                reload,
                getSelectRowKeys<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>,
                getForm</#if>
            }] = useTable({
                title: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.title.forTable'),
                api: page,
                columns: columns(),
                ...useTableSchema({
                    formConfig: {
                        schemas: searchFormSchema()
                    }
                })
            })

            const componentHandlers = useTableHandlers(emit, openEditor, {
                batchDelete,
                getSelectRowKeys
            })

            async function batchDelete(ids: any[]) {
                await remove(ids)
                createMessage.success(t('success.delete'))
                handleSuccess()
            }

            function handleSuccess() {
                reload();
            }

            // <!--定制功能
            <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
            // 导入弹窗
            const [importRegister, importModal] = useModal();
            // 导出弹窗
            const [exportRegister, exportModel] = useModal();

            // 导入成功
            function handleImportSuccess(_data) {
                reload();
            }

            // 导出成功
            function handleExportSuccess() {
                reload();
            }

            // 点击导出按钮
            function handleExport() {
                const form = getForm();
                let params = {...form.getFieldsValue()};
                params = handleSearchInfoByCreateTime(params);
                params.extra = {
                    ...{
                        fileName: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.table.title'),
                    },
                    ...params?.extra,
                };
                params.size = 20000;

                exportModel.openModal(true, {
                    params,
                });
            }

            // 定制功能-->

            return {
                t,
                registerTable,
                registerEditor,
                ...componentHandlers,
                handleSuccess,
                PermCode,
                importRegister,
                handleImport: importModal.openModal,
                handleImportSuccess,
                importFile,
                exportRegister,
                handleExport,
                handleExportSuccess,
                exportFile,
                exportPreview
            }
            <#else>
            return {
                t,
                registerTable,
                registerEditor,
                ...componentHandlers,
                handleSuccess,
                PermCode
            }
            </#if>
        }
    })
</script>
