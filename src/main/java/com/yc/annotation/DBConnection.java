package com.yc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-29 21:09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBConnection {
    //链接数据库的属性
    public String url() default "jdbc:mysql://localhost:3306/apple";

    public String driverCLass() default "com.mysql.cj.jdbc.Driver";

    public String user() default "root";

    public String password() default "a";
}
