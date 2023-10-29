<script lang="ts" setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import { onMounted } from 'vue'
import { useI18n } from 'vue-i18n'
import { eachTree, findNodeByKey } from '@/util/helper/treeHelper'
import crud${table.entityName} from '@/api/modules/${table.plusModuleName}/${table.entityName?uncap_first}'
import BasicTree from '@/components/BasicTree/index.vue'

const emits = defineEmits<{
  select: [any, any]
  add: [any]
  edit: [any, any]
}>()

const { t } = useI18n()

const treeRef = ref<InstanceType<typeof BasicTree>>()
function getTree() {
  const tree = unref(treeRef)
  if (!tree) {
    throw new Error('树结构加载失败,请刷新页面')
  }
  return tree
}

const dataTree = ref({
  search: {
    isChild: false,
  },
  loading: false,
  // 列表数据
  tree: [] as any[],
  current: {} as { [key: string]: any },
  // 批量操作
  batch: {
    enable: true,
    selectionDataList: [] as { [key: string]: any }[],
  },
})
onMounted(() => {
  getTreeList()
})
interface TreeItem {
  [key: string]: any
}
async function getTreeList() {
  dataTree.value.loading = true
  try {
    dataTree.value.tree = await crud${table.entityName}.tree() as TreeItem[]
    eachTree(dataTree.value.tree, (item) => {
      item.key = item.id
      item.title = item.${table.treeName}
      return item
    })
    setTimeout(() => {
      getTree().filterByLevel(2)
    }, 0)
  }
  finally {
    setTimeout(() => {
      dataTree.value.loading = false
    }, 100)
  }
}

function onAdd(row?: any) {
  if (row) {
    emits('add', findNodeByKey(row.id, toValue(dataTree).tree))
  }
  else {
    emits('add', findNodeByKey('0', toValue(dataTree).tree))
  }
}

function onEdit(row: any) {
  if (row) {
    const node = findNodeByKey(row.id, toValue(dataTree).tree)
    const parent = findNodeByKey(node?.parentId, toValue(dataTree).tree)
    emits('edit', parent, node)
  }
}

function onDel(row?: any) {
  let ids: string[]
  if (row) {
    ids = [row.id]
  }
  else {
    ids = getTree().getCheckedKeys() as any
  }

  ElMessageBox.confirm(`确认删除数量「${r"${"}ids.length}」吗？`, '确认信息').then(() => {
    crud${table.entityName}.remove(ids).then(() => {
      getTreeList()
      ElMessage.success({
        message: t('success.delete'),
        center: true,
      })
    })
  }).catch(() => {
  })
}

function handleSelect(keys: any) {
  if (keys[0]) {
    const node = findNodeByKey(keys[0], toValue(dataTree).tree)
    const parent = findNodeByKey(node?.parentId, toValue(dataTree).tree)
    emits('select', parent, node)
  }
}
function onCheckChange(keys: any) {
  dataTree.value.batch.selectionDataList = keys as []
}
defineExpose({
  getDataList: getTreeList,
})
</script>

<template>
  <el-space alignment="center" style="margin-bottom: 8px;" wrap>
    <el-button type="primary" @click="onAdd()">
      {{ t('common.title.addRoot') }}
    </el-button>
    <el-button :disabled="!dataTree.batch.selectionDataList.length" type="primary" @click="onDel()">
      {{ t('common.title.delete') }}
    </el-button>
  </el-space>
  <BasicTree
    ref="treeRef"
    :title="t('system.area.table.title')"
    toolbar
    checkable
    search
    check-strictly
    :loading="dataTree.loading"
    :click-row-to-expand="false"
    :tree-data="dataTree.tree"
    @select="handleSelect"
    @change="onCheckChange"
  >
    <template #action="{ data }">
      <el-button text @click.stop="onAdd(data)">
        {{ t('common.title.add') }}
      </el-button>
      <el-button text @click.stop="onEdit(data)">
        {{ t('common.title.edit') }}
      </el-button>
      <el-button text @click.stop="onDel(data)">
        {{ t('common.title.delete') }}
      </el-button>
    </template>
  </BasicTree>
</template>

<style lang="scss" scoped>
// scss
</style>
