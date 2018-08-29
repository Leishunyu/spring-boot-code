package ${config.pojoPkg};

import ${config.pojoPkg}.base.Base;
import lombok.Data;

/**
 * ${config.pojoName}
 *
 * @author huaifeng
 * @since 2018-08-30
 */
@Data
public class ${config.pojoName}DO extends Base{

	private static final long serialVersionUID = ${serialVersionUID};
<#list table.columns as column>
<#--//如果需要过滤某些字段(继承公共父类等，可配置该项)-->
<#--<#if column.view>-->
    <#if column.javaName!="isValid" && column.javaName!="opTime" && column.javaName!="createTime" && column.javaName!="lastVer">
	/**
	 *${column.comment}
	 */
	private ${column.javaType} ${column.javaName};
    </#if>
<#--</#if>-->
</#list>
}
