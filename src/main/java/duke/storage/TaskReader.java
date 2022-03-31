package duke.storage;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.data.entity.Deadline;
import duke.data.entity.Event;
import duke.data.entity.Task;
import duke.data.entity.Todo;

public class TaskReader {
    public static List<Task> readTaskList(List<String> taskstoRead) {
        final List<Task> tasks = new ArrayList<Task>();
        for (String strTask : taskstoRead) {
            Task t = taskFromString(strTask);
            tasks.add(t);
            //tasks.add(taskFromString(strTask));
        }
        return tasks;
    }

    public static Task taskFromString(String strTask) {
        Task tsk = new Task();
        if (strTask != null) {
            String[] str = strTask.trim().split(" , ");
            String type = str[0];
            boolean m = str[1].equals("1");
            String des = str[2];
            String time = "";
            if (type.equals("E") || type.equals("D"))
                time = str[3];
            switch (str[0].trim()) {
                case "E":
                    tsk = new Event(des, time);
                    tsk.setDone(m);
                    break;
                case "T":
                    tsk = new Todo(des);
                    tsk.setDone(m);
                    break;
                case "D":
                    tsk = new Deadline(des, time);
                    tsk.setDone(m);
                    break;
                default:
                    tsk.setDone(m);
                    tsk.toString();
            }

        }
        return tsk;
    }
}
