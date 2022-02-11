import java.time.LocalTime;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(line);
        LocalTime now = LocalTime.now();
        int hours  = now.getHour();

        String greeting;
        String greeting2 = null;

        if(hours > 0 && hours < 12){
            greeting = "Good Morning!";
            greeting2 = "Bye! Have a nice day.";
        } else if (hours <=16){
            greeting = "Good Afternoon!";
            greeting2 = "Bye! Have a nice day.";
        } else if (hours <=21){
            greeting = "Good Evening!";
            greeting2 = "Bye! Good Night.";
        } else {
            greeting = "Good Night!";
        }

        System.out.println(greeting +" I'm Duke\n" +
                "What can I do for you?");

        System.out.println(line);

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("You: ");
            String Enter;
            Enter = in.nextLine();
            System.out.println("Duke: " + Enter);
            if (Enter.equals("bye")) {
                System.out.println(line);
                System.out.println( greeting2 +" \nHope to see you again soon!");
                break;
            }
        }
    }

}
