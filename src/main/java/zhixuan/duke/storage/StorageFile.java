package zhixuan.duke.storage;

import java.io.*;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;

import zhixuan.duke.data.task.*;
import zhixuan.duke.ui.DukeUI;

public class StorageFile {

    private static String createDirectory() {

        String directoryName = System.getProperty("user.dir") + File.separator + "user-files";
        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
        }
        return directoryName;
    }

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
