import java.util.Scanner;
import java.util.ArrayList;

////potential changes
//make tasks abstract class
//updates - file path string should be initialized rather than hardcoded?
//catch error when directory is not present when trying to initialize text file
//obviously something is wrong with the taskprocessor file path... should it be with constructor or as method?
//last stopped - text file parser! maybe make parser an abstract, then text file, user input, time parser etc

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
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();

            if (response.equals("bye")) {
                ui.printBye();
                tasklist.saveTasks();
                return;
            };

            Parser.processUserInput(tasklist, response); //, tasks, taskListCount
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        //ui.printIntroduction();
        duke.chatting();
        return;
    }
}
