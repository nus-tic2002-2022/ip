package duke.utils;

import duke.task.Task;
import duke.task.TasksWithDate;
import java.util.ArrayList;

public class Ui
{

    /**
     * <h1>User Interface</h1>
     * This class is in charge of displaying most messages related to Duke on the UI
     */
    public Ui() { };

    /**
     * Displays introduction text to user upon executing Duke
     */
    public void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.\n");
        System.out.println("\tHow may I help? (I take carrots as payment). \n");
    }

    /**
     * Displays goodbye message, indicating exit of Duke program
     */
    public void printBye() {
        System.out.println("Already? Cya...");
        System.out.println("  //");
        System.out.println(" ('')");
        System.out.println(" UU \\   ");
        System.out.println(" <><>*");
    }

    /**
     * Prints the current list of tasks
     */
    public void printListUI(ArrayList<Task> tasks, int taskListCount) { //
        if (taskListCount==0) {
            System.out.println("No tasks available...");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskListCount ; i ++) {
            System.out.println( (i+1)+"."+tasks.get(i) ); //+1 to i here due to numbering
        }
    }

    /**
     * Prints a help message that is available to users
     */
    public void printHelpMessage() {
        String helpMessage =
                "Here are the list of commands!\n"
                + "\ttodo: Creates a Todo task. Input format: 'todo [task name]'\n"
                + "\tevent: Creates a Event task. Input format: 'event [task name] /at [date in yyyy-MM-dd format]'\n"
                + "\tdeadline: Creates a Deadline task. Input format: 'deadline [task name] /by [date in yyyy-MM-dd format]'\n"
                + "\tschedule: Creates a Schedule task. Input format: 'schedule [task name] /from [date in yyyy-MM-dd format] /to [date in yyyy-MM-dd format]'\n"
                + "\tmark/unmark: Marks a task done or undone. Input format: 'mark [task number]' or 'unmark [task number]\n"
                + "\tlist: List all task descriptions and number. Input format 'list'\n"
                + "\tdelete: Deletes a task. Input format: 'delete [task number]\n"
                + "\tfind: Finds all tasks related to find keyword. Input format: 'find [keyword]\n"
                + "\treschedule: Reschedules deadline and event tasks (doesn't work on schedule tasks). Input format: 'reschedule [date in yyyy-MM-dd format]\n";
        System.out.println(helpMessage);
    }

    /**
     * Print the related information when user wants to reschedule a task
     */
    public void printRescheduleInfo(int taskNum, TasksWithDate currTaskToReschedule) { //
        System.out.printf("Rescheduled task number %d to \n\t%s\n\n",taskNum + 1 , currTaskToReschedule);
    }

    /**
     * Prints error message if user input a wrong reschedule message.
     */
    public void printRescheduleErrorMessage(int taskNum, Task erroredTask) {
        System.out.printf("Task number %d, '%s' can't be rescheduled\n",taskNum+1,erroredTask.getTask());
    }

    /**
     * Prints error message when user inputs wrong date format
     */
    public void printParseStringToDateErrorMEssage() {
        System.out.printf("Error, input date not in format yyyy-mm-dd\n");
    }

}