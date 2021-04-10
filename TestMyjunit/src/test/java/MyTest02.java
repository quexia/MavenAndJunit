import com.yc.junit.*;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-04-02 23:18
 */

public class MyTest02 {
    @MyBeforeClass
    public static void bc() {
        System.out.println("BeforeClass");
    }

    @MyAfterClass
    public static void ac() {
        System.out.println("AfterClass");
    }


    @Mybefore
    public void setUp() throws Exception {
        System.out.println("Test before");
        //       cal = new Calculator();

    }

    @MyAfter
    public void tearDown() throws Exception {
        System.out.println("Test After");
    }

    @Mytest
    public void add() {
        System.out.println("Test Add");
        //   Assert.assertEquals(3, cal.add(1, 2));//前面的值为3 如果不相同则会报错
    }

    @Mytest
    public void sub() {
        System.out.println("Test sub");
        //    Assert.assertEquals(1, cal.sub(2, 1));
    }
}
