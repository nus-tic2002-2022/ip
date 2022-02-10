import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");

    while(true) {
        Scanner scanInput = new Scanner(System.in); //Scan user input
        String userInput = scanInput.nextLine(); //Read user input

        //Level 1 Greet, Echo, Exit
        if(userInput.equals("bye")){
            System.out.print("Bye. Hope to see you again soon!");
            System.exit(0); //Exit program
        }
        System.out.println(userInput); //Echo
    }}

}
