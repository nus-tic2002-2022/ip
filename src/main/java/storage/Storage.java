package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import exceptions.TooManyDatesException;
import parser.DateParser;
import tasks.Deadline;
import tasks.Event;
import tasks.FixedDuration;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * A class to deal with all file related matters.
 */
public class Storage {
    private static final String FILE_PATH = "data";
    private static final String FILE_NAME = "duke.txt";

    /**
     * Checks if directory exists and creates it if it does not
     *
     * @exception IOException if directory cannot be created
     * @see IOException
     */
    @SuppressWarnings("empty")
    public void init() {
        Path dirPathObj = Paths.get(FILE_PATH);
        boolean dirExists = Files.exists(dirPathObj);
        if (dirExists) {
        } else {
            try {
                Files.createDirectories(dirPathObj);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occurred While Creating The Directory " + ioExceptionObj.getMessage());
            }
        }
    }

    /**
     * Checks if file exists and creates it if it does not
     *
     * @param file the file to be checked
     * @exception IOException if file cannot be created
     * @see IOException
     */
    public void checkIfFileExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occurred While Creating Save File= " + ioExceptionObj.getMessage());
            }
        }
    }



    /**
     * This method is used to save the task list into a txt file for storage
     *
     * @param taskList the list of tasks to be saved
     * @exception IOException if file cannot be saved
     */
    public void save(TaskList taskList) {
        File yourFile = new File(FILE_PATH, FILE_NAME);
        checkIfFileExists(yourFile);
        ArrayList<Task> allTasks = taskList.getAllTask();

        try {
            FileWriter myWriter = new FileWriter(yourFile);
            for (Task task: allTasks) {
                String test = task.getSaveFormat() + "\n";
                myWriter.write (test);
            }
            myWriter.close();
        } catch (IOException ioExceptionObj) {
            System.out.println("Problem Occurred While Saving Data " + ioExceptionObj.getMessage());
        }
    }

    /**
     * This method is used to load the saved task list from a txt file to a new TaskList object
     *
     * @return A TaskList Object containing all saved Task Objects
     * @exception IOException if file cannot be saved
     */
    public TaskList loadTasks() {
        TaskList tempTaskList = new TaskList();

        File yourFile = new File(FILE_PATH, FILE_NAME);

        checkIfFileExists(yourFile);

        try (Scanner scanner = new Scanner(yourFile)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tempTaskList.addTask(convertStrToTask(line));
            }
        } catch (IOException ioExceptionObj) {
            System.out.println("Problem Occurred While Loading Data " + ioExceptionObj.getMessage());
        }

        return tempTaskList;
    }


    /**
     * This method is used to convert a line of saved text into a task
     *
     * @param str String of the saved text
     * @return A Task Object
     * @exception IndexOutOfBoundsException if saved file is in the wrong format
     */
    public static Task convertStrToTask(String str) {
        Task task = null;
        try {
            String[] splittedString = str.split("\\|");

            String taskType = splittedString[0];
            String status = splittedString[1];
            String description = splittedString[2];


            switch (taskType) {

            case "T":
                task = new Todo(description);
                break;

            case "D":
                Date dateD = DateParser.parseDate(splittedString[3]);
                task = new Deadline(description, dateD);
                break;

            case "E":
                Date dateE = DateParser.parseDate(splittedString[3]);
                task = new Event(description, dateE);
                break;

            case "F":
                int duration = Integer.parseInt(splittedString[3]);
                task = new FixedDuration(description, duration);
                break;

            default:
                System.out.println("Something went wrong with the safe file");
            }

            if (status.equals("1")) {
                task.markDone();
            }
        } catch (IndexOutOfBoundsException | TooManyDatesException | NumberFormatException e) {
            System.out.println("Something went wrong with the safe file");
        }

        return task;
    }
}

