import java.util.Arrays;

public class Lesson03_Task1 {

    public static void main(String[] args) {

        String[] stringArray = {"one", "two", "three"};
        Integer[] intArray = {1, 2, 3};
        Character[] charArray = {'o', 'n', 'e'};

        Character[][] arrayArray = {{'o', 'n', 'e'}, {'t', 'w', 'o'}, {'t', 'h', 'r', 'e', 'e'}};

        System.out.println(Arrays.toString(arraySwap(stringArray, 0, 2)));
        System.out.println(Arrays.toString(arraySwap(intArray, 1, 2)));
        System.out.println(Arrays.toString(arraySwap(charArray, 0, 1)));

//        Не уверена, что в такой реазилации есть сильно много смысла, но я все равно сделала, потому что могу.
//        (Хотя я уже почти собралась написать еще один метод, который бы добавлял toString к массивам внутри массивов,
//        но потом вспомнила, что мою первую домашку уже осудили за обилие кода, о котором меня никто не просил)

        System.out.println("Before: " + Arrays.toString(arrayArray));
        System.out.println("After: " + Arrays.toString(arraySwap(arrayArray, 0, 1)));

    }

    public static <T> T[] arraySwap(T[] array, int a, int b) {
        T buffer = array[a];
        array[a] = array[b];
        array[b] = buffer;
        return array;
    }
}
