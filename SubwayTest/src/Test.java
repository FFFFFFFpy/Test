import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            SubwaySystem system = new SubwaySystem("subway.txt");
            // ...测试功能
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
