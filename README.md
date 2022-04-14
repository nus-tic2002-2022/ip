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
### `todo` Add a todo task
- Format: `todo <description>`
- Example: `todo borrow book`
- Expected outcome: 

```
1: [T][ ] borrow book 
```
<br/>

### `deadline` Add a deadline task on a specific date
- Format: `deadline <description> /by <yyyy-mm-dd>`
- Example: `deadline return book /by 2022-05-01`
- Expected outcome:

```
1: [T][ ] borrow book
2: [D][ ] return book  (by: May 01 2022)
```
<br/>

### `event` Add an event on a specific date and time
- Format: `event <description> /at <yyyy-mm-dd 0000>`
- Example: `event extend book loan /at 2022-04-15 0000`
- Expected outcome:

```
1: [T][ ] borrow book
2: [D][ ] return book  (by: May 01 2022)
3: [E][ ] extend book loan  (at: Apr 15 2022 0000)
```

## Commands
### `list` Shows you all you finished and unfinished tasks.
- Format: `list`
- Example: `list`
- Expected outcome:

```
1: [T][ ] borrow book
2: [D][ ] return book  (by: May 01 2022)
3: [E][ ] extend book loan  (at: Apr 15 2022 0000)
```
<br/>

### `find` Find tasks that contains to a keyword.
- Format: `find <keyword>`
- Example: `find return`
- Expected outcome:

```
2: [D][ ] return book  (by: May 01 2022)
```
<br/>

### `mark` Mark a single or multiple tasks.
- Format: `mark <id>`
- Example: `mark 1`
- Expected outcome:

```
1: [T][X] borrow book
2: [D][ ] return book  (by: May 01 2022)
3: [E][ ] extend book loan  (at: Apr 15 2022 0000)
```
<br/>

### `unmark` Unmark a single or multiple tasks.
- Format: `unmark <id>`
- Example: `unmark 1`
- Expected outcome:

```
1: [T][ ] borrow book
2: [D][ ] return book  (by: May 01 2022)
3: [E][ ] extend book loan  (at: Apr 15 2022 0000)
```
<br/>

### `delete` Delete a single or multiple task.
- Format: `delete <id>`
- Format: `delete <id,id,id>`
- Example: `delete 1,2`
- Expected outcome:

```
1: [E][ ] extend book loan  (at: Apr 15 2022 0000)
```
<br/>

### `no` Quit application
- Format: `no`
- Example: `no`
- Expected outcome:

```
************
See YU never
************
```






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
