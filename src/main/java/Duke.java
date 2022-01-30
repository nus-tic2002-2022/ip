import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    static ArrayList<String> storages = new ArrayList<>();
    private static String repeat(char ch, int count) {
        char[] buf = new char[count];
        Arrays.fill(buf, ch);
        return new String(buf);
    }

    public static void main(String[] args) {
        System.out.println("I'm Knot YU");
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            System.out.print("\nCan I help you?\n");

            line = in.nextLine();
            if (line.equals("no")) {
                break;
            }

            if (line.equals("list")) {
                for (int i = 0; i < storages.size(); i++)
                    System.out.println(i+1 + ": " + storages.get(i));
            } else {
                storages.add(line);
                System.out.println("->\t\t+ " + line + "");
            }
        }
        var width = 12;
        System.out.println(repeat('*', width));
        System.out.println("See YU never");
        System.out.println(repeat('*', width));
    }
}
