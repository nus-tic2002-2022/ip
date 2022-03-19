import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    //enum for the tasks

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int taskListCount = 0;

    public static void printList() {
        ui.printListUI(tasks , taskListCount);
    }
    



    public static void chatting() {
        ui.printIntroduction();

        Scanner input = new Scanner(System.in);
        String response;

        while(true) {
            System.out.println("\tWhat's up doc?");
            response = input.nextLine();

            if (response.equals("bye")) {
                ui.printBye();
                return;
            };
            Parser.processUserInput(response);

        }
    }
    
    public static void main(String[] args) {
        ui.printIntroduction();
        chatting();
        return;
    }
}
