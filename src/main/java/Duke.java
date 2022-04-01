import duke.constants.DukeConstants;
import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final TaskList tasks;
    private final UI ui;
    private final Storage storage;

    //The following method takes an input file and reads it. It attempts to store the values from the file into a tasks Tasklist.
    public Duke(final String filePath) {
        ui = new UI();
        storage = new Storage(filePath);

        //A-Assertion feature, asserts file type is .txt
        assert storage.returnFile().getName().contains(".txt") : "Invalid file type!";

        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList = getTasksFromSave(taskArrayList);
        tasks = new TaskList(taskArrayList);
    }


    private ArrayList<Task> getTasksFromSave(ArrayList<Task> taskArrayList) {
        try {
            taskArrayList = storage.readFromFile();
        } catch (Exception e) {
            ui.showLoadingError();
        }
        return taskArrayList;
    }

    //The following method runs a loop to take in user inputs, until a user types in 'bye' to signal an exit to the program.
    public void run() {
        final Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ui.exit();
                in.close();
                break;
            } else if (DukeConstants.ARCHIVE.matcher(input).matches()) {
                archiveTaskList(input);
            } else {
                ui.Query(tasks, input);
            }
            storage.saveToFile(tasks);
        }
    }

    private void archiveTaskList(String input) {
        final String archiveTip = "\nTip: An example command would be, 'archive file1.txt'.";
        try {
            if (input.split("\\s").length < 2) {
                throw new DukeException("Insufficient arguments!");
            }
            input = input.replaceAll("^archive\\s", "").replaceAll("\\s", "");
            storage.saveToFile(tasks, input);
            tasks.deleteAll();
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage() + archiveTip);
        }
    }
}
