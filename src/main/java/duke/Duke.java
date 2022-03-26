package duke;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.lang.StringIndexOutOfBoundsException;

public class Duke {

   private final static String fileName = "data\\duke.txt";

    private Storage storage;
    private TaskList list;
    private Ui ui;

    private int index;

    public Duke(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            storage.readFile();
            TaskList.printListLength();
        } catch (IOException e) {
            Ui.printFileErrorMsg();
        }
    }

    public void run(){

        int status = 0;

        Ui.printHello();

        do {

            Ui.printPrompt();

            try {

                String line = Ui.getLine();

                if (line.toLowerCase().equals("bye")) {
                    Ui.printBye();
                    status = 1; // can escape from loop
                } else if (line.toLowerCase().equals("help")) {
                    Ui.printHelp();
                } else if (line.toLowerCase().equals("hello")) {
                    Ui.printHello();
                } else if (line.toLowerCase().equals("list")) { //Print list
                    TaskList.printList();
                    TaskList.printListLength();
                } else if (line.startsWith("todo ")) {
                    if (line.substring(4, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addTodo(line.substring(5, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength()-1);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("deadline ")) {
                    if (line.substring(9, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addDeadline(line.substring(9, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength()-1);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("event ")) {
                    if (line.substring(6, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    TaskList.addEvent(line.substring(6, line.length()));
                    Ui.printAddTaskMsg();
                    TaskList.printOneTask(TaskList.getListLength()-1);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("mark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    if(TaskList.checkDone(index)) {
                        Ui.printMarkDoneBeforeMsg();
                        TaskList.printOneTask(index);
                    } else {
                        TaskList.markDone(index);
                        Ui.printMarkDoneMsg();
                        TaskList.printOneTask(index);
                    }
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("unmark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    TaskList.markNotDone(index);
                    Ui.printMarkNotDoneMsg();
                    TaskList.printOneTask(index);
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("delete ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("delete ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        Ui.printOutOfRangeMsg();
                        continue;
                    }
                    Ui.printDeleteMsg();
                    TaskList.printOneTask(index);
                    TaskList.deleteTask(index);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.toLowerCase().equals("sort")) {
                    Ui.printSortMsg();
                    TaskList.sortList();
                    TaskList.printList();
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                } else if (line.startsWith("find ")) {
                    if (line.substring(4, line.length()).trim().length() < 1) {
                        Ui.printBlankDescMsg();
                        continue;
                    }
                    Ui.printFindMsg();
                    TaskList.findTask(line.substring(5, line.length()));
                    continue;
                } else {
                    Ui.printUnknownCommandMsg();
                    Ui.printHelp();
                }
            } catch(IOException e) {
                Ui.printFileErrorMsg();
            } catch(DateTimeParseException e) {
                Ui.printDateFormatErrorMsg();
            } catch(StringIndexOutOfBoundsException e) {
                Ui.printShortDescErrorMsg();
            }

        } while (status == 0);

    }

    public static void main(String[] args){
        new Duke(fileName).run();
    }

}
