package bullscows;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final int MAX_CODE_LENGTH = 36;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int codeLength = scanner.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int symbolsNumber = scanner.nextInt();
        if (codeLength > MAX_CODE_LENGTH) {
            System.out.println(String.format("Error: can't generate a secret number with a length of"
                    + " %d because there aren't enough unique symbols.", codeLength));
            return;
        }
        int turn = 1;
        boolean isGameFinished = false;
        String code = generateSecretCodeRandom(codeLength, symbolsNumber);
        System.out.printf("The secret is prepared: %s.\n", createCodeAndSymbolsView(codeLength, symbolsNumber));
        System.out.println("Okay, let's start a game!");
        while (!isGameFinished) {
            System.out.println(String.format("Turn %d:", turn));
            isGameFinished = gradeGuessingAttempt(code, scanner.next());
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static String generateSecretCodeRandom(int length, int symbolsNumber) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        char[] symbols = new char[symbolsNumber];
        for (int i = 0; i < length; i++) {
            int seqNumber = random.nextInt(symbolsNumber - i);
            for (int j = 0; j < symbols.length; j++) {
                if (symbols[j] == 'X') {
                    continue;
                }
                if (symbols[j] != 'X' && seqNumber != 0) {
                    seqNumber--;
                } else if (symbols[j] != 'X' && seqNumber == 0) {
                    if (j < 10) {
                        code.append(j);
                    } else {
                        code.append((char)('a' + (j - 10)));
                    }
                    symbols[j] = 'X';
                    break;
                }
            } // for j
        } // for i
        return code.toString();
    }

    private static boolean gradeGuessingAttempt(String code, String guess) {
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
        if (bulls == code.length()) {
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

    private static char[] createAsterisks(int length) {
        char[] asterisks = new char[length];
        Arrays.fill(asterisks, '*');
        return asterisks;
    }

    private static String createSymbolsView(int symbolsNumber) {
        if (symbolsNumber > 11) {
            return String.format("%d-%d, %c-%c", 0, 9, 'a', (char)('a' + (symbolsNumber - 11)));
        } else if (symbolsNumber == 11) {
            return String.format("%d-%d, %c", 0, 9, 'a');
        } else {
            return String.format("%d-%d", 0, symbolsNumber - 1);
        }
    }
    private static String createCodeAndSymbolsView(int codeLength, int symbolsNumber) {
        return String.format("%s (%s)",
                String.valueOf(createAsterisks(codeLength)), createSymbolsView(symbolsNumber));
    }
}
