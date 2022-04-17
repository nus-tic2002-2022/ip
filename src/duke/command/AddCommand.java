package duke.command;

import duke.deadline.Deadline;
import duke.event.Event;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.todo.ToDo;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class AddCommand extends Command {

    protected String command;
    protected String description;

    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }


    /**
     * To read user's input and execute the respective function of the application
     * @param tasks existing task in the list
     * @param ui to display out the messages to the user
     * @param storage which stores the file
     */
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

            if (!description.contains("/by")) {
                System.out.println("Please re-key in the correct command");
                return;
            }
            if(description.startsWith("/by")) {
                System.out.println("Description cannot be empty!");
                return;
            }

            String[]input1 = description.split(" /by ", 2);

            Task deadline = new Deadline(input1[0], input1[1]);
            tasks.getTasks().add(deadline);

            System.out.println("Got it. I've added this task: ");
            System.out.println(deadline.toString());
            System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
        }

        if (this.command.equals("event")) {
            String[]input1 = description.split(" /at ", 2);

            if (!description.contains("/at")) {
                System.out.println("Please re-key in the correct command");
                return;
            }
            if(description.startsWith("/at")) {
                System.out.println("Description cannot be empty!");
                return;
            }
            Task event = new Event(input1[0], input1[1]);
            tasks.getTasks().add(event);


            System.out.println("Got it. I've added this task: ");
            System.out.println(event.toString());
            System.out.println("Now you have " + tasks.getTasks().size() + " task in the list.");
        }

        if (this.command.equals("mark")) {
            int index = Integer.parseInt(description);

            if (tasks.getTasks().size() < index || index == 0) {
                System.out.println("There is no such task in the list");
                return;
            }

            tasks.getTasks().get(index-1).markAsDone();
            System.out.println("Nice! I've marked this as done:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }

        if (this.command.equals("unmark")) {
            int index = Integer.parseInt(description);
            if (tasks.getTasks().size() < index || index == 0) {
                System.out.println("There is no such task in the list");
                return;
            }
            tasks.getTasks().get(index-1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }

        if (this.command.equals("priority")) {
            int index = Integer.parseInt(description);

            if (tasks.getTasks().size() < index || index == 0) {
                System.out.println("There is no such task in the list");
                return;
            }
            tasks.getTasks().get(index-1).setPriority(true);
            System.out.println("OK, I've marked this task as high priority:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }

        if (this.command.equals("remove-priority")) {
            int index = Integer.parseInt(description);

            if (tasks.getTasks().size() < index || index == 0) {
                System.out.println("There is no such task in the list");
                return;
            }

            tasks.getTasks().get(index-1).setPriority(false);
            System.out.println("OK, I've removed this task as a high priority:");
            System.out.println(tasks.getTasks().get(index-1).toString());
        }

        if (this.command.equals("find")) {
            List<Task> list = new ArrayList<>();

            description = description.toLowerCase();

            for (Task t : tasks.getTasks()) {
                if (t.getDescription().contains(description)) {
                    list.add(t);
                }
            }

            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < list.size(); i++ ){
                System.out.println(i+1 + ". " + list.get(i).toString());
            }
        }
    }
}
