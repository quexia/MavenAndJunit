package com.yc.reflect;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-29 19:15
 */

public class Person implements Showable {
    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
        System.out.println("一个参数的构造函数");
    }

    public Person() {
        System.out.println("无参数的构造函数");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void show() {
        System.out.println("------------");
    }
}
