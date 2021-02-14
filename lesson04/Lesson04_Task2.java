public class Lesson04_Task2 {

    public static void main(String[] args) {

        Phonebook phonebook = new Phonebook();
        phonebook.add("Appleson", "+1234567890");
        phonebook.add("Orangeberg", "+2345678901");
        phonebook.add("Appleson", "+3456789012");

        phonebook.get("Appleson");
        phonebook.get("Orangeberg");
    }
}
