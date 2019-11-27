
import exceptions.NameEmptyException;
import exceptions.NameHasDigitsException;
import exceptions.NameHasInvalidSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    private Name sut;

    @BeforeEach
    void beforeEach() {
        sut = new Name();
    }

    @Test
    void shouldReturnHello() {
        String expected = "Hello";
        sut.setValue(expected);
        var actual = sut.getValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnExceptionIfEmpty() {
        String testData = "";
        assertThrows(NameEmptyException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldReturnExceptionIfHasDigits() {
        String testData = "Test1";
        assertThrows(NameHasDigitsException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldReturnExceptionIfSizeBelow3() {
        String testData = "Te";
        assertThrows(NameHasInvalidSizeException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldReturnExceptionIfSizeAbove25() {
        String testData = "My long long long name should not work";
        assertThrows(NameHasInvalidSizeException.class, () -> sut.setValue(testData));
    }
}



