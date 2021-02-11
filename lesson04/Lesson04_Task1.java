import java.util.*;

public class Lesson04_Task1 {

    public static void main(String[] args) {

        String[] array = {"apple", "orange", "apple", "cherry", "lemon", "orange", "mango", "kiwi", "apple", "orange",
                "mango", "orange", "apple", "kiwi", "apple", "mango", "orange", "lemon"};
        ArrayList<String> list = new ArrayList<>();
        Set<String> uniqueList = new HashSet<>();

        addToHashSet(uniqueList, array);
        addToList(list, array);

        Iterator<String> iterator = uniqueList.iterator();

        System.out.println("The unique elements in the list are the following: " + uniqueList);

        countFrequency(list, iterator);

    }

    protected static void addToHashSet (Set<String> set, String[] array) {
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
    }

    protected static void addToList (ArrayList<String> list, String[] array) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }

    protected static void countFrequency(ArrayList<String> list, Iterator<String> iterator) {
        System.out.println();
        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.printf("List contains item \"%s\" %d times.%n", value, Collections.frequency(list, value));
        }
    }


}
