package duke.utils;

import duke.task.TaskList;

public class Parser extends Exception{


    public static void parse(String userInput){

        String command = userInput.split(" ")[0];

        switch(command) {
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
            default:
                //Level 5 Else, unrecognized command
                System.out.println("☹ OOPS!!! Try typing \"help\" to see a list of available commands");
                break;
        }
    }



}
