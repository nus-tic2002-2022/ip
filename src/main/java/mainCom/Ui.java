package mainCom;
import subTask.*;
import java.io.IOException;
import java.util.Scanner;

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