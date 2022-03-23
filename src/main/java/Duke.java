import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        Pattern mark = Pattern.compile("^mark[\\s][0-9]*$");
        Pattern unmarked = Pattern.compile("^unmark[\\s][0-9]*$");
        Pattern todo = Pattern.compile("^todo[\\s][\\s\\S]*$");
        Pattern deadline = Pattern.compile("^deadline[\\s][\\s\\S]*$");
        Pattern event = Pattern.compile("^event[\\s][\\s\\S]*$");
        Pattern delete = Pattern.compile("^delete[\\s][0-9]*$");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int itemCount = 0;


        while(!line.contains("bye")) {
            System.out.print("Type something: ");
            line = in.nextLine();

            Matcher m = mark.matcher(line);
            Matcher n = unmarked.matcher(line);
            Matcher o = todo.matcher(line);
            Matcher p = deadline.matcher(line);
            Matcher q = event.matcher(line);
            Matcher r = delete.matcher(line);

            if (line.equals("bye")){
                System.out.print("\tGood day and good bye");
                break;
            }

            if (line.equals("todo")){
                System.out.println("The description of a todo task cannot be empty");
            }
            if (line.equals("deadline")){
                System.out.println("The description of a deadline task cannot be empty");
            }
            if (line.equals("event")){
                System.out.println("The description of a event task cannot be empty");
            }

            if(line.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(i+1 + "." + tasks.get(i).getStatusIcon() + tasks.get(i) + "\n");
                }
            }

            else {

                if(m.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    tasks.get(Integer.parseInt(line)-1).setStatus(true);
                    System.out.println("This task has been marked as done: " + "\n" + "\t" + tasks.get(Integer.parseInt(line)-1).getStatusIcon() + tasks.get(Integer.parseInt(line)-1));
                }
                else if(n.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    tasks.get(Integer.parseInt(line)-1).setStatus(false);
                    System.out.println("This task has been marked as not done: " + "\n" + "\t" + tasks.get(Integer.parseInt(line)-1).getStatusIcon() + tasks.get(Integer.parseInt(line)-1));
                }
                else if(o.matches()){
                    line = line.replaceAll("todo ", "");
                    Task t = new Todo(line);
                    tasks.add(t);
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
                    tasks.add(t);
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
                    tasks.add(t);
                    itemCount++;
                    System.out.println("This task has been added: " + "\n" + "\t[ ]" + t);
                    System.out.println("There are " + itemCount + " tasks in the list");
                }
                else if(r.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    itemCount--;
                    System.out.println("This task has been removed: " + "\n" + "\t" + tasks.get(Integer.parseInt(line)-1).getStatusIcon() + tasks.get(Integer.parseInt(line)-1));
                    tasks.remove(Integer.parseInt(line)-1);
                    System.out.println("There are " + itemCount + " tasks in the list");
                }
                else {
                    System.out.println("Sorry I don't understand what you mean.");
                }

            }
        }
    }
}

