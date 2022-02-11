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
            else if (dimensions[0].equalsIgnoreCase("mark")) {
                tasklist.get(Integer.parseInt(dimensions[1])-1).markAsDone();
                System.out.println("Yes Sir, I have marked (" +tasklist.get(Integer.parseInt(dimensions[1])-1).getDescription() + ") as done "+ "[" + tasklist.get(Integer.parseInt(dimensions[1])-1).getStatusIcon() + "]");
            }
            else if (dimensions[0].equalsIgnoreCase("unmark")) {
                tasklist.get(Integer.parseInt(dimensions[1])-1).markAsUndone();
                System.out.println("Yes Sir, I have unmarked (" +tasklist.get(Integer.parseInt(dimensions[1])-1).getDescription() + ") as undone "+ "[" + tasklist.get(Integer.parseInt(dimensions[1])-1).getStatusIcon() + "]");
            }
            else if (dimensions[0].equalsIgnoreCase("event"))
            {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                for(int i=1;i< dimensions1.length;i++)
                {
                    sentence= sentence + " " + dimensions[i];
                }
                String[] dimensions2 = sentence.split("/at");
                Event toadd = new Event(dimensions2[0],dimensions2[1]);
                tasklist.add(toadd);
                counter++;
                printline();
                Speech.todospeech();
            }
            else if (dimensions[0].equalsIgnoreCase("deadline"))
            {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                for(int i=1;i< dimensions1.length;i++)
                {
                    sentence= sentence + " " + dimensions[i];
                }
                String[] dimensions2 = sentence.split("/by");
                Deadline toadd = new Deadline(dimensions2[0],dimensions2[1]);
                tasklist.add(toadd);
                counter++;
                printline();
                Speech.todospeech();
            }
            else if (dimensions[0].equalsIgnoreCase("todo"))
            {
                String[] dimensions1 = line.split(" ");
                String sentence = "";
                for(int i=1;i< dimensions1.length;i++)
                {
                    sentence= sentence + " " + dimensions[i];
                }
                Todo toadd = new Todo(sentence,sentence);
                tasklist.add(toadd);
                counter++;
                printline();
                Speech.todospeech();
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