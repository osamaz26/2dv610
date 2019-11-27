import exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerRegistryTest {
    private CustomerRegistry sut;
    private Customer mockCustomer;

    @BeforeEach
    void beforeEach() {
        sut = new CustomerRegistry();
        mockCustomer = mock(Customer.class);
    }

    @Test
    void shouldThrowCustomerNotFoundException() {
        Customer testData = new Customer();
        sut.add(testData);
        int nonExistentID = 1009;
        assertThrows(CustomerNotFoundException.class, () -> sut.delete(nonExistentID));
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionOnRetrieve() {
        Customer testData = new Customer();
        sut.add(testData);
        int nonExistentID  = 101;
        assertThrows(CustomerNotFoundException.class, () -> sut.retrieve(nonExistentID));
    }

    @Test
    void shouldRetrieveTheFirstCustomerById() {
        Customer expected = new Customer();
        sut.add(expected);
        int firstIDForTheFirstCustomer  = 1;
        Customer actual = sut.retrieve(firstIDForTheFirstCustomer);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRetrieveTheFifthCustomerById() {
        Customer expected = new Customer();
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(expected);
        int idForTheTarget  = 5;
        Customer actual = sut.retrieve(idForTheTarget);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteTheFifthCustomerAndThrowExceptionWhenToRetrieveIt() {
        Customer testData = new Customer();
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(new Customer());
        sut.add(testData);
        int idForTheTarget = 7;
        sut.delete(idForTheTarget);
        assertThrows(CustomerNotFoundException.class, () -> sut.retrieve(idForTheTarget));
    }

    @Test
    void shouldReturnTheListOfCustomersCustomers() {
        Customer testData1 = new Customer();
        Customer testData2 = new Customer();
        Customer testData3 = new Customer();
        sut.add(testData1);
        sut.add(testData2);
        sut.add(testData3);
        List<Customer> expected = new ArrayList<>();
        expected.add(testData1);
        expected.add(testData2);
        expected.add(testData3);
        List<Customer> actual = sut.getList();
        assertEquals(expected, actual);
    }


    @Test
    void shouldInteractWithTheCustomerObjectUponSettingID() {
        sut.add(mockCustomer);
        verify(mockCustomer, times(1)).getId();
    }
}
