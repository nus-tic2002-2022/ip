package duke.importer;

import duke.task.TaskList;
import static duke.importer.FileContentChecker.lineChecker;

public class ImportTasks {
    /**
     * Adds the task file to the task list.
     *
     * @param fileInput takes in each line of the task file and processes it.
     * @throws ImportErrorException @see FileContentChecker.
     */
    public static void importTask (String fileInput) throws ImportErrorException {
        if(!fileInput.isBlank()){
        try {
            lineChecker(fileInput);
            String description = fileInput.substring(7);
            boolean doneStatus = isItDone(fileInput.charAt(4));
            char taskType = fileInput.charAt(1);

            switch (taskType) {
                case 'T':
                    importTodo(description, doneStatus);
                    break;
                case 'D':
                    importDeadline(description, doneStatus);
                    break;
                case 'E':
                    importEvent(description, doneStatus);
                    break;
            }
        } catch (ImportErrorException i){
            throw i;
        }
        }
    }

    private static void importTodo(String substring, boolean isDone){
        String description = "";
        for(int i=0;i<substring.length(); i++){
            description += substring.charAt(i);
        }
        TaskList.importTodo(description, isDone);
    }

    private static void importDeadline(String substring, boolean isDone){
        String description = "", date = "";
        for(int i=0;i<substring.indexOf("(by:"); i++){
            description += substring.charAt(i);
        }
        for(int i=substring.indexOf("(by:")+4;i<substring.length()-1; i++){
            date += substring.charAt(i);
        }
        TaskList.importDeadline(description, date.trim(), isDone);
    }

    private static void importEvent(String substring, boolean isDone){
        String description = "", date = "";
        for(int i=0;i<substring.indexOf("(at:"); i++){
            description += substring.charAt(i);
        }
        for(int i=substring.indexOf("(at:")+4;i<substring.length()-1; i++){
            date += substring.charAt(i);
        }
        TaskList.importEvent(description, date.trim(), isDone);
    }

    private static boolean isItDone(char mark){
        return mark != ' ';
    }

}
