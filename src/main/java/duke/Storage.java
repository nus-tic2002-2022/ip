package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.TaskList.*;

public class Storage {

    /**
     * A file named Duke.txt will be created if the file does not exist.
     *
     * The content of the tasklist will be written into Duke.txt
     */
    public static void writeFile(){
        try {
            File newFile = new File("duke.txt");
            if (!newFile.createNewFile()) {
                newFile.delete();
                newFile.createNewFile();
            }
            FileWriter file = new FileWriter("duke.txt");
            for (int i = 0; i < taskCount; i++) {
                file.write(taskList.get(i).toString() + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Search for the file named Duke.txt
     *
     * If it exist, to populate the content from Duke.txt into the tasklist.
     */
    public static void readFile(){
        try {
            File file = new File("duke.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.substring(1,2).equals("T")){
                    ToDo t = new ToDo(data.substring(7));
                    if(data.substring(4,5).equals("X")){
                        t.isDone = true;
                    }
                    taskList.add(t);
                    taskCount++;
                } else if (data.substring(1,2).equals("E")){
                    String[] temp = data.split(" \\(at: ");
                    Event t = new Event(temp[0].substring(7), temp[1].substring(0, temp[1].length() - 1));
                    if(data.substring(4,5).equals("X")){
                        t.isDone = true;
                    }
                    taskList.add(t);
                    taskCount++;
                } else if (data.substring(1,2).equals("D")){
                    String[] temp = data.split(" \\(by: ");
                    Deadline t = new Deadline(temp[0].substring(7), temp[1].substring(0, temp[1].length() - 1));
                    if(data.substring(4,5).equals("X")){
                        t.isDone = true;
                    }
                    taskList.add(t);
                    deadlineTaskList.add(t);
                    taskCount++;
                } else if (data.substring(1,2).equals("A")){
                    String[] temp = data.split(" \\(after: ");
                    DoAfter t = new DoAfter(temp[0].substring(7), temp[1].substring(0, temp[1].length() - 1));
                    if(data.substring(4,5).equals("X")){
                        t.isDone = true;
                    }
                    taskList.add(t);
                    taskCount++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
