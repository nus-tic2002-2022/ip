public class Duke {
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void printHeader() {
        printDivider();
        String logo = "\t\t\t ____        _        \n"
                + "\t\t\t|  _ \\ _   _| | _____ \n"
                + "\t\t\t| | | | | | | |/ / _ \\\n"
                + "\t\t\t| |_| | |_| |   <  __/\n"
                + "\t\t\t|____/ \\__,_|_|\\_\\___|\tA variant";
        System.out.println(logo);
        printDivider();
    }

    public static void main(String[] args) {
        // Header
        printHeader();
        // Introduction Message
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }
}
