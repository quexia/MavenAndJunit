import java.io.File;

/**
 * @program: MavenDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-04-01 18:45
 */

public class PathTest {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "TestMyjunit" + File.separator + "target" + File.separator + "test-classes";
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            System.out.println("-----");
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.getName().endsWith(".class")) {
                    System.out.println(f.getName());
                }
            }
        }
    }
}
