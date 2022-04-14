import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.exception.DukeException;
import duke.storage.storage;
import duke.tasklist.Task;
import duke.tasklist.Todo;
import duke.ui.Ui;

public class Duke {

    private static storage storage;

    static {
        try {
            storage = new storage(duke.storage.storage.DEFAULT_STORAGE_FILEPATH);
        } catch (duke.storage.storage.InvalidStorageFilePathException e) {
            e.printStackTrace();
        }
    }

//    private static tasklist TL= new tasklist();
    private static Ui ui = new Ui();


    public static void main(String[] args) throws IOException {
        String divider = "==========================================";
        ArrayList<Task> TL = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String[] words;
        String command;
        String filePath = "data/duke.txt";
         ui.printWelcomeMessage();
//        new Print();
        while (in.hasNext()) {
            String reply = in.nextLine();
            words = reply.split(" ");
            command = words[0];
            int option = 0;
            if (command.equalsIgnoreCase("bye")) {
                ui.printByeMsg(reply);
                break;
            } else if (command.equalsIgnoreCase("list")) {
               ui.printCmdMsg(TL, command);
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
                        System.out.println("\tOK, I've marked this task as not done yet:\n\t[" + TL.get(option - 1).getType() + "] [" + TL.get(option - 1).getStatusIcon() + "]" + TL.get(option - 1).getDescription());
                        System.out.println(divider);
                    } else if (command.toLowerCase().contains("mark") && !TL.isEmpty()) {
                        TL.get(option - 1).isDone = true;
                        System.out.println(divider);
                        System.out.println("\tNice! I've marked this task as done:\n\t[" + TL.get(option - 1).getType() + "] [" + TL.get(option - 1).getStatusIcon() + "]" + TL.get(option - 1).getDescription());
                        System.out.println(divider);
                    } else {
                        System.out.println("There is no Task Currently, wanna add some? :P");
                    }
                } else if (command.equalsIgnoreCase("deadline")|| command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("event")) {
                    TL.add(new Todo(reply));
                    ui.printCmdMsg((ArrayList<Task>) TL, command);
//                    new Print((ArrayList<Task>) TL, command);
                    storage.save(filePath,(ArrayList<Task>) TL);
                } else if (command.equalsIgnoreCase("event")) {
                    TL.add(new Todo(reply));
//                    storage.save(filePath,(ArrayList<Task>) TL);
                    ui.printCmdMsg((ArrayList<Task>) TL, command);
                    ui.printCmdMsg((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("todo")) {
                    TL.add(new Todo(reply));
                    storage.save(filePath,(ArrayList<Task>) TL);
                    ui.printCmdMsg((ArrayList<Task>) TL, command);
//                    new Print((ArrayList<Task>) TL, command);
                } else if (command.equalsIgnoreCase("delete")) {
                    option = Integer.parseInt(words[1]);
                    ui.printDeleteMsg((ArrayList<Task>) TL, command, (option - 1));
//                    new Print(TL, command, (option - 1));
                    TL.remove(option - 1);
                    storage.save(filePath, TL);
                } else {
                    System.out.println(DukeException.invalidInputCommand);
                }
            }
        }
    }




}

