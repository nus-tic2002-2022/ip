import java.util.Scanner;
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
                System.out.println("Goodbye Sir");
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
