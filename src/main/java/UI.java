import duke.constants.DukeConstants;
import duke.tasklist.Tasklist;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class UI {
    //The following method takes in the user input and attempts to identify what to do with the input (i.e. store as a Deadline, Event or Task, etc).
    //It will then add the new task to the existing Tasklist.
    public Tasklist Query(Tasklist tasks, String input) {
        if(input.equalsIgnoreCase("list")) {
            tasks.list();
        }else if(input.equalsIgnoreCase("help")) {
            help();
        }else if (DukeConstants.MARK.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");
            tasks.mark(Integer.parseInt(input));
        } else if (DukeConstants.UNMARK.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");
            tasks.unmark(Integer.parseInt(input));
        } else if (DukeConstants.REMOVE.matcher(input).matches()) {
            input = input.replaceAll("\\D+", "");

            //A-Assertion feature, asserts input argument is not blank
            assert !input.isBlank():"Input argument cannot be blank!";

            tasks.delete(Integer.parseInt(input));
        } else if (DukeConstants.TODO.matcher(input).matches()) {
            try {
                if (input.split(" ").length < 2) {
                    throw new DukeException("Insufficient arguments!");
                }
                input = input.replaceAll("^todo\\s", "");
                tasks.add(new Todo(input));
                System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Tip: An example command would be, 'todo task'.");
            }
        } else if (DukeConstants.DEADLINE.matcher(input).matches()) {
            try {
                if(!input.contains("/by")){
                    throw new DukeException("Missing argument: /by");
                }else if (input.split("/by").length < 2) {
                    throw new DukeException("Insufficient arguments!");
                }
                input = input.replaceAll("^deadline\\s", "");
                String[] splited = input.split("/by", 2);
                tasks.add(new Deadline(splited[0], splited[1].replaceAll("^\\s", "")));
                System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Tip: An example command would be, 'deadline task /by 2022-10-20 23:59'.");
            }
        } else if (DukeConstants.EVENT.matcher(input).matches()) {
            try {
                if(!input.contains("/at")){
                    throw new DukeException("Missing argument: /at");
                }else if (input.split("/at").length < 2) {
                    throw new DukeException("Insufficient arguments!");
                }
                input = input.replaceAll("^event\\s", "");
                String[] splited = input.split("/at", 2);
                tasks.add(new Event(splited[0], splited[1].replaceAll("^\\s", "")));
                System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Tip: An example command would be, 'event concert /at school'.");
            }
        } else if (DukeConstants.FIND.matcher(input).matches()) {
            System.out.println("Here are the matching tasks in your list:");
            input = input.replaceAll("^find\\s", "");
            tasks.list(input);
        }else if (input.length() > 0) {
            tasks.add(new Task(input));
            System.out.println("added: " + input);
        } else {
            System.out.println("Please enter something!");
        }
        return tasks;
    }

    //Exit method. Prints an exit message to the user.
    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Help method. Prints out a list of possible commands.
    public void help(){
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
    public void showLoadingError(){
        System.out.println("Error: Unable to read or locate local saved file!");
    }
}
