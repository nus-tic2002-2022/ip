import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("---------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");

        String line;
        Scanner in = new Scanner(System.in);

        boolean start = true;

        while (start){
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye")){
                System.out.println("---------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------");
                start = false;
            }else{
                System.out.println("---------------------------------");
                System.out.println(line);
                System.out.println("---------------------------------");
            }
        }
    }
}
