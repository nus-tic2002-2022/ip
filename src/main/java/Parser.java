public class Parser {

    public static void processUserInput(String userInput) {
        if (userInput.equals("bye")) {
            UI.printBye();
            return;
        } else if (userInput.equals("list")) {
            printList();
            continue;
        } else if ( userInput.startsWith("mark") ) {
            //error here. what if mark is not integer?
            int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ; //minus 1 to account for Java starting array at index 0
            changeTaskStatus(indexToChange,true);
            continue;
        } else if ( userInput.startsWith("unmark")) {
            //error here. what if unmark is not integer?
            int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1; //minus 1 to account for Java starting array at index 0
            changeTaskStatus(indexToChange,false);
            continue;
        } else if (userInput.startsWith("todo") || userInput.startsWith("event") || userInput.startsWith("deadline")) {
            String taskType = userInput.split(" ")[0];
            try {
                processTaskToList(taskType,userInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("=^-.-^= You didn't provide your task description...");
            }
        } else if (userInput.startsWith("delete")) {
            deleteTasks(userInput);
        } else {
            System.out.println("=^-.-^= Not sure what that means...");
        }
    }

}
