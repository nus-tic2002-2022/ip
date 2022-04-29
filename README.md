# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

# List of commands

Below is the list of commands that can be executed.

1. Add task 
   1. Todo (todo [description])
   2. Event (event [description] /at [yyyy-MM-dd HH:mm])
   3. Deadline (deadline [description] /at [yyyy-MM-dd HH:mm])

2. List of tasks (list)
   
3. Update task
   1. Mark task (mark [index of task])
   2. Unmark task (unmark [index of task])

4. Find task
   1. Word Search (find [keyword])
   2. Date Search (find [yyyy-MM-dd HH:mm])

5. Snooze task (snooze [index of task] [year/month/day/hour/minute] [amount])

5. Load file (load)

6. Delete task (delete [index of task]

7. Exit program (bye)

---

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
