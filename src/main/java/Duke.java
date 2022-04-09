import duke.utils.TaskProcessor;
import duke.utils.Ui;
import duke.parser.UserInputParser;

import java.util.Scanner;

//// potential changes
// javadocs for all methods
// one more junit - the save string for the save format!
// more assertions
// work on help message
// find might wanna be case sensitive?

public class Duke {

    //enum for the tasks
    private TaskProcessor tasklist;
    private Ui ui;


    public Duke () {
        tasklist = new TaskProcessor();
        ui = new Ui();
    };

    public void chatting() {
        ui.printIntroduction();

        tasklist.loadTasks();
        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc? Type 'help' for a list of useful commands!\n");
            response = input.nextLine();

            if (response.equals("bye")) {
                ui.printBye();
                tasklist.saveTasks();
                return;
            };

            UserInputParser.processUserInput(tasklist, response); //, tasks, taskListCount
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        //ui.printIntroduction();
        duke.chatting();
        return;
    }
}