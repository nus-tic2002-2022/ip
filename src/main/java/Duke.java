

/**
 * This is homework project of my TIC2002 module. It is a personal task manager that you
 * can arrange and store your personal schedule. It recognizes commands by
 * reading your inputs. Schedule types: 'deadline', 'todo', 'event'.
 * to marking your task, type 'done', or remove task, type 'delete'.
 * save and exit program, type 'bye'.
 *
 * @author S-Quan86
 * @version 1.0
 * @since 15-April 2022
 */
public class Duke {
    private static final String ln = "____________________________________________________________";

    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(ln);
        System.out.println("I'm Duke" + System.lineSeparator()
                + "What can I do for you?");
        System.out.println(ln);
    }

    public static void main(String[] args) throws Exception {
        Greet();
        MySiri.main();
        Storage.main();
    }
}