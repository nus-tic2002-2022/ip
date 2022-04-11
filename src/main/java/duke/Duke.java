package duke;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;


public class Duke{

    private Ui ui;
    private Parser parser;

    /**
     * Constructs Duke application.
     *
     * @param filePath the file path of the save file for tasks storage.
     */
    public Duke(String filePath){
        ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList tasks;

        try{
            tasks = new TaskList(storage.load());
        }catch(IOException e){
            if (!(e instanceof NoSuchFileException)){
                ui.showLoadingError("Cannot load tasks. May overwrite old tasks, if continue");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
        parser = new Parser();
        parser.capture("todo", ToDo.getCommand(tasks, storage));
        parser.capture("event", Event.getCommand(tasks, storage));
        parser.capture("deadline", Deadline.getCommand(tasks, storage));
        parser.capture("list", new ListCommand(tasks));
        parser.capture("done", new DoneCommand(tasks,storage));
        parser.capture("delete", new DeleteCommand(tasks, storage));
        parser.capture("bye", new ByeCommand());
    }

    static void checkWord(String keyWord)throws DukeCheckLineException{
        String keyword = keyWord.toLowerCase();

        if (!keyword.equals("list") && !keyword.equals("bye")
                && !keyword.equals("todo") && !keyword.equals("done")
                && !keyword.equals("event") && !keyword.equals("deadline")
                && !keyword.equals("delete")){
            throw new DukeCheckLineException();
        }
    }

    /**
     * Run Duke application.
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;

        while(!isExit && ui.hasNextLine()){
            String[] fullCommand = ui.readCommand().split(" ");
            ui.printWithLine(List.of());
            try{
                checkWord(fullCommand[0]);
                Command c = parser.parse(fullCommand);
                ui.printCommand(c.run(fullCommand));
                isExit = c.isExit();
            }catch(DukeException | IOException e){
                ui.showError(e.getMessage());
            }catch(DukeCheckLineException e){
                ui.showError(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args){
        new Duke("C:\\Users\\Administrator\\Documents\\duke\\data\\tasks.txt").run();
    }
}