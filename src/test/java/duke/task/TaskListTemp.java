package duke.task;

import java.util.List;


public class TaskListTemp extends TaskList {
    public Task doTask;

    @Override
    public void add(Task task) {
        doTask = task;
    }

    @Override
    public Task remove(int indexNo) {
        return doTask = new Task("remove " + indexNo);
    }

    @Override
    public Task get(int indexNo) {
        return doTask = new Task("get " + indexNo);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<String> convertAsLines() {
        return List.of(doTask.toString());
    }
}
