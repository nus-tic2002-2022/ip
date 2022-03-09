import java.util.*;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>(); // ArrayList of Tasks

    public static void main(String[] args) throws IllegalCommandException {

        String line;
        Scanner in = new Scanner(System.in);
        int status = 0;
        int index = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String command = "Here are the commands you can type: \n"
                + "1. type 'bye' to escape\n"
                + "2. type 'list' to check your list\n"
                + "3. type 'todo <add task here>' (e.g. todo read a book)\n"
                + "4. type 'deadline <add task here> /by <add deadline>' (e.g. deadline submit duke project /by 11 Apr 2022 2359)\n"
                + "5. type 'event <add task here> /at <add event timing>' (e.g. event attend TIC2002 class /at 2 March 2022 7pm)\n";

        System.out.println("Hello from\n" + logo);

        do {

            System.out.print("What can I do for you? (type 'commands' if you need help to remember the commands) ");

            line = in.nextLine();

            if (line.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                status = 1; // can escape
            } else if (line.toLowerCase().equals("commands")) {
                System.out.println(command);
            } else if (line.toLowerCase().equals("list")) { //Print list
                if (list.size() == 0) {
                    System.out.println("There is nothing on the list! :)");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(i + 1 + ".");
                        list.get(i).getTask();
                    }
                }
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else if (line.startsWith("todo ")) {
                if (line.substring(5, line.length()).trim().length() < 1) {
                    System.out.println("Sorry, description cannot be blank. type 'todo <add task here>' (e.g. todo read a book)");
                    throw new IllegalCommandException();
                }
                list.add(new Todo(line.substring(5, line.length())));
                list.get(list.size() - 1).printTask(); // print newly added tasks
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else if (line.startsWith("deadline ")) {
                if (line.substring(9, line.length()).trim().length() < 1) {
                    System.out.println("Sorry, description cannot be blank. type 'deadline <add task here> /by <add deadline>' (e.g. deadline submit duke project /by 11 Apr 2022 2359)");
                    throw new IllegalCommandException();
                }
                list.add(new Deadline(line.substring(9, line.length())));
                list.get(list.size() - 1).printTask(); // print newly added tasks
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else if (line.startsWith("event ")) {
                if (line.substring(6, line.length()).trim().length() < 1) {
                    System.out.println("Sorry, description cannot be blank. type 'event <add task here> /at <add event timing>' (e.g. event attend TIC2002 class /at 2 March 2022 7pm)");
                    throw new IllegalCommandException();
                }
                list.add(new Event(line.substring(6, line.length())));
                list.get(list.size() - 1).printTask(); // print newly added tasks
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else if (line.startsWith("mark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("mark ") + 5, line.length())) -1;
                if (index>= list.size()) {
                    System.out.println("Sorry, you have chosen the item number you choose to mark is out of range. type 'mark <add number that is within the list here>'.");
                    throw new IllegalCommandException();
                }
                list.get(index).markAsDone();
                list.get(index).getTask();
                continue;
            } else if (line.startsWith("unmark ")) {
                index = Integer.parseInt(line.substring(line.indexOf("unmark ") + 7, line.length())) -1;
                if (index >= list.size()) {
                    System.out.println("Sorry, you have chosen the item number you choose to unmark is out of range. type 'unmark <add number that is within the list here>'.");
                    throw new IllegalCommandException();
                }
                list.get(index).markAsNotDone();
                list.get(index).getTask();
                continue;
            } else if (line.startsWith("delete ")) {
                index = Integer.parseInt(line.substring(line.indexOf("delete ") + 7, line.length())) - 1;
                if (index >= list.size()) {
                    System.out.println("Sorry, you have chosen the item number you choose to delete is out of range. type 'delete <add number that is within the list here>'.");
                    throw new IllegalCommandException();
                }
                System.out.println("Noted. I've removed this task: ");
                list.get(index).getTask();
                list.remove(index);
                System.out.println("Now you have " + list.size() + " task(s) in the list.");
                continue;
            } else {
                System.out.println("Sorry, I don't understand. " + command);
                throw new IllegalCommandException();
            }

        } while (status == 0);

    }

}
