package com.huaifeng.code.task;

import com.huaifeng.code.pojo.CodeFile;
import com.huaifeng.code.pojo.Table;
import com.huaifeng.code.wapper.TableWapper;

import java.util.List;
import java.util.Map;

/**
 * TaskNode
 *
 * @author huaifeng
 * @since 2018-07-23
 */
public interface TaskNode {
    
    List<CodeFile> execute(TableWapper table) throws Exception;
    
}
