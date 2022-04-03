package duke;

import duke.task.Task;

import java.util.ArrayList;

public interface Storage {
    void init() throws Exception;

    void load() throws Exception;

    void save(ArrayList<Task> tasks);
}