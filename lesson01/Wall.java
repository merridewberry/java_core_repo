public class Wall implements Challenge {

    protected int height;
    protected boolean race;


    public Wall(int height, boolean race) {
        this.height = (int)(Math.random() * 50) + 50;
        this.race = false;
    }

    public int getNum() {
        return height;
    }

    public void setNum(int height) {
        this.height = height;
    }

    public boolean isRace() {
        return race;
    }

    public void setRace(boolean race) {
        this.race = race;
    }

    public void announce() {
        System.out.printf("%n%nCurrent challenge is %d centimetres wall.%n%n", height);

    }
}
