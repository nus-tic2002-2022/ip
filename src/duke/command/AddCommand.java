package duke.command;

import duke.deadline.Deadline;
import duke.event.Event;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.todo.ToDo;
import duke.ui.Ui;

public class AddCommand extends Command {

    protected String command;
    protected String description;

    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (this.command.equals("todo")) {
            Task todo = new ToDo(description);
            tasks.getTasks().add(todo);

            System.out.println("Got it. I've added this task: ");
            System.out.println(todo.toString());
            System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
        }

        if (this.command.equals("deadline")) {
            String[]input1 = description.split(" /by ", 2);

            Task deadline = new Deadline(input1[0], input1[1]);
            tasks.getTasks().add(deadline);

            System.out.println("Got it. I've added this task: ");
            System.out.println(deadline.toString());
            System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
        }

        if (this.command.equals("event")) {
            String[]input1 = description.split(" /at ", 2);

            Task event = new Event(input1[0], input1[1]);
            tasks.getTasks().add(event);

            System.out.println("Got it. I've added this task: ");
            System.out.println(event.toString());
            System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
        }

        if (this.command.equals("mark")) {
            int index = Integer.parseInt(description);

            tasks.getTasks().get(index-1).markAsDone();
            System.out.println("Nice! I've marked this as done:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }

        if (this.command.equals("unmark")) {
            int index = Integer.parseInt(description);

            tasks.getTasks().get(index-1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }
    }
}
