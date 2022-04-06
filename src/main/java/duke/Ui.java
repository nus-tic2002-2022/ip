package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printWithLine(List.of("Hello from\n" + logo));
        printWithLine(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    private Scanner in = new Scanner (System.in);


    boolean hasNextLine(){
        return in.hasNextLine();
    }


    String readCommand(){
        return in.nextLine();
    }

    public void showLoadingError(String message){
        printWithLine(List.of());
        printWithLine(List.of(message));
    }


    public void showError(String message){
        printWithLine(List.of(message));
    }


    public static void printCommand(List<String>messages){
        for (String message : messages){
            printWithLine(List.of(message));
        }
    }

    public static void printWithLine(List<String> messages){
        for (String message : messages){
            System.out.println("   " + message);
        }
        System.out.println("   _____________________________________");
    }
}
