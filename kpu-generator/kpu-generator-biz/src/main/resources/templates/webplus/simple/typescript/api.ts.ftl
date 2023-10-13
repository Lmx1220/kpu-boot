import { defHttp } from '/@/utils/http/axios'
import { RequestEnum } from '/@/enums/httpEnum'
import { ServicePrefixEnum } from '/@/enums/biz'
import type { PageParams, PageResult } from '/@/api/model/baseModel'
import type {
${resultVoName} as ResultVO,
${saveVoName} as SaveVO,
${updateVoName} as UpdateVO,
${pageQueryName} as PageQuery
} from './model/${table.entityName?uncap_first}Model'
import type { AxiosRequestConfig } from 'axios'
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
    import type { UploadFileParams } from '/#/axios'
</#if>

// tips: 建议在ServicePrefixEnum中新增：${table.serviceName?upper_case} = '/${table.serviceName}'，并将下方代码改为： const PRE = ServicePrefixEnum.${table.serviceName?upper_case}
// tips: /${table.serviceName} 需要与 ${projectPrefix}-gateway-server.yml中配置的Path一致，否则无法正常调用接口！！！
// const PRE = ServicePrefixEnum.${table.serviceName?upper_case};
const PRE = '/${table.serviceName}'
const MODULAR = '${table.entityName?uncap_first}'

export const Api = {
Page: { url: `${r"${"}PRE}/${r"${MODULAR}"}/page`, method: RequestEnum.POST } as AxiosRequestConfig,
<#if table.addShow || table.copyShow>
    Save: { url: `${r"${"}PRE}/${r"${MODULAR}"}`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
<#if table.editShow>
    Update: { url: `${r"${"}PRE}/${r"${MODULAR}"}`, method: RequestEnum.PUT },
</#if>
<#if table.deleteShow>
    Delete: { url: `${r"${"}PRE}/${r"${MODULAR}"}`, method: RequestEnum.DELETE } as AxiosRequestConfig,
</#if>
Query: { url: `${r"${"}PRE}/${r"${MODULAR}"}/query`, method: RequestEnum.POST } as AxiosRequestConfig,
Detail: { url: `${r"${"}PRE}/${r"${MODULAR}"}/detail`, method: RequestEnum.GET } as AxiosRequestConfig,
Copy: { url: `${r"${"}PRE}/${r"${MODULAR}"}/copy`, method: RequestEnum.POST } as AxiosRequestConfig,
<#if table.tplType == TPL_TREE>
    Tree: { url: `${r"${"}PRE}/${r"${MODULAR}"}/tree`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
    Preview: { url: `${r"${"}PRE}/${r"${MODULAR}"}/preview`, method: RequestEnum.POST } as AxiosRequestConfig,
    Export: { url: `${r"${"}PRE}/${r"${MODULAR}"}/export`, method: RequestEnum.POST, responseType: 'blob' } as AxiosRequestConfig,
    Import: { url: `${r"${"}PRE}/${r"${MODULAR}"}/import`, method: RequestEnum.POST } as AxiosRequestConfig,
</#if>
}

export const page = (params: PageParams
<PageQuery>) => {
    return defHttp.request
    <PageResult
    <ResultVO>>({ ...Api.Page, params })
        }
        <#if table.addShow || table.copyShow>
        export const save = (params: SaveVO) => {
        return defHttp.request
        <ResultVO>({ ...Api.Save, params })
            }
            </#if>
            <#if table.editShow>
            export const update = (params: UpdateVO) => {
            return defHttp.request
            <ResultVO>({ ...Api.Update, params })
                }
                </#if>
                <#if table.deleteShow>
                export const remove = (params: string[]) => {
                return defHttp.request
                <boolean>({ ...Api.Delete, params })
                    }
                    </#if>
                    export const query = (params: PageQuery) => {
                    return defHttp.request
                    <ResultVO
                            []>({ ...Api.Query, params })
                        }
                        export const detail = (id: string) => {
                        return defHttp.request
                        <ResultVO>({ ...Api.Detail, params: { id } })
                            }
                            export const copy = (id: string) => {
                            return defHttp.request
                            <ResultVO>({ ...Api.Copy, params: { id } })
                                }
                                <#if table.tplType == TPL_TREE>
                                export const tree = (params?: PageQuery) => {
                                return defHttp.request
                                <ResultVO>({ ...Api.Tree, params })
                                    }
                                    </#if>
                                    <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
                                    export const exportPreview = (params: PageParams
                                    <PageQuery>) => {
                                        return defHttp.request
                                        <string>({ ...Api.Preview, params })
                                            }
                                            export const exportFile = (params: PageParams
                                            <PageQuery>) => {
                                                return defHttp.request
                                                <any>({ ...Api.Export, params }, { isReturnNativeResponse: true })
                                                    }
                                                    export const importFile = (params: UploadFileParams) => {
                                                    return defHttp.uploadFile
                                                    <boolean>({ ...Api.Import }, params)
                                                        }
                                                        </#if>
