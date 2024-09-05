import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description；运行其他程序
 * @author:mar1
 * @data:2024/09/03
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        String userDir = System.getProperty("user.dir");
        String fileName = userDir + File.separator + "/src/main/resources/木马程序.bat";
        Process process = Runtime.getRuntime().exec(fileName);
        process.waitFor();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));
        // 拿到控制台的输入流
        String compileOutputLine;
        while((compileOutputLine = bufferedReader.readLine()) != null){
            System.out.println(compileOutputLine);
        }
        System.out.println("程序执行完毕");
    }
}
