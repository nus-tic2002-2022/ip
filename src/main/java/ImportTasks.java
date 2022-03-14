public class ImportTasks {

    public static void importTask (String fileInput){

        if(inputCheck(fileInput)) {
            String description = fileInput.substring(7);
            
            if(fileInput.charAt(1) == 'T') {
                importTodo(description, isItDone(fileInput.charAt(4)));
            }else if (fileInput.charAt(1) == 'D') {
                importDeadline(description, isItDone(fileInput.charAt(4)));
            }else if (fileInput.charAt(1) == 'E'){
                importEvent(description, isItDone(fileInput.charAt(4)));
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
        for(int i=0;i<substring.indexOf("(by: "); i++){
            description += substring.charAt(i);
        }
        for(int i=substring.indexOf("(by: ")+5;i<substring.length()-1; i++){
            date += substring.charAt(i);
        }
        TaskList.importDeadline(description, date, isDone);
    }

    private static void importEvent(String substring, boolean isDone){
        String description = "", date = "";
        for(int i=0;i<substring.indexOf("(at: "); i++){
            description += substring.charAt(i);
        }
        for(int i=substring.indexOf("(at: ")+5;i<substring.length()-1; i++){
            date += substring.charAt(i);
        }
        TaskList.importEvent(description, date, isDone);
    }

    private static boolean isItDone(char mark){
        if(mark == ' ') {
            return false;
        }
        return true;
    }
    
    private static boolean inputCheck (String fileInput){
        //format checker
        if(fileInput.charAt(0) != '[' || fileInput.charAt(2) != ']' || fileInput.charAt(3) != '[' || fileInput.charAt(5) != ']') {
            System.out.println("☹ OOPS!!! Error importing this line: " + fileInput );
            System.out.println("Invalid format, Example format: \"[E][ ] sleep (at: 2am)\"");
            System.out.println("Skipping...");
            return false;
        }
        //task checker
        if(fileInput.charAt(1) == 'T' || fileInput.charAt(1) == 'D' || fileInput.charAt(1) == 'E') {
        }else{
            System.out.println("☹ OOPS!!! Error importing this line: " + fileInput );
            System.out.println("Invalid task type, Valid task types: T = todo, D = deadline, E = event");
            System.out.println("Skipping...");
            return false;
        }
        //date checker
        if(fileInput.charAt(1) == 'D' && !fileInput.contains("(by:") && fileInput.charAt(fileInput.length()-1) != ')') {
            System.out.println("☹ OOPS!!! Error importing this line: " + fileInput );
            System.out.println("(:by ) not found for deadline");
            System.out.println("Skipping...");
            return false;
        }
        if(fileInput.charAt(1) == 'E' && !fileInput.contains("(at:") && fileInput.charAt(fileInput.length()-1) != ')') {
            System.out.println("☹ OOPS!!! Error importing this fileInput: " + fileInput);
            System.out.println("(:at ) not found for event");
            System.out.println("Skipping...");
            return false;
        }

        if(fileInput.charAt(4) == ' ' || fileInput.charAt(4) == 'X'){
        }else{
            System.out.println("☹ OOPS!!! Error importing this fileInput: " + fileInput);
            System.out.println("Format error with mark your task, Mark with \"X\" if done, leave blank if not done");
            System.out.println("Skipping...");
        }
        return true;
    }


}
