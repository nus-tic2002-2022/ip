package mainCom;
import subTask.DukeException;

public class Duke {
    public static void main (String[]args) throws DukeException {

            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo + "Hi! This mainCom.Duke :)\n" + "Let me load the data first :)\n");
            Keep.main();
            Ui.main();
        }
    }
