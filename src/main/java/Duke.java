import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static int counter = 0;
    public static int list_order = 1;
    public static String[] list = new String[100];

    public static void addlist() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        if (command.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.toLowerCase().equals("list")) {
            String[] print = Arrays.copyOf(list, counter);
            for (String p : print) {
                System.out.println(list_order + ". " + p);
                list_order++;
            }
            System.out.println("\n");
            list_order = 1;
            addlist();
        } else {
            System.out.println("added: " + command + "\n");
            list[counter] = command;
            counter++;
            addlist();
        }

    }
        public static void main (String[]args){
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello I am Taro the Duke");
            System.out.println("What can I do for you today?");

            addlist();
        }

    }

