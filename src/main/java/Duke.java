import java.lang.*;
import java.util.*;

public class Duke {

    public static String[] addIntoArray(String[] original, String add) {
        // create an array with +1 size and copy old array to new array
        String[] newOutput = new String[original.length + 1];
        System.arraycopy(original, 0, newOutput, 0,original.length);

        // add items[i] into new array
        newOutput[original.length] = add;

        // System.out.println(Arrays.toString(newOutput));
        return newOutput;
    }

    public static String[] tokenizer(String input) {
        String[] output = new String[]{};
        // iterate through input string
        for (int i = 0; i < input.length();) {
            String cur_word = "";
            // capture current word separated by space character
            do {
                cur_word = cur_word + Character.toString(input.charAt(i));
                i++;
            }
            while (i < input.length() && input.charAt(i) != ' ');
            cur_word = cur_word.stripLeading();
            cur_word = cur_word.stripTrailing();
            output = addIntoArray(output, cur_word);
        }
        return output;
    }

    public static void printReply(String reply, int code) {
        if (code == 1) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Here's the list ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Roger that ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Understood ! Haro !");
            }
        }
        else if (code == 2) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! \"" + reply + "\" has been added to the list ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro has added \"" + reply + "\" into the list ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! new Task : \"" + reply + "\" will be added to the list ! Haro !");
            }
        }
        else if (code == 3) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! You mean \"" + reply + "\" ? Haro ? ");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! \"" + reply + "\" ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! echoing \"" + reply + "\" ! Haro !");
            }
        }
        else if (code == 4) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro has updated the list as per your command! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Yes " + reply + " ! Here is the updated list ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Another done from the list ! Haro !");
            }
        }
        else if (code == 5) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! The task has already been " + reply + "ed ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Unable to " + reply + " as per command ! Here is the list for reference! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Cannot execute command Haro ! Check the list ! Haro !");
            }
        }
        else if (code == 0){
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            System.out.println("\t\t\t\t\t\t\t\t Haro ! Goodbye and Have a nice day ! Haro !");
            System.out.println(" ");
        }
        else if (code == -5){
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(2);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Feel free to let me know how can I help you ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro ! Haro !");
            }
            System.out.println(" ");
        }
        else {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! What do you mean ? Haro ?");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Unable to comprehend the meaning ! Haro ?");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro does not understand ! Haro ?");
            }
        }
    }

    public static void printToDoList(List <Task> toDoList) {
        if (toDoList.isEmpty()) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! The list is empty ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! You might want to add some item(s) into the empty list ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! There is item in the list ! Haro !");
            }
        }
        else {
            int index = 1;
            for (Task t : toDoList) {
                System.out.print("\t\t\t\t\t\t\t\t\t "+ index + ". ");
                String curStatus = "";
                if (t.getTaskStatus()) {
                    curStatus = "[Done]\t";
                }
                else {
                    curStatus = "[ ]\t";
                }
                System.out.println(curStatus + t.getTask());
                index++;
            }
        }
    }

    public static void main(String[] args) {
        String helloFiglet ="\n"
                            +      " _   _        _  _              My Name is \n"
                            + "| | | |      | || |              _   _     _     ____    ___  \n"
                            + "| |_| |  ___ | || |  ___        | | | |   / \\   |  _ \\  / _ \\ \n"
                            + "|  _  | / _ \\| || | / _ \\       | |_| |  / _ \\  | |_) || | | |\n"
                            + "| | | ||  __/| || || (_) |      |  _  | / ___ \\ |  _ < | |_| |\n"
                            + "\\_| |_/ \\___||_||_| \\___/       |_| |_|/_/   \\_\\|_| \\_\\ \\___/ \n";

        System.out.println(helloFiglet);
        System.out.println("\t\t\t\t\t\t\t\t HARO : ");
        System.out.println("\t\t\t\t\t\t\t\t Haro ! How may I address you ? Haro !");
        System.out.println("??? :");
        Scanner in = new Scanner(System.in);
        String userName = in.nextLine();
        System.out.println("\t\t\t\t\t\t\t\t HARO : ");
        System.out.println("\t\t\t\t\t\t\t\t Haro ! " + userName + ", How may Haro help you today ? Haro ");
        System.out.println(userName + " :");
        String input = "";
        List<Task> toDoList = new ArrayList<Task>();
        do {
            // initialization
            input = in.nextLine();
            String reply = "";
            int code = -1;

            // tokenization
            String[] inputContent = tokenizer(input);

            // case handling
            if (inputContent[0].equalsIgnoreCase("add")) {
                code = 2;
                for (int i = 1; i < inputContent.length; i++) {
                        reply = reply + " " + inputContent[i];
                }
                reply = reply.stripLeading();
                reply = reply.stripTrailing();
                Task curTask = new Task(reply);
                toDoList.add(curTask);
            }
            else if (inputContent[0].equalsIgnoreCase("mark")) {
                int targetIndex = Integer.parseInt(inputContent[1]);
                targetIndex--;
                boolean updateStatus = toDoList.get(targetIndex).setTaskStatus(true);
                if (updateStatus) {
                    code = 4;
                    reply = userName;
                }
                else {
                    code = 5;
                    reply = "mark";
                }

            }
            else if (inputContent[0].equalsIgnoreCase("unmark")) {
                int targetIndex = Integer.parseInt(inputContent[1]);
                targetIndex--;
                boolean updateStatus = toDoList.get(targetIndex).setTaskStatus(false);
                if (updateStatus) {
                    code = 4;
                    reply = userName;
                }
                else {
                    code = 5;
                    reply = "unmark";
                }
            }
            else if (inputContent[0].equalsIgnoreCase("echo")) {
                code = 3;
                for (int i = 1; i < inputContent.length; i++) {
                        reply = reply + " " + inputContent[i];
                }
                reply = reply.stripLeading();
                reply = reply.stripTrailing();
            }
            else if (inputContent[0].equalsIgnoreCase("list")) {
                if (toDoList.isEmpty()) {
                    code = -2;
                }
                else {
                    code = 1;
                }
            }
            else if (Arrays.stream(inputContent).anyMatch("bye"::equalsIgnoreCase) || Arrays.stream(inputContent).anyMatch("that's all"::equalsIgnoreCase)) {
                code = 0;
            }
            else if (inputContent[0].equalsIgnoreCase("hello")) {
                code = -5;
            }

            // output Handling
            if (code == 1 ||code == 4 || code == 5) {
                printReply(reply, code);
                printToDoList(toDoList);
            }
            else if (code == -2) {
                printToDoList(toDoList);
            }
            else {
                printReply(reply, code);
            }

            if (code != 0) {
                System.out.println(userName + " :");
            }
        }
        while (!input.equalsIgnoreCase("bye") && !input.equalsIgnoreCase("that's all"));
    }
}
