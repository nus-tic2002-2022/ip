
import java.util.Scanner;
import duke.importer.TaskFile;
import duke.task.TaskList;
import duke.utils.Parser;
import duke.utils.Ui;

public class Duke {
    /**
     * Duke in an interactive program that helps the user keep track of tasks.
     * This class creates new instances of the Ui object, TaskList object and TaskFile object.
     *
     * @param fp takes in the location of the file with Tasks and passes it to construct the TaskFile object
     */
    public Duke (String fp) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        TaskFile file = new TaskFile(fp);
    }

    /**
     * Run method continues running until the program is exited.
     * Waits for user input and executes certain instructions.
     * Does not end until the user inputs "bye" or the program exits due to errors.
     */
    public void run (){
        while(true) {
            Scanner scanInput = new Scanner(System.in); //Scan user input
            String userInput = scanInput.nextLine().trim(); //Read user input
            Parser.parse(userInput);
        }
    }

    /**
     * Main function of the program.
     * The input file "./data/tasks.txt" is an optional argument when executing Duke.
     * If the file does not exist or cannot be found Duke will help the user create one.
     * This essentially creates Duke, it starts interacting with the user.
     *
     * @param args takes in a directory path where a task file is located
     * @see Duke#Duke(String fp)
     * @see Duke#run()
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
