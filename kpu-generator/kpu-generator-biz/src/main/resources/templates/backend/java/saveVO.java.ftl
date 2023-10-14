package ${package.SaveVO};

<#list saveVoImport as pkg>
import ${pkg};
</#list>
import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 */
<#if table.isLombok>
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
    <#if table.isChain>
@Accessors(chain = true)
    </#if>
@EqualsAndHashCode
@Builder
</#if>
@ApiModel(value = "${saveVoName}", description = "${table.swaggerComment}")
public class ${saveVoName} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list fields as field>
    /**
     * ${field.comment!?replace("\n","\n     * ")}
     */
    @ApiModelProperty(value = "${field.swaggerComment!}")
    <#if field.isRequired || field.isPk>
        <#if field.javaType == "String">
    @NotEmpty(message = "请填写${field.swaggerComment!}")
        <#else>
    @NotNull(message = "请填写${field.swaggerComment!}")
        </#if>
    </#if>
    <#if field.javaType == "String">
    @Size(max = ${field.size?string("##0")}, message = "${field.swaggerComment!}长度不能超过${r'{max}'}")
    <#else>
        <#if field.type?upper_case?starts_with("SHORT")>
    @Size(min = Short.MIN_VALUE, max = Short.MAX_VALUE, message = "${field.customMap.fieldComment!}大小不能超过${r'{max}'}")
        </#if>
        <#if field.type?upper_case?starts_with("BYTE")>
    @Size(min = Byte.MIN_VALUE, max = Byte.MAX_VALUE, message = "${field.customMap.fieldComment!}大小不能超过${r'{max}'}")
        </#if>
    </#if>
    private ${field.javaType} ${field.javaField};
</#list>

<#if isTreeEntity>
    @ApiModelProperty(value = "父节点")
    protected ${pkField.javaType} parentId;

    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;
</#if>

<#if !table.isLombok>
    <#list fields as field>
    public ${field.javaType} get${field.javaField?cap_first}() {
        return ${field.javaField};
    }
    <#if table.isChain>
    public ${saveVoName} set${field.javaField?cap_first}(${field.javaType} ${field.javaField}) {
    <#else>
    public void set${field.javaField?cap_first}(${field.javaType} ${field.javaField}) {
    </#if>
        this.${field.javaField} = ${field.javaField};
    <#if table.isChain>
        return this;
    </#if>
    }

    </#list>
</#if>

<#if table.tplType == TPL_MAIN_SUB>
    private List<${sub.saveVoName}> insertList;
</#if>
<#if !table.isLombok>
    @Override
    public String toString() {
        return "${table.entityName}{" +
    <#list fields as field>
        <#if field_index==0>
            "${field.javaField}=" + ${field.javaField} +
        <#else>
            ", ${field.javaField}=" + ${field.javaField} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
