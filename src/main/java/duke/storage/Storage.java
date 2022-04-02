package duke.storage;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.*;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }
    public ArrayList<String> data = new ArrayList<>();
    private Ui ui;
    public TaskList taskList;
    private Storage storage;

    public TaskList load() throws IOException {
        File f = new File(filePath);
        taskList = new TaskList();
        ui = new Ui();

        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String row = s.nextLine();
            data.add(row);
        }
        classifyTask(data);
        return taskList;
    }

    private void classifyTask(ArrayList<String> data) {
        Command command;
        ui.showLine();
        ui.show("\tProcessing data from file");
        ui.showLine();

        int count = 1;

        for (String d : data) {
            try {
                command = new Parser().parseCommandFromFile(d);
                command.execute(taskList, ui, storage);
                command = new Parser().parseDoneStatusFromFile(d, count);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.show(e.getMessage());
            }
            count++;
        }
        ui.showLine();
    }

    public void save() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = taskList.getListOfSavedTask();
        FileWriter fw = new FileWriter(filePath);
        for (Task l : tasks) {
            fw.write(l + System.lineSeparator());
        }
    }

}

