import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws AllException, IOException {
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
        String filePath="data/duke.txt";
        System.out.println("======================");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("======================");
        while (in.hasNext()) {
            String reply = in.nextLine();
            words = reply.split(" ");
            command = words[0];
            int option = 0;
            if (command.equalsIgnoreCase("bye")) {
                new Print(reply);
                break;
            } else if (command.equalsIgnoreCase("list")) {
                new Print((ArrayList<Task>) TL, command);
            } else {
                if (words.length < 2) {
                    System.out.println(DukeException.UnknownCommand);
                } else if (command.toLowerCase().contains("mark") || command.toLowerCase().contains("unmark")) {
                    try {
                        option = Integer.parseInt(words[1]);
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
                    new save(filePath,(ArrayList<Task>) TL);
                } else if (command.equalsIgnoreCase("event")) {
                    TL.add(new Todo(reply));
                    new save(filePath,(ArrayList<Task>) TL);
                    new Print((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("todo")) {
                    TL.add(new Todo(reply));
                    new save(filePath,(ArrayList<Task>) TL);
                    new Print((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("delete")) {
                    option = Integer.parseInt(words[1]);
                    new Print((ArrayList<Task>) TL, command,(option-1));
                    TL.remove(option - 1);
                    new save(filePath,(ArrayList<Task>) TL);
                } else {
                    System.out.println(DukeException.invalidInputCommand);
                }
            }
        }
    }


}

