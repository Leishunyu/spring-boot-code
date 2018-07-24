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
        if (codePreperties.getPojoName()!=null){
            name=codePreperties.getPojoName();
        }else {
            codePreperties.setPojoName(name);
        }
        name=name + ".java";
        String content = codeService.executeTemplate(codePreperties.getTemplateEntity(), map);
        CodeFile entityFile = new JavaCodeFile(codePreperties.getPojoPkg(), name, content);
        // mapper
        String mapperName=table.getJavaName();
        if (codePreperties.getMapperName()!=null){
            mapperName=codePreperties.getPojoName();
        }else {
            codePreperties.setMapperName(mapperName);
        }
        mapperName=mapperName + "Mapper.xml";
        content = codeService.executeTemplate(codePreperties.getTemplateMapper(), map);
        CodeFile mapperFile = new JavaCodeFile(codePreperties.getMapperPkg() , mapperName, content);
        return Arrays.asList(entityFile, mapperFile);
    }
}
