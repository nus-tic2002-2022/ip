import Task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Storage {
    private String saveFileName;
    private File file;
    //Todo: Add something to check whether a text file with the name taskList.text already exists,
    // if it does then read from that and create the taskList array. Reader for the text file should be in a function.


    public Storage(String fileName) {
        this.saveFileName = fileName;
        this.file = new File(fileName);
    }

    /*public ArrayList<Task> checkFileExistence() throws FileNotFoundException {
        if (file.exists() && !file.isDirectory()) {
            System.out.println("Save file detected, loading Task List from save.");
            //if file exists "create arraylist"
            createArrayList();
            //return arraylist
        } else {
            //create the file
        }
    }*/

    public TaskList createArrayList() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList();
        int taskCounter = 1;
        while (sc.hasNextLine()) {
            Matcher reader = Constant.READER.matcher(sc.nextLine());
            boolean tester = reader.find();
            if (reader.group(1).equals("T")) {
                taskList.createTodoTask(reader.group(3));
                taskCounter++;
            } else if (reader.group(1).equals("D")) {
                //input from text file : [D] [X] deadline task by: 2022-04-10T12:00
                taskList.createDeadlineTaskFromFile(reader.group(3));
                taskCounter++;
            } else if (reader.group(1).equals("E")) {
                //input from text file : [E] [ ] event task at: 2022-04-10T12:00
                taskList.createEventTaskFromFile(reader.group(3));
                taskCounter++;
            } else if (reader.group(2).equals("X")) {
                taskList.markTaskAsDone(String.valueOf(taskCounter));
            }

        }
        if (taskList.getTaskList().isEmpty()) {
            System.out.println("\t" + "Save file is empty, no tasks created.");
        } else {
            System.out.println("\t" + "Task List has been loaded from save file.");
        }
        taskList.displayList();
        return taskList;
    }

    public void saveFile(ArrayList<Task> taskList) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < taskList.size(); i++) {
                printWriter.println(taskList.get(i).toString().trim());
            }
            printWriter.close();
        } catch (FileNotFoundException ignore) {

        }

    }
}
