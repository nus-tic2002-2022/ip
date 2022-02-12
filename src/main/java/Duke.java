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
    String divider="==========================================";
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
        }else if (reply.toLowerCase().contains("mark") || reply.toLowerCase().contains("unmark") ){
            int option = Integer.parseInt(reply.replaceAll("\\D+",""));
            if (reply.toLowerCase().contains("unmark") && !TL.isEmpty()){
                TL.get(option-1).isDone=false;
                System.out.println(divider);
                System.out.println("\tOK, I've marked this task as not done yet:\n\t["+TL.get(option-1).getStatusIcon()+"]"+TL.get(option-1).description);
                System.out.println(divider);
            }
            else if(reply.toLowerCase().contains("mark")&& !TL.isEmpty()){
                TL.get(option-1).isDone=true;
                System.out.println(divider);
                System.out.println("\tNice! I've marked this task as done:\n\t["+TL.get(option-1).getStatusIcon()+"]"+TL.get(option-1).description);
                System.out.println(divider);
            }else{
                System.out.println("There is no Task Currently, wanna add some? :P");
            }
        }else {
            new Print(reply);
            TL.add(new Todo(reply));
        }
    }
}


}

