package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Shows help on how to use commands to the user
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_WORD_2 = "?";

    public static final String MESSAGE_USAGE = "Here are the list of available Commands: \n"
            + "list : Lists all available tasks \n"
            + "todo <description>: Adds a new todo Task \n"
            + "deadline <description> /by <date>: Adds a new deadline Task \n"
            + "event <description> /at <date>: Adds a new event Task \n"
            + "mark <task id>: Marks a task as done \n"
            + "unmark <task id>: Unmarks a task as done \n"
            + "delete <task id> /all/marked: Deletes a task /all tasks / marked tasks \n"
            + "find <keyword>: finds all tasks with keyword \n"
            + "update <task id> <desc/date> <new content>: updates a task \n"
            + "bye :Exits the application \n"
            + "Type help <command> for more information.";

    private String commandProvided;

    public HelpCommand(String command) {
        this.commandProvided = command;
    }

    /**
     * executes help function based on input provided
     */
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String command = commandProvided.toLowerCase();

        switch (command) {
        case "":
            ui.printHelp(this.MESSAGE_USAGE);
            break;
        case AddCommand.COMMAND_WORD_TODO:
            ui.printHelp(AddCommand.MESSAGE_USAGE_TODO);
            break;

        case AddCommand.COMMAND_WORD_DEADLINE:
            ui.printHelp(AddCommand.MESSAGE_USAGE_DEADLINE);
            break;

        case AddCommand.COMMAND_WORD_EVENT:
            ui.printHelp(AddCommand.MESSAGE_USAGE_EVENT);
            break;

        case MarkDoneCommand.COMMAND_WORD:
            ui.printHelp(MarkDoneCommand.MESSAGE_USAGE);
            break;

        case UnmarkDoneCommand.COMMAND_WORD:
            ui.printHelp(UnmarkDoneCommand.MESSAGE_USAGE);
            break;

        case DeleteCommand.COMMAND_WORD:
            ui.printHelp(DeleteCommand.MESSAGE_USAGE);
            break;

        case FindCommand.COMMAND_WORD:
            ui.printHelp(FindCommand.MESSAGE_USAGE);
            break;

        case UpdateCommand.COMMAND_WORD:
            ui.printHelp(UpdateCommand.MESSAGE_USAGE);
            break;

        case ListCommand.COMMAND_WORD:
            ui.printHelp(ListCommand.MESSAGE_USAGE);
            break;

        case ExitCommand.COMMAND_WORD:
            ui.printHelp(ExitCommand.MESSAGE_USAGE);
            break;

        default:
            ui.printHelp("Invalid Command");
        }
    }

}
