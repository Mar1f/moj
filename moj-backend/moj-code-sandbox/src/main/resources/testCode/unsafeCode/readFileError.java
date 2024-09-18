import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @description；(读取文件）信息泄露
 * @author:mar1
 * @data:2024/09/03
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String userDir = System.getProperty("user.dir");
        String fileName = userDir + File.separator + "/src/main/resources/application.yml";
        List<String> allLines = Files.readAllLines(Paths.get(fileName));
        System.out.println(String.join("\n", allLines));
    }
}
