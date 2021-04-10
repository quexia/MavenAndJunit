package com.yc.annotation;

import java.lang.annotation.*;

/**
 * @program: MavenDemo
 * @description: 第一个接口
 * @author: 阙霞
 * @create: 2021-03-29 20:31
 */
//声明一下他的特征 Targrt注解可以加在哪儿 Retention 表示注解在什么时候还保留（源码 字节码 运行）
//元注解 注解的注解 用来描述一个注解的约束 作用和范围 括号放一个数组
@Target(value = {ElementType.TYPE, ElementType.FIELD})//type 类和接口
/**
 * 程序运行：
 * 源码-》字节码-》运行
 * RetentionPolicy.Source 源码的时候保留
 * RetentionPolicy.Class 源码 ，字节码的时候保留
 * RetentionPolicy.RUNTIME 源码 字节码 运行的时候保留
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented //生成的 java doc文档中是否保留这个注解
//默认情况下父类的注解不会被子类继承 添加下面的注解会被继承
@Inherited
public @interface MyAnnotation {

    public String name();//这个注解里面必须加一个值

    public int age() default 20;//默认值

    public String[] ins();//数组值的属性

   
}
