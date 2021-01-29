public class Robot implements Competitor {

    protected String name;
    protected int runningDistance;
    protected int jumpingHeight;
    protected boolean active;

    public Robot(String name, int runningDistance, int jumpingHeight, boolean active) {
        this.name = name;
        this.runningDistance = (int)(Math.random() * 50) + 40;
        this.jumpingHeight = (int)(Math.random() * 1000) + 300;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public int getRunningDistance() {
        return runningDistance;
    }

    public int getJumpingHeight() {
        return jumpingHeight;
    }

    public void setName(String name) {
        this.name = name;
    }

}
