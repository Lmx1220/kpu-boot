<#assign i18n = "${table.plusApplicationName}.${table.plusModuleName?replace('/', '.')}.${table.entityName?uncap_first}">
<script lang="ts" setup>
import { omit } from 'lodash-es'
import { useI18n } from 'vue-i18n'
import DetailForm from './components/DetailForm/index.vue'
import ${table.entityName}Tree from './components/AreaTree/index.vue'
import { ActionEnum } from '@/enums/commonEnum'
import type { TreeConfig } from '#/global'

defineOptions({
  name: '${plusName}${table.entityName}',
})

const { t } = useI18n()
const data = ref<TreeConfig>({
  tableAutoHeight: true,
  // 详情
  formModeProps: {
    id: '',
    parent: {},
    data: {},
  },
  batch: {
    selectionDataList: [],
  },
})

const form = ref<InstanceType<typeof DetailForm>>()
const treeRef = ref <InstanceType<typeof AreaTree>>()

onMounted(() => {
})

onBeforeUnmount(() => {
})

async function getDataList() {
  treeRef.value?.getDataList()
}

function onAdd(parent: any) {
  const rowParent = toRaw(parent)
  omit(rowParent, 'children')
  data.value.formModeProps.id = ''
  data.value.formModeProps.type = ActionEnum.ADD
  data.value.formModeProps.parent = rowParent
  if (parent.id) {
    data.value.formModeProps.data = {
      parentId: rowParent.id,
      parentName: rowParent.${table.treeName},
      id: '',
      name: '',
    }
  } else {
    data.value.formModeProps.data = {
      id: '',
      name: '',
      parentId: '',
      parentName: '根节点',
    }
  }
}

function onEdit(parent: any, node: any) {
  const row = toRaw(node)
  const rowParent = toRaw(parent)
  omit(row, 'children')
  omit(rowParent, 'children')
  row.parentId = parent.id
  row.parentName = parent.${table.treeName}

  data.value.formModeProps.id = node.id
  data.value.formModeProps.type = ActionEnum.EDIT
  data.value.formModeProps.data = row
  data.value.formModeProps.parent = rowParent
}

function onView(parent: any, node: any) {
  const row = toRaw(node)
  const rowParent = toRaw(node)
  omit(row, 'children')
  omit(rowParent, 'children')
  row.parentId = parent.id
  row.parentName = parent.${table.treeName}

  data.value.formModeProps.id = node.id
  data.value.formModeProps.type = ActionEnum.VIEW
  data.value.formModeProps.data = row
  data.value.formModeProps.parent = rowParent
}

const title = computed(() => {
  return `${r"${t(`common.title.${data.value.formModeProps.type ?? ActionEnum.ADD}`)}"}${table.comment}`
})
</script>

<template>
  <div :class="{ 'absolute-container': data.tableAutoHeight }">
    <page-header :title="t('${i18n}.table.title')" />
    <div class="page-main">
      <LayoutContainer hide-left-side-toggle left-side-width="50%">
        <template #leftSide>
          <AreaTree ref="treeRef" @add="onAdd" @edit="onEdit" @select="onView" />
        </template>
        <template #default>
          <div v-show="data.formModeProps.type">
            <page-header :title="title" />
            <DetailForm
              :id="data.formModeProps.data?.id " ref="form"
              :current-data="data.formModeProps.data"
              :parent-data="data.formModeProps.parent"
            />
            <div v-if="data.formModeProps.type !== 'view'" class="flex justify-center">
              <el-button type="primary" @click="form?.reset()">
                {{ t('common.resetText') }}
              </el-button>
              <el-button type="primary" @click="form?.submit(() => { getDataList() })">
                {{ t('common.saveText') }}
              </el-button>
            </div>
          </div>
          <div v-show="!data.formModeProps.type" class="container">
            <div class="empty">
              请在左侧点击选择{{ t('${i18n}.table.title') }}
            </div>
          </div>
        </template>
      </LayoutContainer>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.absolute-container {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .page-header {
    margin-bottom: 0;
  }

  .page-main {
    flex: 1;
    overflow: auto;
    display: flex;
    flex-direction: column;

    .flex-container {
      position: static;
    }
  }
}

.flex-container {
  :deep(.left-side) {
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  :deep(.main) {
    display: flex;
    justify-content: center;
  }

  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    height: 100%;

    .empty {
      text-align: center;
      font-size: 32px;
      color: var(--el-text-color-placeholder);
    }
  }
}
</style>
