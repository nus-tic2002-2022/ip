import java.io.*;

import duke.constants.DukeConstants;
import duke.tasklist.Tasklist;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

public class Storage {
    private File f;
    private ArrayList<Task> taskArr;

    //The following class takes in the supplied value of filepath and uses it to populate variable File f.
    public Storage(String filepath) {
        this.f = new File(filepath + "\\data.txt");
    }

    //The following method will write to the file specified in File f. The written value is taken from ArrayList taskArr along with the getDescription method.
    public void saveToFile(Tasklist tasks) {
        try (PrintWriter out = new PrintWriter(f.getAbsoluteFile())) {
            for(int i=0;i<tasks.size();i++){
                out.println(tasks.get(i).getDescription());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //The following method is for the C-Archive feature.
    public void saveToFile(Tasklist tasks, String filename) {
        String filepath = System.getProperty("user.dir") + "\\archive\\";
        new File(filepath).mkdirs();
        f = new File(filepath + filename + (filename.contains(".txt") ? "" : ".txt"));
        try (PrintWriter out = new PrintWriter(f.getAbsoluteFile())) {
            for(int i=0;i<tasks.size();i++){
                out.println(tasks.get(i).getDescription());
            }
            System.out.println("Your current tasks have been archived under: " + filepath + filename + (filename.contains(".txt") ? "" : ".txt"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //
    public File returnFile(){
        return this.f;
    }

    //The following method returns an ArrayList containing the values stored in file (specified in File f)
    public ArrayList<Task> readFromFile() throws IOException {
        int loopCounter = 0;
        taskArr = new ArrayList<Task>(100);

        BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
        String st;
        while ((st = br.readLine()) != null){
            if(DukeConstants.STORAGE_EVENT.matcher(st).matches()){
                st = st.replaceAll("^\\[E\\]","");
                if(DukeConstants.STORAGE_ISMARKED.matcher(st).matches()){
                    st = st.replaceAll("^\\[X\\]\\s","");
                    String[] splited = st.split("\\(at:", 2);
                    splited[1] = splited[1].replaceAll("\\)$", "");
                    taskArr.add(new Event(splited[0].replaceAll("\\s+$",""), splited[1].replaceAll("^\\s","")));
                    taskArr.get(loopCounter).setDone(true);
                }else{
                    st = st.replaceAll("^\\[\\s\\]\\s","");
                    String[] splited = st.split("\\(at:", 2);
                    splited[1] = splited[1].replaceAll("\\)$", "");
                    //System.out.println("test : " + splited[0] + ", " + splited[1] +"\n");
                    taskArr.add(new Event(splited[0].replaceAll("\\s+$",""), splited[1].replaceAll("^\\s+","")));
                }
            }else if(DukeConstants.STORAGE_DEADLINE.matcher(st).matches()) {
                st = st.replaceAll("^\\[D\\]", "");
                if (DukeConstants.STORAGE_ISMARKED.matcher(st).matches()) {
                    st = st.replaceAll("^\\[X\\]\\s", "");
                    String[] splited = st.split("\\(by:", 2);
                    splited[1] = splited[1].replaceAll("\\)$", "");
                    taskArr.add(new Deadline(splited[0].replaceAll("\\s+$", ""), splited[1].replaceAll("^\\s", "")));
                    taskArr.get(loopCounter).setDone(true);
                } else {
                    st = st.replaceAll("^\\[\\s\\]\\s", "");
                    String[] splited = st.split("\\(by:", 2);
                    splited[1] = splited[1].replaceAll("\\)$", "");
                    taskArr.add(new Deadline(splited[0].replaceAll("\\s+$", ""), splited[1].replaceAll("^\\s+", "")));
                }
            }else if(DukeConstants.STORAGE_TODO.matcher(st).matches()) {
                st = st.replaceAll("^\\[T\\]", "");
                if (DukeConstants.STORAGE_ISMARKED.matcher(st).matches()) {
                    st = st.replaceAll("^\\[X\\]\\s", "");
                    taskArr.add(new Todo(st));
                    taskArr.get(loopCounter).setDone(true);
                } else {
                    st = st.replaceAll("^\\[\\s\\]\\s", "");
                    taskArr.add(new Todo(st));
                }
            }else{
                if (DukeConstants.STORAGE_ISMARKED.matcher(st).matches()) {
                    st = st.replaceAll("^\\[X\\]\\s", "");
                    taskArr.add(new Task(st));
                    taskArr.get(loopCounter).setDone(true);
                } else {
                    st = st.replaceAll("^\\[\\s\\]\\s", "");
                    taskArr.add(new Task(st));
                }
            }
            loopCounter++;
        }
        return taskArr;
    }
}
