# Introduction

This is an unfriendly chatbot named: Knot YU. <br/>
It helps to keep track of our busy life with daily tasks management by having a To-Do List.  

# Features
Knot YU have several basic features to manage your To-Do List, and it has the capabilities to auto-save and load.
1. List Tasks - Shows you all you finished and unfinished tasks.
2. Add Task - Add a single task into your list.
3. Delete Task(s) - Delete a single or multiple task.
4. Find Task(s) - Find tasks that contains to a keyword.
5. Mark Task(s) - Mark a single or multiple tasks. 
6. Unmark Task(s) - Unmark a single or multiple tasks.

## Add a task
### `todo`
### `deadline`
### `event`

## Commands
### `list` Shows you all you finished and unfinished tasks.
### `find`
### `mark`
### `unmark`
### `delete`
### `no`






# Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the
   code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something
   like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
