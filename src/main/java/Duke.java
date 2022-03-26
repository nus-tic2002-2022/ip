import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws AllException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String divider = "==========================================";
        List<Task> TL = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String[] words;
        String command;
        System.out.println("======================");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("======================");
        while (in.hasNext()) {
            String reply = in.nextLine();
            words = reply.split(" ");
            command = words[0];
            if (command.equalsIgnoreCase("bye")) {
                new Print(reply);
                break;
            } else {
                if (words.length < 2) {
                    System.out.println(DukeException.UnknownCommand);
                } else if (command.equalsIgnoreCase("list")) {
                    new Print((ArrayList<Task>) TL, command);
                } else if (command.toLowerCase().contains("mark") || command.toLowerCase().contains("unmark")) {
                    int option = 0;
                    try {
                        option = Integer.parseInt(reply.replaceAll("\\D+", ""));
                    } catch (NumberFormatException ex) {
                        System.out.println("2nd param has to be number");
                    }
                    if (command.toLowerCase().contains("unmark") && !TL.isEmpty()) {
                        TL.get(option - 1).isDone = false;
                        System.out.println(divider);
                        System.out.println("\tOK, I've marked this task as not done yet:\n\t[" + TL.get(option - 1).getType() + "] [" + TL.get(option - 1).getStatusIcon() + "]" + TL.get(option - 1).description);
                        System.out.println(divider);
                    } else if (command.toLowerCase().contains("mark") && !TL.isEmpty()) {
                        TL.get(option - 1).isDone = true;
                        System.out.println(divider);
                        System.out.println("\tNice! I've marked this task as done:\n\t[" + TL.get(option - 1).getType() + "] [" + TL.get(option - 1).getStatusIcon() + "]" + TL.get(option - 1).description);
                        System.out.println(divider);
                    } else {
                        System.out.println("There is no Task Currently, wanna add some? :P");
                    }
                } else if (command.equalsIgnoreCase("deadline")) {
                    TL.add(new Todo(reply));
                    new Print((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("event")) {
                    TL.add(new Todo(reply));
                    new Print((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("todo")) {
                    TL.add(new Todo(reply));
                    new Print((ArrayList<Task>) TL, command);
                } else {
                    System.out.println(DukeException.invalidInputCommand);
                }
            }
        }
    }


}

