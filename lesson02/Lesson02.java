public class Lesson02 {

    public static void main(String[] args) {
        String[][] goodArray = {{"11", "12", "13", "14"}, {"21", "22", "23", "24"}, {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}};
        String[][] inconvertibleArray = {{"11", "12", "13", "14"}, {"21", "22", "twenty three", "24"}, {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}};
        String[][] longArray = {{"11", "12", "13", "14"}, {"21", "22", "22", "24", "25"}, {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}};
        String[][] shortArray = {{"11", "12", "13", "14"}, {"21", "22", "22"}, {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}};

        String[][][] superArray = {goodArray, inconvertibleArray, longArray, shortArray};

        for (int i = 0; i < 4; i++) {
            try {
                arraySum(superArray[i]);
            } catch (MyArrayDataException | MyArraySizeException e) {
                e.printStackTrace();
            }
        }

    }

    public static void arraySum(String[][] array){
        if (array.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < array.length; i++) {
           if (array[i].length != 4) {
               throw new MyArraySizeException();
            }
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum = sum + Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        System.out.println("Total sum is " + sum);
    }
}
