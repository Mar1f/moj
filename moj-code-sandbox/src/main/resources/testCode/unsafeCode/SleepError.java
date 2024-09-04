
/**
 * @description；无限睡眠（阻塞程序执行）
 * @author:mar1
 * @data:2024/09/03
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        long ONE_HOUR = 60 * 60 * 1000;
        Thread.sleep(ONE_HOUR);
        System.out.println("The hour is over");
    }

}
