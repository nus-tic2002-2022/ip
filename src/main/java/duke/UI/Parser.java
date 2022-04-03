package duke.UI;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;
import duke.Tasklist.*;
import duke.command.*;

import java.awt.desktop.SystemEventListener;
import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Integer checkRecurring_gap(String between) throws DukeException, NumberFormatException
    {
        return Integer.parseInt(between);

    }
    public static Integer checkRecurring_count(String number) throws DukeException, dateparseException {
        return Integer.parseInt(number);}
    private static String str_concat(String[] str, int start, int end) {
        String ans = "";
        for (int i = start; i <= end; i++) {
            ans += (str[i] + " ");
        }
        return ans;
    }
    /**-------------------------------------------------------------**/
    private static boolean check_length(String[]passed) throws DukeException
    {
        if(passed[1].isEmpty())
        {
            throw new DukeException();
        }
        return true;
    }
    /**-------------------------------------------------------------**/
    public static String parsing_to_write (Task passed_task)
    {
        String str_1 = null;
        String str_2 = "0";
        String str_3;
        String str_4 = "";
        if(passed_task instanceof Deadlines)
        {
            str_1 = "D";
        }
        if(passed_task instanceof Events)
        {
            str_1 = "E";
        }
        if(passed_task instanceof ToDo)
        {
            str_1 = "T";
        }
        if(passed_task instanceof RecurringTask)
        {
            str_1 = "E";
        }
        if(passed_task.isDone){str_2="1";}
        str_3 = passed_task.description;
        if(passed_task instanceof Deadlines)
        {
            str_4 = ((Deadlines) passed_task).getBy();
        }
        if(passed_task instanceof Events)
        {
            str_4 = ((Events) passed_task).getDetails();
        }
        if(passed_task instanceof RecurringTask)
        {
            str_4 = ((RecurringTask) passed_task).getDetails();
        }

        return (str_1 +" | "+ str_2 +" | "+ str_3+" | "+ str_4);
    }
        /**-------------------------------------------------------------**/


    public static Command parsing(String passed) throws DukeException, dateparseException
    {
        String[] str = new String[20]; // to keep the passed sentence into string array
        try {       // parse to String array when received the passed task
            str = passed.split(" ");
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Index out of bound");
        }

        if (str.length == 2 && (str[0].equals("mark") || str[0].equals("unmark"))) //if is mark or unmark and second is numeric
        {
            int number;
            try { // if first word is "mark" or "unmark", check its second words must be an integer
                number = Integer.parseInt(str[1]);
                //marking(number, str[0]);
                MarkCommand passed_command = new MarkCommand(str[0], number);
                return passed_command;
            } catch (NumberFormatException ex) {
                System.out.println("s[1] Not number");
            }
        }
/***********************************************************************/
        if (str[0].equals("todo"))
        {
            try {
                if (check_length(str)) {
                    String s1 = str_concat(str,1, str.length-1);
                    ToDo t_flag = new ToDo(s1);
                    AddCommand passed_command= new AddCommand (str[0], t_flag);
                    return passed_command;
                }
            }
            catch (DukeException e)
            {
                System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }
        }
/***********************************************************************/
        if (str[0].equals("deadline"))
        {
            try{
                if(check_length(str)) {
                    String[] str2;
                    str2 = passed.split("/");
                    String descrip = str2[0].replaceAll("deadline", "");
                    String detail = str2[1];
                    Deadlines t_flag = new Deadlines(descrip, detail);
                    AddCommand passed_command= new AddCommand (str[0], t_flag);
                    return passed_command;
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Deadline without time");
            }
            catch (java.text.ParseException e)
            {
                System.out.println("Time error");
            }
            catch (DukeException e)
            {
                System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }
            catch (NullPointerException e)
            {
                System.out.println("Should be '/by', no need space");
            }

        }
        /***********************************************************************/
        if (str.length > 1 && str[0].equals("event"))
        {
            try {
                if (check_length(str)) {
                    String[] str2;
                    str2 = passed.split("/");
                    String descrip = str2[0].replaceAll("event", "");
                    String detail = str2[1];
                    System.out.println("To set as Recurring Events?");
                    //Task t_flag = null;
                    UI recurr_UI = new UI();
                    String recurr_command = recurr_UI.readCommand();
                    assert (recurr_command.equals("Y") || recurr_command.equals("y") || recurr_command.equals("N") || recurr_command.equals("n"));
                    if (recurr_command.equals("Y") || recurr_command.equals("y")){
                        System.out.println("First time event date on: YYYY-MM-DD");
                        String firstdate = recurr_UI.readCommand();

                        System.out.println("Recurring Period : ");
                        String recurr_period = recurr_UI.readCommand();
                        Integer between = checkRecurring_gap(recurr_period);

                        System.out.println("How many times to be recurred : ");
                        String recurr_count = recurr_UI.readCommand();
                        Integer number = checkRecurring_count(recurr_count);
                        RecurringTask t_flag = new RecurringTask(descrip,detail,firstdate,number,between);
                        Add_Recur_Command passed_command = new Add_Recur_Command(str[0],t_flag);
                        return passed_command;
                    }
                    else
                    {
                        Events t_flag = new Events(descrip, detail);
                        AddCommand passed_command =new AddCommand(str[0],t_flag);
                        return passed_command;
                    }
                }

            }
            catch (DukeException e)
            {
                System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Number format error");
            }
            catch (DateTimeParseException e)
            {
                System.out.println("Date Time Parse Error");
            }

        }
        /***********************************************************************/

        if(str[0].equals("list")) //if list, list out the task
        {
            ListCommand l = new ListCommand (str[0]);
            return l;
        }
        /***********************************************************************/
        if(str.length == 2 && str[0].equals("delete"))
        {
            int number;
            try{ // if first word is "delete", check its second words must be an integer
                number = Integer.parseInt(str[1]);

                DeleteCommand d = new DeleteCommand(str[0],number-1);
                return d;
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Delete number is not given");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }

        }

/***********************************************************************/

        if(str[0].equals("bye"))
        {
            ExitCommand passed_command = new ExitCommand(str[0]);
            return passed_command;
        }
        throw new DukeException();
    }
}
