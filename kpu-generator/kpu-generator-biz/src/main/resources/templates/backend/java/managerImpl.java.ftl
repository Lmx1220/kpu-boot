package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if table.serviceInterface>
    import ${package.Service}.${table.serviceName};
</#if>
import ${superManagerImplClassPackage};
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if table.serviceInterface>, ${table.serviceName}</#if> {

    }
<#else>
    public class ${entity}ManageImpl extends ${superManagerImplClass}<${table.mapperName}, ${entity}> implements ${entity}Manage {

    }
</#if>
