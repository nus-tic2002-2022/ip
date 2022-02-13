import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________\n");
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("________________________________\n");
        Scanner in = new Scanner(System.in);
        String str;
        str = in.nextLine();

        while (!str.equals("bye")){
                System.out.println("________________________________");
                System.out.println(str);
                str = in.nextLine();
        }
        System.out.println("________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
    }

}
