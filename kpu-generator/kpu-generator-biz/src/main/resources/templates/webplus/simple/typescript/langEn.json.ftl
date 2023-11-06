{
  "object": "${table.entityName?cap_first}",
  "table": { "title": "${table.entityName?cap_first} list" },
<#list allFields as field>
  ${field.javaField}: "${field.javaField?cap_first}",
</#list>
}
