<template>
    <div class="bg-white m-4 mr-2 overflow-hidden">
        <div v-if="query" class="m-4">
            <a-button class="mr-2" type="primary" @click="handleReset">{{ t('title.reset') }}</a-button>
            <Checkbox v-model:checked="recursion" @change="handleQuery">{{ t('title.tree_.includeSubNodes') }}
            </Checkbox>
        </div>
        <div v-else class="m-4">
            <#if table.addShow>
                <a-button
                        <#if table.addAuth?? && table.addAuth != ''>
                            v-hasAnyPermission="['${table.addAuth}']"
                        </#if>
                        class="mr-2"
                        preIcon="ant-design:plus-outlined"
                        @click="handleAddRoot"
                >{{ t('title.addRoot') }}
                </a-button
                >
            </#if>
            <#if table.deleteShow>
                <a-button
                        <#if table.deleteAuth?? && table.deleteAuth != ''>
                            v-hasAnyPermission="['${table.deleteAuth}']"
                        </#if>
                        class="mr-2"
                        preIcon="ant-design:delete-outlined"
                        @click="handleBatchDelete"
                >{{ t('title.delete') }}
                </a-button
                >
            </#if>
        </div>
        <BasicTree
                :actionList="actionList"
                :beforeRightClick="getRightMenuList"
                :checkable="!query"
                :clickRowToExpand="false"
                :title="t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.title.forTable')"
                :treeData="treeData"
                checkStrictly
                search
                toolbar
                @select="handleSelect"
                ref="treeRef"
        />
    </div>
</template>

<script lang="ts">
    import {computed, defineComponent, onMounted} from 'vue'
    import {Checkbox} from 'ant-design-vue'
    import {BasicTree, TreeItem} from '/@/components/Tree'

    import {useI18n} from '/@/hooks/web/useI18n'
    import {useMessage} from '/@/hooks/web/useMessage'
    import {useTreeHandlers} from '/@/cuedes/hooks'

    import {PermCode, remove, tree} from '/~/'

    export default defineComponent({
        name: '${table.entityName}Tree',
        components: {BasicTree, Checkbox},
        props: {
            enableAction: {type: Boolean, default: true},
            query: {type: Boolean, default: false}
        },
        emits: ['select', 'edit', 'add'],
        setup(_, {emit}) {
            const {t} = useI18n()
            const {createMessage} = useMessage()

            const {treeData, getTreeRef, fixDataProps, ...componentHandlers} = useTreeHandlers(emit, {
                enableAction: computed(() => props.enableAction),
                enableRecursion: computed(() => props.query),
                batchDelete,
                // 悬停图标权限
                addAction: () => ({
                    auth: ['${table.addAuth}']
                }),
                editAction: () => ({
                    auth: ['${table.editAuth}']
                }),
                deleteAction: () => ({
                    auth: ['${table.deleteAuth}']
                })
            })

            // 加载数据
            async function fetch() {
                treeData.value = ((await tree())
                as
                unknown
            )
                as
                TreeItem[]

                // <!--数据改性
                fixDataProps < TreeItem > ((item, _) => {
                    item.key = item.id
                    item.title = item.name
                    return item
                })
                // 数据改性-->

                setTimeout(() => {
                    getTreeRef().filterByLevel(2)
                }, 0)
            }

            async function batchDelete(ids: string[]) {
                try {
                    await remove(ids)
                    createMessage.success(t('success.delete'))
                    fetch()
                } catch (e) {
                }
            }

            onMounted(() => {
                fetch()
            })

            return {
                t,
                treeData,
                fetch,
                ...componentHandlers,
                PermCode
            }
        }
    })
</script>
