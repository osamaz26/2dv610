import exceptions.NameEmptyException;
import exceptions.NameHasDigitsException;
import exceptions.NameHasInvalidSizeException;

public class Name {
    private String value;

    public void setValue(String value) {
        if (isEmpty(value)) {
            throw new NameEmptyException();
        }
        if (isLessThanMinimum(value)) {
            throw new NameHasInvalidSizeException();
        }
        if (isAboveMaximum(value)) {
            throw new NameHasInvalidSizeException();
        }
        if (hasDigits(value)) {
            throw new NameHasDigitsException();
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private boolean isEmpty(String value) {
        return value.isEmpty();
    }

    private boolean isLessThanMinimum(String value) {
        return value.length() < 3;
    }

    private boolean isAboveMaximum(String value) {
        return value.length() > 25;
    }

    private boolean hasDigits(String value) {
        for (char ch : value.toCharArray()) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

}
