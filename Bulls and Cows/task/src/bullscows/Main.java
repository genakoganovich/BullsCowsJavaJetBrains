package bullscows;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

//        System.out.println("The secret code is prepared: ****.");
//        System.out.println();
//        System.out.println("Turn 1. Answer:");
//        System.out.println("1234");
//        System.out.println("Grade: None.");
//        System.out.println();
//        System.out.println("Turn 2. Answer:");
//        System.out.println("9876");
//        System.out.println("Grade: 4 bulls.");
//        System.out.println("Congrats! The secret code is 9876.");
    }
}
