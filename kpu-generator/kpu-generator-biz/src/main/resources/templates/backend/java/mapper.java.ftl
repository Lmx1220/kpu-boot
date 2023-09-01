package ${package.Mapper};

<#list mapperImport as pkg>
    import ${pkg};
</#list>

<#if superMapperClass??>
    import ${superMapperClassPackage};
    import ${entityPackage}；
</#if>
import org.springframework.stereotype.Repository;

/**
* <p>
    * ${table.comment!?replace("\n","\n * ")} Mapper 接口
    * </p>
*
* @author ${author}
* @date ${date}
*/
<#if mapperAnnotationClass??>
    @${mapperAnnotationClass.simpleName}
</#if>
@Repository
<#if superMapperClass??>
    public interface ${mapperName} extends ${superMapperClass}<${entity}> {
<#else>
    public interface ${mapperName}> {
</#if>

}