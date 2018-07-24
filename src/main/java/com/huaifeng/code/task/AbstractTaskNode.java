package com.huaifeng.code.task;

import com.huaifeng.code.config.CodePreperties;
import com.huaifeng.code.freemarker.CodeService;
import com.huaifeng.code.pojo.Table;
import com.huaifeng.code.utils.SIDUtils;
import com.huaifeng.code.wapper.TableWapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTaskNode implements TaskNode{
    
    @Resource
    protected CodeService codeService;
    @Resource
    private CodePreperties codePreperties;
    
    protected Map<String, Object> buildContext(TableWapper table) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("table", table);
        //生成SerialVersionUID
        map.put("serialVersionUID", SIDUtils.getSerialVersionUID());
        map.put("config",codePreperties);
        return map;
    }
}
