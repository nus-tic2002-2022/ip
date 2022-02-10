import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");

        String[] userInputList = new String[100];
        int numOfInputs = 0;

    while(true) {
        Scanner scanInput = new Scanner(System.in); //Scan user input
        String userInput = scanInput.nextLine(); //Read user input

        //Level 1 Greet, Echo, Exit
        if(userInput.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0); //Exit program
        }

        //Level 2 Add, List
        if(userInput.equals("list")) {
            for (int i = 0; i < numOfInputs; i++) {
                System.out.println(i + 1 + ". " + userInputList[i]);
            }
            continue;
        }
        userInputList[numOfInputs] = userInput;
        numOfInputs++;

        System.out.println("added: " + userInput); //Echo
    }}

}
