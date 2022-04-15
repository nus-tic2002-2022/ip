package duke.ui;
import duke.tasklist.Deadline;
import duke.tasklist.Task;
import duke.tasklist.tasklist;

import java.util.ArrayList;


public class Ui {
    private String divider = "==========================================";
    public void printWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String divider = "==========================================";
        System.out.println("======================");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("======================");
    }
    public void printByeMsg(String msg){
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

    public void printCmdMsg(tasklist TL, String command){
        int latest = TL.getSize() - 1;
        if (TL.getSize() == 0) {
            System.out.println("There is no Task Currently, wanna add some? :P");
        } else if (command.equalsIgnoreCase("todo")  ) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println(TL.getTask(latest).toString());
//            System.out.println("[" + TL.getTask(latest).getType() + "] [" + TL.getTask(latest).getStatusIcon() + "]" + TL.getTask(latest).getDescription());
            System.out.println("Now you have " + TL.getSize() + " tasks in the list.");
            System.out.println(divider);
        } else if (command.equalsIgnoreCase("deadline")) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println(TL.getTask(latest).toString());
            //System.out.println("[" + TL.getTask(latest).getType() + "] [" + TL.getTask(latest).getStatusIcon() + "]" + TL.getTask(latest).getDescription());
            System.out.println("Now you have " + TL.getSize() + " tasks in the list.");
            System.out.println(divider);
       } else if (command.equalsIgnoreCase("event")) {
            System.out.println(divider + "\n Got it. I've added this task:");
            System.out.println(TL.getTask(latest).toString());
            //System.out.println("[" + TL.get(latest).getType() + "] [" + TL.get(latest).getStatusIcon() + "]" + TL.get(latest).description);
            System.out.println("Now you have " + TL.getSize() + " tasks in the list.");
            System.out.println(divider);
        } else if (command.equalsIgnoreCase("list")) {
            System.out.println(divider + "\n Here are the tasks in your list:");
            for (int i = 0; i < TL.getSize(); i++) {
                System.out.println((i + 1) + ". [" + TL.getTask(i).getType() + "] [" + TL.getTask(i).getStatusIcon() + "]" + TL.getTask(i).getDescription());
            }
            System.out.println(divider);
        }
    }

    public void printDeleteMsg(tasklist TL, String command, int position) {
        if (command.equalsIgnoreCase("Delete")) {
            System.out.println(divider + "\n Noted. I've removed this task:");
            System.out.println("[" + TL.getTask(position).getType() + "] [" + TL.getTask(position).getStatusIcon() + "]" + TL.getTask(position).getDescription());
            System.out.println("Now you have " + (TL.getSize() - 1) + " tasks in the list.");
            System.out.println(divider);
        }
    }
    public int printUndoMsg(tasklist TL, String command) {
        int latest=0;
        if (TL.getSize() == 0) {
            System.out.println("There is no Task Currently, wanna add some? :P");
        }else{
            if (command.equalsIgnoreCase("undo")) {
                latest=TL.getSize()-1;
                System.out.println(divider + "\n Noted. I've undo this task:");
               // System.out.println("[" + TL.getTask(latest).getType() + "] [" + TL.getTask(latest).getStatusIcon() + "]" + TL.getTask(latest).getDescription());
                System.out.println("Now you have " + (TL.getSize() - 1) + " tasks in the list.");
                System.out.println(divider);
            }
        }

        return latest;
    }
}
