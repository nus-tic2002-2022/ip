package ui;
import java.io.*;
import java.util.*;
import tasks.*;
import Duke.*;

public class Storage extends Duke{

    private static final String path = "C:/Users/wangs/Documents/duke/data";
    private static final String filename = "duke.txt";
    private static final String filePath = path + filename;

    public static void main() throws Exception {
        checkFile();
        readFile();
    }
    /**
     * This method checks if duke.txt is there.
     * If It's not there, it will create folder and file automatically.
     *
     */
    public static void checkFile() throws Exception {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
    }
    /**
     * This method reads duke.txt, load and prints previous tasks.
     *
     */
    public static void readFile() throws Exception {
        try {
            System.out.println(System.lineSeparator()+
                    "Here is your Existing List");
            Scanner in = new Scanner(new File(filePath));
            while (in.hasNext()) {
                String input = in.nextLine();
                String[] _enter= input.split(" ", 2);
                String type = (_enter[0]).trim().toLowerCase();
                Type t =  Type.valueOf(type);
                if (input.length() > 0) {
                    MySiri.fileScanner(input, false, t);
                    MySiri.printList();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile() throws IOException {
        PrintWriter prw = new PrintWriter(filePath);
        StringBuilder inputs = new StringBuilder();
        for (Task t : MySiri.task) {
            inputs.append(t.getDescription()).append("\n");
        }
        prw.println(inputs);
        prw.close();
    }
}
