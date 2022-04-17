package mainCom;
import subTask.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides the ability to load existing task data into the 'TaskList' and save the latest modified 'TaskList' into a file.
 * It also provides some basic function to detect if the existing data is in an incompatible format and allows to throw an exception.
 */


public class Keep {
    protected static int count = 0;
    protected static String FileLocation = "data/duke.txt";
    protected static ArrayList<Task> list = new ArrayList<>();

    private static void printContents(String filePath) throws DukeException, IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String current = s.nextLine();
            if (current.contains("[T]")) {
                String description = current.substring(7);
                list.add(new Todo(description));
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else if (current.contains("[D]") || current.contains("[E]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String by = current.substring(m+5,n);
                if (current.contains("[D]")) {
                    list.add(new Deadline(description,by));
                }
                else {
                    list.add(new Event(description,by));
                }
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else {
                throw new DukeException("OOPS!!! subTask.Task in existing data is incompatible\n");
            }
            count++;
        }
    }

    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(FileLocation);
        list = TKlist.UpdatedList();
        for (Task l : list) {
            fw.write(l + System.lineSeparator());
        }
        fw.close();
    }

    public static void main() {
        String FileLocation = "data/duke.txt";
        String Directory = "./data/";
        try {
            Files.createDirectories(Paths.get(Directory));
            printContents(FileLocation);
            new TKlist(list, count);
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

