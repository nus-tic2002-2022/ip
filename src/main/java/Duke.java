import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.exception.DukeException;
import duke.storage.storage;
import duke.tasklist.*;
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

    private static tasklist TL= new tasklist();
    private static Ui ui = new Ui();


    public static void main(String[] args) throws IOException {
        String divider = "==========================================";
        //ArrayList<Task> TL = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String[] words;
        String command,date = "",time="",description="";
        String filePath = "data/duke.txt";
         ui.printWelcomeMessage();
//        new Print();
        while (in.hasNext()) {
            String reply = in.nextLine();
            words = reply.split(" ");
            command = words[0];
            for (int i=1; i< words.length;i++){
                    description+=" "+words[i];
                if (words[i].startsWith("/")){
                    date=words[i+1];
                    time=words[i+2];
                }
            }
            description=description.split("/")[0];
            int option = 0;
            if (command.equalsIgnoreCase("bye")) {
                ui.printByeMsg(reply);
                break;
            } else if (command.equalsIgnoreCase("list")) {
               ui.printCmdMsg(TL, command);
            } else if (command.equalsIgnoreCase("undo")) {
                //ui.printUndoMsg(TL, command);
                TL.deleteTask(ui.printUndoMsg(TL, command));
                storage.save(filePath, TL);
            }else {
                if (words.length < 2) {
                    System.out.println(DukeException.UnknownCommand);
                } else if (command.toLowerCase().contains("mark") || command.toLowerCase().contains("unmark")) {
                    try {
                        option = Integer.parseInt(words[1]);
                    } catch (NumberFormatException ex) {
                        System.out.println("2nd param has to be number");
                    }
                    if (command.toLowerCase().contains("unmark") && !TL.isEmpty()) {
                        TL.getTask(option - 1).isDone = false;
                        System.out.println(divider);
                        System.out.println("\tOK, I've marked this task as not done yet:\n\t[" + TL.getTask(option - 1).getType() + "] [" + TL.getTask(option - 1).getStatusIcon() + "]" + TL.getTask(option - 1).getDescription());
                        System.out.println(divider);
                    } else if (command.toLowerCase().contains("mark") && !TL.isEmpty()) {
                        TL.getTask(option - 1).isDone = true;
                        System.out.println(divider);
                        System.out.println("\tNice! I've marked this task as done:\n\t[" + TL.getTask(option - 1).getType() + "] [" + TL.getTask(option - 1).getStatusIcon() + "]" + TL.getTask(option - 1).getDescription());
                        System.out.println(divider);
                    } else {
                        System.out.println("There is no Task Currently, wanna add some? :P");
                    }
                } else if (command.equalsIgnoreCase("deadline")) {
                    if(date==""){
                        System.out.println(DukeException.invalidDateCommand);
                    }else {
                        TL.addTask(new Deadline(description, date,time));
                        ui.printCmdMsg(TL, command);
                        storage.save(filePath, TL);
                    }
                } else if (command.equalsIgnoreCase("event")) {
                    TL.addTask(new Event(description,date,time));
                    ui.printCmdMsg( TL, command);
                  storage.save(filePath, TL);

               } else if (command.equalsIgnoreCase("todo")) {
                    TL.addTask(new Todo(description));
                    ui.printCmdMsg(TL, command);
                    storage.save(filePath, TL);
                } else if (command.equalsIgnoreCase("delete")) {
                    option = Integer.parseInt(words[1]);
                    ui.printDeleteMsg( TL, command, (option - 1));
                    TL.deleteTask((option - 1));
                    storage.save(filePath, TL);
                } else {
                    System.out.println(DukeException.invalidInputCommand);
                }
                description="";
            }
        }
    }




}

