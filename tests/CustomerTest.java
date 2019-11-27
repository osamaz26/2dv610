import exceptions.CustomerUndefinedNameException;
import exceptions.CustomerUndefinedPersonalNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerTest {

    private Customer sut;
    private PersonalNumber mockPersonalNumber;
    private Name mockName;

    @BeforeEach
    void beforeEach() {
        sut = new Customer();
        mockName = mock(Name.class);
        mockPersonalNumber = mock(PersonalNumber.class);
    }

    @Test
    void shouldReturnTheID() {
        int expected = 10;
        sut.setId(expected);
        int actual = sut.getId();
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowUndefinedPersonalNumberException() {
        assertThrows(CustomerUndefinedPersonalNumberException.class, () -> sut.setPersonalNumber(null));
    }

    @Test
    void shouldThrowUndefinedNameException() {
        assertThrows(CustomerUndefinedNameException.class, () -> sut.setName(null));
    }

    @Test
    void shouldReturnThePersonalNumber() {
        PersonalNumber expected = new PersonalNumber();
        expected.setValue("580309-1452");
        sut.setPersonalNumber(expected);
        PersonalNumber actual = sut.getPersonalNumber();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnTheNameObject() {
        Name expected = new Name();
        expected.setValue("Jacob");
        sut.setName(expected);
        Name actual = sut.getName();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnThePersonalNumberAsString() {
        String expected = "580309-1452";
        when(mockPersonalNumber.getValue()).thenReturn(expected);
        sut.setPersonalNumber(mockPersonalNumber);
        String actual = sut.getPersonalNumberAsString();
        assertEquals(expected, actual);
    }

    @Test
    void shouldInteractWithThePersonalNumberObject() {
        sut.setPersonalNumber(mockPersonalNumber);
        sut.getPersonalNumberAsString();
        verify(mockPersonalNumber, times(1)).getValue();
    }

    @Test
    void shouldReturnTheNameAsString() {
        String expected = "Jacob";
        when(mockName.getValue()).thenReturn(expected);
        sut.setName(mockName);
        String actual = sut.getNameAsString();
        assertEquals(expected, actual);
    }

    @Test
    void shouldInteractWithTheNameObject() {
        sut.setName(mockName);
        sut.getNameAsString();
        verify(mockName, times(1)).getValue();
    }
}
