package zhixuan.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import zhixuan.duke.common.Messages;
import zhixuan.duke.data.task.*;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.data.exceptions.InvalidFileException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * Handler for saving and loading of files
 *
 **/
public class StorageFile {

    public static boolean isFirstBoot = false;
    public static boolean isLoadedNewFile = false;
    public static File loadedFile;

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
            isFirstBoot = true;
        }
        return directoryName;
    }
    /**
     * Calls createDirectory to check if created
     * Sets file name as 'list.txt'
     * **/
    public static File getDefaultLocation(){
        File fileName = new File(createDirectory() + File.separator + "list.txt");
        return fileName;
    }

    /**
     * Calls getAllTask to retrieve all tasks
     * Writes tasks to file
     *
     * @param fileName file for the tasks to be saved
     *
     * @throws IOException if file is not saved correctly
     **/
    public static void saveFile(File fileName){

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
     * Reads tasks from file
     * Calls TaskDecoder.decodeTask to decode tasks
     *
     * @param fileName file provided by user
     *
     * @return true if file is loaded
     *
     * @throws InvalidFileException if file is not in correct format or unreadable.
     * @throws IOException if file is not loaded correctly
     **/
    public static boolean loadFile(File fileName) {
        String line;
        try {
            boolean result = false;
            if (!fileName.canRead()) throw new InvalidFileException(InvalidFileException.FILE_NOT_READABLE);
            if (!fileName.canWrite()) throw new InvalidFileException(InvalidFileException.FILE_NOT_WRITABLE);
            if (fileName.length() == 0) throw new InvalidFileException(InvalidFileException.FILE_EMPTY);
            //length returns 0 if file doesn't exist or if it's empty

            BufferedReader input = new BufferedReader(new FileReader(fileName));
            if (!input.ready()) {
                throw new IOException();
            }
            while ((line = input.readLine()) != null) {
                result = TaskDecoder.decodeTask(line);
            }
            input.close();

            return result;
        } catch (IOException e){
            new DukeUI().showToUser(InvalidFileException.FILE_ERROR);
            return false;
        } catch (InvalidFileException e){
            if (!isFirstBoot) {
                new DukeUI().showToUser(InvalidFileException.FILE_ERROR);
            } else {
                new DukeUI().showToUser(Messages.REPLY_CREATED_DIRECTORY);
            }
            return false;
        }
    }

    /**
     * Uses JFileChooser for user to select file
     * Calls loadFile() on selected file
     *
     * @return true if file is loaded
     **/
    public static boolean loadUserFile() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            isLoadedNewFile = loadFile(selectedFile);
            if (isLoadedNewFile) {
                loadedFile = selectedFile;
            }
            return isLoadedNewFile;
        }
        return false;
    }
}
