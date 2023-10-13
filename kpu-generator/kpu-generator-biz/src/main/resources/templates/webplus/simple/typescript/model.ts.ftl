import type { BaseModel, ReduceForSaveVO, ReduceForUpdateVO, ReduceForPageQuery } from '/@/api/model/baseModel'

export interface ${baseVoName} extends BaseModel {
<#list fields as field>
    ${field.javaField}: ${field.tsType}; // ${field.swaggerComment!}
</#list>
<#if isTreeEntity>
    parentId: ${pkField.tsType};
    sortValue: number;
</#if>
}

export interface ${resultVoName} = Partial<${baseVoName}>

export interface ${saveVoName} = Omit<${baseVoName}, ReduceForSaveVO>

export interface ${updateVoName} = Omit<${baseVoName}, ReduceForUpdateVO>

export interface ${pageQueryName} = Partial
<Omit<${baseVoName}, ReduceForPageQuery>>
