import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];
    private static int listCount = 0;

    public static void addItem(String item) {
        list[listCount] = item;
        listCount++;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke\n" + "What can I do for you?");

        String input;
        Scanner in = new Scanner(System.in);
        boolean bye = false;

        while (bye == false) {
            input = in.nextLine();

            if (input.toLowerCase().equals("bye")) {
                System.out.println("Bye bye!");
                bye = true;
            }
            if (input.toLowerCase().equals("list")) {
                for (int i = 0; i < listCount; i++){
                    System.out.println(list[i]);
                }
            }
            else {
                addItem(input);
                System.out.println("added: " + input);
            }
        }
    }
}
