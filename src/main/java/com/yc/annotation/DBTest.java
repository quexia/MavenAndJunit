package com.yc.annotation;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-29 21:10
 */
@DBConnection()
public class DBTest {
    public static void main(String[] args) throws Exception {
        Class c = DBTest.class;
        DBConnection a = (DBConnection) c.getDeclaredAnnotation(DBConnection.class);
        String s = a.driverCLass();
        String url = a.url();
        String user = a.user();
        String password = a.password();
        Class.forName(s);
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println(con);

    }
}
