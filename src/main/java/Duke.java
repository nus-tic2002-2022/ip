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
        String [] word = new String[100];
        int index = 0;

        do{
            line = in.nextLine();
            printWithLine(List.of());
            printWithLine(List.of(line));
            if (line.equals("list")){
                printWithLine(List.of());
                int k = 1;
                for (int i = 0; i < index; i++){
                    System.out.println("     " + k + ". " + word[i]);
                    k++;
                }
                printWithLine(List.of());
            }else if (!line.equals("bye")){
                word[index] = line;
                index++;
                printWithLine(List.of());
                printWithLine(List.of("added: " + line));
            }
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



