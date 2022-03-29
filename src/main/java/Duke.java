import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    //Code Starts here!
    //Converting to ArrayList
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int counter = 0;

    //Aesthetic Function
    public static void printLine() {
        System.out.println("__________________________________________________________");
    }

    public static void system() {
        String line;
        Scanner in = new Scanner(System.in);

        //Keep the System Running
        while (true) {

            //Ask User for Input
            line = in.nextLine();

            //Print the List
            if (line.toLowerCase().contains("list")) {
                printLine();
                System.out.println("Here are the task in your lists:");
                for (int x = 0; x < tasks.size(); x++) {
                    //Amended to Use toString()
                    System.out.println(x + 1 + ". " + tasks.get(x).toString());
                }
                printLine();
            }

            //UnMark the Box
            else if (line.toLowerCase().contains("unmark")) {
                try {
                    // -1 as Array starts from 0
                    int num2 = Integer.parseInt(line.substring(7)) - 1;
                    Task um = tasks.get(num2);
                    um.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + um.getStatusIcon() + "] " + um.description);
                    saveFile("data/duke.txt");

                } catch (NullPointerException e) {
                    System.out.println("☹ OOPS!!! Please enter a valid task number.");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Mark the Box
            else if (line.toLowerCase().contains("mark")) {
                try {
                    // -1 as Array starts from 0
                    int num = Integer.parseInt(line.substring(5)) - 1;
                    Task m = tasks.get(num);
                    m.markAsDone();
                    System.out.println("Nice! I've marked this task as done");
                    System.out.println("[" + m.getStatusIcon() + "] " + m.description);
                    saveFile("data/duke.txt");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please enter a valid task number.");

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Run Deadline Class
            else if (line.toLowerCase().contains("deadline")) {

                String[] lineSplit = line.split(" ");

                try {
                    if (lineSplit.length < 2) {
                        throw new DukeException("Missing Deadline Description");
                    }

                    if (!line.contains("/by")) {
                        throw new DukeException("Missing Deadline /by");
                    }

                    //Find Position of '/'
                    int slash = line.indexOf("/");

                    //Create Date and Time Variables
                    DateTimeFormatter Standard = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String UserInput = line.substring(slash+4);
                    LocalDateTime UserInputDT = LocalDateTime.parse(UserInput, Standard);

                    String Format = UserInputDT.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));

                    //Initiate Deadline Class
                    tasks.add(new Deadline(line.substring(9, slash - 1), Format));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
                    saveFile("data/duke.txt");

                } catch (DukeException e) {

                    if (e.getError().equals("Missing Deadline Description")) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    if (e.getError().equals("Missing Deadline /by")) {
                        System.out.println("☹ OOPS!!! The /by of a deadline is missing.");
                    }

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description after /by cannot be missing.");

                } catch (IOException e) {
                    System.out.println(e.getMessage());

                } catch (DateTimeParseException e) {
                    System.out.println("☹ OOPS!!! Please enter the date and time in this format: DD/MM/YYYY HHmm " +
                            "e.g 22/03/2022 2359");
                }
            }

            //Run Tod0 Class
            else if (line.toLowerCase().contains("todo")) {

                String[] lineSplit = line.split(" ");

                //Catch Error for T0do Class
                try {
                    if (lineSplit.length < 2) {
                        throw new DukeException("Missing Todo Description");
                    }

                    //Initiate Tod0 Class
                    tasks.add(new Todo(line.substring(5)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile("data/duke.txt");

                } catch (DukeException e) {
                    if (e.getError().equals("Missing Todo Description")) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            //Run Event Class
            else if (line.toLowerCase().contains("event")) {

                String[] lineSplit = line.split(" ");

                try {
                    if (lineSplit.length < 2) {
                        throw new DukeException("Missing Event Description");
                    }

                    if (!line.contains("/at")) {
                        throw new DukeException("Missing Event /at");
                    }

                    //Find Position of '/'
                    int slash = line.indexOf("/");

                    //Create Date and Time Variables
                    DateTimeFormatter Standard = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String UserInput = line.substring(slash+4);
                    LocalDateTime UserInputDT = LocalDateTime.parse(UserInput, Standard);

                    String Format = UserInputDT.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"));

                    //Initiate Event Class
                    tasks.add(new Event(line.substring(6, slash - 1), Format));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile("data/duke.txt");

                } catch (DukeException e) {
                    if (e.getError().equals("Missing Event Description")) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }

                    if (e.getError().equals("Missing Event /at")) {
                        System.out.println("☹ OOPS!!! The /at of an event is missing.");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description after /at cannot be empty.");

                } catch (IOException e) {
                    System.out.println(e.getMessage());

                } catch (DateTimeParseException e) {
                    System.out.println("☹ OOPS!!! Please enter the date and time in this format: DD/MM/YYYY HHmm " +
                            "e.g 22/03/2022 2359");
                }
            }

            //Delete the Task
            else if (line.toLowerCase().contains("delete")) {

                try {
                    // -1 as Array starts from 0
                    int delete = Integer.parseInt(line.substring(7)) - 1;
                    Task d = tasks.get(delete);
                    tasks.remove(delete);
                    System.out.println("Noted. I've removed this task:\n" + d.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveFile("data/duke.txt");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please enter a valid task number.");

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            //End the System
            else if (line.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                System.exit(0);
            }

            //Command Not Recognised
            else {
                printLine();
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        }
    }

    //Save the File to Data Folder
    public static void saveFile(String myFile) throws IOException {
        FileWriter fw = new FileWriter(myFile);
        for (Task t : tasks){
            fw.write(t + System.lineSeparator());
        }
        fw.close();
    }

    //Read the File from Data Folder
    public static void readFile(String myFile) throws FileNotFoundException {
        File f = new File(myFile);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String text = s.nextLine();
            System.out.println(text);
            //Read [T] from File and Update to ArrayList
            if (text.contains("[T]")) {
                String Todo = text.substring(7);
                tasks.add(new Todo(Todo));

                if (text.contains("[X]")) {
                    Task m = tasks.get(counter);
                    m.markAsDone();
                }
                counter++;

                //Read [D] from File and Update to ArrayList
            } else if (text.contains("[D]")) {

                //Find '('
                int by_pos = text.indexOf("(");
                String Deadline = text.substring(7, by_pos-1);
                String by = text.substring(by_pos + 5, by_pos + 24);

                tasks.add(new Deadline(Deadline, by));

                if (text.contains("[X]")) {
                    Task m = tasks.get(counter);
                    m.markAsDone();
                }
                counter++;

                //Read [E] from File and Update to ArrayList
            } else if (text.contains("[E]")) {

                //Find '('
                int at_pos = text.indexOf("(");

                String Event = text.substring(7, at_pos-1);
                String at = text.substring(at_pos + 5, at_pos + 24);

                tasks.add(new Event(Event, at));

                if (text.contains("[X]")) {
                    Task m = tasks.get(counter);
                    m.markAsDone();
                }
                counter++;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "How can I help you today?");
        System.out.println("Psst, this is something that I can do for you:\n" + "(e.g event read book /at 23/03/2022 1200)");
        printLine();

        //Read File
        String FileLocation = "data/duke.txt";
        String Directory = "./data/";
        try {
            Path path = Paths.get(Directory);
            Files.createDirectories(path);
            readFile(FileLocation);
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I can't find the file! :-(");
        }

        //Trigger Code
        system();
    }
}
