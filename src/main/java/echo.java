import java.util.*;
public class echo {
    private static ArrayList <String> buffer = new ArrayList<>();
    public static void greet ()
    {
        Scanner scanInput = new Scanner(System.in);
        String passed = scanInput.nextLine();
        //buffer.add(passed);
        //System.out.println(passed);
        int c_flag =0;
        for (int i =0; !passed.equals("bye");)
        {
            if(passed.equals("list")) //if list
            {
                int j =1;
                for(String s : buffer) {
                    System.out.println(j + ". " + s);
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
            if(buffer.contains(passed)) //if contains, print out but no need add into buffer
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
                System.out.println(passed);
                buffer.add(passed);
                System.out.println("Size of buffer: " + buffer.size());
                passed = scanInput.nextLine();
                continue;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
