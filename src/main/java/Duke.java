import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void printline(){
        System.out.println("_______________________________________");
    }

    static List<Task> tasklist = new ArrayList<Task>();

    public static void system(){
        Speech.introduction();
        printline();
        int counter=0;
        String line;
        Scanner input = new Scanner(System.in);

        while(true)
        {
            line=input.nextLine();
            String[] dimensions = line.split(" ");
            if (dimensions[0].equals("bye")) {
                printline();
                Speech.farewell();
                break;
            }
            else if (dimensions[0].equals("list")) {
                printline();
                if(tasklist.size()==0){
                    System.out.println("Sir, May I remind you that you have no tasks on hand. Maybe you will like to add some, Sir?");
                }
                else {
                    int index = 1;
                    for(Task t : tasklist){
                        System.out.println(index + ". " + t.getDescription() + "["+ t.getStatusIcon() + "]");
                        index++;
                    }
                }
            }
            else if (dimensions[0].equals("mark")) {
                tasklist.get(Integer.parseInt(dimensions[1])-1).markAsDone();
                System.out.println("Yes Sir, I have marked (" +tasklist.get(Integer.parseInt(dimensions[1])-1).getDescription() + ") as done "+ "[" + tasklist.get(Integer.parseInt(dimensions[1])-1).getStatusIcon() + "]");
            }
            else if (dimensions[0].equals("unmark")) {
                tasklist.get(Integer.parseInt(dimensions[1])-1).markAsUndone();
                System.out.println("Yes Sir, I have unmarked (" +tasklist.get(Integer.parseInt(dimensions[1])-1) + ") as undone "+ "[" + tasklist.get(Integer.parseInt(dimensions[1])-1).getStatusIcon() + "]");
            }
            else
            {
                Task toadd = new Task(line);
                tasklist.add(toadd);
                counter++;
                printline();
                System.out.println("added: " + line);
            }
            printline();
        }
    }

    public static void main(String[] args) {
        String logo = "  ╭┳━━━┳━━━┳╮  ╭┳━━┳━━━╮\n"
                + "  ┃┃╭━╮┃╭━╮┃╰╮╭╯┣┫┣┫╭━╮┃\n"
                + "  ┃┃┃ ┃┃╰━╯┣╮┃┃╭╯┃┃┃╰━━╮\n"
                + "╭╮┃┃╰━╯┃╭╮╭╯┃╰╯┃ ┃┃╰━━╮┃\n"
                + "┃╰╯┃╭━╮┃┃┃╰╮╰╮╭╯╭┫┣┫╰━╯┃\n"
                + "╰━━┻╯ ╰┻╯╰━╯ ╰╯ ╰━━┻━━━╯";
        System.out.println(logo);
        printline();
        system();
    }
}