import java.util.*;

public class echo {
    private static ArrayList <Task> buffer = new ArrayList<>();
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
    public static void greet ()
    {
        Scanner scanInput = new Scanner(System.in);
        String passed = scanInput.nextLine();
        int c_flag;//to check if contains the passed word
        int mark_flag; //to check if "mark"  or "unmark" is being passed in
        for (int i =0; !passed.equals("bye");)
        {
            Task t_flag = new Task(passed);
            c_flag = 0;
            mark_flag = 0;
            try {
                String[] str = passed.split(" ");

                if (str.length == 2 && (str[0].equals("mark") || str[0].equals("unmark"))) //if is mark or unmark and second is numeric
                {

                    int number = Integer.parseInt(str[1]);
                    if(str[0].equals("mark"))
                    {
                       //System.out.println("Mark Task Number : " + number);
                       System.out.println("Nice! I've marked this task as done: ");
                       buffer.get(number-1).setDone();
                       System.out.println(buffer.get(number-1).getStatus());
                       mark_flag = 1;
                    }
                    if(str[0].equals("unmark"))
                    {
                        System.out.println("UnMark Task Number : " + number);
                        System.out.println("OK, I've marked this task as not done yet:");
                        buffer.get(number-1).set_unDone();
                        System.out.println(buffer.get(number-1).getStatus());
                        mark_flag = 1;
                    }

                    passed = scanInput.nextLine();
                    continue;
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.println("s[1] Not number");
                mark_flag = 1;
                passed = scanInput.nextLine();

            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
                mark_flag = 1;
                passed = scanInput.nextLine();
            }
            if(passed.equals("list")) //if list, list out the task
            {
                int j =1;
                for(Task s : buffer) {

                    //String check_done = "";
                    //if(s.isDone) {check_done= "[X]";}
                    //else {check_done= "[ ]";}
                    System.out.println(j + "."+s.getStatus());
                    j++;
                }
                passed = scanInput.nextLine();
                continue;
            }
            if(passed.isEmpty()) //if empty, just skip
            {
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
            if(contains_in_arraylist(t_flag)) //if contains, print out but no need add into buffer
            {
                c_flag = 1;
                System.out.println ("Already added");
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
            if(c_flag == 0 && mark_flag == 0  && !passed.equals("list")) // if not contains, print and add into buffer
            {
                System.out.println(passed);
                buffer.add(t_flag);
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
