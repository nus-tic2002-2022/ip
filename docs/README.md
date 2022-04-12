# User Guide for Duke
Author: Ang Jing Xuan A0194262A (NUS BTech TIC2002 - AY21/22 Sem 2)


## Features 

### List task(s)

You can ask Duke what's on your list right now.

### Create new task(s) - Todo, Deadline, Event

You can tell Duke any todos, deadlines or events that you have. Duke will help you to remember them.
* Todos are tasks without dates attached.
* Deadlines are tasks with dates attached and are something that you need to do.
* Events are tasks with dates attached and are something that you need to attend. 

### Mark, Unmark task(s)

You can mark the task when it is done, or unmark the task when it is not done.

### Find task(s)

You can look for task(s) with certain keyword(s) by using the find feature.

### Delete task(s)

You can delete tasks which you no longer want to track.

### Sort task(s)

You can sort your task list according to dates by ascending order. 

### Ask for Help

Whenever you are stuck, don't worry, you can use the Help feature to get the list of commands which you can type.

### Say Hello to Duke

Say Hello to Duke anytime you want! :)

### Say Bye to Duke

Say Bye to Duke when you are logging out! See you soon! :)


## Usage

### How to start Duke

Prerequisites:
Ensure that you have Java 17 on your machine. 

1. Download duke-JAR_v4.zip.
2. Unzip the file. You should see "data" and "docs" folder and "duke.Duke.jar". [If there is no "data" folder, kindly create the "data" folder.]
3. Open command prompt/ terminal from the folder.
4. Type `java -jar duke.Duke.jar` in the command prompt/ terminal.
5. Duke will start.

Duke can be run on various platforms - Windows, Unix and macOS.

### `List` - Type "list"

Type in "list" and Duke will show you the list of tasks that you currently have.

Example of usage:

`list`

Expected outcome:

Duke shows you the list of tasks you currently have in your list. 

```
1.[T] [X] read a book
2.[D] [ ] submit duke (by: 2022-03-16T18:00)
3.[E] [ ] tic2002 class (at: 2022-03-23T19:00)
Now you have 3 task(s) in the list.
```

### `Todo` - Type "todo [task description]"

Type in "todo [task description]" and Duke will remember it for you. This action will automatically save the tasks into duke.txt.

Example of usage:

`todo read textbook`

Expected outcome:

Duke added your todo to the list!

```
Nice! I've added this task: 
[T] [ ] read textbook
Now you have 4 task(s) in the list.
```

### `Deadline` - Type "deadline [task description]"

Type in "deadline [task description] /by [datetime]" and Duke will remember it for you. This action will automatically save the tasks into duke.txt.

Example of usage:

`deadline submit duke project /by 2022-04-11T23:59`

Expected outcome:

Duke added your deadline to the list!

```
Nice! I've added this task: 
[D] [ ] submit duke project (by: 2022-04-11T23:59)
Now you have 5 task(s) in the list.
```

### `Event` - Type "event [task description]"

Type in "event [task description] /by [datetime]" and Duke will remember it for you. This action will automatically save the tasks into duke.txt.

Example of usage:

`event TIC2002 class /at 2022-04-06T19:00`

Expected outcome:

Duke added your event to the list!

```
Nice! I've added this task: 
[E] [ ] TIC2002 class (at: 2022-04-06T19:00)
Now you have 6 task(s) in the list.
```

### `Mark` - Type "mark [task position]"

Type in "mark [task position]" and Duke will mark the task as completed/done for you. This action will automatically save the tasks into duke.txt.

Example of usage:

`mark 3`

Expected outcome:

Duke marked your task as completed/done.

```
Nice! I've marked this task as done: 
[E] [X] tic2002 class (at: 2022-03-23T19:00)
```

### `Unmark` - Type "unmark [task position]"

Type in "unmark [task position]" and Duke will unmark the task as completed/done for you. The task will then be considered as still not done. This action will automatically save the tasks into duke.txt.

Example of usage:

`unmark 3`

Expected outcome:

Duke unmarked your task as completed/done.

```
OK, I've marked this task as not done yet: 
[E] [ ] tic2002 class (at: 2022-03-23T19:00)
```

### `Find` - Type "find [keywords]"

Type in "find [keywords]" and Duke will find the task that contains the keywords.

Example of usage:

`find tic`

Expected outcome:

Duke found and returned tasks that contains "tic", regardless of casing.

```
Here are the matching task(s) in your list: 
1.[E] [ ] tic2002 class (at: 2022-03-23T19:00)
2.[E] [ ] TIC2002 class (at: 2022-04-06T19:00)
There are 2 matching task(s) in the list.
```

### `Delete` - Type "delete [task position]"

Type in "delete [task position]" and Duke will delete the chosen task.

Example of usage:

`delete 2`

Expected outcome:

Duke deleted the task which you have chosen. 

```
Noted. I've removed this task: 
[D] [ ] submit duke (by: 2022-03-16T18:00)
Now you have 5 task(s) in the list.
```

### `Sort` - Type "sort"

Type in "sort" and Duke will sort the task list according to dates in ascending order. This action will automatically save the tasks into duke.txt.

Example of usage:

`sort`

Expected outcome:

Duke sorted the list according to the date in ascending order. Note that tasks are considered to have today's date. 

```
Noted. I've sorted the list by date: 
1.[E] [ ] tic2002 class (at: 2022-03-23T19:00)
2.[T] [X] read a book
3.[T] [ ] read textbook
4.[E] [ ] TIC2002 class (at: 2022-04-06T19:00)
5.[D] [ ] submit duke project (by: 2022-04-11T23:59)
Now you have 5 task(s) in the list.
```

### `Help` - Type "help"

Type in "help" and Duke will show you the commands that you can type when you are stuck.

Example of usage:

`help`

Expected outcome:

Duke shows you the list of commands you can type. 

```
Here are the commands you can type: 
1. type 'list' to see your current list.
2. type 'todo <add task here>' (e.g. todo read a book).
3. type 'deadline <add task here> /by <add deadline  in yyyy-MM-dd'T'HH:mm>' (e.g. deadline submit duke project /by 2022-04-11T23:59).
4. type 'event <add task here> /at <add event timing in yyyy-MM-dd'T'HH:mm>' (e.g. event attend TIC2002 class /at 2022-03-11T19:00).
5. type 'mark <add task number here>' (e.g. mark 1).
6. type 'unmark <add task number here>' (e.g. unmark 2).
7. type 'find <add keywords here>' (e.g. find tic).
8. type 'delete <add task number here>' (e.g. delete 2).
9. type 'sort' to sort the tasks by dates in ascending order.
10. type 'bye' to escape.
Find more in docs/README.md!
```

### `Hello` - Type "hello"

Type in "hello" to say hello to Duke! :) 

Example of usage:

`hello`

Expected outcome:

Duke says Hello to you too! :)

```
Hello from
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```

### `Bye` - Type "bye"

Type in "bye" to say bye to Duke when you are logging out! See you soon! :)

Example of usage:

`bye`

Expected outcome:

Duke says Bye to you too! :)

```
Bye. Hope to see you again soon!
```

## Errors

### File Error (IOException)
If the file cannot be saved or created, Duke prints the file error message, prompting the user to check the file in the mentioned path. Note that if the duke.txt file is not found, Duke will automatically create it. 
Kindly ensure that there is a /data/ folder at any point of time. 

Example in Windows:
```
The list file data\duke.txt is not found and/or cannot be created/saved. Please check.
```

Example in Unix/ macOS:
```
The list file data/duke.txt is not found and/or cannot be created/saved. Please check.
```

### Incorrect Date format (DateTimeParseException)
If the datetime format when adding Deadline or Event is not the right format, Duke prints the date format error message, prompting the user to check the required format by typing `help`.

Example:
```
The date format is not correct. Please type 'help' to check the date format for Events/Deadlines.
```

### Adding a Deadline/ Event without the proper format (StringIndexOutOfBoundsException)
If there is no proper description and datetime when creating deadline and event, Duke will print the short description error message, prompting the user to check the required format by typing `help`. 

Example:
```
The description is too short. Please type 'help' to check the date format for Events/Deadlines.
```

### Marking, Unmarking and Deleting more than 1 task at a time (NumberFormatException)
If a user happens to use mark, unmark or delete in this way, i.e. " mark 1 2", Duke prints the out of range message, prompting the user to type `list` to check the number of items on the list. 

### Blank Task Description
If a user types "todo " or "deadline " or "event ", Duke prints the blank description error message, prompting the user to type `help` to check the format for Todo/Deadlines/Events. 


----- This document is last updated on 2022-04-08T10:53. -----
