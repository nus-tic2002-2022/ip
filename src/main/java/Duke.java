import java.util.*;

public class Duke {

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);
        int status = 0;
        //String[] list = new String[100];
        //int counter = 0;
        Task task;
        Todo todo = new Todo() ;


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        do {

            System.out.print("What can I do for you? ");

            line = in.nextLine();

            if(line.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                status = 1; // can escape
            } if(line.toLowerCase().equals("list")){
                //Print list
                todo.getTodoList();
                continue;
            } if(line.toLowerCase().startsWith("mark ")) {
                int index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5,line.length()));
                todo.markAsDone(index);
                continue;
            } if(line.toLowerCase().startsWith("unmark ")) {
                int index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7,line.length()));
                todo.markAsNotDone(index);
                continue;
            }
            else {
                //list[counter] = line;
                todo.addTask(line);
                System.out.println("added: " + line);
            }

        } while (status == 0);

    }
}
