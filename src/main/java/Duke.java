import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static int counter = 0;
    public static int sequence = 1;
    public static String[] list = new String[100];

    public static void echo(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if(line.equals("bye")){
            System.out.println("Bye. HOpe to see you again soon!");
        }
        else if (line.equals("list")){
            String[] print = Arrays.copyOf(list, counter);
            for (String p : print){
                System.out.println(sequence + ". " + p);
                sequence ++;
            }
            System.out.println("\n");
            sequence = 1;
            echo();
        }
        else {
            System.out.println("added: " + line + "\n");
            list[counter] = line;
            counter ++;
            echo();
        }
    }

    public static void main(String[] args){
        String opening = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(opening);
        echo();
    }
}