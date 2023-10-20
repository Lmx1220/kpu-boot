<template>
  <PageWrapper contentClass="flex" dense fixedHeight>
    <Tree class="md:w-1/2" @select="handleTreeSelect" @add="handleTreeAdd" @edit="handleTreeEdit" ref="treeRef" />
    <Edit class="md:w-1/2" @success="handleEditSuccess" ref="editRef" />
  </PageWrapper>
</template>

<script lang="ts">
  import { defineComponent } from 'vue'
  import { PageWrapper } from '/@/components/Page'
  import Tree from './tree.vue.ftl'
  import Edit from './edit.vue.ftl'

  import { useTreeEditHandlers } from '/@/cuedes/hooks'

  export default defineComponent({
    // 若需要开启页面缓存，请将此参数跟菜单名保持一致
    name: '${table.menuName}',
    components: { Edit, Tree, PageWrapper },
    setup() {
      const { editRef, treeRef, ...componentHandlers } = useTreeEditHandlers()

      return {
        editRef,
        treeRef,
        ...componentHandlers
      }
    }
  })
</script>
