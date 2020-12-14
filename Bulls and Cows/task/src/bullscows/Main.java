package bullscows;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int length = scanner.nextInt();
        if (length > 10) {
            System.out.println(String.format("Error: can't generate a secret number with a length of"
                    + " %d because there aren't enough unique digits.", length));
            return;
        }
        int turn = 1;
        boolean isGameFinished = false;
        //String code = generateSecretCode(length);
        String code = generateSecretCodeRandom(length);
        System.out.println("Okay, let's start a game!");
        while (!isGameFinished) {
            System.out.println(String.format("Turn %d:", turn));
            isGameFinished = gradeGuessingAttempt(code, scanner.next(), length);
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static String generateSecretCode(int length) {
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
        // System.out.println(String.format("The random secret number is %s.", code.toString()));
        return code.toString();
    }

    private static String generateSecretCodeRandom(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        char[] digits = new char[10];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                int randomInt = random.nextInt(9) + 1;
                code.append(randomInt);
                digits[randomInt] = 'X';
                continue;
            }

            int seqNumber = random.nextInt(10 - i);
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == 'X') {
                    continue;
                }
                if (digits[j] != 'X' && seqNumber != 0) {
                    seqNumber--;
                } else if (digits[j] != 'X' && seqNumber == 0) {
                    code.append(j);
                    digits[j] = 'X';
                    break;
                }
            } // for j
        } // for i
        return code.toString();
    }

    private static boolean gradeGuessingAttempt(String code, String guess, int length) {
        // initialize variables
        int cows = 0;
        int bulls = 0;

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

        // print result
        System.out.printf("Grade: %s.\n", composeGrade(cows, bulls));
        if (bulls == length) {
            return true;
        } else {
            return false;
        }
    }
    private static String addS(int n, String str) {
        return n == 1 ? str : str + "s";
    }

    private static String composeGrade(int cows, int bulls) {
        String bullString = addS(bulls, "bull");
        String cowString = addS(cows, "cow");
        String grade = "";
        if (bulls != 0 && cows != 0) {
            grade = String.format("%d %s and %d %s", bulls, bullString, cows, cowString);
        } else if (bulls != 0) {
            grade = String.format("%d %s", bulls, bullString);
        } else if (cows != 0) {
            grade = String.format("%d %s", cows, cowString);
        } else {
            grade = "None";
        }
        return grade;
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
