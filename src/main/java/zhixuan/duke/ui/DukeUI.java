package zhixuan.duke.ui;

import java.io.PrintStream;

import static zhixuan.duke.common.Messages.*;

public class DukeUI {

    private final PrintStream out;

    public DukeUI() {
        this(System.out);
    }

    public DukeUI(PrintStream out) {
        this.out = out;
    }

    public void showToUser(String message) {
        out.println(message);
    }

    public void printWelcomeMessage(boolean success) {
        showToUser(MESSAGE_LOGO);
        showToUser(getWelcomeMessage(success));
    }

    public void printEndingMessage() {
        showToUser(MESSAGE_GOODBYE);
    }

}
