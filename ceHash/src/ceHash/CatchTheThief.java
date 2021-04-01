package ceHash;

import java.util.Scanner;
import edu.princeton.cs.algs4.StdOut;

public class CatchTheThief {
    private final int WINNING_SCORE = 8;
    private int policeScore;
    private int userScore;

    public void startChase() {
        Predictor predict = new Predictor();

        if(done()) {
            displayResult();
            System.exit(0);
        }
        Move glm = getLatestMove();

        updateScore(predict.predict(), glm);
        startChase();

    }

    private boolean done() {
        return this.policeScore >= WINNING_SCORE
                || this.userScore >= WINNING_SCORE;

    }

    private Move getLatestMove() {
        Scanner scanner = new Scanner(System.in);
        StdOut.print("Next move L(left) or R(right): ");
        String input = scanner.nextLine();

        if(input.startsWith("l") || input.startsWith("L")) {
            return Move.LEFT;

        } else if(input.startsWith("r") || input.startsWith("R")) {
            return Move.RIGHT;

        }

        StdOut.println("Illegal argument, try again.");
        getLatestMove();
        return null;
    }

    private void updateScore(Move prediction, Move userChoice) {
        if(prediction.equals(userChoice)) {
            policeScore++;
            StdOut.print("The prediction was correct. ");
            printScore();
        } else {
            userScore++;
            StdOut.print("The prediction was incorrect. ");
            printScore();
        }

    }

    // Thief: {thiefScore} Police: {policeScore}
    private void printScore() {
        System.out.printf("Thief: %d Police: %d\n", userScore, policeScore);
        StdOut.println();

    }

    // used to figure out who the winner is
    private boolean winner() {
        if(this.userScore > this.policeScore) {
            return true;
        }

        return false;
    }

    private void displayResult() {
        if(winner()) {
            StdOut.println("The thief outsmarted the police!");
        } else {
            StdOut.println("The police outsmarted the thief!");
        }

    }

}