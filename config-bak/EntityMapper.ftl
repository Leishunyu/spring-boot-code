package ${config.mapperPkg}.mapper;

import ${config.pojoPkg}.${config.pojoName}DO;
import org.springframework.stereotype.Repository;

import java.lang.Long;
import java.util.List;

/**
 * ${config.pojoName} - Mapper接口
 *
 * @author huaifeng
 */
@Repository
public interface ${config.pojoName}Mapper {

    /**
     * ${config.pojoName} - 添加
     *
     * @param ${config.pojoName} - DO实体
     * @return 添加结果
     */
    int insert(${config.pojoName} ${config.pojoName?uncap_first});

    /**
     * ${config.pojoName} - 修改
     *
     * @param ${config.pojoName} - DO实体
     * @return 修改结果
     */
    int update(${config.pojoName} ${config.pojoName?uncap_first});

    /**
     * ${config.pojoName} - 删除
     *
     * @param ${config.pojoName} - DO实体
     * @return 删除结果
     */
    int delete(Long <#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>);

    /**
     * 根据PK查询
     *
     * @return ${config.pojoName}DO
     */
    ${config.pojoName} get(Long <#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>);

    /**
     * 查询
     *
     * @return ${config.pojoName}DO列表
     */
    List<${config.pojoName}> findAll(${config.pojoName} ${config.pojoName?uncap_first});
}