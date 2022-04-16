import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskFile {


    public static void loadFile() throws FileNotFoundException {
        try{
            File taskFile = new File("./data/tasks.txt");
            Scanner readFile = new Scanner(taskFile);
            System.out.println("Save file is loaded from: " + taskFile.getAbsolutePath());
            while(readFile.hasNext()){
                ImportTasks.importTask(readFile.nextLine());
            }

        } catch (FileNotFoundException f){
            System.out.println("☹ OOPS!!! Save file is not found, a new file will be created.");
            throw f;
        }
    }

    public static void newFile() throws IOException{
        try{
            File newDir = new File("./data");
            newDir.mkdirs();
            File newFile = new File("./data/tasks.txt");
            if(newFile.createNewFile())
                System.out.println("Save file created: " + newFile.getAbsolutePath());
        } catch (IOException i){
            System.out.println("☹ OOPS!!! Unable to create save file!");
            System.out.println("All progress will be lost!");
            throw i;
        }
    }

    public static void appendTask(String task) throws IOException {
        try{
            FileWriter fw = new FileWriter("./data/tasks.txt", true);
            fw.write(task + "\n");
            fw.close();
        }catch(IOException i){
            System.out.println("Save file cannot be found");
            throw i;
        }
    }

    public static void overwriteTask() throws IOException {
        try{
            FileWriter fw = new FileWriter("./data/tasks.txt");
            fw.write("");
            fw.close();
        }catch(IOException i){
            System.out.println("Save file cannot be found");
            throw i;
        }

    }
}

