package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskStorage implements Storage {
    protected File file;
    protected String path;
    protected TaskList tasks = new TaskList();

    public TaskStorage() {
        this.path = "duke.txt";
    }

    /**
     * Initialise file.
     * Create file if file does not exist on the system at run time
     */
    @Override
    public void init() throws Exception {

        assert !this.path.equals("") : "No file path found";
        try {
            this.file = new File(this.path);
            path = this.file.getPath();
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("Created a new file at: " + this.path);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found or created: " + e);
        }
    }

    /**
     * Load all tasks from file
     */
    @Override
    public void load() {
        try {
            init();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            String[] tokens;
            Task task;
            while ((line = br.readLine()) != null) {
                tokens = line.split(",");
                task = new Task(Integer.parseInt(tokens[2]), tokens[3]);

                if (Integer.parseInt(tokens[0]) == 1) {
                    task.markAsDone();
                }

                switch (tokens[1].charAt(0)) {
                    case 'd':
                        task = new Deadline(task, LocalDate.parse(tokens[4]));
                        tasks.insertTask(task);
                        break;
                    case 'e':
                        task = new Events(task, LocalDate.parse(tokens[4]), tokens[5]);
                        tasks.insertTask(task);
                        break;
                    case 't':
                        task = new Todo(task);
                        tasks.insertTask(task);
                        break;
                    default:
                        System.out.println("Line error");
                }
            }
            fr.close();
            System.out.println("File objects loaded");
            tasks.printList();

        } catch (Exception e) {
            System.out.println("Loading error: " + e);
        }
    }

    /**
     * Save all tasks into file after each update
     */
    @Override
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("duke.txt");
            tasks.forEach(task -> {
                try {
                    fw.write(task.toStorage() + "\n");
                } catch (Exception e) {
                    System.out.println("Unable to write to file: " + e);
                }
            });
            fw.close();
        } catch (Exception e) {
            System.out.println("Failed to save: " + e);
        }
    }
}