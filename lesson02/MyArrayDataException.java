public class MyArrayDataException extends NumberFormatException {

    int i;
    int j;

    public MyArrayDataException(int a, int b) {
        i = a;
        j = b;
    }

    @Override
    public String toString() {
        return "Item [" + i + "][" + j + "] cannot be converted to int.";
    }
}
