package duke;

import duke.exceptions.InputException;
import duke.task.Task;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tokens;

        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        UserInput input;

        UserInterface.init();
        tasks.init();
        while (true) {
            System.out.print("\nCan I help you?\n");
            String command;
            command = in.nextLine();
            input = new UserInput();
            tokens = command.split(" ");
//
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
                        tasks.printList();
                        break;
                    case MARK:
                        tasks.mark(input);
                        break;
                    case UNMARK:
                        tasks.unmark(input);
                        break;
                    case DELETE:
                        tasks.delete(input);
                        break;
                }
            } else {
                Integer id = tasks.getNextId();
                Task newTask = new Task(id, input.item.toString());
                tasks.insertTask(newTask, input);
            }
        }
        UserInterface.close();
    }
}
