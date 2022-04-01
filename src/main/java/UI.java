import duke.constants.DukeConstants;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class UI {
    //The following method takes in the user input and attempts to identify what to do with the input (i.e. store as a Deadline, Event or Task, etc).
    //It will then add the new task to the existing Tasklist.
    public TaskList Query(TaskList tasks, String input) {
        final String inputIsEmptyResponse = "Please enter something!";

        if (input.equalsIgnoreCase("list")) {
            tasks.list();
        } else if (input.equalsIgnoreCase("help")) {
            help();
        } else if (DukeConstants.MARK.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");
            tasks.mark(Integer.parseInt(input));
        } else if (DukeConstants.UNMARK.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");
            tasks.unmark(Integer.parseInt(input));
        } else if (DukeConstants.REMOVE.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");

            //A-Assertion feature, asserts input argument is not blank
            assert !input.isBlank() : "Input argument cannot be blank!";

            tasks.delete(Integer.parseInt(input));
        } else if (DukeConstants.TODO.matcher(input).matches()) {
            addTodo(tasks, input);
        } else if (DukeConstants.DEADLINE.matcher(input).matches()) {
            addDeadline(tasks, input);
        } else if (DukeConstants.EVENT.matcher(input).matches()) {
            addEvent(tasks, input);
        } else if (DukeConstants.FIND.matcher(input).matches()) {
            findTask(tasks, input);
        } else if (input.length() > 0) {
            addTask(tasks, input);
        } else {
            System.out.println(inputIsEmptyResponse);
        }
        return tasks;
    }

    private void addTask(final TaskList tasks, final String input) {
        tasks.add(new Task(input, false));
        System.out.println("added: " + input);
    }

    private void findTask(final TaskList tasks, final String st) {
        System.out.println("Here are the matching tasks in your list:");
        String input = st;
        input = input.replaceAll("^find\\s", "");
        tasks.list(input);
    }

    private void addEvent(final TaskList tasks, final String st) {
        final String eventTip = "\nTip: An example command would be, 'event concert /at school'.";
        String input = st;
        try {
            if (!input.contains("/at")) {
                throw new DukeException("Missing argument: /at");
            } else if (input.split("/at").length < 2) {
                throw new DukeException("Insufficient arguments!");
            }
            input = input.replaceAll("^event\\s", "");
            String[] splitted = input.split("/at", 2);
            tasks.add(new Event(splitted[0], splitted[1].replaceAll("^\\s", ""), false));
            System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage() + eventTip);
        }
    }

    private void addDeadline(final TaskList tasks, final String st) {
        final String deadlineTip = "\nTip: An example command would be, 'deadline task /by 2022-10-20 23:59'.";
        String input = st;
        try {
            if (!input.contains("/by")) {
                throw new DukeException("Missing argument: /by");
            } else if (input.split("/by").length < 2) {
                throw new DukeException("Insufficient arguments!");
            }
            input = input.replaceAll("^deadline\\s", "");
            String[] splitted = input.split("/by", 2);
            tasks.add(new Deadline(splitted[0], splitted[1].replaceAll("^\\s", ""), false));
            System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage() + deadlineTip);
        }
    }

    private void addTodo(final TaskList tasks, final String st) {
        final String todoTip = "\nTip: An example command would be, 'todo task'.";
        String input = st;
        try {
            if (input.split(" ").length < 2) {
                throw new DukeException("Insufficient arguments!");
            }
            input = input.replaceAll("^todo\\s", "");
            tasks.add(new Todo(input, false));
            System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage() + todoTip);
        }
    }

    //Exit method. Prints an exit message to the user.
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Help method. Prints out a list of possible commands.
    public void help() {
        System.out.println("Hi, this is Duke. I am here to help you keep track of your schedule.");
        System.out.println("Here are a list of possible commands I will understand:");
        System.out.println("1. list");
        System.out.println("2. todo");
        System.out.println("3. event");
        System.out.println("4. deadline");
        System.out.println("5. archive");
        System.out.println("6. find");
        System.out.println("7. bye");
    }

    //The following method prints an error to inform the user that the specified file location is invalid or the file is unavailable.
    public void showLoadingError() {
        System.out.println("Error: Unable to read or locate local saved file!");
    }
}