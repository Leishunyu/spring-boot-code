package com.huaifeng.code.base;

import com.huaifeng.code.CodeApplication;
import com.huaifeng.code.config.CodePreperties;
import com.huaifeng.code.dao.BaseDAO;
import com.huaifeng.code.facade.BaseFacade;
import com.huaifeng.code.freemarker.CodeService;
import com.huaifeng.code.pojo.CodeFile;
import com.huaifeng.code.pojo.Table;
import com.huaifeng.code.utils.FileUtils;
import com.huaifeng.code.wapper.TableWapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * CodeRunner
 *
 * @author huaifeng
 * @since 2018-07-23
 */
@Component
public class CodeRunner implements ApplicationRunner {
    
    @Resource
    private BaseFacade baseFacade;
    
    @Resource
    private CodePreperties codePreperties;
    
    @Resource
    private CodeService codeService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动成功了");
        Map<String,TableWapper> tableMap= baseFacade.getTables(codePreperties.getTableName(),codePreperties.getSchema());
        System.out.println("Tables: " + Arrays.asList(tableMap));
        List<CodeFile> filelist = codeService.gencode(tableMap);
        FileUtils.writeCodeFile(codePreperties.getRootPath(), filelist);
        System.exit(1);
    }
}
