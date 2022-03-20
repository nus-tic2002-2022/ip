import java.util.ArrayList;

public class Parser {

    public static void processUserInput(String userInput, ArrayList<Task> tasks , int taskListCount) { //
        if (userInput.equals("list")) {
            TaskProcessor.printList();
        } else if ( userInput.startsWith("mark") ) {
            //error here. what if mark is not integer
            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                TaskProcessor.changeTaskStatus(indexToChange,true);
            } catch (NumberFormatException e) {
                System.out.println("Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("I don't think you have that many tasks on your list...");
            };

        } else if ( userInput.startsWith("unmark")) {
            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                TaskProcessor.changeTaskStatus(indexToChange,false);
            } catch (NumberFormatException e) {
                System.out.println("Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("I don't think you have that many tasks on your list...");
            };
        } else if (userInput.startsWith("todo " ) || userInput.startsWith("event ") || userInput.startsWith("deadline ")) {
            String taskType = userInput.split(" ")[0];
            try {
                TaskProcessor.processTaskToList(taskType,userInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("=^-.-^= You didn't provide your task description...");
            }
        } else if (userInput.startsWith("delete")) {
            TaskProcessor.deleteTasks(userInput);
        } else {
            System.out.println("=^-.-^= Not sure what that means...");
        }
    }

}
