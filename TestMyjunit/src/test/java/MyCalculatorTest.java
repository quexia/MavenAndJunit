import com.yc.junit.*;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-03-31 19:42
 */

public class MyCalculatorTest {

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
        System.out.println("before");
        //       cal = new Calculator();

    }

    @MyAfter
    public void tearDown() throws Exception {
        System.out.println("After");
    }

    @Mytest
    public void add() {
        System.out.println("Add");
        //   Assert.assertEquals(3, cal.add(1, 2));//前面的值为3 如果不相同则会报错
    }

    @Mytest
    public void sub() {
        System.out.println("sub");
        //    Assert.assertEquals(1, cal.sub(2, 1));
    }
}
