package com.huaifeng.code.freemarker;

import com.huaifeng.code.config.CodePreperties;
import com.huaifeng.code.pojo.CodeFile;
import com.huaifeng.code.task.EntityTask;
import com.huaifeng.code.wapper.TableWapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CodeService
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Service
public class CodeService {
    
    @Resource
    private FreeMarkerConfigurationFactory freeMarkerConfig;
    @Resource
    private EntityTask entityTask;
    @Resource
    private CodePreperties codePreperties;
    
    public Template getTemplate(String name) throws IOException, TemplateException {
        Configuration configuration=freeMarkerConfig.createConfiguration();
        if (!StringUtils.isEmpty(codePreperties.getPath())){
            configuration.setDirectoryForTemplateLoading(new File(ResourceUtils.getURL("").getPath()+codePreperties.getPath()));
        }
        return configuration.getTemplate(name);
    }
    
    public String executeTemplate(String templateEntity, Map<String, Object> map) throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        Template template = this.getTemplate(templateEntity);
        template.process(map, out);
        return out.toString();
    }
    
    public List<CodeFile> gencode(Map<String, TableWapper> tableMap) throws Exception {
        List<CodeFile> filelist = new ArrayList<CodeFile>();
        for (String tableName : tableMap.keySet()) {
            TableWapper table = tableMap.get(tableName);
            filelist.addAll(this.gencode(table));
        }
        return filelist;
    }
    
    private List<CodeFile> gencode(TableWapper table) throws Exception {
        List<CodeFile> filelist = new ArrayList<CodeFile>();
        filelist.addAll(entityTask.execute(table));
        return filelist;
    }
}
