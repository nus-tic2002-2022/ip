package duke.command;
import duke.task.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to represent the user-interface of the program. It displays an introductory message and provides a list of possible commands that the user can input.
 * <br> Once the input is registered, it refers the input to the Parser Class to parse and understand the command that the user wants.
 */

public class UI {
    public static void command() throws IOException, DukeException {
        Scanner inPut = new Scanner(System.in);
        String line = inPut.nextLine();
        Parser.parse(line);
    }

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */

    public static void main() throws DukeException, IOException {
        System.out.println("Hi! This is Duke:) What can I do for you? \n");
        FilePath.main(); //Confirm current directory / file or choose a different directory / file.

        assert TaskList.UpdatedList().size() > 0;
        Parser.parse("list"); //Load initial list onto screen

        System.out.println("Here are the valid entries for the program:\n" +
                "Bye\n"     +
                "List\n"    +
                "Find\n"    +
                "Undo\n"    +
                "History\n" +
                "Done         'Number'\n" +
                "Delete       'Number'\n" +
                "Todo         'Description'\n" +
                "Event        'Description' /at 'Date'\n" +
                "Deadline     'Description' /by 'Date'\n" +
                "Occurrence       /on 'Date'\n\n");
        while (true) {
            try {
                command();
                System.out.print("\n");
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}