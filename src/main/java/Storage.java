import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static String filePath;

    public Storage (String _filepath) {
        filePath = _filepath;
    }

    public static void writeFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    };

    public static void readFile()  throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
            //process each line to task
        }
    };

    public static void main(String[] args) {
        Storage duke = new Storage("data/duke.txt");

        try {
            duke.writeFile("this is not funny3");
        } catch (IOException e) {
            System.out.println("fail read bro.");
        };


        try {
            duke.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("fail scan bro.");
        }

    }
}
