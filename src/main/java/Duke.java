import java.duke.utils.*;
import java.duke.importutil.*;
import java.duke.task.*;
import java.util.Scanner;


public class Duke {

    private TaskList tasks;
    private TaskFile file;
    private Ui ui;

    public Duke (String fp) {
        ui = new Ui();
        tasks = new TaskList();
        file = new TaskFile(fp);
    }

    public void run (){
        while(true) {
            Scanner scanInput = new Scanner(System.in); //Scan user input
            String userInput = scanInput.nextLine().trim(); //Read user input
            Parser.parse(userInput);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
