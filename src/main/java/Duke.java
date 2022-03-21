import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //enum for the tasks
    private static TaskProcessor tasklist;

    public Duke () {
        tasklist = new TaskProcessor();

    };

    public static void chatting() {
        ui.printIntroduction();

        tasklist = new TaskProcessor();
        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();

            if (response.equals("bye")) {
                ui.printBye();
                return;
            };

            Parser.processUserInput(tasklist, response); //, tasks, taskListCount
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        ui.printIntroduction();
        duke.chatting();
        return;
    }
}
