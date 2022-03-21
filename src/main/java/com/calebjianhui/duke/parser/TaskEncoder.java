package com.calebjianhui.duke.parser;

import com.calebjianhui.duke.taskmanager.Deadline;
import com.calebjianhui.duke.taskmanager.Event;
import com.calebjianhui.duke.taskmanager.Task;

import java.util.ArrayList;

public class TaskEncoder {
    private static final String ENCODING_SEPARATOR = " /| ";

    /**
     * Encode a given task list
     *
     * @return Arraylist containing the encoded task
     * **/
    public static ArrayList<String> encodeTaskList(ArrayList<Task> taskList) {
        ArrayList<String> taskStringList = new ArrayList<>();
        for (Task task: taskList) {
            taskStringList.add(encodeTask(task));
        }
        return taskStringList;
    }

    /**
     * Encode a given task
     *
     * @return Encoded task in string format
     * **/
    private static String encodeTask(Task task) {
        String type = task.getType();
        String description;
        if ("T".equals(type)) {
            description = task.getDescription();
        } else {
            if ("E".equals(type)) {
                description = ((Event) task).getRawDescription();
            } else {
                description = ((Deadline) task).getRawDescription();
            }
        }
        return type.concat(ENCODING_SEPARATOR).concat(task.getDoneStatus() ? "M": "U")
                .concat(ENCODING_SEPARATOR).concat(description);
    }

}
