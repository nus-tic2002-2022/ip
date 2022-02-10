import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "";
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        String[] temp = new String[100];
        int itemCount = 0;
        int listCount = 1;

        while(!line.contains("bye")) {
            System.out.print("Type something: ");
            line = in.nextLine();

            if (line.equals("bye")){
                System.out.print("\tGood day and good bye");
                break;
            }

            if(line.equals("list")) {
                list = Arrays.copyOf(temp, itemCount);
                for (int i = 0; i < list.length; i++) {
                    System.out.print(listCount + ". " + list[i] + "\n");
                    listCount++;
                }
            }
            else {
                temp[itemCount] = line;
                itemCount++;
                System.out.println("\tadded: " + line);
            }

        }
    }
}
