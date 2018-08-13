package com.huaifeng.code.task;

import com.huaifeng.code.config.CodePreperties;
import com.huaifeng.code.pojo.CodeFile;
import com.huaifeng.code.pojo.JavaCodeFile;
import com.huaifeng.code.wapper.TableWapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class EntityTask extends AbstractTaskNode{
    
    @Resource
    private CodePreperties codePreperties;
    
    @Override
    public List<CodeFile> execute(TableWapper table) throws Exception{
        Map<String, Object> map = this.buildContext(table);
        // entity
        String name = table.getJavaName();
        codePreperties.setPojoName(name);
        name=name + "DO.java";
        String content = codeService.executeTemplate(codePreperties.getTemplateEntity(), map);
        CodeFile entityFile = new JavaCodeFile(codePreperties.getPojoPkg(), name, content);
        // mapper
        String mapperName=table.getJavaName();
        codePreperties.setMapperName(mapperName);
        String finalName=mapperName;
        mapperName=mapperName + "Mapper.xml";
        content = codeService.executeTemplate(codePreperties.getTemplateMapperXml(), map);
        CodeFile mappeXmlFile = new JavaCodeFile(codePreperties.getMapperPkg() , mapperName, content);
        name=finalName + "Mapper.java";
        content = codeService.executeTemplate(codePreperties.getTemplateMapper(), map);
        CodeFile mapperFile = new JavaCodeFile(codePreperties.getMapperPkg() , name, content);
        name=finalName + "DAO.java";
        content = codeService.executeTemplate(codePreperties.getTemplateDAO(), map);
        CodeFile daoFile = new JavaCodeFile(codePreperties.getMapperPkg() , name, content);
        return Arrays.asList(entityFile, mapperFile,mappeXmlFile,daoFile);
    }
}
