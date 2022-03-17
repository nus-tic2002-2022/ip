package duke.commands;

import duke.parser.Parser;
import duke.tasklist.*;
import duke.exception.DukeException;

public class AddCommand extends Command{

    public static Task addTodo (Parser userInput, TaskList taskList) throws DukeException{
        String content = "";
        for (int i = 1; i < userInput.getUserInputSize(); i++) {
            content = content + " " + userInput.getUserInput(i);
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")){
            throw new DukeException("missing task");
        }
        else {
            Task curTask = new Task(content);
            taskList.add(curTask);
            return curTask;
        }
    }

    public static Task addDeadline (Parser userInput, TaskList taskList) throws DukeException{
        String content = "";
        String by = "";
        for (int i = 1; i < userInput.getUserInputSize();) {
            if (!userInput.getUserInput(i).equalsIgnoreCase("/by")) {
                content = content + " " + userInput.getUserInput(i);
                i++;
            }
            if (i < userInput.getUserInputSize() && userInput.getUserInput(i).equalsIgnoreCase("/by")) {
                i++;
                while (i < userInput.getUserInputSize()) {
                    by = by + userInput.getUserInput(i) + " ";
                    i++;
                }
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        final boolean b = by.equalsIgnoreCase("") || by.equalsIgnoreCase(" ") || by.equalsIgnoreCase("  ");
        if (content.equalsIgnoreCase("")) {
            if (b){
                throw new DukeException("missing task & missing by");
            }
            else {
                throw new DukeException("missing task");
            }
        }
        else {
            if (b) {
                throw new DukeException("missing by");
            } else {
                Deadline curTask = new Deadline(content, by);
                taskList.add(curTask);
                return curTask;
            }
        }

    }

    public static Task addEvent (Parser userInput, TaskList taskList) throws DukeException {
        String content = "";
        String at = "";
        for (int i = 1; i < userInput.getUserInputSize();) {
            if (!userInput.getUserInput(i).equalsIgnoreCase("/at")) {
                content = content + " " + userInput.getUserInput(i);
                i++;
            }
            if (i < userInput.getUserInputSize() && userInput.getUserInput(i).equalsIgnoreCase("/at")) {
                i++;
                while (i < userInput.getUserInputSize()) {
                    at = at + userInput.getUserInput(i) + " ";
                    i++;
                }
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        final boolean b = at.equalsIgnoreCase("") || at.equalsIgnoreCase(" ") || at.equalsIgnoreCase("  ");
        if (content.equalsIgnoreCase("")) {
            if (b){
                throw new DukeException("missing task & missing at");
            }
            else {
                throw new DukeException("missing task");
            }
        }
        else {
            if (b){
                throw new DukeException("missing at");
            }
            else {
                Event curTask = new Event(content, at);
                taskList.add(curTask);
                return curTask;
            }
        }
    }

}
