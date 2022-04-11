import java.io.File;
import java.lang.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    public static void printline(){
        //to print separator between commands
        System.out.println("_______________________________________");
    }
    public static void printspace(){
        //to print empty line for organization
        System.out.println("\n");
    }

    static List<Task> tasklist = new ArrayList<Task>();

    public static void system(){
        //run program
        Speech.introduction();
        printline();
        printspace();
        int counter=0;
        String line;
        Scanner input = new Scanner(System.in);

        //saving parameters
        String filepathway = "Eventlists.txt";

        //begin loop for program
        while(true)
        {
            //capture inputs
            line=input.nextLine();
            //splitting sentences into words to analyze prefixes
            String[] dimensions = line.split(" ");
            if (dimensions[0].equals("bye")) {
                printline();
                Speech.farewell();
                System.exit(0);
            }
            //capture the word text in user input
            else if (dimensions[0].equals("list")) {
                printline();
                //for empty list
                if(tasklist.size()==0){
                    Speech.emptylist();
                }
                //non-empty list
                else {
                    int index = 1;
                    System.out.println("Yes Sir, this is the current list of Tasks you have on hand:\n");
                    //for loop to run all tasks in a tasklist
                    for(Task t : tasklist){
                        System.out.println(index + ". " +t.toString());
                        index++;
                    }
                    //to capture 1 item in list
                    if(tasklist.size()==1){
                        System.out.println("\nThere is a total of " + tasklist.size() + " task, don't think it requires much time to clear 1 Task Sir");
                    }
                    //different text for small items in list
                    else if(tasklist.size()<5){
                        System.out.println("\nThere are a total of " + tasklist.size() + " tasks, feel free to clear them at your earliest convenience, Sir");
                    }
                    //different text for many items in list
                    else{
                        System.out.println("\nThere are a total of " + tasklist.size() + " tasks, and it is getting too much to do, dont you think Sir?");
                    }
                }
            }
            //capture the word delete in user input
            else if (dimensions[0].equals("delete")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    //error handling for no items to delete
                    if (tasklist.size() == 0 || dimensions1.length<2) {
                        throw new DukeException("no number inserted or nothing on list");
                    }
                    //error handling for delete function beyond available items in the list
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("The number inserted to delete is larger than required");
                    }
                    //error handling for delete function for negative number input
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("The number inserted to delete is smaller than 0");
                    }
                    System.out.println("Yes Sir, I have deleted (" + tasklist.get(Integer.parseInt(dimensions[1]) - 1).getDescription() + ")");
                    //delete item in list
                    tasklist.remove(Integer.parseInt(dimensions[1]) - 1);
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    }
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
            //capture the word mark in user input
            else if (dimensions[0].equalsIgnoreCase("mark")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    //error handling for no input specified under mark
                    if(dimensions1.length<2){
                        throw new DukeException("Missing mark number");
                    }
                    //error handling for marking item that does not exist in list
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("The number inserted to mark is larger than required");
                    }
                    //error handling for marking item that does not exist in list for negative number input
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("The number inserted to mark is small than 0");
                    }
                    //error handling for marking item that is already marked
                    if (tasklist.get(Integer.parseInt(dimensions[1]) - 1).isDone == true) {
                        throw new DukeException("Been Marked Before");
                    }
                    //marking item that is done
                    tasklist.get(Integer.parseInt(dimensions[1]) - 1).markAsDone();
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    }
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
            //capture the word unmark in user input
            else if (dimensions[0].equalsIgnoreCase("unmark")) {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    //error handling for no item number
                    if (dimensions1.length < 2) {
                        throw new DukeException("Missing unmark number");
                    }
                    //error handling for item number outside of available list
                    if ((Integer.parseInt(dimensions[1])) > tasklist.size()) {
                        throw new DukeException("Too big");
                    }
                    //error handling for item number being negative
                    if ((Integer.parseInt(dimensions[1])) < 0) {
                        throw new DukeException("Too Small");
                    }
                    //error handling for item that is already unmarked
                    if (tasklist.get(Integer.parseInt(dimensions[1]) - 1).isDone == false) {
                        throw new DukeException("Been unmarked Before");
                    }
                    //unmark item in tasklist
                    tasklist.get(Integer.parseInt(dimensions[1]) - 1).markAsUndone();
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    }
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
            //capture the word event in user input
            else if (dimensions[0].equalsIgnoreCase("event"))
            {
                try {
                    String[] dimensions1 = line.split(" ");
                    String sentence = "";
                    //error handling for items after event
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Event");
                    }
                    sentence=dimensions[1];
                    //loop to append words into a sentence
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    //splitting words with separator "/at"
                    String[] dimensions2 = sentence.split("/at");
                    //error handling for no items after "/at"
                    if(dimensions2.length<2){
                        throw new DukeException("Missing Event Time and Place");
                    }
                    DateTimeFormatter Standard = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String UserInput = dimensions2[1].substring(1);
                    LocalDateTime Eventdate = LocalDateTime.parse(UserInput, Standard);
                    String Format = Eventdate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
                    //add event into tasklist
                    Event toadd = new Event(dimensions2[0], Format);
                    tasklist.add(toadd);
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (DateTimeParseException e) {
                        System.out.println("Sir, there is an issue with your date format, Let me remind you to key in this format: 22/03/2022 2359");
                    }
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
            //capture the word deadline in user input
            else if (dimensions[0].equalsIgnoreCase("deadline"))
            {
                try {
                    String[] dimensions1 = line.split(" ");
                    String sentence = "";
                    //error handling for no words after deadline
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Deadline Task");
                    }
                    sentence=dimensions[1];
                    //append words into a sentence
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    //splitting sentences into 2 parts by "/by"
                    String[] dimensions2 = sentence.split("/by");
                    //no items after "/by"
                    if(dimensions2.length<2){
                        throw new DukeException("Missing Deadline Date and Time");
                    }
                    DateTimeFormatter dateFormat  = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String UserInput = dimensions2[1].substring(1);
                    LocalDateTime Eventdate = LocalDateTime.parse(UserInput, dateFormat);
                    String Format = Eventdate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"));
                    //add Deadline items into a tasklist
                    Deadline toadd = new Deadline(dimensions2[0], Format);
                    tasklist.add(toadd);
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (DateTimeParseException e) {
                        System.out.println("Sir, there is an issue with your date format, Let me remind you to key in this format: 22/03/2022 2359");
                    }
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
            //capture the word todo in user input
            else if (dimensions[0].equalsIgnoreCase("todo"))
            {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                try {
                    //error handling for no specified todo item
                    if(dimensions1.length<2){
                        throw new DukeException("Missing Todo Task");
                    }
                    //to capture first word after todo
                    sentence=dimensions[1];
                    //loop to append words together into a sentence
                    for (int i = 2; i < dimensions1.length; i++) {
                        sentence = sentence + " " + dimensions[i];
                    }
                    //create new todo item
                    Todo toadd = new Todo(sentence, sentence);
                    //add task to a tasklist
                    tasklist.add(toadd);
                    try {
                        save(filepathway, tasklist);
                    } catch (DukeException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    } catch (IOException e) {
                        System.out.println("Sir, there is an issue with your directory and I am unable save your tasks");
                    }
                    //a counter for number of items in a list
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
            //error handling of input that does not exist within specified function
            else if (dimensions[0].equals("find")) {
                printline();
                int index = 1;
                String word = line.substring(5);
                System.out.println("Yes Sir, this is the current list of Tasks you have on hand:\n with "+word);
                //for loop to run all tasks in a tasklist
                for(Task t : tasklist){
                    if(t.description.toLowerCase().contains(word)) {
                        System.out.println(index + ". " + t.toString());
                        index++;
                    }
                }

            }
            else{
                printline();
                System.out.println("Sir, May I remind you to use functions: Todo, delete, find, deadline, event, list, mark, unmark and bye");
            }
            printline();
        }
    }

    public static void save(String path, List<Task>tasklist) throws DukeException, IOException{
        String savetext = "No   Type  Mark  Description ";
        File savedfile = new File(path);
        int index = 1;
        //for loop to run all tasks in a tasklist
        for(Task t : tasklist){
            savetext=savetext + "\n" + " "+ index + ".  " + t.toString();
            index++;
        }
        FileWriter writefile = new FileWriter(path);
        writefile.write(savetext);
        writefile.close();
    }

    public static void readFileAsString(String fileName)throws Exception
    {
        String data = "";
        int counter=0;
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] dimensions1 = data.split("\n");
        for(int i=1;i<dimensions1.length;i++){
            String[] dimensions2 = dimensions1[i].split("\\s{3,3}");
            if(dimensions2[1].contains("[T]")){
                Todo toadd = new Todo(dimensions2[3], dimensions2[3]);
                tasklist.add(toadd);
                counter++;
                if(dimensions2[2].contains("[X]")){
                    tasklist.get((counter) - 1).markAsDone();
                }
            }
            else if(dimensions2[1].contains("[D]")){
                String[] dimensions3 = dimensions2[3].split("\\(+by+\\:+\\s{1,2}");
                dimensions3[1]=dimensions3[1].replace(")","");
                Deadline toadd = new Deadline(dimensions3[0], dimensions3[1]);
                tasklist.add(toadd);
                counter++;
                if(dimensions2[2].contains("[X]")){
                    tasklist.get((counter) - 1).markAsDone();
                }
            }
            else if(dimensions2[1].contains("[E]")){
                String[] dimensions3 = dimensions2[3].split("\\(+at+\\:+\\s{1,2}");
                dimensions3[1]=dimensions3[1].replace(")","");
                Event toadd = new Event(dimensions3[0], dimensions3[1]);
                tasklist.add(toadd);
                counter++;
                if(dimensions2[2].contains("[X]")){
                    tasklist.get((counter) - 1).markAsDone();
                }
            }
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
        File file = new File ("Eventlists.txt");
        boolean exists = file.exists();
        System.out.println(logo);
        printline();
        if(exists==true){
            try {
                readFileAsString("Eventlists.txt");
            } catch (Exception e) {
                System.out.println("Open file error");
            }
            System.out.println("A previous Task file exists and will be booted up. Here is the preloaded task lists from your previous session:");
            int index=1;
            for(Task t : tasklist){
                System.out.println(index + ". " +t.toString());
                index++;
            }
        }
        else{
            System.out.println("A previous Task file does not exist and will not be booted up");
        }
        printline();
        system();
    }
}

