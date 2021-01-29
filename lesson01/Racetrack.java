public class Racetrack implements Challenge {

    protected int length;
    protected boolean race;

    public Racetrack(int length, boolean race) {
        this.length = (int)(Math.random() * 500) + 200;
        this.race = true;
    }

    public int getNum() {
        return length;
    }

    public void setNum(int length) {
        this.length = length;
    }

    public boolean isRace() {
        return race;
    }

    public void setRace(boolean race) {
        this.race = race;
    }

    public void announce() {
        System.out.printf("%n%nCurrent challenge is %d metres racetrack.%n%n", length);
    }
}
