import java.util.regex.Pattern;
import duke.*;

public class UI {
    Pattern m = Pattern.compile("^mark\\s[0-9]*$");
    Pattern u = Pattern.compile("^unmark\\s[0-9]*$");
    Pattern rm = Pattern.compile("^delete\\s[0-9]*$");
    Pattern t = Pattern.compile("^todo.*$");
    Pattern d = Pattern.compile("^deadline.*$");
    Pattern e = Pattern.compile("^event.*$");

    public void UI(){
    }

    public Tasklist Query(Tasklist tasks, String input) {
        switch (input) {
            case "list":
                tasks.list();
                break;
            default:
                if (m.matcher(input).matches()) {
                    input = input.replaceAll("\\D+", "");
                    tasks.mark(Integer.parseInt(input));
                } else if (u.matcher(input).matches()) {
                    input = input.replaceAll("\\D+", "");
                    tasks.unmark(Integer.parseInt(input));
                } else if (rm.matcher(input).matches()) {
                    input = input.replaceAll("\\D+", "");
                    tasks.delete(Integer.parseInt(input));
                } else if (t.matcher(input).matches()) {
                    try {
                        String[] check = input.split(" ");
                        if (check.length < 2) {
                            throw new DukeException("Insufficient arguments!");
                        }
                        input = input.replaceAll("^todo\\s", "");
                        tasks.add(new Todo(input));
                        System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (d.matcher(input).matches()) {
                    try {
                        String[] check = input.split("/by");
                        if (check.length < 2) {
                            throw new DukeException("Insufficient arguments!");
                        }
                        input = input.replaceAll("^deadline\\s", "");
                        String[] splited = input.split("/by", 2);
                        tasks.add(new Deadline(splited[0], splited[1].replaceAll("^\\s", "")));
                        System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (e.matcher(input).matches()) {
                    try {
                        String[] check = input.split("/at");
                        if (check.length < 2) {
                            throw new DukeException("Insufficient arguments!");
                        }
                        input = input.replaceAll("^event\\s", "");
                        String[] splited = input.split("/at", 2);
                        tasks.add(new Event(splited[0], splited[1].replaceAll("^\\s", "")));
                        System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size() - 1).getDescription() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (input.length() > 0) {
                    tasks.add(new Task(input));
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Please enter something!");
                }
        }
        return tasks;
    }
    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError(){
        System.out.println("Error: Unable to read or locate local saved file!");
    }
}
