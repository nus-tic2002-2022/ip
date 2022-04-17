package mainCom;
import subTask.DukeException;
import java.io.IOException;

/**
 * TIC2002 - Duke, your personalized task manager
 * This task manager can help you mange your task and schedule.
 * You can use the command like ‘todo’, ‘event’, ‘deadline’ etc commands
 * to manage your tasks. To exit the program, just type ‘bye’. Enjoy:)
 *
 *
 * @author CyberS-N
 * @version 1.0
 * @since March 2022
 */

public class Duke {

    /**
     * This is the main method which does 3 key things to get the program started.
     * 1. It initializes many different formats of possible Date Time configurations which allows flexibility in user input.
     * 2. It loads any previously stored Tasks List into the current program so you can continue from that point forward.
     * 3. It loads the UI class that starts the interaction with the user.
     */

    public static void main (String[]args) throws DukeException, IOException {

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo + "Hi! This mainCom.Duke :)\n" + "Let me load the data first :)\n");
            Keep.main();
            Ui.main();
        }
    }
