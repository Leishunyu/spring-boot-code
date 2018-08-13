package ${config.pojoPkg};

import com.dfire.soa.consumer.activity.presell.domain.base.Base;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ${config.pojoName}
 *
 * @author huaifeng
 * @since 2018-04-26
 */
@Data
public class ${config.pojoName}DO extends Base{

	private static final long serialVersionUID = ${serialVersionUID};
<#list table.columns as column>
<#--//如果需要过滤某些字段(继承公共父类等，可配置该项)-->
<#--<#if column.view>-->
	/**
	 *${column.comment}
	 */
	private ${column.javaType} ${column.javaName};
<#--</#if>-->
</#list>
}
