import exceptions.ConsoleReadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleTest {

    private Console sut;
    BufferedReader mockBufferedReader = mock(BufferedReader.class);

    @BeforeEach
    void beforeEach() {
        sut = new Console(mockBufferedReader);
    }

    @Test
    void shouldReturnLine() throws IOException {
        String expected = "line";
        when(mockBufferedReader.readLine()).thenReturn(expected);
        var actual = sut.readLine();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnOne() throws IOException {
        int expected = 1;
        when(mockBufferedReader.readLine()).thenReturn("1");
        var actual = sut.readInt();
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowNumberFormatException() throws IOException {
        when(mockBufferedReader.readLine()).thenReturn("");
        assertThrows(NumberFormatException.class, () -> sut.readInt());
    }

    @Test
    void shouldThrowConsoleReadExceptionWhenReadLine() throws IOException {
        when(mockBufferedReader.readLine()).thenThrow(IOException.class);
        assertThrows(ConsoleReadException.class, () -> sut.readLine());
    }

    @Test
    void shouldThrowConsoleReadExceptionWhenReadInt() throws IOException {
        when(mockBufferedReader.readLine()).thenThrow(IOException.class);
        assertThrows(ConsoleReadException.class, () -> sut.readInt());
    }

    @Test
    void shouldWriteHello() throws IOException {
        var expected = "hello";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sut.write(expected);
        bo.flush();
        var actual = new String(bo.toByteArray());
        assertEquals(actual, expected);
    }

    @Test
    void shouldWriteHelloWithLineFeed() throws IOException {
        var expected = "hello";
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        sut.writeLine(expected);
        bo.flush();
        var actual = new String(bo.toByteArray());
        assertEquals(actual, expected + "\r\n");
    }


}
