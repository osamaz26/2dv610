import exceptions.ConsoleReadException;

import java.io.BufferedReader;
import java.io.IOException;

public class Console {
    private BufferedReader bufferedReader;

    public Console(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String readLine() {
        try {
            var line = bufferedReader.readLine();
            return line;
        } catch (IOException e) {
            throw new ConsoleReadException();
        }
    }

    public int readInt(){
        String str;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            throw new ConsoleReadException();
        }
        var a = Integer.parseInt(str);
        return a;
    }

    public void writeLine(String line) {
        System.out.println(line);
    }

    public void write(String line) {
        System.out.print(line);
    }

}
