import com.yc.biz.Calculator;
import org.junit.*;

//测试用例类
public class CalculatorTest {
    private Calculator cal;//待测试的单元

    @BeforeClass
    public static void bc() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void ac() {
        System.out.println("AfterClass");
    }


    @Before
    public void setUp() throws Exception {
        System.out.println("before");
        cal = new Calculator();

    }

    @After
    public void tearDown() throws Exception {

        System.out.println("After");
    }

    @Test
    public void add() {
        System.out.println("Add");
        Assert.assertEquals(3, cal.add(1, 2));//前面的值为3 如果不相同则会报错
    }

    @Test
    public void sub() {
        System.out.println("sub");
        Assert.assertEquals(1, cal.sub(2, 1));
    }
}