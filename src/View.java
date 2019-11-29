import exceptions.CustomerNotFoundException;

public class View {
    private CustomerRegistry registry;
    private Console console;

    public View(Console console, CustomerRegistry registry) {
        this.registry = registry;
        this.console = console;
    }

    public void run() {
        console.writeLine("============================");
        console.writeLine("Welcome to Customer registry");
        var choice = readMenu();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    deleteCustomer();
                    break;
                case 3:
                    listCustomers();
                    break;
                case 4:
                    retrieveCustomer();
                    break;
                default:
                    console.writeLine("invalid choice");
                    break;
            }
            choice = readMenu();
        }
    }

    public int readMenu() {
        console.writeLine("============================\n" +
                "1: Add customer\n" +
                "2: delete customer\n" +
                "3: list customer\n" +
                "4: retrieve customer\n" +
                "0: exit\n" +
                "============================");
        return readIntWithMessage("Your choice: ");
    }

    public void addCustomer() {
        console.writeLine("Add a new customer");
        var name = readName();
        if (name == null) {
            console.writeLine("Invalid name, try again");
            return;
        }
        var personalNumber = readPersonalNumber();
        if (personalNumber == null) {
            console.writeLine("Invalid name, try again");
            return;
        }
        var customer = new Customer();
        customer.setName(name);
        customer.setPersonalNumber(personalNumber);
        registry.add(customer);
    }

    public Name readName() {
        var name = new Name();
        try {
            name.setValue(readLineWithMessage("Name: "));
        } catch (Exception exception) {
            console.writeLine("Exception: " + exception.toString());
            return null;
        }
        return name;
    }

    public PersonalNumber readPersonalNumber() {
        var personalNumber = new PersonalNumber();
        try {
            personalNumber.setValue(readLineWithMessage("Personal number: "));
        } catch (Exception exception) {
            console.writeLine("Exception: " + exception.toString());
            return null;
        }
        return personalNumber;
    }

    public void deleteCustomer() {
        console.writeLine("Delete customer");
        var id = readIntWithMessage("Customer Id: ");
        try {
            registry.delete(id);
        } catch (Exception exception) {
            console.writeLine("Exception: " + exception.toString());
        }
    }

    public void listCustomers() {
        console.writeLine("List of customers");
        var list = registry.getList();
        for (var customer : list) {
            console.writeLine("[" + customer.getId() + "] " +
                    customer.getNameAsString() + " " +
                    customer.getPersonalNumberAsString());
        }
    }

    public void retrieveCustomer() {
        console.writeLine("Retrieve customer");
        var id = readIntWithMessage("Customer Id: ");

        Customer customer;
        try {
            customer = registry.retrieve(id);
        } catch (Exception exception) {
            console.writeLine("Exception: " + exception.toString());
            return;
        }
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        console.writeLine("[" + customer.getId() + "] " + customer.getNameAsString() + " " +
                customer.getPersonalNumberAsString());
    }

    public int readIntWithMessage(String message) {
        console.write(message);
        return console.readInt();
    }

    public String readLineWithMessage(String message) {
        console.write(message);
        return console.readLine();
    }
}
