package duke.storage;

import duke.data.entity.Deadline;
import duke.data.entity.Event;
import duke.data.entity.Task;
import duke.data.entity.Todo;
import duke.data.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will read the data from text file according to user input.
 */
public class TaskReader {
    public static List<Task> readTaskList(List<String> taskToRead) throws DateTimeParseException, DukeException {
        final List<Task> tasks = new ArrayList<>();
        for (String strTask : taskToRead) {
            try {
                tasks.add(taskFromString(strTask));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(
                        "Something wrong with the file. Please delete the file and run again. Don't edit the file.");
            }
        }
        return tasks;
    }

    public static Task taskFromString(String strTask) throws ArrayIndexOutOfBoundsException, DateTimeParseException {
        Task tsk = new Task();
        if (strTask != null) {
            String[] str = strTask.trim().split(" , ");
            String type = str[0];
            boolean m = str[1].equals("1");
            String des = str[2];
            LocalDateTime dateObj = null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            if (type.equals("E") || type.equals("D")) {
                String time = str[3];
                dateObj = LocalDateTime.parse(time, formatter);
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
            }
            tsk.setDone(m);
        }

        return tsk;
    }
}
