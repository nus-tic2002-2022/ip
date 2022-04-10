package zhixuan.duke.parser;

import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.data.task.Deadline;
import zhixuan.duke.data.task.Event;
import zhixuan.duke.data.task.Task;
import zhixuan.duke.data.task.Todo;
import zhixuan.duke.ui.DukeUI;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private static void processInput(String input) throws UnknownCommandException {

        if (input.startsWith("bye")) {
            bye();
        }
        else if (input.startsWith("list")) {
            list();
        }
        else if (input.startsWith("todo")) {
            addItem("todo", input.replaceFirst("todo", "").trim());
            saveFile();
        }
        else if (input.startsWith("event")) {
            addItem("event", input.replaceFirst("event", "").trim());
            saveFile();
        }
        else if (input.startsWith("deadline")) {
            addItem("deadline", input.replaceFirst("deadline", "").trim());
            saveFile();
        }
        else if (input.startsWith("mark") || input.startsWith("unmark")) {
            String index = input.replaceAll("\\D+","");
            if (index.isEmpty()) throw new UnknownCommandException("Invalid task input. Use an integer.");
            mark(input, Integer.parseInt(index));
            saveFile();
        }
        else if (input.startsWith("delete")) {
            String index = input.replaceAll("\\D+","");
            if (index.isEmpty()) throw new UnknownCommandException("Invalid task input. Use an integer.");
            deleteItem(Integer.parseInt(index));
            saveFile();
        }
        else {
            throw new UnknownCommandException();
        }
    }
}
