import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-______________________-||\n");
        System.out.println("Hello! I am the most superior AI~~ Friday");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String echo = "";
        echo = in.nextLine();

        while(!echo.equals("bye")){
            System.out.println("=========================================" );
            System.out.println(echo);
            echo = in.nextLine();
        }
        System.out.println("=========================================" );
        System.out.println("Bye. Hope to see you again! Love you 3000 <3");
    }
}
