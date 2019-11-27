import exceptions.CustomerUndefinedNameException;
import exceptions.CustomerUndefinedPersonalNumberException;

public class Customer {

    private int id;
    private Name name;
    private PersonalNumber personalNumber;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        if (name == null) {
            throw new CustomerUndefinedNameException();
        }

        this.name = name;
    }

    public PersonalNumber getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(PersonalNumber personalNumber) {
        if (personalNumber == null) {
            throw new CustomerUndefinedPersonalNumberException();
        }
        this.personalNumber = personalNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameAsString() {
        return this.name.getValue();
    }


    public String getPersonalNumberAsString() {
        return this.personalNumber.getValue();
    }
}

