<script lang="ts" setup>
import type { Ref } from 'vue'
import { ElMessage, ElMessageBox, ElTable } from 'element-plus'
import { get } from 'lodash-es'
import { useI18n } from 'vue-i18n'
import FormMode from './components/FormMode/index.vue'
import type { ${table.entityName}PageQuery, ${table.entityName}ResultVO } from '@/api/modules/${table.plusModuleName}/model/${table.entityName?uncap_first}Model'
import crud${table.entityName} from '@/api/modules/${table.plusModuleName}/${table.entityName?uncap_first}'
import eventBus from '@/util/eventBus'
import usePagination from '@/util/usePagination.js'
import type { DataConfig } from '@/types/global'

defineOptions({
  name: '${plusName}${table.entityName}',
})
const {
  pagination,
  search,
  getParams,
  onSizeChange,
  onCurrentChange,
  onSortChange,
  resetQuery,
} = usePagination<${table.entityName}PageQuery>()
const router = useRouter()
const { t } = useI18n()
// const route = useRoute()
const defaultQuery = {
  daterange: [],
}
const data: Ref<DataConfig<${table.entityName}PageQuery, ${table.entityName}ResultVO>> = ref({
  loading: false,
  tableAutoHeight: true,
  /**
   * 详情展示模式
   * router 路由跳转
   * dialog 对话框
   * drawer 抽屉
   */
  formMode: '<#if table.popupType == POPUP_TYPE_MODAL>dialog<#elseif table.popupType == POPUP_TYPE_DRAWER>drawer<#else>router</#if>',
  // 详情
  formModeProps: {
    visible: false,
    id: '',
  },
  // 搜索
  search,
  searchFold: false,
  // 批量操作
  batch: {
    enable: false,
    selectionDataList: [],
  },
  // 列表数据
  dataList: [],
  dicts: new Map(),
})

const table = ref<InstanceType<typeof ElTable>>()

onMounted(() => {
  getDataList()
  if (data.value.formMode === 'router') {
    eventBus.on('get-data-list', () => {
      getDataList()
    })
  }
})

onBeforeUnmount(() => {
  if (data.value.formMode === 'router') {
    eventBus.off('get-data-list')
  }
})

async function getDataList(current?: number) {
  if (current && current === 1) {
    pagination.value.page = current
  }
  data.value.loading = true
  const params = getParams<${table.entityName}PageQuery>({
    ...data.value.search,
  },
  {
    type: 'daterange',
    name: 'daterange',
    prop: 'createdTime',
  },
  )
  const res = await crud${table.entityName}.page(params)
  data.value.dataList = get(res, 'records', [])
  pagination.value.total = Number(res.total)
  pagination.value.page = Number(get(res, 'current', 1))
  // pagination.value.size = res.size
  setTimeout(() => {
    data.value.loading = false
  }, 100)
}

// 每页数量切换
function sizeChange(size: number) {
  onSizeChange(size).then(() => getDataList())
}

// 当前页码切换（翻页）
function currentChange(page = 1) {
  onCurrentChange(page).then(() => getDataList())
}

// 字段排序
function sortChange({ prop, order }: any) {
  onSortChange(prop, order).then(() => getDataList())
}

function onCreate() {
  if (data.value.formMode === 'router') {
    router.push({
      name: '${plusName}${table.entityName}Create',
      params: {
        type: 'add',
      },
    })
  }
  else {
    data.value.formModeProps.id = ''
    data.value.formModeProps.visible = true
    data.value.formModeProps.type = 'add'
  }
}

function onEdit(row: any) {
  if (data.value.formMode === 'router') {
    router.push({
      name: '${plusName}${table.entityName}Edit',
      params: {
        id: row.id,
        type: 'edit',
      },
    })
  }
  else {
    data.value.formModeProps.id = row.id
    data.value.formModeProps.visible = true
    data.value.formModeProps.type = 'edit'
  }
}

function onView(row: any) {
  if (data.value.formMode === 'router') {
    router.push({
      name: '${plusName}${table.entityName}Detail',
      params: {
        id: row.id,
        type: 'view',
      },
    })
  }
  else {
    data.value.formModeProps.id = row.id
    data.value.formModeProps.visible = true
    data.value.formModeProps.type = 'view'
  }
}

function onDel(row?: any) {
  let ids: string[] = []
  if (row) {
    ids.push(row.id)
  }
  else {
    ids = data.value.batch.selectionDataList.map(item => item.id ?? '')
  }
  ElMessageBox.confirm(`确认删除数量「${r"${"}ids.length}」吗？`, '确认信息').then(() => {
    crud${table.entityName}.delete(ids).then(() => {
      getDataList()
      ElMessage.success({
        message: '删除成功',
        center: true,
      })
    })
  }).catch(() => {
  })
}
</script>

<template>
  <div :class="{ 'absolute-container': data.tableAutoHeight }">
    <page-header title="${table.swaggerComment}" />
    <page-main>
      <search-bar
        :fold="data.searchFold"
        :show-toggle="false"
      >
        <template #default="{ fold }">
          <el-form class="search-form" :model="data.search" size="default" inline-message label-width="100px" label-suffix="：">
            <el-row>
            <#list fields as field>
              <#if field.isQuery && !field.isLogicDeleteField>
              <el-col :span="6">
                <el-form-item :label="t('${table.plusModuleName}.${table.entityName?uncap_first}.meta.${field.javaField}')">
                  <#if field.javaType =="LocalDateTime">
                  <el-date-picker v-model="data.search.${field.javaField}" type="datetime" value-format="YYYY-MM-DD HH:mm:ss" />
                  <#elseif field.javaType =="LocalDate">
                  <el-date-picker v-model="data.search.${field.javaField}" type="date" value-format="YYYY-MM-DD" />
                  <#elseif field.javaType =="LocalTime">
                  <el-time-picker v-model="data.search.${field.javaField}" format="HH:mm:ss" />
                  <#elseif field.javaType =="Boolean">
                  <el-radio-group v-model="data.search.${field.javaField}">
                    <el-radio v-for="(item, index) in data.dicts?.get('YesOrNo')" :key="index" :label="item?.value">
                      {{ item?.label }}
                    </el-radio>
                  </el-radio-group>
                  <#elseif field.enumStr?? && field.enumStr?trim != ''>
<#--                     生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',-->
<#--                     请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！-->
<#--                     ...enumDef.componentProps(${table.plusApplicationName?cap_first}EnumEnum.${field.javaType}),-->
                  <el-select v-model="data.search.${field.javaField}" clearable placeholder="请选择" size="default" @change="currentChange()" >
                    <el-option v-for="item in data.dicts.get('${field.javaType}') || []" :key="item.value" :label="item.label" :value="item.value" />
                  </el-select>

                  <#elseif field.dictType?? && field.dictType?trim != ''>
                    <#if field.dictType?contains('"')>
                  <el-select v-model="data.search.${field.javaField}" clearable placeholder="请选择" size="default"  @change="currentChange()">
                    <el-option v-for="item in data.dicts.get('${field.dictType}') || []" :key="item.value" :label="item.label" :value="item.value" />
                  </el-select>
                    <#else>
                      <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
                      <#assign dt=field.dictType?substring(dotIndex?number) />
<#--                      // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';-->
<#--                      // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！-->
<#--                      // ...dictDef.componentProps(${table.plusApplicationName?cap_first}DictEnum.${field.dictType!?replace(".","_")}),-->
                  <el-select v-model="data.search.${field.javaField}" clearable placeholder="请选择" size="default" @change="currentChange()">
                    <el-option v-for="item in data.dicts.get('${dt!?upper_case}') || []" :key="item.value" :label="item.label" :value="item.value" />
                  </el-select>
                    </#if>
                  <#else>
                  <el-input
                    v-model="data.search.${field.javaField}" placeholder="请输入，支持模糊查询" clearable size="default"
                    @keydown.enter="currentChange()" @clear="currentChange()"
                  />
                  </#if>
                </el-form-item>
              </el-col>
              </#if>
            </#list>
              <el-col :span="6">
                <el-form-item v-show="!fold" label="创建时间">
                  <el-date-picker
                    v-model="data.search.daterange"
                    :default-time="[
                      new Date(2000, 1, 1, 0, 0, 0),
                      new Date(2000, 2, 1, 23, 59, 59),
                    ]"
                    size="default"
                    end-placeholder="结束时间"
                    range-separator=":"
                    start-placeholder="开始时间"
                    style="width: 250px;"
                    type="daterange"
                    value-format="YYYY-MM-DD HH:mm:ss"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item>
                  <el-button type="primary" @click="currentChange()">
                    <template #icon>
                      <svg-icon name="ep:search" />
                    </template>
                    筛选
                  </el-button>
                  <el-button type="primary" @click="resetQuery()">
                    重置
                  </el-button>
                  <el-button type="primary" link @click="data.searchFold = !fold">
                    <template #icon>
                      <svg-icon :name="fold ? 'i-ep:caret-bottom' : 'i-ep:caret-top'" />
                    </template>
                    {{ fold ? '展开' : '收起' }}
                  </el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
      </search-bar>
      <el-divider border-style="dashed" />
      <el-space wrap>
        <el-button type="primary" size="default" @click="onCreate">
          <template #icon>
            <svg-icon name="ep:plus" />
          </template>
          新增
        </el-button>
        <el-button size="default" :disabled="!data.batch.selectionDataList.length" type="danger" @click="onDel()">
          <template #icon>
            <svg-icon name="ep:delete" />
          </template>
          删除
        </el-button>
      </el-space>
      <ElTable
        ref="table" v-loading="data.loading" class="list-table" height="100%" :data="data.dataList" border stripe
        highlight-current-row @sort-change="sortChange" @selection-change="data.batch.selectionDataList = $event"
      >
        <el-table-column v-if="data.batch.enable" type="selection" align="center" fixed />
        <el-table-column align="center" label="序号" width="100">
          <template #default="{ $index }">
            {{ (pagination.size * (pagination.page - 1)) + $index + 1 }}
          </template>
        </el-table-column>
      <#list fields as field>
        <#if field.isList && !field.isLogicDeleteField>
        <el-table-column key="${field.javaField}" :label="t('${table.plusModuleName}.${table.entityName?uncap_first}.meta.${field.javaField}')"<#if field.echoStr?? && field.echoStr?trim != ''> prop="echoMap.${field.javaField}"<#else> prop="${field.javaField}"</#if><#if field.width?? && field.width?trim != ''> width="${field.width}"</#if><#if field.indexHelpMessage?? && field.indexHelpMessage?trim != ''> helpMessage="${field.indexHelpMessage}"</#if> align="center" />
        </#if>
      </#list>
        <el-table-column label="创建时间" prop="createdTime" width="180" sortable="custom" align="center" />
        <el-table-column label="操作" width="250" align="center" fixed="right">
          <template #default="scope">
            <el-button type="primary" size="small" plain @click="onView(scope.row)">
              查 看
            </el-button>
            <el-button type="primary" size="small" plain @click="onEdit(scope.row)">
              编 辑
            </el-button>
            <el-button type="danger" size="small" plain @click="onDel(scope.row)">
              删 除
            </el-button>
          </template>
        </el-table-column>
      </ElTable>
      <el-pagination :current-page="pagination.page" :total="pagination.total" :page-size="pagination.size" :page-sizes="pagination.sizes" :layout="pagination.layout" :hide-on-single-page="false" class="pagination" background @size-change="sizeChange" @current-change="currentChange" />
    </page-main>
    <FormMode v-if="['dialog', 'drawer'].includes(data.formMode)" :id="data.formModeProps.id" v-model="data.formModeProps.visible" :mode="data.formMode" @success="getDataList" />
  </div>
</template>

<style lang="scss" scoped>
  .el-pagination {
    margin-top: 20px;
  }
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
      .search-container {
        margin-bottom: 0;
      }
    }
  }

  .page-main {
    .search-form {
      display: flex;
      flex-wrap: wrap;
      margin-bottom: -18px;
      :deep(.el-form-item) {
        flex: 1 1 300px;
        &:last-child {
          margin-left: auto;
          .el-form-item__content {
            justify-content: flex-end;
          }
        }
      }

    }
    .el-divider {
      margin-inline:-20px;width: calc(100% + 40px);
    }
  }
</style>
