import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    public String filePath;

    public Storage (String _filepath) {
        filePath = _filepath;
    }

    public void writeFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0 ; i < tasks.size() ; i ++) {
                fw.write(tasks.get(i).taskToSaveFile() + "\n"); //+1 to i here due to numbering
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Can't write to file!!");
        }

    };

    public ArrayList<String> readFile()   { //ArrayList<Task>
        ArrayList<String> tasks  = new ArrayList<String>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                //System.out.println(s.nextLine());
                tasks.add(s.nextLine());
            };
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return tasks;
    };
}
