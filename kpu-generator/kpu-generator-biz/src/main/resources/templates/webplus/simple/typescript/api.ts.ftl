import type { AxiosRequestConfig } from 'axios'
import type { ${pageQueryName}, ${resultVoName}, ${saveVoName}, ${updateVoName} } from './model/${table.entityName?uncap_first}Model'
import defHttp from '@/api'
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  import type { UploadFileParams } from '#/axios'
</#if>
import { RequestEnum } from '@/enums/httpEnum'
import type { PageParams, PageResult } from '@/api/model/baseModel'

const MODULAR = '${table.entityName?uncap_first}'
const ServicePrefix = ''

export const Api: Record<string, any> = {
  Page: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/page`, method: RequestEnum.POST } as AxiosRequestConfig,
<#if table.addShow || table.copyShow>
  Save: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
<#if table.editShow>
  Update: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`, method: RequestEnum.PUT },
</#if>
<#if table.deleteShow>
  Delete: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`, method: RequestEnum.DELETE } as AxiosRequestConfig,
</#if>
  Query: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/query`, method: RequestEnum.POST } as AxiosRequestConfig,
  Detail: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/detail`, method: RequestEnum.GET } as AxiosRequestConfig,
  Copy: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/copy`, method: RequestEnum.POST } as AxiosRequestConfig,
<#if table.tplType == TPL_TREE>
  Tree: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/tree`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  Preview: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/preview`, method: RequestEnum.POST } as AxiosRequestConfig,
  Export: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/export`, method: RequestEnum.POST, responseType: 'blob' } as AxiosRequestConfig,
  Import: { url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/import`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
}

export function page(params: PageParams<${pageQueryName}>) {
  return defHttp.request<PageResult<${resultVoName}>>({ ...Api.Page, params })
}
<#if table.addShow || table.copyShow>
export function save(params: ${saveVoName}) {
  return defHttp.request<${resultVoName}>({ ...Api.Save, params })
}
</#if>
<#if table.editShow>
export function update(params: ${updateVoName}) {
  return defHttp.request<${resultVoName}>({ ...Api.Update, params })
}
</#if>
<#if table.deleteShow>
export function remove(params: string[]) {
  return defHttp.request<boolean>({ ...Api.Delete, params })
}
</#if>
export function query(params: ${pageQueryName}) {
  return defHttp.request<${resultVoName}[]>({ ...Api.Query, params })
}
export function detail(id: string) {
  return defHttp.request<${resultVoName}>({ ...Api.Detail, params: { id } })
}
export function copy(id: string) {
  return defHttp.request<${resultVoName}>({ ...Api.Copy, params: { id } })
}
<#if table.tplType == TPL_TREE>
export function tree(params?: ${pageQueryName}) {
  return defHttp.request<${resultVoName}>({ ...Api.Tree, params })
}
</#if>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
export function exportPreview(params: PageParams<${pageQueryName}>) {
  return defHttp.request<string>({ ...Api.Preview, params })
}
export function exportFile(params: PageParams<${pageQueryName}>) {
  return defHttp.request<any>({ ...Api.Export, params }, { isReturnNativeResponse: true })
}
export function importFile(params: UploadFileParams) {
  return defHttp.uploadFile<boolean>({ ...Api.Import }, params)
}
</#if>
export default {
  page,
<#if table.addShow || table.copyShow>
  save,
</#if>
<#if table.editShow>
  update,
</#if>
<#if table.deleteShow>
  remove,
</#if>
<#if table.tplType == TPL_TREE>
  tree,
</#if>
  query,
  detail,
  copy,
}