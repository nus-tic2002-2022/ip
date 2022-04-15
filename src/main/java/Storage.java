import java.io.*;
import java.util.*;

public class Storage extends Duke{

    private static final String path = "C:/Users/wangs/Documents/duke/data";
    private static final String filename = "duke.txt";
    private static final String filePath = path + filename;

    public static void main() throws Exception {
        checkFile();
        readFile();
    }

    public static void checkFile() throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
    }

    public static void readFile() throws Exception {
        try {
            System.out.println(System.lineSeparator()+
                    "Here is your Existing List");
            Scanner in = new Scanner(new File(filePath));
            while (in.hasNext()) {
                String str = in.nextLine();
                if (str.length() > 0) {
                    MySiri.fileScanner(str, false);
                    MySiri.printList();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile() throws Exception {
        PrintWriter prw = new PrintWriter(filePath);
        StringBuilder inputs = new StringBuilder();
        for (Task t : MySiri.task) {
            inputs.append(t.getDescription()).append(System.lineSeparator());
        }
        prw.println(inputs);
        prw.close();
    }
}
