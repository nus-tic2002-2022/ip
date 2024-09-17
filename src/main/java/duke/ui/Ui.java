package duke.ui;

import duke.commands.ResultCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

import java.util.Random;
import java.util.Scanner;

/**
 * This class handles UI interaction between Haro and User by capturing user input and generate reply message then output it to user.
 * It also stores the user's name requested at the start of every session.
 */
public class Ui {

    public static String userName;

    /**
     * Constructor for Ui and set userName as unknown.
     */
    public Ui() {
        userName = "???";
    }

    /**
     * Show welcome message on UI.
     */
    public static void showWelcomeMessage() {
        String helloFiglet = "\n"
                + " _   _        _  _              My Name is \n"
                + "| | | |      | || |              _   _     _     ____    ___  \n"
                + "| |_| |  ___ | || |  ___        | | | |   / \\   |  _ \\  / _ \\ \n"
                + "|  _  | / _ \\| || | / _ \\       | |_| |  / _ \\  | |_) || | | |\n"
                + "| | | ||  __/| || || (_) |      |  _  | / ___ \\ |  _ < | |_| |\n"
                + "\\_| |_/ \\___||_||_| \\___/       |_| |_|/_/   \\_\\|_| \\_\\ \\___/ \n";

        System.out.println(helloFiglet);
        System.out.println("\t\t\t\t\t\t\t\t HARO : ");
        System.out.println("\t\t\t\t\t\t\t\t Haro ! How may I address you ? Haro !");

        System.out.println(userName + " :");
        Scanner in = new Scanner(System.in);
        userName = in.nextLine();

        System.out.println("\t\t\t\t\t\t\t\t HARO : ");
        System.out.println("\t\t\t\t\t\t\t\t Haro ! " + userName + ", How may Haro help you today ? Haro ");

    }

    /**
     * Show username on UI.
     */
    public static void displayUsername() {
        System.out.println(userName + " : ");
    }

    /**
     * Show goodbye message on UI.
     */
    public static void goodbyeMessage() {
        String message = generateMessage("goodbye", " ");
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message + "\n\n");
    }

    /**
     * Show error message generated on UI based on DukeException provided.
     *
     * @param e DukeException error with details on type of error
     */
    public static void errorMessage(DukeException e) {
        String message = generateMessage(e.getError(), " ");
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message);
    }

    /**
     * Show io error message generated on UI.
     */
    public static void ioErrorMessage() {
        String message = generateMessage("fileLoadFail", " ");
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message);
    }

    /**
     * Echo String variable provided with generated message on UI.
     *
     * @param placeholder String to be echoed.
     */
    public static void echoMessage(String placeholder) {
        String message = generateMessage("echo", placeholder);
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message);
    }

    /**
     * Output generated response message to user on UI based on details on ResultCommand.
     *
     * @param result ResultCommand with information of executed action.
     */
    public static void replyMessage(ResultCommand result) {
        String placeholder = "";
        if (result.getTargetString() == null) {
            if (result.getTargetItem().getTask() != null) {
                placeholder = result.getTargetItem().getTask();
            } else {
                placeholder = result.getTargetString();
            }
        } else {
            placeholder = result.getTargetString();
        }

        String message = generateMessage(result.getFeedback(), placeholder);
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message);
    }

    /**
     * Output taskList on UI based on TaskList provided.
     *
     * @param taskList TaskList to be outputted onto UI.
     */
    public static void showTaskList(TaskList taskList) {
        String message = generateMessage("getList", "");
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        System.out.println(message);
        System.out.print(taskList);
        System.out.println("\t\t\t\t\t\t\t\t Haro ! You now have " + taskList.size() + " tasks in the list ! Haro !");
    }

    /**
     * Output searched taskList on UI based on TaskList provided.
     *
     * @param taskList TaskList to be outputted onto UI.
     */
    public static void showSearchTaskList(TaskList taskList) {
        System.out.println("\t\t\t\t\t\t\t\t Haro : ");
        if (taskList.size() != 0 ) {
            System.out.print(taskList);
            System.out.println("\t\t\t\t\t\t\t\t Haro ! " + taskList.size() + " tasks has been found ! Haro !");
        } else {
            System.out.println("\t\t\t\t\t\t\t\t Haro ! No tasks with the specified keyword has been found ! Haro !");
        }
    }

    /**
     * Return user input as String after obtaining it from UI.
     *
     * @return user input as String.
     */
    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Return generated message based on command and placeholder variable provided.
     * Some command has multiple possible response and the decision on which message to return is determined by a random number generator.
     *
     * @return message generated based on command and placeholder variable as String.
     */
    public static String generateMessage(String command, String placeholder) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);

        String output = "";

        switch (command) {
            case "getList":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Here's the list ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Roger that ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Understood ! Haro !";
                }
                break;

            case "emptyList":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The list is empty ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! You might want to add some item(s) into the empty list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! There is no item in the list ! Haro !";
                }
                break;

            case "addTask":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! \"" + placeholder + "\" has been added to the list ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro has added \"" + placeholder + "\" into the list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! new Task : \"" + placeholder + "\" will be added to the list ! Haro !";
                }
                break;

            case "updateTask":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! \"" + placeholder + "\" has been updated in the list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro has updated \"" + placeholder + "\" in the list ! Haro !";
                }
                break;

            case "missing task & missing by":
                output = "\t\t\t\t\t\t\t\t Haro ! Missing task & deadline information ! Haro ";
                break;

            case "missing task" :
                output = "\t\t\t\t\t\t\t\t Haro ! Missing task information ! Haro ";
                break;

            case "missing by" :
                output = "\t\t\t\t\t\t\t\t Haro ! Missing deadline information ! Haro ";
                break;

            case "missing task & missing at" :
                output = "\t\t\t\t\t\t\t\t Haro ! Missing event & duration information ! Haro ";
                break;

            case "missing at" :
                output = "\t\t\t\t\t\t\t\t Haro ! Missing duration information ! Haro ";
                break;

            case "goodbye":
                output = "\t\t\t\t\t\t\t\t Haro ! Goodbye and Have a nice day ! Haro !";
                break;

            case "echo":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! You mean \"" + placeholder + "\" ? Haro ? ";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! \"" + placeholder + "\" ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! echoing \"" + placeholder + "\" ! Haro !";
                }
                break;

            case "updateList":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro has updated the list as per your command! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Here is the updated list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Update ! Haro ! Update list ! Haro !";
                }
                break;

            case "markNoChange":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The task is already marked ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Unable to mark as per command ! Task indicated is marked ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Cannot mark tasks that has been marked ! Haro !";
                }
                break;

            case "unmarkNoChange":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The task is already unmarked ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Unable to unmark as per command ! Task indicated is unmarked ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Cannot unmark tasks that has been unmarked ! Haro !";
                }
                break;

            case "outOfRangeIndex":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The index is out of range ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Please enter a valid index ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Unable to find the input index ! Haro !";
                }
                break;

            case "invalidIndex":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The index must only contain numerical numbers ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Alphabetic characters are not allowed in index ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Unable to proceed with command due to non-numerical index input ! Haro !";
                }
                break;

            case "invalidDate":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! The date time format has to be dd/MM/yyyy HHmm or dd-MM-yyyy HHmm! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Please make sure the date time format is in dd/MM/yyyy HHmm or dd-MM-yyyy HHmm ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Check date time format Haro ! dd/MM/yyyy HHmm or dd-MM-yyyy HHmm ! Haro !";
                }
                break;

            case "missingTime":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Missing date time information ! Please follow dd/MM/yyyy HHmm or dd-MM-yyyy HHmm! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Please enter date time information in dd/MM/yyyy HHmm or dd-MM-yyyy HHmm ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Check date time information Haro ! dd/MM/yyyy HHmm or dd-MM-yyyy HHmm ! Haro !";
                }
                break;

            case "missingKeyword":
                output = "\t\t\t\t\t\t\t\t Haro ! Please key in a keyword to be searched ! Haro !";
                break;

            case "missingInformation":
                output = "\t\t\t\t\t\t\t\t Haro ! Please input new description to be updated ! Haro !";
                break;

            case "freeTime":
                output = "\t\t\t\t\t\t\t\t Haro ! " + placeholder + " ! Haro !";
                break;

            case "delete":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Indicated task has been deleted from the list ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Understood ! Haro has removed specified task from the list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Roger that ! That task has been removed from the list ! Haro !";
                }
                break;

            case "hello":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Feel free to let me know how can I help you ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro ! Haro !";
                }
                break;

            case "save":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! task.txt has been updated the data folder according to the list ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro updated task.txt according to the list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Roger that ! Successfully updated task.txt in data folder ! Haro !";
                }
                break;

            case "create":
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Duke.txt has been created in the data folder with to the list content ! Haro !";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro created Duke.txt in data folder according to the current list ! Haro !";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Roger that ! Successfully created Duke.txt in data folder with list content ! Haro !";
                }
                break;

            case "fileLoadFail":
                output = "\n[Issue]\tDetecting error in loading task list file.\nPlease try manually create Directory and File \"data\\task.txt\"";
                break;

            case "readError":
                output = "\n[Issue]\tDetecting error in reading task list file.\nPlease ensure File \"data\\task.txt\" is formatted correctly";
                break;

            default :
                if (randomNumber == 0) {
                    output = "\t\t\t\t\t\t\t\t Haro ! What do you mean ? Haro ?";
                } else if (randomNumber == 1) {
                    output = "\t\t\t\t\t\t\t\t Haro ! Unable to comprehend the meaning ! Haro ?";
                } else {
                    output = "\t\t\t\t\t\t\t\t Haro ! Haro does not understand ! Haro ?";
                }
                break;
        }
        return output;
    }
}