package com.jiang.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-08 21:13
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns=new ThreadLocal<>();
    //通过静态代码块，在类加载时，创建数据库连接池
    static {
        try {
            Properties properties=new Properties();
            // 读取jdbc.properties属性配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return  如果返回null，则获取连接池对象失败，否则返回一个连接对象
     */
    public static Connection getConnection(){
        // 从ThreadLocal中获取数据库连接对象(使得所有的jdbc操作使用同一个数据库连接对象)
        Connection connection = conns.get();
        // 第一次使用数据库时，ThreadLocal中没有数据库连接对象，则从数据库连接池中获取数据库连接对象
        if (connection==null){
            try {
                connection=dataSource.getConnection();  // 从数据库连接池中获取数据库连接对象
                conns.set(connection);  // 将数据库连接对象保存到ThreadLocal中，供jdbc操作使用
                connection.setAutoCommit(false);    // 设置为手动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     *      提交事务，并关闭数据库连接对象
     */
    public static void commitAndClose(){
        // 从ThreadLocal中获取数据库连接对象
        Connection connection = conns.get();
        // 数据库连接对象不为null时，提交事务并关闭连接对象
        if (connection!=null){  // 如果数据库连接对象不为null，说明之前使用过数据库连接对象，并保存在了ThreadLocal中
            try {
                connection.commit();     // 手动提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();     // 关闭数据库连接对象
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 移除ThreadLocal对象
        conns.remove();
    }

    /**
     *      回滚事务，并关闭数据库连接对象
     */
    public static void rollbackAndClose(){
        // 从ThreadLocal中获取数据库连接对象
        Connection connection = conns.get();
        // 数据库连接对象不为null时，回滚事务并关闭连接对象
        if (connection!=null){  // 如果数据库连接对象不为null，说明之前使用过数据库连接对象，并保存在了ThreadLocal中
            try {
                connection.rollback();     // 回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();     // 关闭数据库连接对象
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 移除ThreadLocal对象
        conns.remove();
    }
}
