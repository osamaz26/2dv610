import exceptions.PersonalNumberInvalidChecksumException;
import exceptions.PersonalNumberInvalidDateException;
import exceptions.PersonalNumberInvalidFormatException;

public class PersonalNumber {

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        if (!checkFormat(value)) {
            throw new PersonalNumberInvalidFormatException();
        }
        if (!checkDate(value)) {
            throw new PersonalNumberInvalidDateException();
        }
        if (!checkChecksum(value)) {
            throw new PersonalNumberInvalidChecksumException();
        }
        this.value = value;
    }

    private boolean checkFormat(String value) {
        if (value.length() != 11) {
            return false;
        }

        for (int i = 0; i < value.length(); ++i) {
            if (i == 6) {
                if (value.charAt(i) != '-') {
                    return false;
                }
                continue;
            }
            if (value.charAt(i) < 0x30 || value.charAt(i) > 0x39) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDate(String value) {
        int year = Integer.parseInt(value.substring(0, 2));
        int month = Integer.parseInt(value.substring(2, 4));
        int day = Integer.parseInt(value.substring(4, 6));
        if ((year >= 0) && (month > 0 && month < 13) && checkDay(year, month, day)) {
            return true;
        } else{
            return false;
        }
    }


    /**
     * Check day.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return true, if successful
     */
    private boolean checkDay(int year, int month, int day) {
        // Basic check of number of days
        if ((day == 0) || (day > 32)) {
            return false;
        }
        // Check max number of days per month
        int[] months = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (day > months[month - 1]) {
            return false;
        }
        // Check leap year
        if ((month != 2) || ((year % 400 != 0) && ((year % 4 != 0) || (year % 100 == 0))) || (day <= 28)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verify checksum.
     *
     * @param value the value
     * @return true, if successful
     */
    private boolean checkChecksum(String value) {
        // Pre-defined array to multiply with same digit position
        int[] arr = {2, 1, 2, 1, 2, 1, 0, 2, 1, 2};
        int sum = 0;
        int x;
        for (int i = 0; i < value.length() - 1; ++i) {
            // Multiply with 2 or 1 according to position
            x = (value.charAt(i) - 0x30) * arr[i];
            // Sum of tens and ones of x
            sum += (x % 10) + (x / 10);
        }
        if((value.charAt(10) - 0x30) == 10 - (sum % 10)){
            return true;
        } else {
            return false;
        }
    }
}
