package duke.command;

import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommand implements Command {
    private final TaskList tasks;

    public FindCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Return a message with the matching task list.
     *
     * @param fullCommand Array of command from the user input.
     * @return A matching task list.
     */
    @Override
    public List<String> run(String[] fullCommand) {
        String keyWord = String.join(" ", Arrays.copyOfRange(fullCommand, 1, fullCommand.length));
        List<String> messages = new ArrayList<>();
        messages.add("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyWord)) {
                count++;
                messages.add("    " + count + "." + tasks.get(i));
            }
        }
        return messages;
    }
}
