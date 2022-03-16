public class UI
{
    public UI () { };

    public static void printIntroduction() {
        System.out.println("  (\\_/)");
        System.out.println("  (^_^)");
        System.out.println("  (____)0");
        System.out.println("\tHey how's it going? I'm Bugs, a transient robotic bunny.");
        System.out.println("\tHow may I help? (I take carrots as payment)");
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
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

}