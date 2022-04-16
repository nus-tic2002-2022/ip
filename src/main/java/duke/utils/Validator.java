package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;

/**
 * Contains methods that do validation to make sure errors are handled properly before making changes to the task list
 */

public class Validator {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Validates if the user input is a valid Todo task
     *
     * @param userInput description of task.
     * @throws StringIndexOutOfBoundsException thrown when description of task is empty.
     */
    public static void todoValidate (String userInput) throws StringIndexOutOfBoundsException{
        try {
            String description = userInput.substring(0, userInput.indexOf(' '));
        } catch (StringIndexOutOfBoundsException s) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            throw s;
        }
    }

    /**
     * Validates if the user input is a valid Deadline task.
     *
     * @param userInput description and timestamp of Deadline.
     * @throws StringIndexOutOfBoundsException thrown when deadline is empty.
     */
    public static void deadlineValidate (String userInput) throws StringIndexOutOfBoundsException {
        //Below checks are for deadline
        int delimiter = userInput.indexOf("/by");
        if(delimiter == -1){
                //Validate /by
                System.out.println("☹ OOPS!!! Please make sure /by is mentioned for deadline");
                throw new StringIndexOutOfBoundsException();
        }

        String date = userInput.substring(delimiter + 3).trim();
        String description = userInput.substring(userInput.indexOf(' '), delimiter).trim();
        try{
            //Validate if date is empty
            dateValidate(date);
            //Validate if description is empty
            descriptionValidate(description);
        }catch (StringIndexOutOfBoundsException s){
            throw s;
        }
    }

    /**
     * Validates if the user input is a valid Event task.
     *
     * @param userInput description and timestamp of Event.
     * @throws StringIndexOutOfBoundsException thrown when timestamp is empty.
     */
    public static void eventValidate (String userInput) throws StringIndexOutOfBoundsException {
        //Below checks are for event
        int delimiter = userInput.indexOf("/at");
        if(delimiter == -1){
            //Validate /at
            System.out.println("☹ OOPS!!! Please make sure /at is mentioned for event");
            throw new StringIndexOutOfBoundsException();
        }

        String date = userInput.substring(delimiter + 3).trim();
        String description = userInput.substring(userInput.indexOf(' '), delimiter).trim();
        try{
            //Validate if date is empty
            dateValidate(date);
            //Validate if description is empty
            descriptionValidate(description);
        }catch (StringIndexOutOfBoundsException s){
            throw s;
        }
    }

    /**
     * Validate if date is part of the user input for Deadline and Event tasks before passing to other methods.
     *
     * @param date timestamp of the task.
     * @throws StringIndexOutOfBoundsException thrown when date is empty.
     * @see Validator#dateTimeFormatValidate(String)
     */
    private static void dateValidate (String date) throws StringIndexOutOfBoundsException{
        if (date.isBlank()) {
            System.out.println("☹ OOPS!!! The date cannot be empty.");
            throw new StringIndexOutOfBoundsException();
        }else{
            try{
                dateTimeFormatValidate(date);
            }catch(DateTimeParseException p){
                throw new StringIndexOutOfBoundsException();
            }
        }
    }

    private static void descriptionValidate (String description) throws StringIndexOutOfBoundsException {
        if (description.isBlank()) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            throw new StringIndexOutOfBoundsException();
        }
    }
    private static void dateTimeFormatValidate (String date){
        try{
            LocalDateTime dt = LocalDateTime.parse(date, DATE_TIME_FORMAT);
        }catch(DateTimeParseException p){
            System.out.println("☹ OOPS!!! That was invalid. I accept something like 2000-02-28 1830");
            throw p;
        }
    }

    /**
     * Checks the index exists for actions that require the task list to be indexed.
     *
     * @param userInput action and the index of the task.
     * @param action mark, unmark, delete, find.
     * @throws NumberFormatException thrown when the index given is not in the task list.
     * @see duke.task.TaskList#delete(String)
     * @see duke.task.TaskList#mark(String)
     * @see duke.task.TaskList#unmark(String)
     * @see duke.task.TaskList#find(String)
     */
    public static void indexValidate (String userInput, String action) throws NumberFormatException{
        try {
            String index = userInput.substring(userInput.indexOf(' ')+1);
            int i = Integer.parseInt(index);
        } catch (NumberFormatException n) {
            // Missing index
            System.out.println("☹ OOPS!!! Please indicate the index of the task you wish to " + action +"!");
            throw n;
        }
    }


}
