import java.util.Scanner;

public class Duke {

        public static void main (String[]args){
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello I am Taro the Duke");
            System.out.println("What can I do for you today?");

            new TaskList();

            while(true) {
                Scanner scanInput = new Scanner(System.in); //Scan user input
                String userInput = scanInput.nextLine(); //Read user input
                Parser.parse(userInput);
            }
        }

    }

