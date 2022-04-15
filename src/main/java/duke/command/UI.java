package duke.command;
//import duke.task.*;
import duke.task.DukeException;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void command() throws IOException, DukeException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Parser.parse(line);
        Storage.writeToFile();
        System.out.println("\n");
    }

    public static void main() throws DukeException {
        System.out.println("HI! This is Duke\n" + "Let me load the existing data for you (if any)\n");

        int x = TaskList.UpdatedList().size();

        if (x == 0) {
            System.out.println("No existing data is found");
        }
        else {
            Parser.parse("list");
        }

        System.out.println("\nWhat would you like to do ?");
        System.out.println("List of valid entries include the following:\n\n" +
                "Bye\n" +
                "List\n" +
                "Done         'X'\n" +
                "Delete       'X'\n" +
                "Todo         'Y'\n" +
                "Event        'Y' /at 'Z'\n" +
                "Deadline     'Y' /by 'Z'\n" +
                "Occurrence       /on 'Z'" +  "    //Specify which events / deadlines occur on a particular date\n\n" +
                "Where 'X' refers to the task number, 'Y' refers to the task description and 'Z' refers to the date.\n");
        while (true) {
            try {
                command();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
