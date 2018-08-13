package ${config.mapperPkg};

import ${config.mapperPkg}.mapper.${config.pojoName}Mapper;
import ${config.pojoPkg}.${config.pojoName}DO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.Long;
import java.util.List;

/**
 * ${config.pojoName}DAO
 *
 * @author huaifeng
 */
@Repository
public class ${config.pojoName}DAO {
    @Resource
    private ${config.pojoName}Mapper ${config.pojoName?uncap_first}Mapper;

    /**
     * 插入一条数据
     *
     * @param ${config.pojoName}DO 实体
     * @return 是否成功
     */
    public boolean insert(${config.pojoName}DO ${config.pojoName?uncap_first}) {
        int result = ${config.pojoName?uncap_first}Mapper.insert(${config.pojoName?uncap_first});
        return result > 0;
    }

    /**
     * 更新
     *
     * @param ${config.pojoName}DO 实体
     * @return 是否成功
     */
    public boolean update(${config.pojoName}DO ${config.pojoName?uncap_first}) {
        int result = ${config.pojoName?uncap_first}Mapper.update(${config.pojoName?uncap_first});
        return result > 0;
    }

    /**
     * 删除
     *
     * @param PK
     * @return 是否成功
     */
    public boolean delete(Long <#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>) {
        int result = ${config.pojoName?uncap_first}Mapper.delete(<#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>);
        return result > 0;
    }

    /**
     * get${config.pojoName}DO
     *
     * @param Pk
     * @return ${config.pojoName}DO
     */
    public ${config.pojoName} get(Long <#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>) {
        ${config.pojoName} ${config.pojoName?uncap_first} = ${config.pojoName?uncap_first}Mapper.get(<#list table.columns as column><#if column_index==0>${column.javaName}</#if></#list>);
        return ${config.pojoName?uncap_first};
    }

    /**
     * 查询全部
     *
     * @return ${config.pojoName}DO列表
     */
    public List<${config.pojoName}> findAll(${config.pojoName}DO ${config.pojoName?uncap_first}) {
        return  ${config.pojoName?uncap_first}Mapper.findAll(${config.pojoName?uncap_first});
    }
}