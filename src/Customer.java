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
        } else {
            this.name = name;
        }
    }

    public PersonalNumber getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(PersonalNumber personalNumber) {
        if (personalNumber == null) {
            throw new CustomerUndefinedPersonalNumberException();
        } else {
            this.personalNumber = personalNumber;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameAsString() {
        String name = this.name.getValue();
        return name;
    }


    public String getPersonalNumberAsString() {
        String personalNumber = this.personalNumber.getValue();
        return personalNumber;
    }
}

