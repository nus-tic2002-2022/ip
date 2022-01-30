import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static String repeat(char ch, int count) {
        char[] buf = new char[count];
        Arrays.fill(buf, ch);
        return new String(buf);
    }

    public static void main(String[] args) {
        System.out.println("I'm Knot YU\n");
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            System.out.print("Can I help you?\n");

            line = in.nextLine();
            if (line.equals("no")) {
                break;
            }
            System.out.println("->\t\t" + line + "\n\n");
        }
        var width = 12;
        System.out.println(repeat('*', width));
        System.out.println("See YU never");
        System.out.println(repeat('*', width));
    }
}
