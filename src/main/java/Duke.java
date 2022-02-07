import java.util.Scanner;
public class Duke {
    public static void printline(){
        System.out.println("_______________________________________");
    }

    public static void system(){
        String line;
        Scanner input = new Scanner(System.in);
        line=input.nextLine();
        if (line.equals("bye")) {
            System.out.println("Goodbye Sir");
        }
        else {
            printline();
            System.out.println(line);
            printline();
            system();
        }
    }

    public static void main(String[] args) {
        String logo = "  ╭┳━━━┳━━━┳╮  ╭┳━━┳━━━╮\n"
                + "  ┃┃╭━╮┃╭━╮┃╰╮╭╯┣┫┣┫╭━╮┃\n"
                + "  ┃┃┃ ┃┃╰━╯┣╮┃┃╭╯┃┃┃╰━━╮\n"
                + "╭╮┃┃╰━╯┃╭╮╭╯┃╰╯┃ ┃┃╰━━╮┃\n"
                + "┃╰╯┃╭━╮┃┃┃╰╮╰╮╭╯╭┫┣┫╰━╯┃\n"
                + "╰━━┻╯ ╰┻╯╰━╯ ╰╯ ╰━━┻━━━╯\n";
        System.out.println(logo + "Welcome Back Sir, Jarvis at your assistance\n" );
        printline();
        system();
    }
}
