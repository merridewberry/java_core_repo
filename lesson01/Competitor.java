public interface Competitor {

    boolean isActive();
    void setActive(boolean active);
    String getName();
    int getRunningDistance();
    int getJumpingHeight();

    default void run(int distance) {
        if (getRunningDistance() >= distance) {
            System.out.printf("%s was able to finish this round.%n%n", getName());
        } else {
            System.out.printf("%s didn't make it. To finish this round they had to run %d more metres. " +
                    "%s was eliminated from the competition.%n%n", getName(), distance - getRunningDistance(),
                    getName());
            setActive(false);
        }
    }

    default void jump(int height) {
        if (getJumpingHeight() >= height) {
            System.out.printf("%s was able to finish this round.%n%n", getName());
        } else {
            System.out.printf("%s didn't make it. To finish this round they had to jump %d centimetres higher. " +
                    "%s was eliminated from the competition.%n%n", getName(), height - getJumpingHeight(), getName());
            setActive(false);
        }
    }

    default void announce() {
        System.out.printf("%s enters competition. They can run %d metres and jump %d centimetres.%n%n",
                getName(), getRunningDistance(), getJumpingHeight());
    }

}
