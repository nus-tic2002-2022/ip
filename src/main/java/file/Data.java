package file;

import model.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    private static String filePath = "data.txt";

    /**
     * Read the file based on the file path.
     * Creating the task based on the task category.
     *
     * @param taskList Existing task array to be added
     * @return Updated task array
     */
    public static ArrayList<Task> readFile(ArrayList<Task> taskList){
        File myObj = new File(filePath);
        try {
            if (!myObj.createNewFile()) {
                Scanner myReader = new Scanner(myObj);  
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    String[] lineList = line.split("\\|");
                    taskList.add(categoriseTask(lineList));
                }
                myReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred upon reading file.");
        } 
        return taskList;
    }

    /**
     * Write the file based on the file path.
     *
     * @param taskList Existing task array to be written to the file
     */
    public static void writeFile(ArrayList<Task> taskList){
        try{
            FileWriter myObj = new FileWriter(filePath);
            for(Task t : taskList){
                myObj.write(t.toFileString());
                myObj.write("\n");
            }
            myObj.close();
        } catch (IOException e) {
            System.out.println("An error occurred upon reading file.");
        }
    }

    /**
     * Categorising task based alphabet.
     *
     * @param lineList String array that read from the file
     */
    private static Task categoriseTask(String[] lineList){
        Task task;
        switch(lineList[0]){
        case "E":
            task = new Event(lineList[2], lineList[3]);
            break;
        case "D":
            task = new Deadline(lineList[2], lineList[3]);
            break;
        case "T":
            task = new Todo(lineList[2]);
            break;
        case "DR":
            task = new Duration(lineList[2], lineList[3]);
            break;
        default:
            assert false;
            return null;
        }
        if(lineList[1].equals("1")){
            task.setDone(true);
        }
        return task;
    }
}
