package com.calebjianhui.duke.storage;

import com.calebjianhui.duke.commands.AddCommand;
import com.calebjianhui.duke.parser.TaskDecoder;
import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.DukeUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Handle all file related matters, mainly reading/writing of file
 */
public class FileHandler {
    private static final String CORRUPTED_FILE = "There is an issue reading your saved file, your task list might be corrupted.";
    private static final String IO_ERROR = "An error occurred while accessing file.";
    private static final String FILE_PATH = "data/";
    private static final String FILE_NAME = "task_file.txt";

    /**
     * Return the file name
     *
     * @return Path to file name
     */
    private static Path getFilePath() {
        return Paths.get(FILE_PATH.concat(FILE_NAME));
    }

    /**
     * Read the contents of the task file
     *
     * @return Arraylist containing the various task, empty should there be no task
     */
    private static ArrayList<String> readFileContents() {
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            BufferedReader fileReader = Files.newBufferedReader(getFilePath(), StandardCharsets.UTF_8);
            String currentLine;
            while((currentLine = fileReader.readLine()) != null) {
                fileContents.add(currentLine);
            }
            return fileContents;
        } catch (IOException e) {
            return fileContents;
        }
    }

    /**
     * Write contents to the task file
     */
    private static void writeFileContents(String contents) throws IOException {
        Files.writeString(getFilePath(), contents);
    }

    /**
     * Check if folder exist, else create it
     */
    private static void checkFolderExist() throws IOException {
        Path folderPath = Paths.get(FILE_PATH);
        if (!Files.exists(folderPath)) {
            Files.createDirectory(folderPath);
        }
    }

    /**
     * Retrieve & Update the task from the task file
     * - Only done at start-up
     *
     * @return If there are updates made
     */
    public static boolean readAndUpdateTask() {
        ArrayList<String> fileContents = readFileContents();
        // Return since there is nothing to update
        if (fileContents.isEmpty()) {
            return false;
        }

        try {
            // Update the contents in the task manager
            for (String task: fileContents) {
                String[] decodeTask = TaskDecoder.decodeTask(task);
                // Validate task by performing the following checks:
                // - Array Length
                if (decodeTask.length != 3) {
                    throw new MalformedParametersException();
                }
                // - Type
                Set<String> possibleTypes = TaskManager.getInstance().getAllPossibleTypes();
                if (!possibleTypes.contains(decodeTask[0])) {
                    throw new MalformedParametersException();
                }
                // - Mark Status
                Set<String> possibleStatus = new HashSet<>(Arrays.asList("M", "U"));
                if (!possibleStatus.contains(decodeTask[1])) {
                    throw new MalformedParametersException();
                }
                boolean result = new AddCommand(true, TaskManager.getInstance().decodeTypeAlias(decodeTask[0]),
                        "M".equals(decodeTask[1]), decodeTask[2]).execute();
                if (!result) {
                    return false;
                }
            }
        } catch (MalformedParametersException e) {
            new DukeUI().formatDukeReply(CORRUPTED_FILE);
            return false;
        }
        return true;
    }

    /**
     * Save the current tasks
     */
    public static void saveTask() {
        try {
            checkFolderExist();
            ArrayList<String> taskString = TaskManager.getInstance().getAllTask();
            StringBuilder content = new StringBuilder();
            for (String task: taskString) {
                content.append(task).append("\n");
            }
            writeFileContents(content.toString());
        } catch (IOException e) {
            new DukeUI().formatDukeReply(IO_ERROR);
        }
    }
}
