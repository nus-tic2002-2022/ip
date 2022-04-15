import java.io.IOException;
import java.io.File;

public class Duke {
    private final static String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello from\n" + logo);
        String home = System.getProperty("user.dir");
        File newFile = new File(home + "\\" + "taskList.txt");
        if(!newFile.exists()) {
            try {
                if (newFile.createNewFile()) {
                    System.out.println("\t" + "Save file created: " + newFile.getName());
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("\t" + "Save file already exists.");
        }
        Storage storage = new Storage("taskList.txt");
        TaskManager taskManager = new TaskManager(storage);

        taskManager.run();
    }
}

