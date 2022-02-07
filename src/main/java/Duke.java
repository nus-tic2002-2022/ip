import java.util.Scanner;
import java.util.Random;

public class Duke {

    public static void printline(){
        System.out.println("_______________________________________");
    }

    public static void introduction(){
        System.out.println("Welcome Back Sir, Jarvis at your assistance" );
        printline();
    }

    public static void system(){
        introduction();
        String line;
        Scanner input = new Scanner(System.in);

        while(true)
        {
            line=input.nextLine();
            if (line.equals("bye")) {
                int byebye = (int)Math.floor((Math.random() * 7) + 1);
                switch (byebye) {
                    case 1:
                        System.out.println("Out Saving the world with the Avengers, Sir?");
                        break;
                    case 2:
                        System.out.println("Have a good day Sir");
                        break;
                    case 3:
                        System.out.println("Have you forgotten your Ironman Suit?");
                        break;
                    case 4:
                        System.out.println("Maybe take the subway instead of flying Sir");
                        break;
                    case 5:
                        System.out.println("Out to get your Cheeseburger Sir?");
                        break;
                    case 6:
                        System.out.println("The world should thank you for your service, Sir");
                        break;
                    case 7:
                        System.out.println("Of Course Sir, it is my pleasure serving you");
                        break;
                }
                break;
            }
            printline();
            System.out.println(line);
            printline();
        }
    }

    public static void main(String[] args) {
        String logo = "  ╭┳━━━┳━━━┳╮  ╭┳━━┳━━━╮\n"
                + "  ┃┃╭━╮┃╭━╮┃╰╮╭╯┣┫┣┫╭━╮┃\n"
                + "  ┃┃┃ ┃┃╰━╯┣╮┃┃╭╯┃┃┃╰━━╮\n"
                + "╭╮┃┃╰━╯┃╭╮╭╯┃╰╯┃ ┃┃╰━━╮┃\n"
                + "┃╰╯┃╭━╮┃┃┃╰╮╰╮╭╯╭┫┣┫╰━╯┃\n"
                + "╰━━┻╯ ╰┻╯╰━╯ ╰╯ ╰━━┻━━━╯";
        System.out.println(logo);
        printline();
        system();
    }
}
