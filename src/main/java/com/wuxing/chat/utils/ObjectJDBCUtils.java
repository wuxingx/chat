package com.wuxing.chat.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wuxing on 2016-12-25.
 * 可以绑定当前线程解决dao事物的c3p0工具类
 */
public class ObjectJDBCUtils {
    private static final ComboPooledDataSource cpds = new ComboPooledDataSource();
    //获得当前线程的对象
    private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
    //此处DAO引用的时候已经被绑定到了当前线程,
    public static Connection getCurConnection() {
        //当前线程连接
        return tl.get();
    }
    //该方法是第一个执行的方法,实现了获得连接对象和绑定至当前线程
    public static void startTransaction() throws SQLException {
        Connection conn = cpds.getConnection();
        //将连接绑定到当前线程
        tl.set(conn);
        //将事物改为手动关闭
        conn.setAutoCommit(false);
    }

    //获得连接池对象
    public static DataSource getDataSource() {
        return cpds;
    }
}
