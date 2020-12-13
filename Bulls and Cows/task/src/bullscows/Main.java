package bullscows;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int length = new Scanner(System.in).nextInt();
        if (length > 10) {
            System.out.println(String.format("Error: can't generate a secret number with a length of"
                    + " %d because there aren't enough unique digits.", length));
            return;
        }
        boolean success = false;
        long pseudoRandomNumber = System.nanoTime();
        StringBuilder code = new StringBuilder();
        while (!success) {
            StringBuilder digits = new StringBuilder(String.valueOf(pseudoRandomNumber)).reverse();
            for (int i = 0; i < digits.length(); i++) {
                if (code.length() == 0) {
                    if (digits.charAt(i) != '0') {
                        code.append(digits.charAt(i));
                    }
                    continue;
                }
                boolean isUnique = true;
                for (int j = 0; j < code.length(); j++) {
                    if (digits.charAt(i) == code.charAt(j)) {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique) {
                    code.append(digits.charAt(i));
                }
                if (code.length() == length) {
                    success = true;
                    break;
                }
            }

            if (!success) {
                pseudoRandomNumber = System.nanoTime();
                code = new StringBuilder();
            }
        }
        System.out.println(String.format("The random secret number is %s.", code.toString()));
    }

    private static void stageTwoDone() {
        // initialize variables
        String code = "9305";
        int cows = 0;
        int bulls = 0;

        // get input
        String guess = new Scanner(System.in).next();

        // calculate bulls
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }

        // calculate cows
        for (int i = 0; i < code.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (code.charAt(i) == guess.charAt(j) && i != j) {
                    cows++;
                }
            }
        }

        // compose result
        String grade = "";
        if (bulls != 0 && cows != 0) {
            grade = String.format("%d bull(s) and %d cow(s)", bulls, cows);
        } else if (bulls != 0) {
            grade = String.format("%d bull(s)", bulls);
        } else if (cows != 0) {
            grade = String.format("%d cow(s)", cows);
        } else {
            grade = "None";
        }

        // print result
        System.out.printf("Grade: %s. The secret code is %s.", grade, code);
    }

    private static void stageOneDone() {
        System.out.println("The secret code is prepared: ****.");
        System.out.println();
        System.out.println("Turn 1. Answer:");
        System.out.println("1234");
        System.out.println("Grade: None.");
        System.out.println();
        System.out.println("Turn 2. Answer:");
        System.out.println("9876");
        System.out.println("Grade: 4 bulls.");
        System.out.println("Congrats! The secret code is 9876.");
    }
}
