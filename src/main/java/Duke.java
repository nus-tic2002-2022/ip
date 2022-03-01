import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void printline(){
        System.out.println("_______________________________________");
    }
    public static void printspace(){
        System.out.println("\n");
    }


    static List<Task> tasklist = new ArrayList<Task>();

    public static void system(){
        Speech.introduction();
        printline();
        printspace();
        int counter=0;
        String line;
        Scanner input = new Scanner(System.in);

        while(true)
        {
            line=input.nextLine();
            String[] dimensions = line.split(" ");
            if (dimensions[0].equals("bye")) {
                printline();
                Speech.farewell();
                System.exit(0);
            }
            else if (dimensions[0].equals("list")) {
                printline();
                if(tasklist.size()==0){
                    Speech.emptylist();
                }
                else {
                    int index = 1;
                    System.out.println("Yes Sir, this is the current list of Tasks you have on hand:\n");
                    for(Task t : tasklist){
                        System.out.println(index + ". " +t.toString());
                        index++;
                    }
                    if(tasklist.size()==1){
                        System.out.println("\nThere is a total of " + tasklist.size() + " task, don't think it requires much time to clear 1 Task Sir");
                    }
                    else if(tasklist.size()<5){
                        System.out.println("\nThere are a total of " + tasklist.size() + " tasks, feel free to clear them at your earliest convenience, Sir");
                    }
                    else{
                        System.out.println("\nThere are a total of " + tasklist.size() + " tasks, and it is getting too much to do, dont you think Sir?");
                    }
                }
            }
            else if (dimensions[0].equals("delete")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    if (tasklist.size() == 0 || dimensions1.length<2) {
                        throw new DukeException("no number inserted or nothing on list");
                    }
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("The number inserted to delete is larger than required");
                    }
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("The number inserted to delete is smaller than 0");
                    }
                    System.out.println("Yes Sir, I have deleted (" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getDescription() + ")");
                    tasklist.remove(Integer.parseInt(dimensions[1]) - 1);
                } catch (DukeException e) {
                    if (e.getError().equalsIgnoreCase("no number inserted or nothing on list")) {
                        printline();
                        System.out.println("Sir, May i remind you that there are 0 tasks to delete for that matter");
                    }
                    if (e.getError().equalsIgnoreCase("The number inserted to delete is larger than required")) {
                        printline();
                        System.out.println("Sir, The number inserted to delete is larger than required");
                    }
                    if (e.getError().equalsIgnoreCase("The number inserted to delete is smaller than 0")) {
                        printline();
                        System.out.println("Sir, The number inserted to delete is smaller than 0");
                    }
                }
            }
            else if (dimensions[0].equalsIgnoreCase("mark")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    if(dimensions1.length<2){
                        throw new DukeException("Missing mark number");
                    }
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("The number inserted to mark is larger than required");
                    }
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("The number inserted to mark is small than 0");
                    }
                    if (tasklist.get(Integer.parseInt(dimensions[1]) - 1).isDone == true) {
                        throw new DukeException("Been Marked Before");
                    }
                    tasklist.get(Integer.parseInt(dimensions[1]) - 1).markAsDone();
                    System.out.println("Yes Sir, I have marked (" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getDescription() + ") as done " + "[" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getStatusIcon() + "]");
                } catch (DukeException e) {
                    if (e.getError().equalsIgnoreCase("The number inserted to mark is larger than required")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert a task number smaller than what you have on the list");
                    }
                    if (e.getError().equalsIgnoreCase("The number inserted to mark is small than 0")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert task number larger than 0");
                    }
                    if (e.getError().equalsIgnoreCase("Been Marked Before")) {
                        printline();
                        System.out.println("Sir, May i remind you to that this task was completed before");
                    }
                    if (e.getError().equalsIgnoreCase("Missing mark number")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert a number after mark");
                    }
                }
            }
            else if (dimensions[0].equalsIgnoreCase("unmark")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    if (dimensions1.length < 2) {
                        throw new DukeException("Missing unmark number");
                    }
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("Too big");
                    }
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("Too Small");
                    }
                    if (tasklist.get(Integer.parseInt(dimensions[1]) - 1).isDone == false) {
                        throw new DukeException("Been unmarked Before");
                    }
                    tasklist.get(Integer.parseInt(dimensions[1]) - 1).markAsUndone();
                    System.out.println("Yes Sir, I have unmarked (" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getDescription() + ") as undone " + "[" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getStatusIcon() + "]");

                } catch (DukeException e) {
                    if (e.getError().equalsIgnoreCase("Too big")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert a task number smaller than what you have on the list");
                    }
                    if (e.getError().equalsIgnoreCase("Too Small")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert task number larger than 0");
                    }
                    if (e.getError().equalsIgnoreCase("Been unmarked Before")) {
                        printline();
                        System.out.println("Sir, May i remind you to that this task was undone before you decided to unmark it");
                    }
                    if (e.getError().equalsIgnoreCase("Missing unmark number")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert a number after unmark");
                    }
                }
            }
            else if (dimensions[0].equalsIgnoreCase("event"))
            {
                try {
                    String[] dimensions1 = line.split(" ");
                    String sentence = "";
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Event");
                    }
                    sentence=dimensions[1];
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    String[] dimensions2 = sentence.split("/at");
                    if(dimensions2.length<2){
                        throw new DukeException("Missing Event Time and Place");
                    }
                    Event toadd = new Event(dimensions2[0], dimensions2[1]);
                    tasklist.add(toadd);
                    counter++;
                    printline();
                    Speech.todospeech();
                }
                catch (DukeException e) {
                    if (e.getError().equalsIgnoreCase("Missing Event")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert an Event");
                    }
                    if (e.getError().equalsIgnoreCase("Missing Event Time and Place")) {
                        printline();
                        System.out.println("Sir, May i remind you to insert an Event Time and Place");
                    }
                }
            }
            else if (dimensions[0].equalsIgnoreCase("deadline"))
            {
                try {
                    String[] dimensions1 = line.split(" ");
                    String sentence = "";
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Deadline Task");
                    }
                    sentence=dimensions[1];
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    String[] dimensions2 = sentence.split("/by");
                    if(dimensions2.length<2){
                        throw new DukeException("Missing Deadline Date and Time");
                    }
                    Deadline toadd = new Deadline(dimensions2[0], dimensions2[1]);
                    tasklist.add(toadd);
                    counter++;
                    printline();
                    Speech.todospeech();
                }
                catch (DukeException e) {
                if (e.getError().equalsIgnoreCase("Missing Deadline Task")){
                    printline();
                    System.out.println("Sir, May i remind you to insert a Deadline task to do");}
                if (e.getError().equalsIgnoreCase("Missing Deadline Date and Time")){
                    printline();
                    System.out.println("Sir, May i remind you to insert a Deadline Date and Time");}
                }
            }
            else if (dimensions[0].equalsIgnoreCase("todo"))
            {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Todo Task");
                    }
                    sentence=dimensions[1];
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    Todo toadd = new Todo(sentence, sentence);
                    tasklist.add(toadd);
                    counter++;
                    printline();
                    Speech.todospeech();
                    }
                catch (DukeException e) {
                    if (e.getError().equalsIgnoreCase("Missing Todo Task")){
                        printline();
                        System.out.println("Sir, May i remind you to insert a Todo task to do");}
                }
            }
            else{System.out.println("Sir, May I remind you to use functions: Todo, delete, deadline, event, list, mark, unmark and bye");
            }
            printline();
        }
    }

    public static void main(String[] args) {
        String logo = "\n\n________________Boot Up Sequence Activated_____________________\n\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢀⢄⢄⠢⡠⡀⢀⠄⡀⡀⠄⠄⠄⠄⠐⠡⠄⠉⠻⣻⣟⣿⣿⣄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⢣⠣⡎⡪⢂⠊⡜⣔⠰⡐⠠⠄⡾⠄⠈⠠⡁⡂⠄⠔⠸⣻⣿⣿⣯⢂⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⡀⠄⠄⠄⠄⠄⠄⠄⠐⢰⡱⣝⢕⡇⡪⢂⢊⢪⢎⢗⠕⢕⢠⣻⠄⠄⠄⠂⠢⠌⡀⠄⠨⢚⢿⣿⣧⢄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⡐⡈⠌⠄⠄⠄⠄⠄⠄⠄⡧⣟⢼⣕⢝⢬⠨⡪⡚⡺⡸⡌⡆⠜⣾⠄⠄⠄⠁⡐⠠⣐⠨⠄⠁⠹⡹⡻⣷⡕⢄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⢄⠇⠂⠄⠄⠄⠄⠄⠄⠄⢸⣻⣕⢗⠵⣍⣖⣕⡼⡼⣕⢭⢮⡆⠱⣽⡇⠄⠄⠂⠁⠄⢁⠢⡁⠄⠄⠐⠈⠺⢽⣳⣄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⢔⢕⢌⠄⠄⠄⠄⠄⢀⠄⠄⣾⢯⢳⠹⠪⡺⡺⣚⢜⣽⣮⣳⡻⡇⡙⣜⡇⠄⠄⢸⠄⠄⠂⡀⢠⠂⠄⢶⠊⢉⡁⠨⡒⠄⠄\n" +
                "⠄⠄⠄⠄⡨⣪⣿⢰⠈⠄⠄⠄⡀⠄⠄⠄⣽⣵⢿⣸⢵⣫⣳⢅⠕⡗⣝⣼⣺⠇⡘⡲⠇⠄⠄⠨⠄⠐⢀⠐⠐⠡⢰⠁⠄⣴⣾⣷⣮⣇⠄\n" +
                "⠄⠄⠄⠄⡮⣷⣿⠪⠄⠄⠄⠠⠄⠂⠠⠄⡿⡞⡇⡟⣺⣺⢷⣿⣱⢕⢵⢺⢼⡁⠪⣘⡇⠄⠄⢨⠄⠐⠄⠄⢀⠄⢸⠄⠄⣿⣿⣿⣿⣿⡆\n" +
                "⠄⠄⠄⢸⣺⣿⣿⣇⠄⠄⠄⠄⢀⣤⣖⢯⣻⡑⢕⢭⢷⣻⣽⡾⣮⡳⡵⣕⣗⡇⠡⡣⣃⠄⠄⠸⠄⠄⠄⠄⠄⠄⠈⠄⠄⢻⣿⣿⣵⡿⣹\n" +
                "⠄⠄⠄⢸⣿⣿⣟⣯⢄⢤⢲⣺⣻⣻⡺⡕⡔⡊⡎⡮⣿⣿⣽⡿⣿⣻⣼⣼⣺⡇⡀⢎⢨⢐⢄⡀⠄⢁⠠⠄⠄⠐⠄⠣⠄⠸⣿⣿⣯⣷⣿\n" +
                "⠄⠄⠄⢸⣿⣿⣿⢽⠲⡑⢕⢵⢱⢪⡳⣕⢇⢕⡕⣟⣽⣽⣿⣿⣿⣿⣿⣿⣿⢗⢜⢜⢬⡳⣝⢸⣢⢀⠄⠄⠐⢀⠄⡀⠆⠄⠸⣿⣿⣿⣿\n" +
                "⠄⠄⠄⢸⣿⣿⣿⢽⣝⢎⡪⡰⡢⡱⡝⡮⡪⡣⣫⢎⣿⣿⣿⣿⣿⣿⠟⠋⠄⢄⠄⠈⠑⠑⠭⡪⡪⢏⠗⡦⡀⠐⠄⠄⠈⠄⠄⠙⣿⣿⣿\n" +
                "⠄⠄⠄⠘⣿⣿⣿⣿⡲⣝⢮⢪⢊⢎⢪⢺⠪⣝⢮⣯⢯⣟⡯⠷⠋⢀⣠⣶⣾⡿⠿⢀⣴⣖⢅⠪⠘⡌⡎⢍⣻⠠⠅⠄⠄⠈⠢⠄⠄⠙⠿\n" +
                "⠄⠄⠄⠄⣿⣿⣿⣿⣽⢺⢍⢎⢎⢪⡪⡮⣪⣿⣞⡟⠛⠋⢁⣠⣶⣿⡿⠛⠋⢀⣤⢾⢿⣕⢇⠡⢁⢑⠪⡳⡏⠄⠄⠄⠄⠄⠄⢑⠤⢀⢠\n" +
                "⠄⠄⠄⠄⢸⣿⣿⣿⣟⣮⡳⣭⢪⡣⡯⡮⠗⠋⠁⠄⠄⠈⠿⠟⠋⣁⣀⣴⣾⣿⣗⡯⡳⡕⡕⡕⡡⢂⠊⢮⠃⠄⠄⠄⠄⠄⢀⠐⠨⢁⠨\n" +
                "⠄⠄⠄⠄⠈⢿⣿⣿⣿⠷⠯⠽⠐⠁⠁⢀⡀⣤⢖⣽⢿⣦⣶⣾⣿⣿⣿⣿⣿⣿⢎⠇⡪⣸⡪⡮⠊⠄⠌⠎⡄⠄⠄⠄⠄⠄⠄⡂⢁⠉⡀\n" +
                "⠄⠄⠄⠄⠄⠈⠛⠚⠒⠵⣶⣶⣶⣶⢪⢃⢇⠏⡳⡕⣝⢽⡽⣻⣿⣿⣿⣿⡿⣺⠰⡱⢜⢮⡟⠁⠄⠄⠅⠅⢂⠐⠄⠐⢀⠄⠄⠄⠂⡁⠂\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠰⠄⠐⢒⣠⣿⣟⢖⠅⠆⢝⢸⡪⡗⡅⡯⣻⣺⢯⡷⡯⡏⡇⡅⡏⣯⡟⠄⠄⠄⠨⡊⢔⢁⠠⠄⠄⠄⠄⠄⢀⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠹⣿⣿⣿⣿⢿⢕⢇⢣⢸⢐⢇⢯⢪⢪⠢⡣⠣⢱⢑⢑⠰⡸⡸⡇⠁⠄⠄⠠⡱⠨⢘⠄⠂⡀⠂⠄⠄⠄⠄⠈⠂⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⢻⣿⣿⣿⣟⣝⢔⢅⠸⡘⢌⠮⡨⡪⠨⡂⠅⡑⡠⢂⢇⢇⢿⠁⠄⢀⠠⠨⡘⢌⡐⡈⠄⠄⠠⠄⠄⠄⠄⠄⠄⠁\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠹⣿⣿⣿⣯⢢⢊⢌⢂⠢⠑⠔⢌⡂⢎⠔⢔⢌⠎⡎⡮⡃⢀⠐⡐⠨⡐⠌⠄⡑⠄⢂⠐⢀⠄⠄⠈⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣯⠂⡀⠔⢔⠡⡹⠰⡑⡅⡕⡱⠰⡑⡜⣜⡅⡢⡈⡢⡑⡢⠁⠰⠄⠨⢀⠐⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⠻⢿⣿⣷⣢⢱⠡⡊⢌⠌⡪⢨⢘⠜⡌⢆⢕⢢⢇⢆⢪⢢⡑⡅⢁⡖⡄⠄⠄⠄⢀⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠛⢿⣿⣵⡝⣜⢐⠕⢌⠢⡑⢌⠌⠆⠅⠑⠑⠑⠝⢜⠌⠠⢯⡚⡜⢕⢄⠄⠁⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠙⢿⣷⡣⣇⠃⠅⠁⠈⡠⡠⡔⠜⠜⣿⣗⡖⡦⣰⢹⢸⢸⢸⡘⠌⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⠋⢍⣠⡤⡆⣎⢇⣇⢧⡳⡍⡆⢿⣯⢯⣞⡮⣗⣝⢎⠇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠁⣿⣿⣎⢦⠣⠳⠑⠓⠑⠃⠩⠉⠈⠈⠉⠄⠁⠉⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⡿⡞⠁⠄⠄⢀⠐⢐⠠⠈⡌⠌⠂⡁⠌⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢂⢂⢀⠡⠄⣈⠠⢄⠡⠒⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠢⠠⠊⠨⠐⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄";

        System.out.println(logo);
        printline();
        system();
    }
}