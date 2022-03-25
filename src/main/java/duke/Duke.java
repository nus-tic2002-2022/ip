package duke;

import java.io.IOException;

public class Duke {

    //private static ArrayList<Task> list = new ArrayList<>(); // ArrayList of Tasks
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
            //ui.showLoadingError();
            System.out.println(e.getMessage());
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
                } else if (line.toLowerCase().equals("commands")) {
                    Ui.printCommand();
                } else if (line.toLowerCase().equals("hello")) {
                    Ui.printHello();
                } else if (line.toLowerCase().equals("list")) { //Print list
                    TaskList.printList();
                    TaskList.printListLength();
                } else if (line.startsWith("todo ")) {
                    if (line.substring(5, line.length()).trim().length() < 1) {
                        System.out.println("Sorry, description cannot be blank. type 'todo <add task here>' (e.g. todo read a book)");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.addTodo(line.substring(5, line.length()));
                    TaskList.printLastTask(TaskList.getListLength()-1);
                    TaskList.printListLength();
                    //list.add(new Todo(line.substring(5, line.length())));
                    // list.get(list.size() - 1).printTask();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("deadline ")) {
                    if (line.substring(9, line.length()).trim().length() < 1) {
                        System.out.println("Sorry, description cannot be blank. type 'deadline <add task here> /by <add deadline>' (e.g. deadline submit duke project /by 11 Apr 2022 2359)");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.addDeadline(line.substring(9, line.length()));
                    TaskList.printListLength();
                    //list.add(new Deadline(line.substring(9, line.length())));
                    //list.get(list.size() - 1).printTask(); // print newly added tasks
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("event ")) {
                    if (line.substring(6, line.length()).trim().length() < 1) {
                        System.out.println("Sorry, description cannot be blank. type 'event <add task here> /at <add event timing>' (e.g. event attend TIC2002 class /at 2 March 2022 7pm)");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.addEvent(line.substring(6, line.length()));
                    TaskList.printListLength();
                    //list.add(new Event(line.substring(6, line.length())));
                    //list.get(list.size() - 1).printTask(); // print newly added tasks
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("mark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        System.out.println("Sorry, you have chosen the item number you choose to mark is out of range. type 'mark <add number that is within the list here>'.");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.markDone(index);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("unmark ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        System.out.println("Sorry, you have chosen the item number you choose to unmark is out of range. type 'unmark <add number that is within the list here>'.");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.markNotDone(index);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else if (line.startsWith("delete ")) {
                    index = Integer.parseInt(line.substring(line.indexOf("delete ") + 7, line.length())) - 1;
                    if (index >= TaskList.getListLength()) {
                        System.out.println("Sorry, you have chosen the item number you choose to delete is out of range. type 'delete <add number that is within the list here>'.");
                        continue;
                        //throw new IllegalCommandException();
                    }
                    TaskList.deleteTask(index);
                    TaskList.printListLength();
                    storage.writeFile(list); // save file
                    continue;
                } else {
                    System.out.print("Sorry, I don't understand. ");
                    Ui.printCommand();
                    //throw new IllegalCommandException();
                }
            } catch(IOException e) {
                    System.out.println(e.getMessage());
            }

        } while (status == 0);


    }

    public static void main(String[] args){
        new Duke(fileName).run();
    }

}
