import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import duke.*;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Storage {
    protected File f;
    protected ArrayList<Task> taskArr;

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

    //The following method returns an ArrayList containing the values stored in file (specified in File f)
    public ArrayList<Task> readFromFile(){
        int counter = 0;
        taskArr = new ArrayList<Task>(100);
        Pattern event = Pattern.compile("^\\[E\\].*$");
        Pattern deadline = Pattern.compile("^\\[D\\].*$");
        Pattern todo = Pattern.compile("^\\[T\\].*$");
        Pattern isMarked = Pattern.compile("^\\[X\\]\\s.*$");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
            String st;
            while ((st = br.readLine()) != null){
                if(event.matcher(st).matches()){
                    st = st.replaceAll("^\\[E\\]","");
                    if(isMarked.matcher(st).matches()){
                        st = st.replaceAll("^\\[X\\]\\s","");
                        String[] splited = st.split("\\(at:", 2);
                        splited[1] = splited[1].replaceAll("\\)$", "");
                        taskArr.add(new Event(splited[0].replaceAll("\\s+$",""), splited[1].replaceAll("^\\s","")));
                        taskArr.get(counter).setDone(true);
                    }else{
                        st = st.replaceAll("^\\[\\s\\]\\s","");
                        String[] splited = st.split("\\(at:", 2);
                        splited[1] = splited[1].replaceAll("\\)$", "");
                        //System.out.println("test : " + splited[0] + ", " + splited[1] +"\n");
                        taskArr.add(new Event(splited[0].replaceAll("\\s+$",""), splited[1].replaceAll("^\\s+","")));
                    }
                }else if(deadline.matcher(st).matches()) {
                    st = st.replaceAll("^\\[D\\]", "");
                    if (isMarked.matcher(st).matches()) {
                        st = st.replaceAll("^\\[X\\]\\s", "");
                        String[] splited = st.split("\\(by:", 2);
                        splited[1] = splited[1].replaceAll("\\)$", "");
                        taskArr.add(new Deadline(splited[0].replaceAll("\\s+$", ""), splited[1].replaceAll("^\\s", "")));
                        taskArr.get(counter).setDone(true);
                    } else {
                        st = st.replaceAll("^\\[\\s\\]\\s", "");
                        String[] splited = st.split("\\(by:", 2);
                        splited[1] = splited[1].replaceAll("\\)$", "");
                        taskArr.add(new Deadline(splited[0].replaceAll("\\s+$", ""), splited[1].replaceAll("^\\s+", "")));
                    }
                }else if(todo.matcher(st).matches()) {
                    st = st.replaceAll("^\\[T\\]", "");
                    if (isMarked.matcher(st).matches()) {
                        st = st.replaceAll("^\\[X\\]\\s", "");
                        taskArr.add(new Todo(st));
                        taskArr.get(counter).setDone(true);
                    } else {
                        st = st.replaceAll("^\\[\\s\\]\\s", "");
                        taskArr.add(new Todo(st));
                    }
                }else{
                    if (isMarked.matcher(st).matches()) {
                        st = st.replaceAll("^\\[X\\]\\s", "");
                        taskArr.add(new Task(st));
                        taskArr.get(counter).setDone(true);
                    } else {
                        st = st.replaceAll("^\\[\\s\\]\\s", "");
                        taskArr.add(new Task(st));
                    }
                }
                counter++;
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return taskArr;
    }
}
