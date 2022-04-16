import duke.constants.DukeConstants;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final File f;
    private ArrayList<Task> taskArr;
    private String dirFilePath;

    //The following class takes in the supplied value of filepath and uses it to populate variable File f.
    public Storage() {
        try {
            dirFilePath = new File(Storage.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        }catch(Exception e){
            dirFilePath = System.getProperty("user.dir");
        }
        this.f = new File(dirFilePath + "\\data.txt");
    }

    //The following method will write to the file specified in File f. The written value is taken from ArrayList taskArr along with the getDescription method.
    public void saveToFile(TaskList tasks) {
        try (PrintWriter out = new PrintWriter(f.getAbsoluteFile())) {
            for (int i = 0; i < tasks.size(); i++) {
                out.println(tasks.get(i).getDescription());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //The following method is for the C-Archive feature.
    public void saveToFile(TaskList tasks, String filename) {
        String filepath = dirFilePath + "\\archive\\";
        new File(filepath).mkdirs();
        File a = new File(filepath + filename + (filename.contains(".txt") ? "" : ".txt"));
        try (PrintWriter out = new PrintWriter(a.getAbsoluteFile())) {
            for (int i = 0; i < tasks.size(); i++) {
                out.println(tasks.get(i).getDescription());
            }
            System.out.println("Your current tasks have been archived under: " + filepath + filename + (filename.contains(".txt") ? "" : ".txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //The following method returns the
    public File returnFile() {
        return this.f;
    }

    //The following method returns an ArrayList containing the values stored in file (specified in File f)
    public ArrayList<Task> readFromFile() throws IOException {
        taskArr = new ArrayList<Task>(100);
        BufferedReader br = new BufferedReader(new FileReader(f.getAbsoluteFile()));
        String st;
        while ((st = br.readLine()) != null) {
            if (DukeConstants.STORAGE_EVENT.matcher(st).matches()) {
                saveEvent(st);
            } else if (DukeConstants.STORAGE_DEADLINE.matcher(st).matches()) {
                saveDeadline(st);
            } else if (DukeConstants.STORAGE_TODO.matcher(st).matches()) {
                saveTodo(st);
            } else {
                saveTask(st);
            }
        }
        return taskArr;
    }

    private void saveTask(final String line) {
        boolean isDone = DukeConstants.STORAGE_ISMARKED.matcher(line).matches();
        String st = removeMarker(line);
        taskArr.add(new Task(st, isDone));
    }

    private void saveTodo(final String line) {
        String st = line.replaceAll("^\\[T\\]", "");
        boolean isDone = DukeConstants.STORAGE_ISMARKED.matcher(st).matches();
        st = removeMarker(st);
        taskArr.add(new Todo(st, isDone));
    }

    private void saveDeadline(final String line) {
        String st = line.replaceAll("^\\[D\\]", "");
        boolean isDone = DukeConstants.STORAGE_ISMARKED.matcher(st).matches();
        st = removeMarker(st);
        String[] dateTime = getDateTime(st, "by");
        taskArr.add(new Deadline(dateTime[0], dateTime[1], isDone));
    }

    private void saveEvent(final String line) {
        String st = line.replaceAll("^\\[E\\]", "");
        boolean isDone = DukeConstants.STORAGE_ISMARKED.matcher(st).matches();
        st = removeMarker(st);
        String[] dateTime = getDateTime(st, "at");
        taskArr.add(new Event(dateTime[0], dateTime[1], isDone));
    }

    private String removeMarker(String st) {
        st = st.replaceAll("^\\[X\\]\\s", "");
        st = st.replaceAll("^\\[\\s\\]\\s", "");
        return st;
    }

    private String[] getDateTime(final String st, final String designation) {
        String[] dateTime = st.split("\\(" + designation + ":", 2);
        dateTime[0] = dateTime[0].trim();
        dateTime[1] = dateTime[1].replaceAll("\\)$", "").trim();
        return dateTime;
    }
}
