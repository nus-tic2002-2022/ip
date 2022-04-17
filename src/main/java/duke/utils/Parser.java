package duke.utils;

import duke.task.DateFunctions;
import duke.task.TaskList;
import static duke.utils.SearchDate.searchDate;

public class Parser extends Exception{
    /**
     * Parses user input so Duke can understand what to do
     *
     * @param userInput the user input read from scanner.
     */

    public static void parse(String userInput){
        String command = userInput.split(" ")[0];
        switch(command) {
        case "?":
        case "help":
            Ui.help();
            break;
        //Level 1 Greet, Echo, Exit
        case "bye":
            System.out.println("Bye :) I was happy to help you, hope to see you again soon!");
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
        case "after":
            try {
                Validator.indexValidate(userInput,"see what comes after");
            }catch (NumberFormatException n){
                return;
            }
            TaskList.doAfter(userInput);
            break;
        case "find":
            TaskList.find(userInput);
            break;
        default:
        //Level 5 Else, unrecognized command
            System.out.println("Sorry! :( I could not understand that. Try typing \"help\" to see a list of actions I can do.");
            break;
        }
    }



}
