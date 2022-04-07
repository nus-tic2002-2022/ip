package duke.storage;

import duke.deadline.Deadline;
import duke.dukeexception.DukeException;
import duke.event.Event;
import duke.task.Task;
import duke.todo.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    public List<Task> load() throws DukeException, FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        // Read the file
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] lineList = line.split("\\|");

            if (lineList[0].equals("T")) {
                Task todo = new ToDo(lineList[2]);
                if (lineList[1].equals("1")) {
                    todo.markAsDone();
                } else {
                    todo.markAsNotDone();
                }
                taskList.add(todo);
            } else if (lineList[0].equals("D")) {
                Task deadline = new Deadline(lineList[2], lineList[3]);
                if (lineList[1].equals("1")) {
                    deadline.markAsDone();
                } else {
                    deadline.markAsNotDone();
                }
                taskList.add(deadline);
            } else if (lineList[0].equals("E")) {
                Task event = new Event(lineList[2], lineList[3]);
                if (lineList[1].equals("1")) {
                    event.markAsDone();
                } else {
                    event.markAsNotDone();
                }
                taskList.add(event);
            }
        }
        myReader.close();

        return taskList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
