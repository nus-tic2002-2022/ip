package mainCom;
import subTask.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to represent the user-interface of the program. It displays an introductory message and provides a list of possible commands that the user can input.
 * Once the input is registered, it refers the input to the Parser Class to parse and understand the command that the user wants.
 */


public class Ui {
    public static void echo() throws IOException, DukeException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Act.parse(line);
        Keep.writeFile();
        System.out.println("\n");
    }

    public static void main() throws DukeException {
        if (TKlist.UpdatedList().size() == 0) {
            System.out.println("No existing data is found");
        }
        else {
            Act.parse("list");
        }
        System.out.println("\nWhat can I do for you :)\n");
        while (true) {
            try {
                echo();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}