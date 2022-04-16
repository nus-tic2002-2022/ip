package zhixuan.duke.data.task;

import zhixuan.duke.storage.TaskEncoder;
import zhixuan.duke.ui.DukeUI;
import zhixuan.duke.common.EnumTask;
import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.exceptions.UnknownCommandException;
import zhixuan.duke.common.Messages;

import java.util.ArrayList;

public class TaskManager {

    private static TaskManager taskManager;
    private final DukeUI ui;
    private ArrayList<Task> taskList;

    private TaskManager () {
        ui = new DukeUI();
        taskList = new ArrayList<>();
    }

    public static synchronized TaskManager getInstance( ) {
        if (taskManager == null)
            taskManager = new TaskManager();
        return taskManager;
    }

    public void listTask() {
        try {
            String list;
            if (!taskList.isEmpty()) {
                list = Messages.REPLY_LIST;
                for (int i = 0; i < taskList.size(); i++) {
                    if (i != 0) {
                        list = list.concat("\n");
                    }
                    list = list.concat(String.valueOf(i + 1)).concat(".");
                    list = list.concat(String.valueOf(taskList.get(i)));
                }
            }
            else {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            ui.showToUser(list);
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
        }
    }

    public String getTaskAmount() {
        return "You have " + taskList.size() + " tasks in your list.";
    }

    public boolean addToTaskList(EnumTask type, boolean isDone, String input) {
        try {
            String[] commandList;
            switch (type) {
                case TODO:
                    if (input.isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_TODO_EMPTY);
                    taskList.add(new Todo(input, isDone));
                    break;
                case DEADLINE:
                    if (!input.contains(" /by ")) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_DATE);
                    commandList = input.split(" /by ");
                    if (commandList[0].isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_DESC);
                    if (commandList.length != 2) throw new InvalidTaskException(InvalidTaskException.REPLY_DEADLINE_INVALID_LENGTH);
                    taskList.add(new Deadline(commandList[0].trim(), isDone, commandList[1].trim()));
                    break;
                case EVENT:
                    if (!input.contains(" /at ")) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_DATE);
                    commandList = input.split(" /at ");
                    if (commandList[0].isEmpty()) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_DESC);
                    if (commandList.length != 2) throw new InvalidTaskException(InvalidTaskException.REPLY_EVENT_INVALID_LENGTH);
                    taskList.add(new Event(commandList[0].trim(), isDone, commandList[1].trim()));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            ui.showToUser(Messages.REPLY_ADD_TASK + taskList.get(taskList.size()-1) + "\n" + getTaskAmount());
            return true;
        } catch (InvalidTaskException e) {
            ui.showToUser(e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            ui.showToUser(UnknownCommandException.ERROR);
            return false;
        }
    }

    public boolean markTask(String command, int taskIndex) {

        boolean success = false;
        try {
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            if (taskIndex<0 || taskList.size()<taskIndex) {
                throw new UnknownCommandException();
            }
            taskIndex--;
            String reply = "";
            if (command.equals("mark")) {
                success = taskList.get(taskIndex).markAsDone();
                if (success) {
                    reply = Messages.REPLY_MARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_MARK_TASK;
                }
            }
            else if (command.equals("unmark")) {
                success = taskList.get(taskIndex).markAsUndone();
                if (success) {
                    reply = Messages.REPLY_UNMARK_TASK;
                } else {
                    reply = Messages.REPLY_ALR_UNMARK_TASK;
                }
            }

            reply = reply.concat("\n\t").concat(taskList.get(taskIndex).toString());
            ui.showToUser(reply);
            return success;
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            return false;
        }
    }

    public void deleteTask(int taskIndex) {
        try {
            if (taskList.isEmpty()) {
                throw new UnknownCommandException(UnknownCommandException.EMPTY);
            }
            if (taskIndex < 1 || taskList.size()<taskIndex) {
                throw new UnknownCommandException();
            }
            String output = Messages.REPLY_DELETE_TASK + taskList.get(taskIndex).toString() + "\n";
            taskList.remove(taskIndex);
            output += getTaskAmount();
            ui.showToUser(output);
        } catch (UnknownCommandException e) {
            ui.showToUser(e.getMessage());
            }
        }

    public ArrayList<String> getAllTask() {
        return TaskEncoder.encodeTaskList(taskList);
    }

}

