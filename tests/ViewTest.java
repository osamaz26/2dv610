import exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ViewTest {

    private View sut;
    Console mockConsole = mock(Console.class);
    CustomerRegistry mockCustomerRegistry = mock(CustomerRegistry.class);
    Customer mockCustomer = mock(Customer.class);

    @BeforeEach
    void beforeEach() {
        sut = new View(mockConsole, mockCustomerRegistry);
    }

    @Test
    void shouldReturnHello() {
        var expected = "Hello";
        when(mockConsole.readLine()).thenReturn(expected);
        var actual = sut.readLineWithMessage("");
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnOne() {
        var expected = 1;
        when(mockConsole.readInt()).thenReturn(expected);
        var actual = sut.readIntWithMessage("");
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionIfCustomerNotRetrieved() {
        when(mockConsole.readInt()).thenReturn(1);
        when(mockCustomerRegistry.retrieve(1)).thenThrow(CustomerNotFoundException.class);
        sut.retrieveCustomer();
        verify(mockConsole).writeLine("Retrieve customer");
        verify(mockConsole).writeLine("Exception: exceptions.CustomerNotFoundException");
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionIfCustomerIsNull() {
        when(mockConsole.readInt()).thenReturn(0);
        assertThrows(CustomerNotFoundException.class, () -> sut.retrieveCustomer());
    }

    @Test
    void shouldPrintCustomer() {
        when(mockConsole.readInt()).thenReturn(1);
        when(mockCustomer.getNameAsString()).thenReturn("Test");
        when(mockCustomer.getPersonalNumberAsString()).thenReturn("670203-1938");
        when(mockCustomer.getId()).thenReturn(1);
        when(mockCustomerRegistry.retrieve(1)).thenReturn(mockCustomer);

        sut.retrieveCustomer();

        verify(mockConsole).writeLine("Retrieve customer");
        verify(mockConsole).writeLine("[1] Test 670203-1938");
    }

    @Test
    void shouldPrintListOfCustomers() {
        when(mockConsole.readInt()).thenReturn(1);
        when(mockCustomer.getNameAsString()).thenReturn("Test");
        when(mockCustomer.getPersonalNumberAsString()).thenReturn("670203-1938");
        when(mockCustomer.getId()).thenReturn(1);
        var list = new ArrayList<Customer>();
        list.add(mockCustomer);
        when(mockCustomerRegistry.getList()).thenReturn(list);

        sut.listCustomers();

        verify(mockConsole).writeLine("List of customers");
        verify(mockConsole).writeLine("[1] Test 670203-1938");
    }

    @Test
    void shouldDeleteCustomer() {
        when(mockConsole.readInt()).thenReturn(1);
        when(mockCustomer.getNameAsString()).thenReturn("Test");
        when(mockCustomer.getPersonalNumberAsString()).thenReturn("670203-1938");
        when(mockCustomer.getId()).thenReturn(1);
        var list = new ArrayList<Customer>();
        list.add(mockCustomer);
        when(mockCustomerRegistry.getList()).thenReturn(list);

        sut.deleteCustomer();

        verify(mockConsole).writeLine("Delete customer");
    }

    @Test
    void shouldThrowExceptionCustomerNotFoundExceptionWhenDelete() {
        when(mockConsole.readInt()).thenReturn(1);
        Mockito.doThrow(new CustomerNotFoundException()).when(mockCustomerRegistry).delete(1);
        sut.deleteCustomer();
        verify(mockConsole).writeLine("Delete customer");
        verify(mockConsole).writeLine("Exception: exceptions.CustomerNotFoundException");
    }

    @Test
    void shouldReadName() {
        var expected = "Test";
        when(mockConsole.readLine()).thenReturn(expected);
        var name = sut.readName();
        assertNotNull(name);
        var actual = name.getValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReadPersonalNumber() {
        var expected = "670203-1938";
        when(mockConsole.readLine()).thenReturn(expected);
        var personalNumber = sut.readPersonalNumber();
        assertNotNull(personalNumber);
        var actual = personalNumber.getValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReadNameShowException() {
        when(mockConsole.readLine()).thenReturn("1234");
        sut.readName();
        verify(mockConsole).writeLine("Exception: exceptions.NameHasDigitsException");
    }

    @Test
    void shouldReadPersonalNumberShowException() {
        when(mockConsole.readLine()).thenReturn("");
        sut.readPersonalNumber();
        verify(mockConsole).writeLine("Exception: exceptions.PersonalNumberInvalidFormatException");
    }

    @Test
    void shouldReadMenu() {
        sut.readMenu();
        verify(mockConsole).writeLine(
                "============================\n" +
                        "1: Add customer\n" +
                        "2: delete customer\n" +
                        "3: list customer\n" +
                        "4: retrieve customer\n" +
                        "0: exit\n" +
                        "============================");
    }



}
