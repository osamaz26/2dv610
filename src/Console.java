import exceptions.ConsoleReadException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The type Console.
 */
public class Console {
    private BufferedReader bufferedReader;

    /**
     * Instantiates a new Console.
     *
     * @param bufferedReader the buffered reader
     */
    public Console(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Read line string.
     *
     * @return the string
     */
    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new ConsoleReadException();
        }
    }

    /**
     * Read int int.
     *
     * @return the int
     */
    public int readInt() {
        String str;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            throw new ConsoleReadException();
        }
        return Integer.parseInt(str);
    }

    /**
     * Write line.
     *
     * @param line the line
     */
    public void writeLine(String line) {
        System.out.println(line);
    }

    /**
     * Write.
     *
     * @param line the line
     */
    public void write(String line) {
        System.out.print(line);
    }

}
