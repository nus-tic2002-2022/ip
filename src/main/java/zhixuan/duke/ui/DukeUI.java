package zhixuan.duke.ui;

import java.io.PrintStream;
import static zhixuan.duke.common.Messages.MESSAGE_GOODBYE;
import static zhixuan.duke.common.Messages.MESSAGE_LOGO;
import static zhixuan.duke.common.Messages.MESSAGE_WELCOME;

public class DukeUI {

    private final PrintStream out;

    public DukeUI() {
        this(System.out);
    }

    public DukeUI(PrintStream out) {
        this.out = out;
    }

    private void showToUser(String message) {
        out.println(message);
    }

    public void printWelcomeMessage() {
        showToUser(MESSAGE_LOGO);
        showToUser(MESSAGE_WELCOME);
    }

    public void printEndingMessage() {
        showToUser(MESSAGE_GOODBYE);
    }

}
