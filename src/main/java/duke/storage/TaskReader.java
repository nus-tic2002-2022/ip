package duke.storage;

import duke.data.entity.Deadline;
import duke.data.entity.Event;
import duke.data.entity.Task;
import duke.data.entity.Todo;
import duke.data.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class will read the data from text file according to user input.
 */
public class TaskReader {
    public static List<Task> readTaskList(List<String> taskToRead) throws ParseException, DukeException {
        final List<Task> tasks = new ArrayList<Task>();
        for (String strTask : taskToRead) {
            try {
                tasks.add(taskFromString(strTask));
            }catch  (ArrayIndexOutOfBoundsException e){
                throw new DukeException("Something wrong with the file. Please delete the file and run again. Don't edit the file.");
            }
        }
        return tasks;
    }

    public static Task taskFromString(String strTask) throws ArrayIndexOutOfBoundsException,ParseException {
        Task tsk = new Task();
        if (strTask != null) {
            String[] str = strTask.trim().split(" , ");
            String type = str[0];
            boolean m = str[1].equals("1");
            String des = str[2];
            Date dateObj = null;
            if (type.equals("E") || type.equals("D")) {
                String time = str[3];
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                dateObj = sdf.parse(time);
            }
            switch (str[0].trim()) {
                case "E":
                    tsk = new Event(des, dateObj);
                    break;
                case "T":
                    tsk = new Todo(des);
                    break;
                case "D":
                    tsk = new Deadline(des, dateObj);
                    break;
                default:
                    tsk.setDone(m);
            }
        }
        return tsk;
    }
}
