package def;

public class InvalidValueException extends Exception {
    int invalidValue;
    public InvalidValueException(int value) {
        this.invalidValue = value;
    }
    public int getInvalidValue(){
        return invalidValue;
    }
}
