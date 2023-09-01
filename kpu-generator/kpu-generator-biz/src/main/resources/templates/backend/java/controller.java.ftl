package ${package.Controller};


<#list controllerImport as pkg>
    import ${pkg};
</#list>
import io.swagger.annotations.Api;
<#if table.isLombok>
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
</#if>
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
<#if superControllerClass??>
    import ${superControllerClassPackage};
    import ${entityPackage};

</#if>
<#if controllerConfig.restStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if table.isLombok>
    @Slf4j
    @RequiredArgsConstructor
</#if>
@Validated
<#if controllerConfig.restStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>")
@Api(value = "${table.entityName}", tags="${table.swaggerComment}")
<#if superControllerClass??>
    public class ${controllerName} extends ${superControllerClass}<${serviceName}, ${pkField.javaType}, ${table.entityName}, ${saveVoName},
    ${updateVoName}, ${pageQueryName}, ${resultVoName}> {
<#else>
    public class ${table.controllerName} {
</#if>
<#if table.isLombok>
    private final EchoService echoService;
<#else >
    @Autowired
    private EchoService echoService;
</#if>
<#if superControllerClass?? && superControllerClass != superSimpleControllerSimpleName>
    @Override
    public EchoService getEchoService() {
    return echoService;
    }
</#if>

}

