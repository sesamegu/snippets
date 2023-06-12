/**
 * Introduction:
 *
 * @author sesame 2023/5/8
 */
public class TestMainInJar {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new TransClass().getNumber());
        int count = 0;
        while (true) {
            Thread.sleep(500);
            int number = new TransClass().getNumber();
            System.out.println(number);
        }
    }
}
