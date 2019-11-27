import exceptions.CustomerNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerRegistry {

    private HashMap<Integer, Customer> list = new HashMap<>();
    private int index = 1;

    public void add(Customer customer) {
        customer.setId(index++);
        list.put(customer.getId(), customer);
    }

    public void delete(int id) {
        if (list.get(id) == null) {
            throw new CustomerNotFoundException();
        }
        list.remove(id);
    }

    public List<Customer> getList() {
        return new ArrayList<>(list.values());
    }

    public Customer retrieve(int id) {
        var customer = list.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
