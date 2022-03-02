import java.util.*;

public class echo {
    private static ArrayList <Task> buffer = new ArrayList<>();
    public static String str_concat(String[] str, int start, int end)
    {
        String ans="";
        for(int i = start; i<= end;i++)
        {
            ans +=(str[i] + " ");
        }
        return ans;
    }
    public static boolean contains_in_arraylist (Task t_passed)
    {
        for (Task e: buffer)
        {
            if  (t_passed.description.equals(e.description))
            {
                System.out.println(t_passed.description + " "  + e.description);
                return true;
            }
        }
        return false;
    }
    public static boolean check_length(String [] passed) throws DukeException
    {
        if(passed[1].isEmpty())
        {
            throw new DukeException();

        }
        return true;
    }
    public static void greet ()
    {
        Scanner scanInput = new Scanner(System.in);
        String passed = scanInput.nextLine();
        int c_flag;//to check if contains the passed word
        int mark_flag; //to check if "mark"  or "unmark" is being passed in
        for (int i =0; !passed.equals("bye");)
        {
            //Task t_flag = new Task(passed);
            c_flag = 0;
            mark_flag = 0;
            String[] str = new String[20];
            //ArrayList<String> str = new ArrayList<String> (20);
            try {       // parse to String array when received the passed task
                str = passed.split(" ");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
                mark_flag = 1;
                passed = scanInput.nextLine();
            }

            if (str.length == 2 && (str[0].equals("mark") || str[0].equals("unmark"))) //if is mark or unmark and second is numeric
            {
                int number;
                try{ // if first word is "mark" or "unmark", check its second words must be an integer
                    number = Integer.parseInt(str[1]);
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("s[1] Not number");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                    break;
                }

                //int number = Integer.parseInt(str[1]);
                if (str[0].equals("mark")) {
                    //System.out.println("Mark Task Number : " + number);
                    System.out.println("Nice! I've marked this task as done: ");
                    buffer.get(number - 1).setDone();
                    System.out.println(buffer.get(number - 1).getStatus());
                    mark_flag = 1;
                }
                if (str[0].equals("unmark")) {
                    System.out.println("UnMark Task Number : " + number);
                    System.out.println("OK, I've marked this task as not done yet:");
                    buffer.get(number - 1).set_unDone();
                    System.out.println(buffer.get(number - 1).getStatus());
                    mark_flag = 1;
                }
                passed = scanInput.nextLine();
                continue;
            }
            if (str[0].equals("todo"))
            {
                try {
                    if (check_length(str)) {
                        String s1 = str_concat(str,1, str.length-1);
                        ToDo t_flag = new ToDo(s1);
                        buffer.add(t_flag);
                        System.out.println("Todo");
                        mark_flag = 1;

                        passed = scanInput.nextLine();
                    }
                }
                catch (DukeException e)
                {
                    System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }
                 catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Index out of bound");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }
            }
            if (str[0].equals("deadline"))
            {
                try{
                    if(check_length(str)) {
                        String[] str2 = new String[2];
                        //int backslash = Arrays.asList(str).indexOf("/");
                        str2 = passed.split("/");
                        //String descrip = str_concat(str,1,backslash);
                        //String detail = str_concat(str,backslash, str.length-1);
                        String descrip = str2[0].replaceAll("deadline", "");
                        String detail = str2[1];
                        Deadlines t_flag = new Deadlines(descrip, detail);
                        buffer.add(t_flag);
                        mark_flag = 1;
                        passed = scanInput.nextLine();
                    }
                }
                catch (DukeException e)
                {
                    System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Index out of bound");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }

            }
            if (str.length > 1 && str[0].equals("event"))
            {
                try{
                    if(check_length(str)) {
                        String[] str2 = new String[2];
                        //int backslash = Arrays.asList(str).indexOf("/");
                        str2 = passed.split("/");
                        //String descrip = str_concat(str,1,backslash);
                        //String detail = str_concat(str,backslash, str.length-1);
                        String descrip = str2[0].replaceAll("event", "");
                        String detail = str2[1];
                        Events t_flag = new Events(descrip, detail);
                        buffer.add(t_flag);
                        mark_flag = 1;
                        passed = scanInput.nextLine();
                    }
                }
                catch (DukeException e)
                {
                    System.out.println("OOPS!!! The description of a " + str[0] + " cannot be empty.");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Index out of bound");
                    mark_flag = 1;
                    passed = scanInput.nextLine();
                }

            }
            if(passed.equals("list")) //if list, list out the task
            {
                int j =1;
                for(Task s : buffer) {
                    System.out.println(j + "."+s.getStatus());
                    j++;
                }
                mark_flag = 1;
                passed = scanInput.nextLine();
                continue;
            }
            if(passed.isEmpty()) //if empty, just skip
            {
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
            if(c_flag == 0 && mark_flag == 0  && !passed.equals("list")) // if not contains, print and add into buffer
            {
                Task t_flag = new Task(passed);
                if (contains_in_arraylist(t_flag)) //if contains, print out but no need add into buffer
                {
                    c_flag = 1;
                    System.out.println("Already added");
                    System.out.println("Size of buffer: " + buffer.size());
                    passed = scanInput.nextLine();
                    continue;
                } else {
                    System.out.println(passed);
                    buffer.add(t_flag);
                    System.out.println("Size of buffer: " + buffer.size());
                    passed = scanInput.nextLine();
                    continue;
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
