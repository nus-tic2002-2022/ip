package duke.utils;

import duke.task.*;
import java.util.ArrayList;

public class Ui
{
    public Ui() { };

    public static void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.\n");
        System.out.println("\tHow may I help? (I take carrots as payment). \n");
    }

    public static void printBye() {
        System.out.println("Already? Cya...");
        System.out.println("  //");
        System.out.println(" ('')");
        System.out.println(" UU \\   ");
        System.out.println(" <><>*");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n"
                +"Type 'help' for a list of commands!";

    }

    public static void printListUI(ArrayList<Task> tasks, int taskListCount) { //
        if (taskListCount==0) {
            System.out.println("No tasks available...");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < taskListCount ; i ++) {
            System.out.println( (i+1)+"."+tasks.get(i) ); //+1 to i here due to numbering
        }
    }

    public static void printHelpMessage() {
        String helpMessage =
                "Here are the list of commands!\n"
                + "\ttodo: \n"
                + "\tevent: \n"
                + "\tdeadline: \n"
                + "\tschedule: \n";
                + "\tmark/unmark: \n"
                + "\tlist: \n"
                + "\tdelete: \n"
                + "\tfind: \n"
                + "\treschedule: \n";
        System.out.println(helpMessage);
    }

    public static void printRescheduleInfo(int taskNum, TasksWithDate currTaskToReschedule) { //
        System.out.printf("Rescheduled task number %d to \n\t%s\n\n",taskNum + 1 , currTaskToReschedule);
    }

    public static void printRescheduleErrorMessage(int taskNum, Task erroredTask) {
        System.out.printf("Task number %d, '%s' can't be rescheduled\n",taskNum+1,erroredTask.getTask());
    }

    public static void printParseStringToDateErrorMEssage() {
        System.out.printf("Error, input date not in format yyyy-mm-dd\n");
    }

}