package com.cjf.utils;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
*
*   使用DBCP实现数据的连接池
*       连接池配置，自定义类
*       最基本四项完整
*       对于数据看连接其他配置自定义
* */
public class JDBCUtils {
    private static BasicDataSource basicDataSource=new BasicDataSource();
  static {
/*        //创建数据源接口的实现类(连接池)       空参构造
        BasicDataSource basicDataSource= new BasicDataSource();*/
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/demobase?characterEncoding=utf8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        //配置对象连接池的  四个基本（扩展）项
        basicDataSource.setInitialSize(10);     //初始化个数
        basicDataSource.setMaxActive(8);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(1);
        //获取连接
        try {
            Connection con=basicDataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            throw  new RuntimeException("数据库连接失败");
        }

    }

    public static DataSource getDataSource() {
        return basicDataSource;
    }
}
