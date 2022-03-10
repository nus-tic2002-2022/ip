import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> storages = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static UserInput input;

    private static String repeat(char ch, int count) {
        char[] buf = new char[count];
        Arrays.fill(buf, ch);
        return new String(buf);
    }

    private static void printList() {
        for (int i = 0; i < storages.size(); i++) {
            System.out.println((i + 1) + ": " + storages.get(i).toString());
        }
    }

    private static void markItem(UserInput input) {
        int index = Integer.parseInt(input.item.toString());
        if (index <= storages.size() && storages.size() >= 1) {
            Task item = storages.get(index - 1);
            if (input.command.equals("mark")) {
                item.markAsDone();
            } else {
                item.markAsUnDone();
            }
            printList();
        } else {
            System.out.println("Invalid index, try again");
        }
    }

    private static void insertTask(String cat, String command) {
        if (cat.equals("deadline")){
            storages.add(new Deadline(command, input.day));
        } else if (cat.equals("event")){
            storages.add(new Events(command, input.day, input.time));
        } else  {
            storages.add(new Todo(command));
        }
    }

    public static void main(String[] args) {
        String[] tokens;
        System.out.println("I'm Knot YU");
        while (true) {
            String command;
            System.out.print("\nCan I help you?\n");
            command = in.nextLine();
            input = new UserInput();
            tokens = command.split(" ");

            input.parseInput(tokens);
            if(input.command.equals("no")) {
                break;
            }

            switch (input.command){
                case "list":
                    printList();
                    break;
                case "mark":
                case "unmark":
                    markItem(input);
                    break;
                default:
                    insertTask(input.category, command);
                    System.out.println("->\t\t+ " + "Added " + input.category + ": " + input.item);
                    printList();
            }
        }
        var width = 12;
        System.out.println(repeat('*', width));
        System.out.println("See YU never");
        System.out.println(repeat('*', width));
    }
}
