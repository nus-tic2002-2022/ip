import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        Pattern mark = Pattern.compile("^mark[\\s][0-9]*$");
        Pattern unmarked = Pattern.compile("^unmark[\\s][0-9]*$");
        Pattern todo = Pattern.compile("^todo[\\s][\\s\\S]*$");
        Pattern deadline = Pattern.compile("^deadline[\\s][\\s\\S]*$");
        Pattern event = Pattern.compile("^event[\\s][\\s\\S]*$");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "";
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        Task[] temp = new Task[100];
        int itemCount = 0;


        while(!line.contains("bye")) {
            System.out.print("Type something: ");
            line = in.nextLine();
            Matcher m = mark.matcher(line);
            Matcher n = unmarked.matcher(line);
            Matcher o = todo.matcher(line);
            Matcher p = deadline.matcher(line);
            Matcher q = event.matcher(line);

            if (line.equals("bye")){
                System.out.print("\tGood day and good bye");
                break;
            }

            if(line.equals("list")) {
                tasks = Arrays.copyOf(temp, itemCount);
                for (int i = 0; i < tasks.length; i++) {
                    System.out.print(i+1 + "." + tasks[i].getStatusIcon() + tasks[i] + "\n");
                }
            }

            else {

                if(m.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    tasks[Integer.parseInt(line)-1].setStatus(true);
                    System.out.println("This task has been marked as done: " + "\n" + tasks[Integer.parseInt(line)-1].getStatusIcon() + tasks[Integer.parseInt(line)-1].getDescription());
                }
                else if(n.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    tasks[Integer.parseInt(line)-1].setStatus(false);
                    System.out.println("This task has been marked as not done: " + "\n" + tasks[Integer.parseInt(line)-1].getStatusIcon() + tasks[Integer.parseInt(line)-1].getDescription());
                }
                else if(o.matches()){
                    line = line.replaceAll("todo ", "");
                    Task t = new Todo(line);
                    temp[itemCount] = t;
                    itemCount++;
                    System.out.println("This task has been added: " + "\n" + "\t[ ]" + t);
                    System.out.println("There are " + itemCount + " tasks in the list");
                }
                else if(p.matches()){
                    line = line.replaceAll("deadline ", "");
                    String name = line.substring(0, line.indexOf("/") - 1);
                    String date = line.substring(line.indexOf("/") + 4, line.lastIndexOf("y"));
                    date = date.concat("y");
                    Task t = new Deadline(name, date);
                    temp[itemCount] = t;
                    itemCount++;
                    System.out.println("This task has been added: " + "\n" + "\t[ ]" + t);
                    System.out.println("There are " + itemCount + " tasks in the list");
                }
                else if(q.matches()){
                    line = line.replaceAll("event ", "");
                    String name = line.substring(0, line.indexOf("/") - 1);
                    String date = line.substring(line.indexOf("/") + 4, line.lastIndexOf("m"));
                    date = date.concat("m");
                    Task t = new Event(name, date);
                    temp[itemCount] = t;
                    itemCount++;
                    System.out.println("This task has been added: " + "\n" + "\t[ ]" + t);
                    System.out.println("There are " + itemCount + " tasks in the list");
                }
                else {
                    System.out.println("Regex failed");
                }

            }
        }
    }
}

