import java.util.Scanner;

public class Duke {
    public Scanner in = new Scanner(System.in);
    String input;
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
       try {
           tasks = new Tasklist(storage.readFromFile());
        } catch (Exception e) {
           ui.showLoadingError();
           tasks = new Tasklist();
        }
    }

    public void run(){
        while(true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ui.exit();
                in.close();
                break;
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
