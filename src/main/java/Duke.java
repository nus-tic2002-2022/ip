import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
public static void main(String[] args) {
   /* String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);*/
    List<Task> TL = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    System.out.println("======================");
    System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    System.out.println("======================");
    while(in.hasNext()) {
        String reply=in.nextLine();
        if (reply.equalsIgnoreCase("bye")){
            new Print(reply);
            break;
        }
        else if (reply.equalsIgnoreCase("list")){
            new Print((ArrayList<Task>) TL);
        }else {
            new Print(reply);
            TL.add(new Todo(reply));
        }
    }
}


}

