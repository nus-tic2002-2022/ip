package duke.ui;

import java.util.Scanner;

public class Ui {

    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private String command = "Here are the commands you can type: \n"
            + "1. type 'bye' to escape\n"
            + "2. type 'list' to check your list\n"
            + "3. type 'todo <add task here>' (e.g. todo read a book)\n"
            + "4. type 'deadline <add task here> /by <add deadline>' (e.g. deadline submit project /by 11 March 2022 2359)\n"
            + "5. type 'event <add task here> /at <add event timing>' (e.g. event attend class /at 2 March 2022 7pm)\n";

    public void showWelcome() {
        System.out.println(logo);
        System.out.println();
        System.out.println(command);
    }

    public void showLine() {
        System.out.println("________________________________");
    }

    public String readCommand() {
        System.out.println("Please enter an input: ");
        Scanner in = new Scanner(System.in);
        String command;
        command = in.nextLine();

        return command;
    }

    public void showError(String errorMsg) {
        System.err.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showLoadingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but an error occurred upon reading file.");
    }
}
