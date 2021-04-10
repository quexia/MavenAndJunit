package com.yc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-29 18:43
 */
//在程序运行中 有人给了一个类 请动态的了解这个类 并且船舰这个类的对象
public class Test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入类路径");
            String path = scanner.nextLine();
            System.out.println("待加载得类" + path);
            try {
                Class c = Class.forName(path);
                String name = c.getName();//获取类名
                System.out.println(name + "类名");

                Field[] fs = c.getDeclaredFields();//自己定义的 没有的话就是继承的

                if (fs != null && fs.length > 0) {
                    for (Field f : fs) {
                        //getModifiers  返回Java字段的访问修饰符 作为整数返回 2是private
                        String modifiers = "";
                        switch (f.getModifiers()) {
                            case 1:
                                modifiers = "public";
                                break;
                            case 2:
                                modifiers = "private";
                                break;
                        }
                        System.out.println(modifiers + "\t" + f.getName());
                        //位图算法 可以对一个成员 设置多个属性的时候用位运算最方便
                    }
                }
                Method[] ms = c.getDeclaredMethods();
                if (ms != null && ms.length > 0) {
                    for (Method m : ms) {
                        System.out.println(m.getModifiers() + "\t" + m.getName());
                    }
                }
                Constructor[] constructor = c.getConstructors();
                if (constructor != null && constructor.length > 0) {
                    for (Constructor con : constructor) {
                        System.out.println(con.getModifiers() + "\t" + con.getName());
                    }
                }


                //通过反射获取带构造参数的构造方法
                Constructor con = c.getConstructor(String.class);
                Object test = con.newInstance("阙霞");

                //利用反射完成实例化 拿到字节码 反向的创建
                Object o = c.newInstance();//无参构造方法
                //instanceof
                //项目直接依赖于这个类 硬耦合 面向接口
                if (o instanceof Showable) {
                    Showable p = (Showable) o;
                    p.show();
                }
                //利用反射调用某个方法 适合J2ee中的规范化方法调用场景 setXX getXXX 面向某个接口开发
                if (ms != null && ms.length > 0) {
                    for (Method m : ms) {
                        if (m.getName().startsWith("sh")) {
                            m.invoke(o);
                        }
                    }
                }
                Map<String, Object> pMap = new HashMap<>();
                pMap.put("name", "张三");
                pMap.put("age", 18);
                Object oo = setValue(pMap, c);
                System.out.println(oo.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }


    /**
     * 反射功能模块 将Mmap中保存的属性值到 cls对应的对象中 已经知道 cls j2ee的Javabean规范 （setXXX getxxx）
     */
    public static Object setValue(Map map, Class cls) throws Exception {
        Object o = null;
        o = cls.newInstance();
        Method[] ms = cls.getDeclaredMethods();
        if (ms != null && ms.length > 0) {
            for (Method m : ms) {
                //只用setXXX才激活
                if (m.getName().startsWith("set")) {
                    Object value = map.get(m.getName().substring(3).toLowerCase());
                    if ("java.lang.Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName()) || "int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())) {
                        m.invoke(o, Integer.parseInt(String.valueOf(value)));//调用set方法调用值
                    } else if ("java.lang.String".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())) {
                        m.invoke(o, String.valueOf(value));//调用set方法调用值
                    } else if ("java.lang.Float".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName()) || "float".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())) {
                        m.invoke(o, Float.parseFloat(String.valueOf(value)));//调用set方法调用值
                    }

                }
            }
        }


        return o;
    }

}
