import java.util.ArrayList;
import java.util.List;

public class Print {
    String divider = "==========================================";

    public Print() {

    }

    public Print(String msg) {


        if (msg.equalsIgnoreCase("bye")) {
            System.out.println("\t================================");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t================================");
        } else {
            System.out.println("\t" + divider);
            System.out.println("\tadded:" + msg);
            System.out.println("\t" + divider);
        }
    }

    public Print(ArrayList<Task> TL, String command) {
        int latest = TL.size() - 1;
        if (TL.size() == 0) {
            System.out.println("There is no Task Currently, wanna add some? :P");
        } else if (command.equalsIgnoreCase("todo")) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println("[" + TL.get(latest).getType() + "] [" + TL.get(latest).getStatusIcon() + "]" + TL.get(latest).description);
            System.out.println("Now you have " + TL.size() + " tasks in the list.");
            System.out.println(divider);
        } else if (command.equalsIgnoreCase("deadline")) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println("[" + TL.get(latest).getType() + "] [" + TL.get(latest).getStatusIcon() + "]" + TL.get(latest).description);
            System.out.println("Now you have " + TL.size() + " tasks in the list.");
            System.out.println(divider);
        } else if (command.equalsIgnoreCase("event")) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println("[" + TL.get(latest).getType() + "] [" + TL.get(latest).getStatusIcon() + "]" + TL.get(latest).description);
            System.out.println("Now you have " + TL.size() + " tasks in the list.");
            System.out.println(divider);
        } else if (command.equalsIgnoreCase("list")) {
            System.out.println(divider + "\n Here are the tasks in your list:");
            for (int i = 0; i < TL.size(); i++) {
                System.out.println((i + 1) + ". [" + TL.get(i).getType() + "] [" + TL.get(i).getStatusIcon() + "]" + TL.get(i).description);
            }
            System.out.println(divider);
        }

    }

    public Print(ArrayList<Task> TL, String command, int position) {
        if (command.equalsIgnoreCase("Delete")) {
            System.out.println(divider + "\n Noted. I've removed this task:");
            System.out.println("[" + TL.get(position).getType() + "] [" + TL.get(position).getStatusIcon() + "]" + TL.get(position).description);
            System.out.println("Now you have " + (TL.size()-1) + " tasks in the list.");
            System.out.println(divider);
        }
    }
}

