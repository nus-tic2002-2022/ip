import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.*;

public class Duke {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^mark[\\s][0-9]*$");
        Pattern q = Pattern.compile("^unmark[\\s][0-9]*$");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "";
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        Task[] temp = new Task[100];
        int itemCount = 0;


        while(!line.contains("bye")) {
            System.out.print("Type something: ");
            line = in.nextLine();
            Matcher m = p.matcher(line);
            Matcher n = q.matcher(line);

            if (line.equals("bye")){
                System.out.print("\tGood day and good bye");
                break;
            }

            if(line.equals("list")) {
                list = Arrays.copyOf(temp, itemCount);
                for (int i = 0; i < list.length; i++) {
                    System.out.print(i+1 + "." + list[i].getStatusIcon() + list[i].getDescription() + "\n");
                }
            }
            else {

                if(m.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    list[Integer.parseInt(line)-1].setStatus(true);
                    System.out.println("This task has been marked as done: " + "\n" + list[Integer.parseInt(line)-1].getStatusIcon() + list[Integer.parseInt(line)-1].getDescription());
                }
                else if(n.matches()){
                    line = line.replaceAll("[^0-9]", "");
                    list[Integer.parseInt(line)-1].setStatus(false);
                    System.out.println("This task has been marked as not done: " + "\n" + list[Integer.parseInt(line)-1].getStatusIcon() + list[Integer.parseInt(line)-1].getDescription());
                }
                else {
                    Task t = new Task(line);
                    temp[itemCount] = t;
                    itemCount++;
                    System.out.println("\tadded: " + line);
                }

            }
        }
    }
}

