import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private TaskHandler TaskHandler;
    private String path;
    public Storage(String path){
        this.path = path;
    }
    public  void saveTasks(){
        try{
            BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
            String tasks = "";
            for (Task task : TaskList.getTaskLists()){
                tasks += task.toString() + "\n";
            }
            taskWriter.write(tasks);
            taskWriter.close();
        } catch(IOException e){
            System.out.println("Exception: " + e.getMessage());
        }
    }
    public void ReadFile() throws IOException{
        File file = new File(this.path);
        file.getParentFile().mkdirs();
        if(!file.exists()){
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            String texts = sc.nextLine();
            if(texts.startsWith("todo")){
                TaskHandler.todo(texts);
            } else if(texts.startsWith("deadline")){
                TaskHandler.deadline(texts);
            } else if(texts.startsWith("event")){
                TaskHandler.event(texts);
            }
            else{
                System.out.println("error input");
            }
        }
        sc.close();
    }
}
