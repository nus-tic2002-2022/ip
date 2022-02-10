import java.util.Scanner;

public class Level1 {
    public Scanner in = new Scanner(System.in);
    String input;

    public void query(){
        while(true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else {
                System.out.println(input);
            }
        }
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
