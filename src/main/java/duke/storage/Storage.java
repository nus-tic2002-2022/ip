package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Access file for loading and saving tasks
 */
public class Storage {

    private final String filePath;
    private final ArrayList<String> data = new ArrayList<>();

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Load tasks from file in specified path
     * and call {@link TaskList} to add and update tasks
     * @return TaskList contains tasks from file
     * @throws DukeException for showing customised exception message
     * @throws IOException will be thrown when encounter error writing to a file
     */
    public TaskList load() throws DukeException, IOException {

        File f = new File(filePath);
        TaskList taskList = new TaskList();

        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String row = s.nextLine();
            data.add(row);
        }
        taskList.parseDataFromFile(data);
        return taskList;
    }

    /**
     * Save tasks to file
     * @param taskList list of tasks to save
     * @throws IOException will be thrown when encounter error writing to a file
     */
    public void save(TaskList taskList) throws IOException {

        String taskTypeCode;
        String status;
        String description = "";
        String dateTime = "";
        StringBuilder dataToSave = new StringBuilder();
        TaskType taskType;

        for (int i = 0; i < taskList.getSize(); i++) {
            taskType = taskList.getTaskByIndex(i).getTaskType();
            taskTypeCode = taskType.getTaskTypeCode();
            status = taskList.getTaskByIndex(i).getDoneStatus()? "1" : "0" ;

            switch (taskType) {
                case DEADLINE:
                    description = taskList.getTaskByIndex(i).getDescription();
                    Deadline d = (Deadline) taskList.getTaskByIndex(i);
                    dateTime = " | " + d.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    break;
                case EVENT:
                    description = taskList.getTaskByIndex(i).getDescription();
                    Event e = (Event) taskList.getTaskByIndex(i);
                    dateTime = " | " + e.getAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    break;
                case TODO:
                    description = taskList.getTaskByIndex(i).getDescription();
                    dateTime = "";
                    break;
            }

            dataToSave.append(taskTypeCode).append(" | ").append(status).append(" | ").append(description).append(dateTime);
            dataToSave.append(System.lineSeparator());
        }

        assert filePath.endsWith("txt") : "file path should be ended with .txt";
        FileWriter fw = new FileWriter(filePath);
        fw.write(dataToSave.toString());
        fw.close();
    }

}

