import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void showTaskList(ArrayList<String> taskList){
        int counter = 1;

        for (int i = 0; i < taskList.size(); i++) {

            System.out.println("    " + counter + ". " + taskList.get(i));
            counter++;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String userInput = "notBye";
        ArrayList<String> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    What up! I'm D. Spawn");
        System.out.println("    Something need doing?");
        System.out.println("    ____________________________________________________________");

        do {
            userInput = in.nextLine();
            System.out.println("    ____________________________________________________________");

            switch (userInput) {
                case "list": showTaskList(taskList);
                    break;

                default: taskList.add(userInput);
                    System.out.println("    added: " + userInput); //do stuff here after input
                    break;
            }
            System.out.println("    ____________________________________________________________");
        } while (!userInput.equals("bye"));


        System.out.println("    Ciao!");
        System.out.println("    ____________________________________________________________");
    }
}
