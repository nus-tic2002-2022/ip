package duke.common;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_WELCOME = "Hello Master! My name is ";
    public static final String MESSAGE_GOODBYE = "Bye. Summon me when you need me again!!";
    public static final String MESSAGE_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-( ";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "Now you have %1$d tasks in the list!";
    public static final String MESSAGE_TASK_ADD = "Got it. I've added this task.";
    public static final String MESSAGE_TASK_DUPLICATE = "☹ OOPS!!! This is duplicate task. I won't add to the list. ";
    public static final String MESSAGE_TASK_SHOW = "Here are the tasks in your list:";
    public static final String MESSAGE_ZERO_TASK = "Sorry, there are currently no tasks in your list. Please add some.";
    public static final String MESSAGE_RESULT_TASK_SHOW = "Here are the matching tasks in your list:";
    public static final String MESSAGE_RESULT_NOT_FOUND = "There are no matching tasks in your list";
    public static final String MESSAGE_TASK_DELETE = "Noted. I've removed this task.";
    public static final String MESSAGE_TASK_MARK = "I've marked this task as done.";
    public static final String MESSAGE_TASK_UNMARK = "OK, I've marked this task as not done yet.";
    public static final String MESSAGE_DATE_FORMAT_ERROR = "Please enter date format correctly (eg. 2/12/2022 18:00).";
    public static final String MESSAGE_SHOW_TO_USER = "Here are the things that GENNIE can do for you : \nTo show all the tasks in the list, please type 'list' or 'l'\n" +
            "To add task under TODO category, please type 'todo [description]' or 't [description]'\n" +
            "To add task under EVENT category, please type 'event [description] /on d/M/yyyy H:m' or 'e [description]' /on d/M/yyyy H:m\n" +
            "To add task under DEADLINE category, please type 'deadline [description] /by d/M/yyyy H:m' or 'd [description]' /by d/M/yyyy H:m\n" +
            "To MARK task, please type 'mark [index]' or 'm [index]'\n" +
            "To UNMARK task, please type 'unmark [index]' or 'u [index]'\n" +
            "To DELETE task, please type 'delete [index]' or 'del [index]'\n" +
            "To FIND task, please type 'find [description]' or 'f [description]'\n" +
            "To clear the list, please type 'delete all' or 'del all'";
}
