package duke.importer;

public class ImportErrorException extends Exception{
    /**
     * Constructs ImportErrorException.
     * For an exception that is thrown when there is an error importing tasks from the task file.
     * There are error messages for each respective cause.
     *
     * @param item The reason for error.
     * @see FileContentChecker
     */
    public ImportErrorException(String item){

        switch (item){
            case "format":
                System.out.println("Sorry! :( That was an invalid format, Example format: \"[E][ ] sleep (at: 2am)\"\n");
                break;
            case "task":
                System.out.println("Sorry! :( That was an invalid task type, Valid task types: T = todo, D = deadline, E = event\n");
                break;
            case "by":
                System.out.println("Did you forget to mention (by: date) for deadline task?\n");
                break;
            case "at":
                System.out.println("Did you forget to mention (at: date) not found event task?\n");
                break;
            case "dateTime":
                System.out.println("Sorry! :( I think there was an error with the date format, try YYYY-MM-DD HHmm\n");
                break;
            case "mark":
                System.out.println("Sorry! :( I think there was an error with the format of your tasks status, Mark with \"X\" if done, leave blank if not done\n");
                break;
            default:
                System.out.println("Sorry! :( I am not sure what went wrong, I could not import the task.\n");
        }
    }
}
