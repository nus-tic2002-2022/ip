package duke.utils;

import duke.task.DateFunctions;
import duke.task.TaskList;
import static duke.utils.SearchDate.searchDate;

public class Parser extends Exception{
    /**
     * User inputs are parsed here so Duke I can understand what to do
     * @param userInput the user input read from scanner
     * @see {@link duke.task.TaskList} used for making changes to the task list
     * @see {@link duke.utils.SearchDate} used for searching day of week or month
     */

    public static void parse(String userInput){

        String command = userInput.split(" ")[0];

        switch(command) {
            case "?":
            case "help":
                CommandList.help();
                break;
            //Level 1 Greet, Echo, Exit
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0); //Exit program
            case "list":
                TaskList.list();
                break;
            case "mark":
                try {
                    Validator.indexValidate(userInput,"mark");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.mark(userInput);
                break;
            case "unmark":
                try {
                    Validator.indexValidate(userInput,"unmark");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.unmark(userInput);
                break;
            case "delete":
                try {
                    Validator.indexValidate(userInput,"delete");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.delete(userInput);
                break;
            case "todo":
                try {
                    Validator.todoValidate(userInput);
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.todo(userInput);
                break;
            case "deadline":
                try {
                    Validator.deadlineValidate(userInput);
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.deadline(userInput);
                break;
            case "event":
                try {
                    Validator.eventValidate(userInput);
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.event(userInput);
                break;
            case "ascend":
                DateFunctions.listSort(true);
                break;
            case "descend":
                DateFunctions.listSort(false);
                break;
            case "expired":
                DateFunctions.expiredList();
                break;
            case "upcoming":
                DateFunctions.upcomingList();
                break;
            case "search":
                searchDate(userInput);
                break;
            case "days":
                Ui.showDays();
                break;
            case "months":
                Ui.showMonths();
                break;
            default:
                //Level 5 Else, unrecognized command
                System.out.println("☹ OOPS!!! Try typing \"help\" to see a list of available commands");
                break;
        }
    }



}
