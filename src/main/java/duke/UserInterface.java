package duke;

import java.util.Arrays;

public class UserInterface {

    public static void init() {
        System.out.println("I'm Knot YU");
    }

    /**
     * @param count number of counts to repeat
     * @param ch    character to repeat
     * @return new string buffer
     */
    public static String repeat(char ch, int count) {
        char[] buf = new char[count];
        Arrays.fill(buf, ch);
        return new String(buf);
    }

    public static void close() {
        var lineWidth = 12;
        System.out.println(UserInterface.repeat('*', lineWidth));
        System.out.println("See YU never");
        System.out.println(UserInterface.repeat('*', lineWidth));
    }
}
