package duke.importer;

import duke.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskFile {

    private static String filePath;

    /**
     * Constructor for the task file.
     * Loads the file if it can be found.
     * Otherwise, tries to create the file.
     * Else, warns the user that changes to the task list will not be saved.
     * @param fp Directory location of the task file
     * @see {@link #loadFile()}
     */
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

    /**
     * Method that loads the task file and initiates the import
     * @throws IOException throws exception when the task file cannot be found
     * @see {@link ImportTasks#importTask(String)}
     */
    public static void loadFile() throws IOException {
        try{
            File taskFile = new File(filePath);
            Scanner readFile = new Scanner(taskFile);
            System.out.println("Save file is loaded from: " + taskFile.getCanonicalPath());
            while(readFile.hasNext()){
                try{
                    ImportTasks.importTask(readFile.nextLine());
                } catch (ImportErrorException i){
                    System.out.println("Skipping...");
                }
            }
        } catch (IOException i){
            System.out.println("☹ OOPS!!! Save file is not found, a new file will be created.");
            throw i;
        }
    }

    /**
     * Method that creates a new task file
     * @throws IOException Throws exception if Duke is unable to create the save file
     */
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

    /**
     * This method adds task to the task file
     * @param task Tasks that are to be added into the task file
     * @throws IOException throws exception if the task file cannot be found
     */
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

    /**
     * This method overwrites all existing content in the task file
     * @throws IOException throws exception if the task file cannot be found
     */
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