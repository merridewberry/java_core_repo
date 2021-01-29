import java.util.Scanner;

public class lesson01 {

    protected static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("How many humans should participate in challenges?");
        int humanQuantity = input.hasNextInt() ? input.nextInt() : 0;
        input.nextLine();
        System.out.println("How many cats should participate in challenges?");
        int catQuantity = input.hasNextInt() ? input.nextInt() : 0;
        input.nextLine();
        System.out.println("How many robots should participate in challenges?");
        int robotQuantity = input.hasNextInt() ? input.nextInt() : 0;
        input.nextLine();
        System.out.println("How many challenges would you like to have?");
        int challengeQuantity = input.hasNextInt() ? input.nextInt() : 0;
        input.nextLine();

        Competitor[] competitorArray = new Competitor[humanQuantity + catQuantity + robotQuantity];
        Challenge[] challengeArray = new Challenge[challengeQuantity * 2];

        fillChallenges(challengeArray);
        prepareCompetitors(humanQuantity, catQuantity, robotQuantity, competitorArray);
        startChallenge(challengeArray, competitorArray);

    }

    private static void fillChallenges(Challenge[] challengeArray){
        for (int i = 0; i < (challengeArray.length); i+=2) {
            Racetrack racetrack = new Racetrack(0, true);
            challengeArray[i] = racetrack;
            Wall wall = new Wall(0, false);
            challengeArray[i + 1] = wall;
        }
    }

    private static void startChallenge(Challenge[] challengesArray, Competitor[] competitorArray){
        for (int i = 0; i < challengesArray.length; i++) {
            challengesArray[i].announce();
            for (int j = 0; j < competitorArray.length; j++) {
                if (competitorArray[j].isActive()) {
                    if (challengesArray[i].isRace()) {
                        competitorArray[j].run(challengesArray[i].getNum());
                    } else {
                        competitorArray[j].jump(challengesArray[i].getNum());
                    }
                }
            }
        }
    }

    private static void prepareCompetitors(int humanQuantity, int catQuantity, int robotQuantity, Competitor[] competitorArray) {
        fillCompetitors(humanQuantity, catQuantity, robotQuantity, competitorArray);
        shuffleCompetitors(competitorArray);
        announceCompetitors(competitorArray);
    }

    private static void fillCompetitors(int humanQuantity, int catQuantity, int robotQuantity, Competitor[] competitorArray) {
        for (int i = 0; i < humanQuantity; i++) {
            Human human = new Human(("Human #" + (i + 1)), 0, 0, true);
            competitorArray[i] = human;;
        }
        for (int i = 0; i < catQuantity; i++) {
            Cat cat = new Cat(("Cat #" + (i + 1)), 0, 0, true);
            competitorArray[i + humanQuantity] = cat;
        }
        for (int i = 0; i < robotQuantity; i++) {
            Robot robot = new Robot(("Robot #" + (i + 1)), 0,0, true);
            competitorArray[i + humanQuantity + catQuantity] = robot;
        }
    }

    private static void shuffleCompetitors(Competitor[] competitorArray) {
        int index;
        Competitor buffer;
        for (int i = competitorArray.length - 1; i > 0 ; i--) {
            index = (int) (Math.random() * (i + 1));
            buffer = competitorArray[index];
            competitorArray[index] = competitorArray[i];
            competitorArray[i] = buffer;
        }
    }

    private static void announceCompetitors(Competitor[] competitorsArray) {
        System.out.println();
        System.out.println();
        for (int i = 0; i < competitorsArray.length; i++) {
            competitorsArray[i].announce();

        }
    }


}
