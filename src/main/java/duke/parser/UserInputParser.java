package duke.parser;

import duke.utils.TaskProcessor;
import duke.utils.Ui;
import java.time.format.DateTimeParseException;

public class UserInputParser extends Parser {

    /**
     * Processes User input. Takes in a string, does the necessary parsing, and calls necessary class for execution
     */
    private Ui ui;

    public UserInputParser ()  {
        super();
        ui = new Ui();
    }

    public void processUserInput(TaskProcessor tasklist, String userInput) { //
        if (userInput.equals("list")) {
            tasklist.printList();
        } else if ( userInput.startsWith("mark") ) {
            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                tasklist.changeTaskStatus(indexToChange,true);
            } catch (NumberFormatException e) {
                System.out.println("Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("I don't think you have that many tasks on your list...");
            };

        } else if ( userInput.startsWith("unmark")) {
            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                tasklist.changeTaskStatus(indexToChange,false);
            } catch (NumberFormatException e) {
                System.out.println("Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("I don't think you have that many tasks on your list...");
            };

        } else if (userInput.startsWith("todo " ) || userInput.startsWith("event ") || userInput.startsWith("deadline ") || userInput.startsWith("schedule ")) {
            String taskType = userInput.split(" ")[0];
            try {
                tasklist.processTaskToList(taskType,userInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("=^-.-^= You didn't provide your task description...");
            } catch (DateTimeParseException e) {
                ui.printParseStringToDateErrorMEssage();
            }

        } else if (userInput.startsWith("delete")) {
            try {
                tasklist.deleteTasks(userInput);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("The delete input is not correct... please specify an integer within the bounds ^=.=^");
            }
        } else if (userInput.startsWith("find")) {
            String keyword = userInput.split(" ")[1];
            tasklist.findTasksWithKeyword(keyword);
        } else if (userInput.startsWith("reschedule")) {
            Integer taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            String postponeTaskDate =  userInput.split(" ")[2];
            try {
                tasklist.rescheduleTask(taskNumber,postponeTaskDate);
            } catch (DateTimeParseException e) {
                ui.printParseStringToDateErrorMEssage();
            }

        } else if (userInput.equals("help")) {
            ui.printHelpMessage();
        }
        else {
            System.out.println("=^-.-^= Not sure what that means...");
        }
    }
}
