import java.util.Scanner;

/**
 * Realease dialog with user for calculator
 */
public class userInteract {
    public static void main(String[] arg) {
        try (Scanner reader = new Scanner(System.in)) {
            calculator calc = new calculator();
            String exit;
            do {
                System.out.println("Enter args: ");
                String arg1 = reader.next();
                System.out.println("First arg is " + arg1);

                String arg2 = reader.next();
                System.out.println("Second arg is " + arg2);

                calc.add(Integer.valueOf(arg1), Integer.valueOf(arg2));
                System.out.println("Result: " + calc.getResult());

                System.out.println("Exit (yes/no): ");
                exit = reader.next();

            } while (!exit.equals("yes"));
        }
    }
}