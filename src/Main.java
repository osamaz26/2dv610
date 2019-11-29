import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        var console = new Console(new BufferedReader(new InputStreamReader(System.in)));
        var registry = new CustomerRegistry();
        var view = new View(console, registry);
        view.run();
    }
}
