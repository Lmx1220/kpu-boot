package ${package.Service};

import ${package.Entity}.${entity};
import ${superManagerClassPackage};

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superMapperClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superManagerClass}<${entity}> {

    }
</#if>
