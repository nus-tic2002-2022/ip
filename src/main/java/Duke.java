import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\n" + "What can I do for you?");

       new TaskList();

        try{
            TaskFile.loadFile();
        } catch (FileNotFoundException f) {
           try {
               TaskFile.newFile();
           } catch (IOException i) {

           }
        }

        while(true) {
            Scanner scanInput = new Scanner(System.in); //Scan user input
            String userInput = scanInput.nextLine().trim(); //Read user input
            Parser.parse(userInput);
        }
    }

}
