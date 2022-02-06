import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWithLine(List.of("Hello! I'm Duke", "What can I do for you?"));

        String line;
        Scanner in = new Scanner(System.in);

        do{
            line = in.nextLine();
            printWithLine(List.of());
            printWithLine(List.of(line));

        }while(!line.equals("bye"));

        printWithLine(List.of("Bye. Hope to see you again soon!"));

    }

    private static void printWithLine(List<String> messages){
        for (String message : messages){
            System.out.println("   " + message);
        }
        System.out.println("______________________________________________");
    }


}



