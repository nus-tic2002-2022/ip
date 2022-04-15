
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