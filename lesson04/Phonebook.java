import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {

    protected HashMap<String, ArrayList<String>> data;
    ArrayList<String> temp = new ArrayList<>();

    public Phonebook() {
        HashMap<String, ArrayList<String >> data = new HashMap<>();
        this.data = data;
    }

    public void add(String surname, String phoneNum) {
        ArrayList<String> temp = null;
        if (data.containsKey(surname)) {
            temp = data.get(surname);
        } else {
            temp = new ArrayList<>();
        }
        temp.add(phoneNum);
        data.put(surname, temp);
    }

    public void get(String surname){
        System.out.println("Surname: " + surname + "\nPhone number: " + data.get(surname));
        System.out.println();
    }

}
