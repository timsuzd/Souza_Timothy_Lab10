import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";

        do {
            IO.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

            if (retString.trim().length() == 0) {
                IO.println("Invalid input. You need to enter something.");
            }
        }while(retString.trim().length() == 0);

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            IO.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            }
            else {
                trash = pipe.nextLine();
                IO.println("You entered: " + trash + ".");
                IO.println("This is not an integer.");
            }
        }while(!done);

        return retVal;
    }

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            IO.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else {
                trash = pipe.nextLine();
                IO.println("You entered: " + trash + ".");
                IO.println("This is not a double.");
            }
        }while(!done);

        return retVal;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            IO.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                }
                else {
                    IO.println("That number is out of range.");
                }
            }
            else {
                trash = pipe.nextLine();
                IO.println("You entered: " + trash + ".");
                IO.println("This is not an integer.");
            }
        }while(!done);

        return retVal;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            IO.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                }
                else {
                    IO.println("That number is out of range.");
                }
            }
            else {
                trash = pipe.nextLine();
                IO.println("You entered: " + trash + ".");
                IO.println("This is not a double.");
            }
        }while(!done);

        return retVal;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String choice = "";
        boolean done = false;
        boolean retVal = false;

        do {
            IO.print("\n" + prompt + " [Y/N]: ");
            choice = pipe.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                retVal = true;
                done = true;
            }
            else if (choice.equalsIgnoreCase("N")) {
                retVal = false;
                done = true;
            }
            else {
                IO.println("You entered: " + choice + ".");
                IO.println("Please enter Y or N.");
            }
        }while(!done);

        return retVal;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String retString = "";
        boolean done = false;

        do {
            IO.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
            if (retString.matches(regEx)) {
                done = true;
            }
            else {
                IO.println("You entered: " + retString + ".");
                IO.println("This does not match the required format.");
            }
        }while(!done);

        return retString;
    }

    public static void prettyHeader(String msg)
    {
        // 3 stars on each side leaves 54 spaces for the message.
        if (msg.length() > 54) {
            msg = msg.substring(0, 54);
        }

        int leftSpace = (54 - msg.length()) / 2;
        int rightSpace = 54 - msg.length() - leftSpace;

        for (int i = 0; i < 60; i++) {
            IO.print("*");
        }
        IO.println();

        for (int i = 0; i < 3; i++) {
            IO.print("*");
        }
        for (int i = 0; i < leftSpace; i++) {
            IO.print(" ");
        }
        IO.print(msg);
        for (int i = 0; i < rightSpace; i++) {
            IO.print(" ");
        }
        for (int i = 0; i < 3; i++) {
            IO.print("*");
        }
        IO.println();

        for (int i = 0; i < 60; i++) {
            IO.print("*");
        }
        IO.println();

    }
}
