package zhixuan.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.data.task.*;
import zhixuan.duke.ui.DukeUI;

/**
 * Handler for saving and loading of files
 *
 **/
public class StorageFile {

    /**
     * Checks if directory is created and then
     * creates 'user-files' directory in user's directory
     *
     * @return created directory location string
     **/
    private static String createDirectory() {

        String directoryName = System.getProperty("user.dir") + File.separator + "user-files";
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
        }
        return directoryName;
    }

    /**
     * Calls createDirectory to check if created
     * Sets file name as 'list.txt'
     * Calls getAllTask to retrieve all tasks
     * Writes tasks to file
     *
     * @throws IOException if file is not saved correctly
     **/
    public static void saveFile(){
        File fileName = new File(createDirectory() + File.separator + "list.txt");
        try {
            ArrayList<String> taskString = TaskManager.getInstance().getAllTask();
            FileWriter fw = new FileWriter(fileName);
            Writer output = new BufferedWriter(fw);
            for (String task: taskString) {
                output.write(task + "\n");
            }
            output.close();
        } catch (IOException e) {
            new DukeUI().showToUser(e.getMessage());
        }
    }

    /**
     * Calls createDirectory to check if created
     * Checks for file 'list.txt'
     * Reads tasks from file
     * Calls TaskDecoder.decodeTask to decode tasks
     *
     * @return true if file is loaded
     *
     * @throws IOException if file is not loaded correctly
     **/
    public static boolean loadFile() {

        File fileName = new File(createDirectory() + File.separator + "list.txt");
        String line;

        try {
            boolean result = false;
            if (fileName.length() > 0) { //length returns 0 if file doesn't exist or if it's empty
                BufferedReader input = new BufferedReader(new FileReader(fileName));
                if (!input.ready()) {
                    throw new IOException();
                }
                while ((line = input.readLine()) != null) {
                     result = TaskDecoder.decodeTask(line);
                }
                input.close();
            }
            return result;
        } catch (IOException e){
            new DukeUI().showToUser(e.getMessage());
            return false;
        }
    }
}
