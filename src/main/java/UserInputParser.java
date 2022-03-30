public class UserInputParser extends Parser{

    public static void processUserInput(TaskProcessor tasklist, String userInput) { //
        if (userInput.equals("list")) {
            tasklist.printList();
        } else if ( userInput.startsWith("mark") ) {

            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                tasklist.changeTaskStatus(indexToChange,true);
            } catch (NumberFormatException e) {
                //ui.printErrorMessage()
                System.out.println("tojava.util.Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                //ui.printErrorMessage()
                System.out.println("I don't think you have that many tasks on your list...");
            };

        } else if ( userInput.startsWith("unmark")) {
            try {
                int indexToChange = Integer.parseInt(userInput.split(" ")[1]) - 1 ;
                tasklist.changeTaskStatus(indexToChange,false);
            } catch (NumberFormatException e) {
                //ui.printErrorMessage()
                System.out.println("tojava.util.Task number not specified properly..");
            } catch (IndexOutOfBoundsException e) {
                //ui.printErrorMessage()
                System.out.println("I don't think you have that many tasks on your list...");
            };

        } else if (userInput.startsWith("todo " ) || userInput.startsWith("event ") || userInput.startsWith("deadline ")) {
            String taskType = userInput.split(" ")[0];
            try {
                tasklist.processTaskToList(taskType,userInput);
            } catch (ArrayIndexOutOfBoundsException e) {
                //ui.printErrorMessage()
                System.out.println("=^-.-^= You didn't provide your task description...");
            }

        } else if (userInput.startsWith("delete")) {
            try {
                tasklist.deleteTasks(userInput);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                //ui.printErrorMessage()
                System.out.println("The delete input is not correct... please specify an integer within the bounds ^=.=^");
            }

        } else {
            System.out.println("=^-.-^= Not sure what that means...");
        }
    }

}
