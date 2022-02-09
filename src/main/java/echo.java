import java.util.*;
public class echo {
    private static ArrayList <Task> buffer = new ArrayList<>();
    public static void greet ()
    {
        Scanner scanInput = new Scanner(System.in);
        String passed = scanInput.nextLine();
        Task t_flag = new Task(passed);
         // to check if task existing
        //buffer.add(passed);
        //System.out.println(passed);
        int c_flag =0;
        for (int i =0; !passed.equals("bye");)
        {

            try {
                String[] str = passed.split(" ");

                if (str.length == 2 && (str[0].equals("mark") || str[0].equals("unmark"))) //if is mark or unmark and second is numeric
                {
                    int number = Integer.parseInt(str[1]);
                    if(str[0].equals("mark"))
                    {
                       System.out.println("Mark Task Number : " + number);
                    }
                    if(str[0].equals("unmark"))
                    {
                        System.out.println("UnMark Task Number : " + number);
                    }
                    int j = 1;
                    for (Task s : buffer) {
                        System.out.println(j + ". " + s.description);
                        j++;
                    }
                    passed = scanInput.nextLine();
                    continue;
                }
            }
            catch (NumberFormatException ex)
            {
                System.out.println("s[1] Not number");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Index out of bound");
            }
            if(passed.equals("list")) //if list, list out the task
            {
                int j =1;
                for(Task s : buffer) {
                    System.out.println(j + ". " + s.description);
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
            if(buffer.contains(t_flag)) //if contains, print out but no need add into buffer
            {
                c_flag = 1;
                System.out.println ("added: " + passed);
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                c_flag=0;
                continue;
            }
            if(c_flag == 0) // if not contains, print and add into buffer
            {
                Task t = new Task(passed);
                System.out.println(passed);
                buffer.add(t);
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
