package project.gennie;

import java.util.Scanner;

public class addTasks
{
    public void addTasks()
    {
        String line;
        String[] store = new String [100];
        int storeWords = 0;
        int mark;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            line = in.nextLine();
            System.out.println("------------------------------------------\n");
            if (line.equals("bye") || line.equals("Bye"))
                System.out.println("Summon me anytime if you need me! Bye ~ ");
            if (line.equals("bye")|| line.equals("Bye"))
                break;
            if (line.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.length; i++)
                {
                    if (store[i] != null)
                        System.out.println((i + 1) + "." + " [ ] " + store[i]);
                }
                System.out.println("------------------------------------------\n");
                continue;
            }

            store[storeWords++] = line;
            System.out.println("\tAdded: " + line);
            System.out.println("------------------------------------------\n");
    }
}}
