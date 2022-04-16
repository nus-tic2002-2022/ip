package duke.ui;

import duke.data.entity.Task;
import duke.data.entity.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static duke.common.Messages.*;

public class Ui {

    /**
     * A platform independent line separator.
     */
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "------------------------------------------";
    private static final String TAB = "\t";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcome() {
        showToUser(
                TAB + DIVIDER,
                TAB + DIVIDER,
                TAB + MESSAGE_WELCOME);

        String hello = "\n"
                + "GGGGGGGGGGGGGEEEEEEEEEEEEEEEEEEEEEENNNNNNNN        NNNNNNNNNNNNNNNN        NNNNNNNNIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEE\n"
                + "GGG::::::::::::GE::::::::::::::::::::EN:::::::N       N::::::NN:::::::N       N::::::NI::::::::IE::::::::::::::::::::E\n"
                + "GG:::::::::::::::GE::::::::::::::::::::EN::::::::N      N::::::NN::::::::N      N::::::NI::::::::IE::::::::::::::::::::E\n"
                + "G:::::GGGGGGGG::::GEE::::::EEEEEEEEE::::EN:::::::::N     N::::::NN:::::::::N     N::::::NII::::::IIEE::::::EEEEEEEEE::::E\n"
                + "G:::::G       GGGGGG  E:::::E       EEEEEEN::::::::::N    N::::::NN::::::::::N    N::::::N  I::::I    E:::::E       EEEEEE\n"
                + "G:::::G                E:::::E             N:::::::::::N   N::::::NN:::::::::::N   N::::::N  I::::I    E:::::E\n"
                + "G:::::G                E::::::EEEEEEEEEE   N:::::::N::::N  N::::::NN:::::::N::::N  N::::::N  I::::I    E::::::EEEEEEEEEE\n"
                + "G:::::G    GGGGGGGGGG  E:::::::::::::::E   N::::::N N::::N N::::::NN::::::N N::::N N::::::N  I::::I    E:::::::::::::::E\n"
                + "G:::::G    G::::::::G  E:::::::::::::::E   N::::::N  N::::N:::::::NN::::::N  N::::N:::::::N  I::::I    E:::::::::::::::E\n"
                + "G:::::G    GGGGG::::G  E::::::EEEEEEEEEE   N::::::N   N:::::::::::NN::::::N   N:::::::::::N  I::::I    E::::::EEEEEEEEEE\n"
                + "G:::::G        G::::G  E:::::E             N::::::N    N::::::::::NN::::::N    N::::::::::N  I::::I    E:::::E\n"
                + "G:::::G       G::::G  E:::::E       EEEEEEN::::::N     N:::::::::NN::::::N     N:::::::::N  I::::I    E:::::E       EEEEEE\n"
                + "G:::::GGGGGGGG::::GEE::::::EEEEEEEE:::::EN::::::N      N::::::::NN::::::N      N::::::::NII::::::IIEE::::::EEEEEEEE:::::E\n"
                + "GG:::::::::::::::GE::::::::::::::::::::EN::::::N       N:::::::NN::::::N       N:::::::NI::::::::IE::::::::::::::::::::E\n"
                + "GGG::::::GGG:::GE::::::::::::::::::::EN::::::N        N::::::NN::::::N        N::::::NI::::::::IE::::::::::::::::::::E\n"
                + "GGGGGG   GGGGEEEEEEEEEEEEEEEEEEEEEENNNNNNNN         NNNNNNNNNNNNNNN         NNNNNNNIIIIIIIIIIEEEEEEEEEEEEEEEEEEEEEE  \n";

        showToUser(hello);
        showToUser(MESSAGE_SHOW_TO_USER);
    }

    /**
     * Shows message(s) to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showToUserWithTAB(String... message) {
        for (String m : message) {
            out.println(TAB + m.replace("\n", LS));
        }
    }

    public String readCommand() {
        showToUserWithTAB("Enter command: ");
        String fullInputLine = in.nextLine();
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }


    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showError(String message) {
        showToUser(TAB + message);
    }

    public void showDeleted(Task task) {
        showToUserWithTAB(MESSAGE_TASK_DELETE);
        showToUserWithTAB(task.toString());
    }

    public void showMarked(Task task) {
        showToUserWithTAB(MESSAGE_TASK_MARK);
        showToUserWithTAB(task.toString());
    }

    public void showUnMarked(Task task) {
        showToUserWithTAB(MESSAGE_TASK_UNMARK);
        showToUserWithTAB(task.toString());
    }


    public void showAdded(Task task) {
        showToUserWithTAB(MESSAGE_TASK_ADD);
        showToUserWithTAB(task.toString());
    }

    public void showTaskSize(int size) {
        showToUserWithTAB(String.format(MESSAGE_TASKS_LISTED_OVERVIEW, size));
    }

    public void showTask(TaskList s) {
        List<String> str = s.getTasksString();
        for (String value : str) {
            showToUserWithTAB(value);
        }
    }
}
