import java.util.ArrayList;

public class Box<T extends Fruit> {

    protected double weight;
    protected ArrayList<T> content;

    public Box() {
        ArrayList<T> content = new ArrayList<T>();
        this.content = content;
    }

    public double getWeight() {
        double a;
        try {
            a = this.content.get(0).getWeight() * 1.0;
        } catch (IndexOutOfBoundsException e) {
            return 0.0;
        }
        weight = this.content.size() * a;
        return weight;
    }

    public void add(T fruit) {
        content.add(fruit);
    }

    public boolean compare(Box box){
        double a = this.getWeight();
        double b = box.getWeight();
        return a == b;
    }

    public void moveFruits(Box<T> box){
        this.moveFruits(box, this.content.size());
    }

    public void moveFruits(Box<T> box, int quantity){
        if (this.content.size() < quantity){
            System.out.println("There is not enough items in the " + this);
        } else {
            for (int i = 0; i < quantity; i++) {
                box.content.add(this.content.get(0));
                this.content.remove(this.content.size() - 1);
            }
        }
    }

}
