import Exceptions.InputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        if (index <= storages.size() && index != 0) {
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
        if (cat.equals("deadline")) {
            storages.add(new Deadline(command, input.day));
        } else if (cat.equals("event")) {
            storages.add(new Events(command, input.day, input.time));
        } else {
            storages.add(new Todo(command));
        }
        System.out.println("---> + Inserted Task: " + input.item);
        printList();
    }

    public static void main(String[] args) {
        String[] tokens;
        System.out.println("I'm Knot YU");
        var lineWidth = 12;

        while (true) {
            String command;
            System.out.print("\nCan I help you?\n");
            command = in.nextLine();
            input = new UserInput();
            tokens = command.split(" ");

            try {
                input.parseInput(tokens);
                // Got category with no item
                if (!input.category.equals("") && input.item.toString().equals("")) {
                    throw new InputException("MissingItem");
                }
                // No category, no command
                if (input.category.equals("") && input.command.length() == 0) {
                    throw new InputException("NoCommand");
                }
            } catch (InputException e) {
                e.printError();
                continue;
            }

            if (input.command.equals("no")) {
                break;
            }
            switch (input.command) {
                case "list":
                    printList();
                    break;
                case "mark":
                case "unmark":
                    markItem(input);
                    break;
                default:
                    insertTask(input.category, input.item.toString());
            }
        }
        System.out.println(repeat('*', lineWidth));
        System.out.println("See YU never");
        System.out.println(repeat('*', lineWidth));
    }
}
