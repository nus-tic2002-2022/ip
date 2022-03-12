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

    private static void actOn(UserInput input) {
        int index = Integer.parseInt(input.item.toString()) - 1;

        if (index < storages.size() && index >= 0) {
            Task item = storages.get(index);
            if (input.command == UserInput.Command.MARK) {
                item.markAsDone();
            } else if (input.command == UserInput.Command.UNMARK) {
                item.markAsUnDone();
            } else {
                storages.remove(index);
            }
            printList();
        } else {
            System.out.println("Invalid index, try again");
        }
    }

    private static void insertTask(UserInput.Category cat, String item) {
        if (cat == UserInput.Category.DEADLINE) {
            storages.add(new Deadline(item, input.day));
        } else if (cat == UserInput.Category.EVENT) {
            storages.add(new Events(item, input.day, input.time));
        } else if (cat == UserInput.Category.TODO) {
            storages.add(new Todo(item));
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
                input = input.parseInput(tokens);
                // Got category with no item
                if (input.category != null && input.item.toString().equals("")) {
                    throw new InputException("MissingItem");
                }
                // No category, no command
                if (input.category == null && input.command == null) {
                    throw new InputException("NoCommand");
                }
            } catch (InputException e) {
                e.printError();
                continue;
            }

            if (input.command == UserInput.Command.NO) {
                break;
            }
            if (input.command != null) {
                switch (input.command) {
                    case LIST:
                        printList();
                        break;
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        actOn(input);
                        break;
                }
            } else {
                insertTask(input.category, input.item.toString());
            }
        }
        System.out.println(repeat('*', lineWidth));
        System.out.println("See YU never");
        System.out.println(repeat('*', lineWidth));
    }
}
