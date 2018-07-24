package com.huaifeng.code;

import com.huaifeng.code.dao.BaseDAO;
import com.huaifeng.code.pojo.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeApplicationTests {

	@Resource
	private BaseDAO baseDAO;
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testBaseDAO() {
		List<Table> tables=baseDAO.getTable("presell","presell_stock");
		System.out.println(tables);
	}

}
