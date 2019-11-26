import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonalNumberTest {

    private PersonalNumber sut;

    @BeforeEach
    void beforeEach() {
        sut = new PersonalNumber();
    }

    @Test
    void shouldThrowInvalidFormatExceptionWhenThereIsNonNumericValues() {
        String testData = "19980920-*889";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidFormatExceptionWhenThereIsNoHyphen() {
        String testData = "8714124612";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenThereIsWhiteSpaceInTheDate() {
        String testData = "87  12-4612";
        assertThrows(PersonalNumberInvalidDateException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidFormatExceptionWhenThereAreLetters() {
        String testData = "871412-äw92";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }


    @Test
    void shouldThrowInvalidDateException() {
        String testData = "871412-4612";
        assertThrows(PersonalNumberInvalidDateException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidCheckSumException() {
        String testData = "870412-0000";
        assertThrows(PersonalNumberInvalidChecksumException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldReturnTheExpectedValue() {
        String expected = "871412-4612";
        sut.setValue(expected);
        var actual = sut.getValue();
        assertEquals(expected, actual);
    }
}
