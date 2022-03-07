import java.lang.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void addTodo (String[] inputContent, List<Task> toDoList) throws DukeException{
        String content = "";
        for (int i = 1; i < inputContent.length; i++) {
            content = content + " " + inputContent[i];
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")){
            throw new DukeException("missing task");
        }
        else {
            Task curTask = new Task(content);
            toDoList.add(curTask);
        }
    }

    public static void addDeadline (String[] inputContent, List<Task> toDoList) throws DukeException{
        String content = "";
        String by = "";
        for (int i = 1; i < inputContent.length;) {
            if (!inputContent[i].equalsIgnoreCase("/by")) {
                content = content + " " + inputContent[i];
                i++;
            }
            if (i < inputContent.length && inputContent[i].equalsIgnoreCase("/by")) {
                by = by + "(by :";
                i++;
                while (i < inputContent.length) {
                    by = by + " " + inputContent[i];
                    i++;
                }
                by = by + ")";
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")) {
            if (by.equalsIgnoreCase("")|| by.equalsIgnoreCase("(by : )")){
                throw new DukeException("missing task & missing by");
            }
            else {
                throw new DukeException("missing task");
            }
        }
        else {
            if (by.equalsIgnoreCase("") || by.equalsIgnoreCase("(by : )")) {
                throw new DukeException("missing by");
            } else {
                Deadline curTask = new Deadline(content, by);
                toDoList.add(curTask);
            }
        }
    }

    public static void addEvent (String[] inputContent, List<Task> toDoList) throws DukeException {
        String content = "";
        String at = "";
        for (int i = 1; i < inputContent.length;) {
            if (!inputContent[i].equalsIgnoreCase("/at")) {
                content = content + " " + inputContent[i];
                i++;
            }
            if (i < inputContent.length && inputContent[i].equalsIgnoreCase("/at")) {
                at = at + "(at :";
                i++;
                while (i < inputContent.length) {
                    at = at + " " + inputContent[i];
                    i++;
                }
                at = at + ")";
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")) {
            if (at.equalsIgnoreCase("")|| at.equalsIgnoreCase("(at : )")){
                throw new DukeException("missing task & missing at");
            }
            else {
                throw new DukeException("missing task");
            }
        }
        else {
            if (at.equalsIgnoreCase("")|| at.equalsIgnoreCase("(at : )")){
                throw new DukeException("missing at");
            }
            else {
                Event curTask = new Event(content, at);
                toDoList.add(curTask);
            }
        }
    }

    public static String deleteTask (int index, List<Task> toDoList) throws DukeException{
        String output = "";
        if (index > -1 && index < toDoList.size()) {
            output = toDoList.get(index).getTask();
            toDoList.remove(index);
        }
        else {
            throw new DukeException("Out of Range");
        }
        return output;
    }

    public static void checkToDoList(List <Task> toDoList, int index) throws DukeException{
        if (index > toDoList.size()) {
            throw new DukeException("Out of Range");
        }
    }

    public static void saveList(String filePath, List <Task> toDoList) throws DukeException, IOException {
        int createNew = 0;
        if (toDoList.size() == 0) {
            throw new DukeException("Empty List");
        }
        String textToAdd = "| index | type | status | task (Deadline / Duration) ";

        File f = new File(filePath);
        if (f.createNewFile()){
            createNew = 1;
        }

        int index = 1;
        for (Task t : toDoList) {
            textToAdd = textToAdd + "\n";
            String task = t.toString();
            char type = task.charAt(1);
            String cur_task = "";

            String status = "";
            if (t.getTaskStatus()) {
                status = " Done ";
                cur_task = task.substring(11);
            } else {
                status = "      ";
                cur_task = task.substring(9);
            }
            textToAdd = textToAdd + "|   " + index + ".  |  " + Character.toString(type) + "   | " + status + " | " + cur_task;
            index++;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();

        System.out.println("\t\t\t\t\t\t\t\t HARO : ");
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        if (createNew == 0) {
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Duke.txt has been updated the data folder according to the list ! Haro !");
            } else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro updated Duke.txt according to the list ! Haro !");
            } else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Roger that ! Successfully updated Duke.txt in data folder ! Haro !");
            }
        }
        else {
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Duke.txt has been created in the data folder with to the list content ! Haro !");
            } else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Haro created Duke.txt in data folder according to the current list ! Haro !");
            } else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Roger that ! Successfully created Duke.txt in data folder with list content ! Haro !");
            }
        }

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
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Update ! Haro ! Update list ! Haro !");
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
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Cannot execute \"" + reply + "\" command Haro ! Check the list ! Haro !");
            }
        }
        else if (code == 6) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! The index is out of range ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Please enter a valid index ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Unable to find the input index ! Haro !");
            }
        }
        else if (code == 7) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! The index must only contain numerical numbers ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Alphabetic characters are not allowed in index ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Unable to proceed with command due to non-numerical index input ! Haro !");
            }
        }
        else if (code == 8) {
            System.out.println("\t\t\t\t\t\t\t\t HARO : ");
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! \" " + reply + " \" has been deleted from the list ! Haro !");
            }
            else if (randomNumber == 1) {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Understood ! Haro has removed \" " + reply + " \" from the list ! Haro !");
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t Haro ! Roger that ! \" " + reply + " \" has been removed from the list ! Haro !");
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
                System.out.println(t);
                index++;
            }
        }
    }

    public static void main (String[] args) throws DukeException{
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
            if (inputContent[0].equalsIgnoreCase("todo")) {
                code = 2;
                try {
                    addTodo(inputContent, toDoList);
                    reply = toDoList.get(toDoList.size()-1).getTask();
                } catch (DukeException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You have entered an invalid command for \"todo\" ! Haro ");
                    if (e.getError().equalsIgnoreCase("missing task")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing task information ! Haro ");
                    }
                    code = -3;
                }
            }
            else if (inputContent[0].equalsIgnoreCase("deadline")) {
                code = 2;
                try {
                    addDeadline(inputContent, toDoList);
                    reply = toDoList.get(toDoList.size()-1).getTask();
                } catch (DukeException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You have entered an invalid command for \"deadline\"! Haro ");
                    if (e.getError().equalsIgnoreCase("missing task & missing by")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing task & deadline information ! Haro ");
                    }
                    else if (e.getError().equalsIgnoreCase("missing task")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing task information ! Haro ");
                    }
                    else if (e.getError().equalsIgnoreCase("missing by")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing deadline information ! Haro ");
                    }
                    code = -3;
                }
            }
            else if (inputContent[0].equalsIgnoreCase("event")) {
                code = 2;
                try {
                    addEvent(inputContent, toDoList);
                    reply = toDoList.get(toDoList.size()-1).getTask();
                } catch (DukeException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You have entered an invalid command for \"event\"! Haro ");
                    if (e.getError().equalsIgnoreCase("missing task & missing at")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing event & duration information ! Haro ");
                    }
                    else if (e.getError().equalsIgnoreCase("missing task")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing event information ! Haro ");
                    }
                    else if (e.getError().equalsIgnoreCase("missing at")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Missing duration information ! Haro ");
                    }
                    code = -3;
                }
            }
            else if (inputContent[0].equalsIgnoreCase("delete")) {
                code = 8;
                try {
                    int deleteIndex = Integer.parseInt(inputContent[1]) - 1;
                    reply = deleteTask(deleteIndex, toDoList);
                } catch (DukeException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You have entered an invalid command for \" delete \" ! Haro ");
                    if (e.getError().equalsIgnoreCase("Out of Range")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! Error ! Please enter a valid index ! Haro ");
                    }
                    code = -3;
                }
            }
            else if (inputContent[0].equalsIgnoreCase("mark")) {
                boolean check = true;
                for (int i = 0; i < inputContent[1].length(); i++){
                    if (!Character.isDigit(inputContent[1].charAt(i))) {
                        check = false;
                        break;
                    }
                }
                if (!check) {
                    code = 7;
                }
                else {
                    int targetIndex = Integer.parseInt(inputContent[1]);
                    targetIndex--;

                    if (targetIndex < toDoList.size()) {
                        boolean updateStatus = toDoList.get(targetIndex).setTaskStatus(true);
                        if (updateStatus) {
                            code = 4;
                            reply = userName;
                        } else {
                            code = 5;
                            reply = "mark";
                        }
                    } else {
                        code = 6;
                    }
                }
            }
            else if (inputContent[0].equalsIgnoreCase("unmark")) {
                boolean check = true;
                for (int i = 0; i < inputContent[1].length(); i++){
                    if (!Character.isDigit(inputContent[1].charAt(i))) {
                        check = false;
                        break;
                    }
                }
                if (!check) {
                    code = 7;
                }
                else {
                    int targetIndex = Integer.parseInt(inputContent[1]);
                    targetIndex--;

                    if (targetIndex < toDoList.size()) {
                        boolean updateStatus = toDoList.get(targetIndex).setTaskStatus(false);
                        if (updateStatus) {
                            code = 4;
                            reply = userName;
                        } else {
                            code = 5;
                            reply = "unmark";
                        }
                    } else {
                        code = 6;
                    }
                }
            }
            else if (inputContent[0].equalsIgnoreCase("save")) {
                code = -3;
                String file = "data/Duke.txt";
                File f = new File(file);
                try {
                    saveList(file, toDoList);
                } catch (DukeException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! Error ! Unable to save the list ! Haro ");
                    if (e.getError().equalsIgnoreCase("Empty List")) {
                        System.out.println("\t\t\t\t\t\t\t\t Haro ! The list is empty ! Haro ");
                    }
                } catch (IOException e) {
                    System.out.println("\t\t\t\t\t\t\t\t HARO : ");
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! Error ! Something is wrong ! Haro ");
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
            if (code == 1 ||code == 4 || code == 5 || code == 6) {
                printReply(reply, code);
                printToDoList(toDoList);
            }
            else if (code == 2) {
                printReply(reply, code);
                if (!toDoList.isEmpty()) {
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You now have " + toDoList.size() + " tasks in the list ! Haro !");
                }
            }
            else if (code == 8) {
                printReply(reply, code);
                printToDoList(toDoList);
                if (!toDoList.isEmpty()) {
                    System.out.println("\t\t\t\t\t\t\t\t Haro ! You now have " + toDoList.size() + " tasks in the list ! Haro !");
                }
            }
            else if (code == -2) {
                printToDoList(toDoList);
            }
            else if (code == -3) {
                // print nothing
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
