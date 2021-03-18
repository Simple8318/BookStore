package com.jiang.dao.impl;

import com.jiang.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 9:16
 */
public abstract class BaseDao {

    private QueryRunner queryRunner=new QueryRunner();

    /**
     * 用来执行Insert、Update、Delete语句
     * @param sql   传入的SQL语句
     * @param args  不同SQL语句的参数
     * @return  如果返回-1，则执行失败，否则返回SQL语句影响的行数
     */
    public int update(String sql,Object ... args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            // 将异常抛出到上一层，以便数据库操作能捕获异常从而执行相应的事务操作
            throw new RuntimeException(e);
        }
    }

    /**
     *  查询并返回一个javaBean
     * @param type  返回的对象类型
     * @param sql   执行的SQL语句
     * @param args  sql对应的参数
     * @param <T>   返回的类型的泛型
     * @return  如果查询失败，返回null，否则返回一个泛型 T 类型的JavaBean对象
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 将异常抛出到上一层，以便数据库操作能捕获异常从而执行相应的事务操作
            throw new RuntimeException(e);
        }
    }

    /**
     *  查询并返回一个javaBean集合
     * @param type  返回的对象类型
     * @param sql   执行的SQL语句
     * @param args  sql对应的参数
     * @param <T>   返回的类型的泛型
     * @return  如果查询失败，返回null，否则返回一个泛型 T 类型的JavaBean对象集合
     */
    public <T>List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 将异常抛出到上一层，以便数据库操作能捕获异常从而执行相应的事务操作
            throw new RuntimeException(e);
        }
    }

    /**
     *      查询单个属性
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return  如果执行失败，返回null，否则返回一个要查询的单个属性
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            // 将异常抛出到上一层，以便数据库操作能捕获异常从而执行相应的事务操作
            throw new RuntimeException(e);
        }
    }
}
