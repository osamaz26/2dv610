import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalNumberTest {

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
    void shouldThrowInvalidFormatExceptionWhenTheSizeIsAboveTheLimit() {
        String testData = "19870412-4612";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }


    @Test
    void shouldThrowInvalidFormatExceptionWhenEmpty() {
        String testData = "";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidFormatExceptionWhenThereIsNoHyphenButSomethingElse() {
        String testData = "870412*4612";
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
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenTheDaysPassTheLimitOfTheMonth() {
        String testData = "20100931-6164";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenTheYearIsNotLeapYear() {
        String testData = " 970229-3797";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenTheDayNotCorrect() {
        String testData = " 970029-3797";
        assertThrows(PersonalNumberInvalidFormatException.class, () -> sut.setValue(testData));
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
    void shouldThrowInvalidDateExceptionWhenTheDayIs31() {
        String testData = "870431-4612";
        assertThrows(PersonalNumberInvalidDateException.class, () -> sut.setValue(testData));
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenTheDayIs00() {
        String testData = "870400-4612";
        assertThrows(PersonalNumberInvalidDateException.class, () -> sut.setValue(testData));
    }




    @Test
    void shouldThrowInvalidCheckSumException() {
        String testData = "870412-0000";
        assertThrows(PersonalNumberInvalidChecksumException.class, () -> sut.setValue(testData));
    }


    @Test
    void shouldReturnTheExpectedValue() {
        String expected = "870412-4612";
        sut.setValue(expected);
        var actual = sut.getValue();
        assertEquals(expected, actual);
    }
}
