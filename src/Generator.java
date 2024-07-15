import java.util.Objects;
import java.util.Scanner;

public class Generator {
    Alphabet alph;
    public static Scanner keyboard;

    public Generator(Scanner s) { keyboard = s; }

    public Generator(boolean IncUppr, boolean IncLwr, boolean IncNum, boolean IncSym) {
        alph = new Alphabet(IncUppr, IncLwr, IncNum, IncSym);
    }

    public void mainLoop() {
        System.out.println("========================================");
        System.out.println("========== PASSWORD GENERATOR ==========");
        System.out.println("========================================");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {

            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    reqPass();
                    printMenu();
                }
                case "2" -> {
                    checkPass();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();

                default -> {
                    System.out.println();
                    printMenu();
                }
            }
        }
    }

    private Password GenPass(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alph.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alph.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void reqPass() {
        boolean IncUppr = false;
        boolean IncLwr = false;
        boolean IncNum = false;
        boolean IncSym = false;

        boolean control;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer"
                + " the following questions by Yes [Y] or No [N] \n");

        do {
            String input;
            control = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used?");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) && !(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")));

            IncLwr = isInclude(input) ? true : false;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) && !(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")));

            IncUppr = isInclude(input) ? true : false;

            do {
            System.out.println("Do you want Numbers \"1234...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) && !(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")));

            IncNum = isInclude(input) ? true : false;

            do {
            System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
            input = keyboard.next();
            PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            IncSym = isInclude(input) ? true : false;

            if (!IncUppr && !IncLwr && !IncNum && !IncSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                control = true;
            }

        } while (control);

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator gen = new Generator(IncUppr, IncLwr, IncNum, IncSym);
        final Password pass = gen.GenPass(length);

        System.err.println("Your generated password\n" + pass);
    }

    private boolean isInclude(String Input) {
        if (Input.equalsIgnoreCase("y") || Input.equalsIgnoreCase("yes")) {
            return true;
        } 
        else {
            return false;
        }
    }

    private void PasswordRequestError(String i) {
        if (!(i.equalsIgnoreCase("yes") || i.equalsIgnoreCase("y")) && (!i.equalsIgnoreCase("no") || i.equalsIgnoreCase("y"))) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPass() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("================================");
        System.out.println("** 1. Password Generator      **");
        System.out.println("** 2. Password Strength Check **");
        System.out.println("** 3. Useful Information      **");
        System.out.println("** 4. Quit                    **");
        System.out.println("================================");
        System.out.print("[ CHOICE ] :: ");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
