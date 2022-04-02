# User Guide for Duke
Author: Ang Jing Xuan A0194262A (NUS BTech TIC2002 - AY21/22 Sem 2)


## Features 

### List

You can ask Duke what's on your list right now.

### Todo, Deadline, Event

You can tell Duke any todos, deadlines or events that you have. Duke will help you to remember them.
* Todos are tasks without dates attached.
* Deadlines are tasks with dates attached and are something that you need to do.
* Events are tasks with dates attached and are something that you need to attend. 

### Mark, Unmark

You can mark the task when it is done, or unmark the task when it is not done.

### Find

You can look for task(s) with certain keyword(s) by using the find feature.

### Delete

You can delete tasks which you no longer want to track.

### Sort

You can sort your task list according to dates by ascending order. 

### Help

Whenever you are stuck, don't worry, you can use the Help feature to get the list of commands which you can type.

### Hello command

Say Hello to Duke anytime you want! :)

### Bye command

Say Bye to Duke when you are logging out! See you soon! :)


## Usage

### How to start Duke

1. Download and unzip duke-JAR_v1.zip.
2. Open command prompt/ terminal in the location of the folder.
3. Type `java -jar duke.Duke.jar` in the command prompt/ terminal to start Duke.

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

### `Mark` - Type "mark [task number]"

Type in "mark [task number]" and Duke will mark the task as completed/done for you. This action will automatically save the tasks into duke.txt.

Example of usage:

`mark 3`

Expected outcome:

Duke marked your task as completed/done.

```
Nice! I've marked this task as done: 
[E] [X] tic2002 class (at: 2022-03-23T19:00)
```

### `Unmark` - Type "unmark [task number]"

Type in "unmark [task number]" and Duke will unmark the task as completed/done for you. The task will then be considered as still not done. This action will automatically save the tasks into duke.txt.

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

### `Delete` - Type "delete [task number]"

Type in "delete [task number]" and Duke will delete the chosen task.

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
1. type 'bye' to escape
2. type 'list' to check your list
3. type 'todo <add task here>' (e.g. todo read a book) [todo is assumed as today's date.]
4. type 'deadline <add task here> /by <add deadline  in yyyy-MM-dd'T'HH:mm>' (e.g. deadline submit duke project /by 2022-04-11T23:59)
5. type 'event <add task here> /at <add event timing in yyyy-MM-dd'T'HH:mm>' (e.g. event attend TIC2002 class /at 2022-03-11T19:00)
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

## Using the file

1. Download duke-JAR_v1.zip.
2. Unzip the file. You should see "data" and "docs" folder and "duke.Duke.jar".
3. Open command prompt from the folder.
4. Type `java -jar duke.Duke.jar` in the command prompt.
5. Duke will start.


----- This document is last updated on 30 Mar 2022, 6pm. -----
