import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static int counter = 0;
    public static boolean test = true;
    public static ArrayList<Task> task = new ArrayList<>();

    private static void writeFile(String filePath) throws IOException {
        FileWriter wf = new FileWriter(filePath);
        for (Task l : task) {
            wf.write(l + System.lineSeparator());
        }
        wf.close();
    }

    private static void printFile(String filePath) throws DukeException , IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        if (!scanner.hasNext()) {
            System.out.println("No existing data is found");
        }
        while (scanner.hasNext()) {
            String current = scanner.nextLine();
            System.out.println(current);
            if (current.contains("[T]")) {
                String description = current.substring(7);
                task.add(new Todo(description));
                if (current.contains("\u2713")) {
                    task.get(counter).setStatus(true);
                }
            }
            else if (current.contains("[D]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String by = current.substring(m+5,n);
                task.add(new Deadline(description,by));
                if (current.contains("\u2713")) {
                    task.get(counter).setStatus(true);
                }
            }
            else if (current.contains("[E]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String at = current.substring(m+5,n);
                task.add(new Event(description,at));
                if (current.contains("\u2713")) {
                    task.get(counter).setStatus(true);
                }
            }
            else {
                throw new DukeException("OOPS!!! Task in existing data is incompatible\n");
            }
            counter++;
        }
    }

    public static void handle(String t) throws DukeException {
        if (t.contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            int i = t.toLowerCase().indexOf("todo");
            String description = t.substring(i+4).trim();
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Todo(description));
        }
        else if (t.contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }
            int num2 = t.toLowerCase().indexOf("deadline");
            int num = t.indexOf('/');
            String description = t.substring(9, num-1);
            String by = t.substring(num+4);
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Deadline(description, by));
        }
        else if (t.contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("OOPS!!! The description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("OOPS!!! Please specify time.\n");
            }
            int num2 = t.toLowerCase().indexOf("event");
            int num = t.indexOf('/');
            String description = t.substring(num2+5, num).trim();
            String what = t.substring(num+3).trim();
            for (Task l : task) {
                if (l.description.equals(description)) {
                    throw new DukeException("OOPS!!! The task has already been added previously\n");
                }
            }
            task.add(new Event(description, what));
        }
        else {
            throw new DukeException("OOPS!!! Please enter a valid task such as todo / deadline / event\n");
        }
    }

    public static void echo() throws DukeException{
        Scanner inPut = new Scanner(System.in);
        String line = inPut.nextLine();

        if (line.trim().equalsIgnoreCase("bye")) {
            System.out.println("Bye. See you soon!");
            System.exit(0);
        }
        else if (line.trim().equalsIgnoreCase("list")) {
            if(counter == 0){
                throw new DukeException("Currently no items in the list\n");
            }
            System.out.println("Here is the task list:\n");
            int sub = 1;
            for (Task l : task) {
                System.out.println(sub + ". " + l);
                sub++;
            }
            test = false;
        }
        else if (line.toLowerCase().contains("done")) {
            int m = line.toLowerCase().indexOf("done");
            String text = line.substring(m+4).trim();
            if(text.length() < 1){
                throw new DukeException("OOPS!!! Please enter a done task \n");
            }
            int n = Integer.parseInt(text);
            if (n > counter) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            if (task.get(n-1).getStatusIcon().equals("\u2713")) {
                throw new DukeException("Error: Task has already been completed\n");
            }
            else {
                System.out.println("Nice! I've marked this task as done:");
                task.get(n-1).setStatus(true);
                System.out.println(task.get(n-1) + "\n");
            }
        }
        else if (line.toLowerCase().contains("delete")) {
            int m = line.toLowerCase().indexOf("delete");
            String num = line.substring(m+6).trim();
            if (num.length() < 1) {
                throw new DukeException("Error: Please enter which task to be deleted\n");
            }
            int n = Integer.parseInt(num);
            if (n > counter) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(task.get(n-1));
                task.remove(n-1);
                counter--;
                System.out.println("Now you have " + counter + " tasks in the list.\n");
            }
        }
        else {
            handle(line);
            System.out.println("Task added:");
            System.out.println(task.get(counter));
            counter++;
            System.out.println("Total " + counter + " tasks in the list.\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "Let me load the existing data for you (if any)\n");
        String FileLocation = "data/duke.txt";
        String Directory = "./data/";
        try {
            Path path = Paths.get(Directory);
            Files.createDirectories(path);
            printFile(FileLocation);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            test = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("What would you like to do ?");
        while(true) {
            try {
                echo(); 
                if (test) {
                    writeFile(FileLocation);
                }
                test = true;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                test = true;
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}