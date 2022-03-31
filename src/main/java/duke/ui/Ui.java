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
                TAB + MESSAGE_WELCOME,
                TAB + DIVIDER);
    }

    /**
     * Shows message(s) to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showLoadingError() {
        showToUser(TAB + MESSAGE_INIT_FAILED);
    }

    public String readCommand() {
        showToUser(TAB + "Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
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
        showToUser(TAB +MESSAGE_TASK_DELETE);
        showToUser(TAB +task.toString());
    }
    public void showMarked(Task task) {
        showToUser(TAB +MESSAGE_TASK_MARK);
        showToUser(TAB +task.toString());
    }

    public void showUnMarked(Task task) {
        showToUser(TAB +MESSAGE_TASK_MARK);
        showToUser(TAB +task.toString());
    }


    public void showAdded(Task task) {
        showToUser(TAB +MESSAGE_TASK_ADD);
        showToUser(TAB +task.toString());
    }

    public void showTaskSize(int size) {
        showToUser(TAB +String.format(MESSAGE_TASKS_LISTED_OVERVIEW, size));
    }

    public void showTask(TaskList s) {
        showToUser(TAB +MESSAGE_TAKS_SHOW);
        List<String> str = s.getTasksString();

        for (int i = 0; i < str.size(); i++) {
            showToUser(TAB +str.get(i));

        }
    }

}
