<script lang="ts" setup>
import { useI18n } from 'vue-i18n'
import type { Props as DetailFormProps } from '../DetailForm/index.vue'
import DetailForm from '../DetailForm/index.vue'

export interface Props extends DetailFormProps {
  modelValue?: boolean
  mode?: 'dialog' | 'drawer' | string
}

const props = withDefaults(defineProps<Props>(),
  {
    id: '',
    modelValue: false,
    mode: 'dialog',
  })

const emits = defineEmits<{
  'update:modelValue': [
    value: boolean,
  ]
  success: []
}>()

defineOptions({
  name: 'FormMode',
})

const myVisible = computed({
  get() {
    return props.modelValue
  },
  set(val) {
    emits('update:modelValue', val)
  },
})

const form = ref<InstanceType<typeof DetailForm>>()
const { t } = useI18n()
const title = computed(() => {
  return `${r'${'}t(`common.title.${r'${props.type}'}`)}${table.comment}`
})

function onSubmit() {
  // submit() 为组件内部方法
  form.value?.submit((success = true) => {
    if (success) {
      emits('success')
    }
    onCancel()
  })
}

function onCancel() {
  myVisible.value = false
}
</script>

<template>
  <div>
    <el-dialog
      v-if="props.mode === 'dialog'" v-model="myVisible" :close-on-click-modal="false" :title="title"
      append-to-body destroy-on-close
    <#if table.tplType == TPL_MAIN_SUB>
      width="70%"
      <#else>
      width="50%"
    </#if>
    >
      <DetailForm ref="form" v-bind="$props" />
      <template #footer>
        <el-button size="large" @click="onCancel">
          {{ t('common.cancelText') }}
        </el-button>
        <el-button size="large" type="primary" @click="onSubmit">
          {{ t('common.okText') }}
        </el-button>
      </template>
    </el-dialog>
    <el-drawer
      v-else-if="props.mode === 'drawer'" v-model="myVisible" :close-on-click-modal="false" :title="title"
      destroy-on-close size="600px"
    >
      <DetailForm ref="form" v-bind="$props" />
      <template #footer>
        <el-button size="large" @click="onCancel">
          {{ t('common.cancelText') }}
        </el-button>
        <el-button size="large" type="primary" @click="onSubmit">
          {{ t('common.okText') }}
        </el-button>
      </template>
    </el-drawer>
  </div>
</template>
