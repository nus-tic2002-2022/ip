package zhixuan.duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.data.task.Deadline;
import zhixuan.duke.data.task.Event;
import zhixuan.duke.data.task.Task;
import zhixuan.duke.data.task.Todo;
import zhixuan.duke.ui.DukeUI;

public class Duke {

    private DukeUI ui;
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void bye() {
        System.out.println("Bye bye!");
        System.exit(0);
    }

    private static void list() {
        if (!taskList.isEmpty()) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
        }
        else {
            System.out.println("No tasks in your list.");
        }
    }

    private static void mark(String input, int taskIndex){

        String markTask = input.substring(0, input.indexOf(' ')).trim();
        boolean success;
        try {
            if (taskIndex<1 || taskList.size()<taskIndex) throw new UnknownCommandException("Selected task to mark is invalid.");
            taskIndex--;
            if (markTask.equals("mark")) {
                success = taskList.get(taskIndex).markAsDone();
                if (success) {
                    System.out.println("Nice! I've marked this task as done: ");
                } else {
                    System.out.println("Task is already marked as done: ");
                }
                System.out.println(taskList.get(taskIndex));
            }
            else if (markTask.equals("unmark")) {
                success = taskList.get(taskIndex).markAsUndone();
                if (success) {
                    System.out.println("OK, I've marked this task as not done yet: ");
                } else {
                    System.out.println("Task is already marked as not done: ");
                }
                System.out.println(taskList.get(taskIndex));
            }
        }
        catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void addItem(String type, String input){
        try {
            if (type.equals("todo")) {
                if (input.isEmpty()) throw new InvalidTaskException("Todo cannot be empty.");
                taskList.add(new Todo(input));
            }
            else if (type.equals("event")){
                if (!input.contains("/at")) throw new InvalidTaskException("Use the correct command (event DESCRIPTION /at EVENT_DATE).");
                String[] task = input.split("/at");
                if (task[0].isEmpty()) throw new InvalidTaskException("Include description (event DESCRIPTION /at EVENT_DATE).");
                if (task.length != 2) throw new InvalidTaskException("Include description and event date (event DESCRIPTION /at EVENT_DATE).");
                taskList.add(new Event(task[0].trim(), task[1].trim()));
            }
            else if (type.equals("deadline")){
                if (!input.contains("/by")) throw new InvalidTaskException("Use the correct command (deadline DESCRIPTION /by DEADLINE).");
                String[] task = input.split("/by");
                if (task[0].isEmpty()) throw new InvalidTaskException("Include description (deadline DESCRIPTION /by DEADLINE).");
                if (task.length != 2) throw new InvalidTaskException("Include deadline (deadline DESCRIPTION /by DEADLINE).");
                taskList.add(new Deadline(task[0].trim(), task[1].trim()));
            }
            System.out.println("Added this task: " + taskList.get(taskList.size()-1));
            System.out.println("You have " + (taskList.size()) + " tasks in your list.");
        }
        catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteItem(int taskIndex){
        try {
            if (taskIndex<1 || taskList.size()<taskIndex) throw new UnknownCommandException("Selected task to delete is invalid.");
            taskIndex--;

            System.out.println("Deleted this task: " + taskList.get(taskIndex));
            System.out.println("You have " + (taskList.size()-1) + " tasks in your list.");
            taskList.remove(taskIndex);
        }
        catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String input) throws UnknownCommandException {

        if (input.startsWith("bye")) {
            bye();
        }
        else if (input.startsWith("list")) {
            list();
        }
        else if (input.startsWith("todo")) {
            addItem("todo", input.replaceFirst("todo", "").trim());
            saveFile();
        }
        else if (input.startsWith("event")) {
            addItem("event", input.replaceFirst("event", "").trim());
            saveFile();
        }
        else if (input.startsWith("deadline")) {
            addItem("deadline", input.replaceFirst("deadline", "").trim());
            saveFile();
        }
        else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String index = input.replaceAll("\\D+","");
            if (index.isEmpty()) throw new UnknownCommandException("Invalid task input. Use an integer.");
            mark(input, Integer.parseInt(index));
            saveFile();
        }
        else if (input.startsWith("delete")) {
            String index = input.replaceAll("\\D+","");
            if (index.isEmpty()) throw new UnknownCommandException("Invalid task input. Use an integer.");
            deleteItem(Integer.parseInt(index));
            saveFile();
        }
        else {
            throw new UnknownCommandException();
        }
    }

    private static String createDirectory() {

        String directoryName = System.getProperty("user.dir") + File.separator + "user-files";
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
        }
        return directoryName;
    }

    private static void saveFile() {

        File fileName = new File(createDirectory() + File.separator + "list.txt");

        try {
            FileWriter fw = new FileWriter(fileName);
            Writer output = new BufferedWriter(fw);
            for (Task task : taskList) {
                output.write(task.toFile() + "\n");
            }
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadFile() {

        File fileName = new File(createDirectory() + File.separator + "list.txt");
        String line;

        try {
            if (fileName.length() > 0) { //length returns 0 if file doesn't exist or if it's empty
                BufferedReader input = new BufferedReader(new FileReader(fileName));
                if (!input.ready()) {
                    throw new IOException();
                }
                while ((line = input.readLine()) != null) {
                    loadItem(line);
                }
                input.close();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void loadItem(String input) {

        //split takes a regular expression and | is a special character (means 'or').
        // requires additional \\ in regex since \ is Java's escape character in a string.
        // Java understands the string like "\|", and the regex then understands it like "|"

        if (input.startsWith("T")) {
            String[] task = input.split("\\|");
            taskList.add(new Todo(task[2].trim(), Boolean.parseBoolean((task[1].trim()))));
        }
        else if (input.startsWith("E")) {
            String[] task = input.split("\\|");
            taskList.add(new Event(task[2].trim(), Boolean.parseBoolean((task[1].trim())), task[3].trim()));
        }
        else if (input.startsWith("D")) {
            String[] task = input.split("\\|");
            taskList.add(new Deadline(task[2].trim(), Boolean.parseBoolean((task[1].trim())), task[3].trim()));
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke\n" + "What can I do for you?");

        loadFile();
        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            input = in.nextLine();

            try {
                processInput(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
