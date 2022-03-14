package java.duke.importutil;

import java.duke.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskFile {

    private static String filePath;

    public TaskFile (String fp){
        filePath = fp;
        try{
            loadFile();
            System.out.println(TaskList.getNumOfTasks() + " task(s) have been imported");
        } catch (IOException i1) {
            try {
                newFile();
            } catch (IOException i2) {
                System.out.println("All progress will be lost!");
            }
        }
    }


    public static void loadFile() throws IOException {
        try{
            File taskFile = new File(filePath);
            Scanner readFile = new Scanner(taskFile);
            System.out.println("Save file is loaded from: " + taskFile.getCanonicalPath());
            while(readFile.hasNext()){
                ImportTasks.importTask(readFile.nextLine());
            }
        } catch (IOException i){
            System.out.println("☹ OOPS!!! Save file is not found, a new file will be created.");
            throw i;
        }
    }

    public static void newFile() throws IOException{
        try{
            File newDir = new File(filePath.substring(0,filePath.lastIndexOf('/')));
            newDir.mkdirs();
            File newFile = new File(filePath);
            if(newFile.createNewFile())
                System.out.println("Save file created: " + newFile.getCanonicalPath());
        } catch (IOException i){
            System.out.println("☹ OOPS!!! Unable to create save file!");
            throw i;
        }
    }

    public static void appendTask(String task) throws IOException {
        try{
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task + "\n");
            fw.close();
        }catch(IOException i){
            System.out.println("Save file cannot be found");
            throw i;
        }
    }

    public static void overwriteTask() throws IOException {
        try{
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        }catch(IOException i){
            System.out.println("Save file cannot be found");
            throw i;
        }

    }
}