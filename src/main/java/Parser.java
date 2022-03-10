public class Parser extends Exception{

    private static void taskValidate (String userInput, String taskType) throws StringIndexOutOfBoundsException{
        try {
            String description = userInput.substring(0, userInput.indexOf(' '));
        } catch (StringIndexOutOfBoundsException s) {
            // Missing description (task)
            System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
            throw new StringIndexOutOfBoundsException();
        }
    }
    private static void dateValidate (String userInput, String taskType) throws StringIndexOutOfBoundsException {
        //Below checks are for deadlines and events
        int delimiter = 0;
        if(taskType.equals("deadline")){
            delimiter = userInput.indexOf("/by");
            if(delimiter == -1){
                //Validate /by
                System.out.println("☹ OOPS!!! Please make sure /by is mentioned for " + taskType);
                throw new StringIndexOutOfBoundsException();
            }
        }else if(taskType.equals("event")){
            delimiter = userInput.indexOf("/at");
            if(delimiter == -1){
                //Validate /at
                System.out.println("☹ OOPS!!! Please make sure /at is mentioned for " + taskType);
                throw new StringIndexOutOfBoundsException();
            }
        }

        String date = userInput.substring(delimiter + 3);
        //Validate if date is empty
        if (date.isBlank()) {
           System.out.println("☹ OOPS!!! The date of a " + taskType + " cannot be empty.");
           throw new StringIndexOutOfBoundsException();
        }
        //Validate if description is empty
        String description = userInput.substring(userInput.indexOf(' '), delimiter);
        if (description.isBlank()) {
            System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
            throw new StringIndexOutOfBoundsException();
        }
    }
    private static void indexValidate (String userInput, String action) throws NumberFormatException{
        try {
            String index = userInput.substring(userInput.indexOf(' ')+1);
            int i = Integer.parseInt(index);
        } catch (NumberFormatException n) {
            // Missing index
            System.out.println("☹ OOPS!!! Please indicate the index of the task you wish to " + action +"!");
            throw new NumberFormatException();
        }
    }

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
                    indexValidate(userInput,"mark");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.mark(userInput);
                break;
            case "unmark":
                try {
                    indexValidate(userInput,"unmark");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.unmark(userInput);
                break;
            case "delete":
                try {
                    indexValidate(userInput,"delete");
                }catch (NumberFormatException n){
                    return;
                }
                TaskList.delete(userInput);
                break;
            case "todo":
                try {
                    taskValidate(userInput, "todo");
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.todo(userInput);
                break;
            case "deadline":
                try {
                    taskValidate(userInput, "deadline");
                    dateValidate(userInput, "deadline");
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.deadline(userInput);
                break;
            case "event":
                try {
                    taskValidate(userInput, "event");
                    dateValidate(userInput, "event");
                } catch (StringIndexOutOfBoundsException s){
                    return;
                }
                TaskList.event(userInput);
                break;
            default:
                //Level 5 Else, unrecognized command
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
    }


}
