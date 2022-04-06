package duke;

import duke.commands.*;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.FileTaskList;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;



public class Duke {

    private Ui ui;
    private Parser parser;
    public Duke(String path){
        ui = new Ui();
        Storage storage = new Storage(path);
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                ui.showLoadingError("Cannot load tasks. May overwrite old tasks, if continue");
                e.printStackTrace();
            }
            tasks = new TaskList();
        }
        parser = new Parser();
        parser.capture("todo", Todo.getCommand(tasks, storage));
        parser.capture("event", Event.getCommand(tasks, storage));
        parser.capture("deadline", Deadline.getCommand(tasks, storage));
        parser.capture("list", new ListCommand(tasks));
        parser.capture("done", new DoneCommand(tasks,storage));
        parser.capture("delete", new DeleteCommand(tasks, storage));
        parser.capture("bye", new ByeCommand());


    }



    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\Administrator\\Documents\\duke\\data\\tasks.txt").run();
    }

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
                ui.showError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void printWithLine (List < String > messages) {
        for (String message : messages) {
            System.out.println("   " + message);
        }
        System.out.println("______________________________________________");
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

}



