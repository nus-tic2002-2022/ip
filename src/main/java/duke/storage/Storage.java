package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import duke.exception.DukeException;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

import static duke.parser.Parser.fileContentParser;

/**
 * This class will store filePath of a text file used for storage of TaskList
 * and provide methods to create, read and modify the text file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath Filepath for text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructor for Storage
     */
    public Storage() {
        this.filePath = "";
    }

    /**
     * Return filepath variable in Storage class.
     *
     * @return filepath in String format.
     */
    public String getFilePath(){
        return this.filePath;
    }

    /**
     * Return true if directory exist and false if directory does not exist.
     *
     * @return boolean of existence of directory.
     */
    public boolean checkDirectory(){
        File fileDirectory = new File("data");
        return fileDirectory.exists();
    }

    /**
     * Create data directory .
     */
    public void createDirectory(){
        File fileDirectory = new File("data");
        fileDirectory.mkdir();
    }

    /**
     * Create new text file with template at filepath.
     *
     * @param taskList TaskList which Task content to be saved in text file.
     */
    public void create(TaskList taskList) throws IOException {
        createDirectory();

        File f = new File(filePath);
        f.createNewFile();

        String textToAdd = "|type|status|task|deadline/duration";

        for (Task t : taskList.getTaskList()) {
            textToAdd = textToAdd + "\n|";
            String task = t.toString();
            char type = task.charAt(1);
            String cur_task = "";

            String status = "";
            if (t.getTaskStatus()) {
                status = "1";
                cur_task = task.substring(11);
            } else {
                status = "0";
                cur_task = task.substring(9);
            }
            textToAdd = textToAdd
                    + "|" + Character.toString(type)
                    + "|" + status
                    + "|" + cur_task ;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Return TaskList with the Task information saved in the text file in filepath.
     *
     * @return taskList in ArrayList format.
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        boolean directoryExist = checkDirectory();
        if (!directoryExist){
            createDirectory();
        }

        File f = new File(filePath);
        if(f.exists()){
            //convert file content to string
            String fileContentString  = read();
            //tokenize string
            List<String> fileContentList = fileContentParser(fileContentString);
            //create Task/Deadline/Event
            for (int index = 0; index < fileContentList.size();) {
                if (fileContentList.get(index).equalsIgnoreCase("T")){
                    Task curTask = new Task(fileContentList.get(index + 2));
                    if (fileContentList.get(index + 1).equalsIgnoreCase("1")) {
                        curTask.setTaskStatus(true);
                    }
                    taskList.add(curTask);
                    index = index + 3;
                } else if (fileContentList.get(index).equalsIgnoreCase("D")){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(fileContentList.get(index + 3), formatter);
                    Task curTask = new Deadline(fileContentList.get(index + 2), dateTime);
                    if (fileContentList.get(index + 1).equalsIgnoreCase("1")) {
                        curTask.setTaskStatus(true);
                    }
                    taskList.add(curTask);
                    index = index + 4;
                } else if (fileContentList.get(index).equalsIgnoreCase("E")){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(fileContentList.get(index + 3), formatter);
                    Task curTask = new Event(fileContentList.get(index + 2), dateTime);
                    if (fileContentList.get(index + 1).equalsIgnoreCase("1")) {
                        curTask.setTaskStatus(true);
                    }
                    taskList.add(curTask);
                    index = index + 4;
                } else {
                    throw new DukeException("readError");
                }
            }
        } else {
            TaskList blank = new TaskList();
            create(blank);
        }
        return taskList;
    }

    /**
     * Saves and updates text file in the filepath with the content of TaskList provided.
     *
     * @param taskList TaskList which Task content to be saved in text file.
     */
    public void save(TaskList taskList) throws IOException, DukeException {
        String textToAdd = "|type|status|task|Deadline/Duration";

        for (int index = 0; index < taskList.size(); index++) {
            textToAdd = textToAdd + "\n";
            String task = taskList.get(index).toString();

            int fixedDurationStartIndex = -1;
            int fixedDurationEndIndex = -1;
            for (int i = 0; i < task.length(); i++) {
                if (task.charAt(i) == '(') {
                    fixedDurationStartIndex = i;
                }
                if (task.charAt(i) == ')') {
                    fixedDurationEndIndex = i;
                }
            }
            if (fixedDurationEndIndex != -1 && fixedDurationStartIndex != -1) {
                task = task.substring(0, fixedDurationStartIndex)
                        + task.substring(fixedDurationEndIndex + 1);
            }

            char type = task.charAt(1);
            String curTask = "";

            String status = "";
            if (taskList.get(index).getTaskStatus()) {
                status = "1";
                curTask = task.substring(11);
                curTask = curTask.replace("\tby : " ,"|");
                curTask = curTask.replace("\tat : " ,"|");
            } else {
                status = "0";
                curTask = task.substring(9);
                curTask = curTask.replace("\tby : " ,"|");
                curTask = curTask.replace("\tat : " ,"|");
            }
            textToAdd = textToAdd
                    + "|" + Character.toString(type)
                    + "|" + status
                    + "|" + curTask;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns the entire content of text file in a single line String format.
     *
     * @return content of text file in String format.
     */
    public String read() throws IOException {
        String output = "";
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            output = output + s.nextLine();
        }
        return output;
    }

}
