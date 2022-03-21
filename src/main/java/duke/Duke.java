package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.FileTaskList;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;



public class Duke {

    static void checkLineEmpty(String line)throws DukeCheckLineEmptyException {
        if (line.equals("")){
            throw new DukeCheckLineEmptyException();
        }
    }

    static void checkWord(String line)throws DukeCheckLineException {
        String keyword = line.split(" ")[0].toLowerCase();

        if (!keyword.equals("list") && !keyword.equals("bye")
                && !keyword.equals("todo") && !keyword.equals("done")
                && !keyword.equals("event") && !keyword.equals("deadline")
                && !keyword.equals("delete")){
            throw new DukeCheckLineException();
        }
    }

    static void checkDescription(String line)throws DukeException {
        String keyword = line.split(" ")[0].toLowerCase();

        if (keyword.equals("todo") && line.split(" ").length == 1) {
            throw new DukeException();
        }

        if (keyword.equals("event") && line.split(" ").length == 1) {
            throw new DukeException();
        }

        if (keyword.equals("deadline") && line.split(" ").length == 1) {
            throw new DukeException();
        }

        if (keyword.equals("done") && line.split(" ").length == 1){
            throw new DukeException();
        }

        if (keyword.equals("delete") && line.split(" ").length == 1){
            throw new DukeException();
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printWithLine(List.of("Hello from\n" + logo));
        printWithLine(List.of("Hello! I'm Duke", "What can I do for you?"));

        String line;
        Scanner in = new Scanner(System.in);
        List<Task> items = new ArrayList<Task>();

        try {
            String fileList;
            File f = new File("data/tasks.txt");
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                fileList = s.nextLine();
                Task fileListTask = new FileTaskList(fileList);
                items.add(fileListTask);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found, please add new tasks.");
        }

        do {
            line = in.nextLine();
            String keyword = line.split(" ")[0].toLowerCase();
            try {
                checkLineEmpty(line);
                checkWord(line);
                checkDescription(line);

                if (line.toLowerCase().equals("list")) {
                    printWithLine(List.of());
                    List<String> messages = new ArrayList<>();
                    System.out.println("   Here are the tasks in your list: ");
                    for (int i = 0; i < items.size(); i++) {
                        messages.add(i + 1 + "." + items.get(i));
                    }
                    printWithLine(messages);


                } else if (line.split(" ")[0].toLowerCase().equals("done")) {
                    printWithLine(List.of());
                    try {
                        Task markItem = items.get(Integer.parseInt(line.substring(5)) - 1);
                        markItem.markAsDone();
                        printWithLine(List.of("Nice! I've marked this task as done: ", " " + markItem));
                    } catch (NumberFormatException e) {
                        printWithLine((List.of("☹ OOPS!!! This is not a number: " + line.split(" ")[1])));
                    } catch (IndexOutOfBoundsException e) {
                        printWithLine((List.of("☹ OOPS!!! The index out of bound: " + line.split(" ")[1])));
                    }

                } else if (line.split(" ")[0].equalsIgnoreCase("delete")) {
                    printWithLine(List.of());
                    try {
                        Task deleteItem = items.get((Integer.parseInt(line.split(" ")[1]) - 1));
                        items.remove(deleteItem);
                        printWithLine((List.of("Noted. I've removed this task: ", " " + deleteItem, "Now you have " + items.size() + " task in the list. ")));
                    } catch (NumberFormatException e) {
                        printWithLine((List.of("☹ OOPS!!! This is not a number: " + line.split(" ")[1])));
                    } catch (IndexOutOfBoundsException e) {
                        printWithLine((List.of("☹ OOPS!!! The index out of bound: " + line.split(" ")[1])));
                    }

                } else if (line.split(" ")[0].toLowerCase().equals("todo")) {
                    printWithLine(List.of());
                    Task todoTask = new Todo(line.replace(line.split(" ")[0] + " ", ""));
                    items.add(todoTask);
                    printWithLine((List.of("Got it. I've added this task: ", todoTask.toString(), "Now you have " + items.size() + " task in the list. ")));

                } else if (line.split(" ")[0].toLowerCase().equals("deadline")) {
                    printWithLine(List.of());
                    int position = line.indexOf("/");
                    String time = line.split("/")[1].replace("by ", "");
                    Task deadlineTask = new Deadline(line.substring(9, position - 1), time);
                    items.add(deadlineTask);
                    printWithLine((List.of("Got it. I've added this task: ", deadlineTask.toString(), "Now you have " + items.size() + " task in the list. ")));

                } else if (line.split(" ")[0].toLowerCase().equals("event")) {
                    printWithLine(List.of());
                    int position = line.indexOf("/");
                    String time = line.split("/")[1].replace("at ", "");
                    Task eventTask = new Event(line.substring(6, position - 1), time);
                    items.add(eventTask);
                    printWithLine((List.of("Got it. I've added this task: ", eventTask.toString(), "Now you have " + items.size() + " task in the list. ")));

                } else if (line.toLowerCase().equals("bye")) {
                    printWithLine(List.of());
                    printWithLine(List.of("Bye. Hope to see you again soon!"));

                }
            }
            catch (DukeCheckLineException e) {
                printWithLine(List.of());
                printWithLine(List.of("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
            catch (DukeCheckLineEmptyException e) {
                printWithLine(List.of());
                printWithLine(List.of("   ☹ OOPS!!! Please enter somethings."));
            }
            catch (DukeException e) {
                printWithLine(List.of());
                printWithLine((List.of("    ☹ OOPS!!! The description of a " + keyword + " cannot be empty.")));
            }

        }while (!line.equals("bye"));

        FileWriter writer = new FileWriter("data/tasks.txt");
        for(Task taskList: items){
            writer.write(taskList + System.lineSeparator());
        }
        writer.close();
    }

            private static void printWithLine (List < String > messages) {
                for (String message : messages) {
                    System.out.println("   " + message);
                }
                System.out.println("______________________________________________");
            }

    }


