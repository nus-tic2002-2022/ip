package duke.importer;

public class ImportErrorException extends Exception{
    /**
     * ImportErrorException Constructor.
     * For an exception that is thrown when there is an error importing tasks from the task file.
     * There are error messages for each respective cause.
     *
     * @param item The reason for error
     * @see FileContentChecker
     */
    public ImportErrorException(String item){

        switch (item){
            case "format":
                System.out.println("Invalid format, Example format: \"[E][ ] sleep (at: 2am)\"");
                break;
            case "task":
                System.out.println("Invalid task type, Valid task types: T = todo, D = deadline, E = event");
                break;
            case "by":
                System.out.println("(by: date) not found for deadline task");
                break;
            case "at":
                System.out.println("(at: date) not found event task");
                break;
            case "dateTime":
                System.out.println("Date Format Error, try YYYY-MM-DD HHmm");
                break;
            case "mark":
                System.out.println("Format error with mark your task, Mark with \"X\" if done, leave blank if not done");
                break;
            default:
                System.out.println("☹ OOPS!!! Error importing");
        }
    }
}
