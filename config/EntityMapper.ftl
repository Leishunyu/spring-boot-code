<?xml version="1.0" encoding="UTF-8" ?>   
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.mapperPkg}">

	<!-- 插入实体 -->
	<insert id="insert" parameterType="${config.pojoPkg}.${config.pojoName}">
		INSERT INTO ${table.name} (
		<#list table.columns as column>
		   	${column.name}<#if column_has_next>,</#if>
		</#list>
		) VALUES (
		<#list table.columns as column>
		   	${"#"}{${column.javaName},jdbcType=${column.jdbcType}}<#if column_has_next>,</#if>
		</#list>
		)
		<selectKey databaseId="mysql" keyProperty="id" order="AFTER" resultType="long">
			SELECT LAST_INSERT_ID() AS ID
		</selectKey>
		<selectKey databaseId="oracle" keyProperty="id" order="BEFORE" resultType="long">
			SELECT SEQ_${table.name}.nextval as id FROM dual
		</selectKey>
	</insert>

	<!-- 更新实体 -->
	<update id="update" parameterType="${config.pojoPkg}.${config.pojoName}">
		UPDATE ${table.name}
		<set>
		<#assign versionFlag = false />
		<#list table.columns as column>
		<#if column.name != 'ID'>
			<#if column.name == 'version' || column.name == 'VERSION'>
			<#assign versionFlag = true />
			<#else>
			<if test="${column.javaName} != null">
				${column.name} = ${"#"}{${column.javaName},jdbcType=${column.jdbcType}},
			</if>
			</#if>
		</#if>
		</#list>
		</set>
		WHERE ID = ${"#"}{id,jdbcType=INTEGER} 
	</update>

	<!-- 删除实体 -->
	<delete id="deleteEntity" parameterType="${config.pojoPkg}.${config.pojoName}">
		DELETE FROM ${table.name} WHERE ID = ${"#"}{id,jdbcType=INTEGER}
	</delete>

	<!-- 查询字段 -->
	<sql id="selectFieldSQL">
		SELECT 
		<#list table.columns as column>
		       ${column.name} ${column.javaName}<#if column_has_next>,</#if>
		</#list>
	</sql>

	<!-- 查询 -->
	<select id="get" parameterType="map" resultType="${config.pojoPkg}.${config.pojoName}">
		<include refid="selectFieldSQL" />
		  FROM ${table.name}
		 WHERE ID = ${"#"}{id,jdbcType=INTEGER}
	</select>

	<!-- 查询 -->
	<select id="find" parameterType="${config.pojoPkg}.${config.pojoName}" resultType="${config.pojoPkg}.${config.pojoName}">
		<include refid="selectFieldSQL" />
		  FROM ${table.name}
		<where>
			<if test="id != null">
				AND ID = ${"#"}{id,jdbcType=INTEGER}
			</if>
		<#list table.columns as column>
			<if test="${column.javaName} != null<#if column.stringType> and ${column.javaName} != ''</#if>">
				AND ${column.name} = ${"#"}{${column.javaName},jdbcType=${column.jdbcType}}
			</if>
		</#list>
		</where>
	</select>

</mapper>   
