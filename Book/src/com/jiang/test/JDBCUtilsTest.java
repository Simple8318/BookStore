package com.jiang.test;

import com.jiang.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-08 22:52
 */
public class JDBCUtilsTest {

    /**
     * 测试是否能正常获取数据库连接池对象
     */
    @Test
    public void testJDBCUtils(){
        for (int i=0;i<50;i++){
            Connection connection = JDBCUtils.getConnection();
            System.out.println(connection);
            JDBCUtils.commitAndClose();
            // JDBCUtils.close(connection);
        }
    }
}
