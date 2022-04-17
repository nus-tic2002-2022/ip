# Duke (Variant)

A personal task manager that keeps track of your task!

Features includes:
1. Adding of different types of task
   1. ToDos
   2. Deadlines
   3. Events
   4. Fixed Duration Task
2. Listing of Task
   1. By order of add
   2. By datetime
   3. Events / Deadline occuring on a specific date
3. Edit of task
   1. Description
   2. Date of event / deadline
4. Clone task
5. Find Task
   1. Word Search
   2. Character Search
6. Delete Task (single or all)

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
