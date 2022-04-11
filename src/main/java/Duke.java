import duke.utils.TaskProcessor;
import duke.utils.Ui;
import duke.parser.UserInputParser;

import java.util.Scanner;

//// potential changes
// documentation
// sequence diagram, object diagram, class diagram
// remove inport *
// remove all comments
// javafx - priority for this week
// sysout all to Ui? (if no time nvm)
// find might wanna be case sensitive? (if no time nvm)
// try to not use class level variables (if no time nvm)


public class Duke {

    private TaskProcessor tasklist;
    private Ui ui;
    private UserInputParser uiParser;


    public Duke () {
        tasklist = new TaskProcessor();
        ui = new Ui();
        uiParser = new UserInputParser();
    };

    /**
     * The main function of Duke
     */
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

            uiParser.processUserInput(tasklist, response); //, tasks, taskListCount
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chatting();
        return;
    }
}
