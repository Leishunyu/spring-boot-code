<?xml version="1.0" encoding="UTF-8" ?>   
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.mapperPkg}.mapper.${config.pojoName}Mapper">

    <!-- 创建数据库与实体类字段对应关系 -->
    <resultMap id="BaseResultMap" type="${config.pojoPkg}.${config.pojoName}DO">
		<#list table.columns as column>
			<#if column_index==0>
				<id column="${column.name}" property="${column.javaName}"/>
			<#else>
				<result column="${column.name}" property="${column.javaName}"/>
			</#if>
		</#list>
    </resultMap>

    <!-- 公共基础SQL -->
    <sql id="Base_Column_List">
        <#list table.columns as column>
			${column.name} <#if column_has_next>,</#if>
		</#list>
    </sql>

    <select id="get" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List"/>
        from ${table.name?lower_case}
        where <#list table.columns as column><#if column_index==0>${column.name}=${"#"}{${column.javaName}}</#if></#list>
        and is_valid = 1
    </select>

	<!--查询全部-->
    <select id="findAll" resultMap="BaseResultMap"
            parameterType="${config.pojoPkg}.${config.pojoName}DO">
        select
        <include refid="Base_Column_List"/>
        from ${table.name?lower_case}
        <where>
            is_valid = 1
			<#list table.columns as column>
				<#if column.javaName!="isValid" && column.javaName!="opTime" && column.javaName!="createTime" && column.javaName!="lastVer">
				<if test="${column.javaName} != null<#if column.stringType> and ${column.javaName} != ''</#if>">
                    and ${column.name} = ${"#"}{${column.javaName}}
                </if>
			</#if>
			</#list>
        </where>
    </select>

    <!-- 新增 -->
    <insert id="insert" parameterType="${config.pojoPkg}.${config.pojoName}DO">
        insert into ${table.name?lower_case}(
        	<include refid="Base_Column_List"/>
        ) values(
		<#list table.columns as column>
			${"#"}{${column.javaName}} <#if column_has_next>,</#if>
		</#list>
        )
    </insert>

	<!-- 修改 -->
    <update id="update" parameterType="${config.pojoPkg}.${config.pojoName}DO">
        update
			${table.name?lower_case}
        <set>
			<#list table.columns as column>
			<#if column_index != 0 && column.name!="last_ver" >
				<if test="${column.javaName} != null">
				${column.name} = ${"#"}{${column.javaName}},
				</if>
			</#if>
			</#list>
            last_ver = last_ver + 1
		</set>
        where <#list table.columns as column><#if column_index==0>${column.name}=${"#"}{${column.javaName}}</#if></#list> and is_valid = 1
    </update>

    <!-- 软删除实体 -->
    <update id="delete" parameterType="java.lang.Long">
        update ${table.name?lower_case}
        set is_valid = 0
        where <#list table.columns as column><#if column_index==0>${column.name}=${"#"}{${column.javaName}}</#if></#list>
    </update>

</mapper>   
