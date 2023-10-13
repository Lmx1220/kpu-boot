export default {
object: '${table.swaggerComment!}',
title: { forTable: '${table.swaggerComment!}列表' },
<#list allFields as field>
    ${field.javaField}: '${field.swaggerComment!}',
</#list>
}
