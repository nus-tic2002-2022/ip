import duke.*;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Duke {
    private Scanner in = new Scanner(System.in);
    private String input;
    private Storage storage;
    private Tasklist tasks;
    private UI ui;
    private Pattern archive = Pattern.compile("^archive.*$");

    //The following method takes an input file and reads it. It attempts to store the values from the file into a tasks Tasklist.
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);

        //A-Assertion feature, asserts file type is .txt
        assert storage.returnFile().getName().contains(".txt"):"Invalid file type!";

        try {
           tasks = new Tasklist(storage.readFromFile());
        } catch (Exception e) {
           ui.showLoadingError();
           tasks = new Tasklist();
        }
    }

    //The following method runs a loop to take in user inputs, until a user types in 'bye' to signal an exit to the program.
    public void run(){
        while(true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ui.exit();
                in.close();
                break;
            }else if(archive.matcher(input).matches()){
                try {
                    if (input.split("\\s").length < 2) {
                        throw new DukeException("Insufficient arguments!");
                    }
                    input = input.replaceAll("^archive\\s", "").replaceAll("\\s", "");
                    storage.saveToFile(tasks, input);
                    tasks.deleteAll();
                } catch (DukeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }else{
                ui.Query(tasks, input);
            }
        }
        storage.saveToFile(tasks);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        new Duke(System.getProperty("user.dir")).run();
    }
}
