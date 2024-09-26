package duke;

import duke.command.*;
import duke.exceptions.DukeCheckLineException;
import duke.exceptions.DukeException;
import duke.expandD.ExParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class Duke{

    private Ui ui;
    private Parser parser;
    private ExParser exParser;
    /**
     * Constructs Duke application.
     *
     * @param filePath the file path of the save file for tasks storage.
     */
    public Duke(String filePath){
        ui = new Ui();
        exParser = new ExParser();
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
        parser.capture("find", new FindCommand(tasks));
    }

    static void checkWord(String keyWord)throws DukeCheckLineException{
        String keyword = keyWord.toLowerCase();

        if (!keyword.equals("list") && !keyword.equals("bye")
                && !keyword.equals("todo") && !keyword.equals("done")
                && !keyword.equals("event") && !keyword.equals("deadline")
                && !keyword.equals("delete")&& !keyword.equals("find")
                && !keyword.equals("D")){
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
            if (fullCommand[0].equalsIgnoreCase("D")){
                List<String>list = new ArrayList<>();
                for (int i = 1; i < fullCommand.length; i++) {
                    list.add(fullCommand[i]);
                }
                exParser.parse(list);
                exParser.exit();
                continue;
            }
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