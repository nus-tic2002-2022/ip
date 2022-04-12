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

    public static final String MESSAGE_USAGE = "Here are the list of available Commands: \n\n"
            + "Action                        | Command\n"
            + "----------------------------------------------------------------------\n"
            + "Lists all tasks               | list \n"
            + "Adds a new todo Task          | todo <description>\n"
            + "Adds a new deadline Task      | deadline <description> /by <date>\n"
            + "Adds a new event Task         | event <description> /at <date>\n"
            + "Adds a new fixed duration Task| fixed <duration> <description>\n"
            + "Marks a task as done          | mark <task id>\n"
            + "Unmarks a task as done        | unmark <task id>\n"
            + "Deletes a task /all /marked   | delete <task id> /all/marked\n"
            + "Finds all tasks with keyword  | find <keyword>\n"
            + "Updates a task                | update <task id> <desc/date> <new content>\n"
            + "View tasks on certain day     | view <date> \n"
            + "Exit                          | Bye \n\n"
            + "Type help <command> for more information.";

    private final String commandProvided;

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
            ui.printHelp(MESSAGE_USAGE);
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

        case AddCommand.COMMAND_WORD_FIXED:
            ui.printHelp(AddCommand.MESSAGE_USAGE_FIXED);
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

        case ViewCommand.COMMAND_WORD:
            ui.printHelp(ViewCommand.MESSAGE_USAGE);
            break;

        default:
            ui.printHelp("Invalid Command");
        }
    }

}
