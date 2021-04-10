package com.yc.junit;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-31 19:48
 */

public class MyJunitRunner {
    public static void main(String[] args) throws Exception {
        //  URL url=new U
//        URLClassLoader
        //获取路径下所有的类
        String path = System.getProperty("user.dir") + File.separator + "TestMyjunit" + File.separator + "target" + File.separator + "test-classes" + File.separator;
        System.out.println(path);
        File file = new File(path);

        List<String> ClassList = new ArrayList<>();
        if (file.exists() && file.isDirectory()) {
            System.out.println("-----");
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.getName().endsWith(".class")) {
                    ClassList.add(f.getName());
                }
            }
        }

        String path2 = "file:///" + path;

        //url里面是文件的根路径

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入测试的类名");
//        String className = scanner.nextLine();
        // 循环类名是否存在 存在则运行类名 否则抛出类名不存在的异常
//

        //判断是否有类需要测试
        if (ClassList == null && ClassList.size() <= 0) {
            throw new Exception("没有类需要测试");
        }
        URLClassLoader loader = new URLClassLoader(new URL[]{new URL(path2)});
        for (int i = 0; i < ClassList.size(); i++) {

            System.out.println("第" + (i + 1) + "个类测试，类名为:" + ClassList.get(i).substring(0, ClassList.get(i).length() - 6));

            Class cls = loader.loadClass(ClassList.get(i).substring(0, ClassList.get(i).length() - 6));
            //1.获取这个类中所有的方法
            Method[] ms = cls.getDeclaredMethods();
            //2.循环这方法 判断上面加了什么注解
//        List testlist = new ArrayList();
            List<Method> testList = new ArrayList<>();
            Method BeforeClass = null;
            Method AfterClass = null;
            Method Before = null;
            Method After = null;

            //     Map<String, Object> map = new HashMap<>();
            for (Method m : ms) {
//            Annotation[] annotation = m.getDeclaredAnnotations();
//            if (annotation[0].toString().equalsIgnoreCase("@com.yc.junit.Mytest()")) {
//                testlist.add(m);
//                map.put(annotation[0].toString(), testlist);
//            } else {
//                System.out.println(m.getName() + "-----");
//                map.put(annotation[0].toString(), m);
//            }
                if (m.isAnnotationPresent(Mytest.class)) {
                    testList.add(m);
                }
                if (m.isAnnotationPresent(MyAfter.class)) {
                    After = m;
                }
                if (m.isAnnotationPresent(MyAfterClass.class)) {
                    AfterClass = m;
                }
                if (m.isAnnotationPresent(Mybefore.class)) {
                    Before = m;
                }
                if (m.isAnnotationPresent(MyBeforeClass.class)) {
                    BeforeClass = m;
                }


            }
            //3.将这些方法存好 @Test对应的方法存在一个集合中 其他注解对应的方法只有一个 直接存

            //4.按junit的生命周器调用
//        Object o = cls.newInstance();
//        for (int i = 0; i < testlist.size(); i++) {
//            if (i == 0) {
//                Method m = (Method) map.get("@com.yc.junit.MyBeforeClass()");
//                m.invoke(o);
//            }
//            Method before = (Method) map.get("@com.yc.junit.Mybefore()");
//            before.invoke(o);
//            List<Method> test01 = (List<Method>) map.get("@com.yc.junit.Mytest()");
//            test01.get(i).invoke(o);
//            Method after = (Method) map.get("@com.yc.junit.MyAfter()");
//            after.invoke(o);
//
//            if (i == testlist.size() - 1) {
//                Method m = (Method) map.get("@com.yc.junit.MyAfterClass()");
//                m.invoke(o);
//            }
//
//        }

            if (testList == null || testList.size() <= 0) {
                throw new Exception("暂无要测试的类");
            }
            Object object = cls.newInstance();
            BeforeClass.invoke(object);
            for (Method m : testList) {
                Before.invoke(object);
                m.invoke(object);
                After.invoke(object);
            }
            AfterClass.invoke(object);

        }
    }


}
