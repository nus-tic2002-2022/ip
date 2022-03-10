import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> storages = new ArrayList<>();

    private static String repeat(char ch, int count) {
        char[] buf = new char[count];
        Arrays.fill(buf, ch);
        return new String(buf);
    }

    private static void printList() {
        for (int i = 0; i < storages.size(); i++) {
            System.out.println("[" + storages.get(i).getStatusIcon() + "]" + (i + 1) + ": " + storages.get(i).description);
        }
    }

    public static void main(String[] args) {
        String command = "";
        String item = "";
        String[] tokens;
        System.out.println("I'm Knot YU");
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            System.out.print("\nCan I help you?\n");

            line = in.nextLine();

            tokens = line.split(" ");
            command = tokens[0];
            if (tokens.length > 1) {
                item = tokens[1];
            }
            if (command.equals("no")) {
                break;
            }

            switch (command){
                case "list":
                    printList();
                    break;
                case "mark":
                case "unmark":
                    if (Integer.parseInt(item) <= storages.size()) {
                        if (command.equals("mark")) {
                            storages.get(Integer.parseInt(item)-1).markAsDone();
                        } else {
                            storages.get(Integer.parseInt(item)-1).markAsUnDone();
                        }
                        printList();
                    } else {
                        System.out.println("Invalid index, try again");
                    }
                    break;
                default:
                    Task t = new Task(line);
                    storages.add(t);
                    System.out.println("->\t\t+ " + line + "");
            }
        }
        var width = 12;
        System.out.println(repeat('*', width));
        System.out.println("See YU never");
        System.out.println(repeat('*', width));
    }
}
