# User Guide

## Installation

1. Download the latest version of Duke from [here](https://github.com/keyule/duke/releases/tag/FixedBug)
2. Move into directory of Jar file
3. Run using: `java -jar Duke.jar`  

Requirements: java 11 or higher

## Commands

### Overview of all Commands
| Command  | Description                    | Example                                    |
|----------|--------------------------------|--------------------------------------------|
| list     | Lists all tasks                | list                                       |
| todo     | Adds a new todo task           | todo `description`                         |
| deadline | Adds a new deadline task       | deadline `description` /by `date`          |
| event    | Adds a new event task          | event `description` /at `date`             |
| fixed    | Adds a new fixed duration Task | fixed `duration` `description`             |
| mark     | Marks a task as done           | mark `task id`                             |
| unmark   | Unmarks a task as done         | unmark `task id`                           |
| delete   | Deletes a/all/marked task/s    | delete `task id` /`all` /`marked`          |
| find     | Finds all tasks with keyword   | find `keyword`                             |
| update   | Updates a task                 | update `task id` `desc/date` `new content` |
| view     | View tasks on certain day      | view `date`                                |
| bye      | Exits the application          | bye                                        |
| help     | Shows help for a command       | help `command`                             |

### list
Lists all tasks in the current Task List.
```
list
```
Example of usage:
```
list
```
Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] test
2. [T][ ] test2
```

### todo
Adds a new todo task into the current Task List.
```
todo <description>
```

Example of usage:
```
todo read a book
```

Expected outcome:
```
Got it. I've added this task:
  [T][ ] read a book
Now you have 3 in the list.
```

### deadline
Adds a new deadline task into the current Task List.
```
deadline <description> /by <date>
```
Note: Duke accepts natural date formats as well, Eg, Monday/Mon, today, 3 days later

Example of usage:
```
deadline read a book /by tomorrow
```
Expected outcome:
```
Got it. I've added this task:
  [D][ ] read a book (by: Apr 13)
Now you have 4 in the list.
```

### event
Adds a new event task into the current Task List.
```
event <description> /at <date and time>
```
Note: Duke accepts natural date formats as well, Eg, Monday/Mon, today, 3 days later, 3pm

Example of usage:
```
event reading session /at tomorrow 3pm
```
Expected outcome:
```
Got it. I've added this task:
  [E][ ] reading session (at: Apr 13 3PM)
Now you have 5 in the list.
```

### fixed
Adds a new fixed duration task to the Task List
```
fixed <duration> <description> 
```
Example of usage:
```
fixed 5 read book
```
Expected outcome:
```
Got it. I've added this task:
  [F][ ] read book (needs 5 hours)
Now you have 6 in the list.
```

### mark
Marks provided task as done

```
mark <task id> 
```

Example Usage:
```
mark 1
```

Expected outcome:
```
Marked task as done
```

### unmark
Unmarks provided task as done

```
unmark <task id>
```

Example Usage:
```
unmark 1
```

Expected outcome:
```
Unmarked task
```

### delete
Delete tasks from the Task List

```
delete <task id> or <all> or <marked> 
```

Example Usage:
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
  [T][ ] test
Now you have 5 in the list.
```

### find
Searches the list and returns all task with the keyword provided

```
find <keyword> 
```

Example Usage:
```
find book
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] read a book
2. [D][ ] read a book (by: Apr 13)
3. [F][ ] read book (needs 5 hours)
```

### update
Updates the description or date of the task provided

```
update <task ID> <date/desc> <new date/new description> 
```

Example Usage:
```
update 2 date tomorrow 
```

Expected outcome:
```
Got it. I've updated this task:
  [D][ ] read a book (by: Apr 13)
```
### view
View the tasks scheduled on a particular day

```
view <date> 
```

Example Usage:
```
view 13 april
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][ ] read a book (by: Apr 13)
2. [E][ ] reading session (at: Apr 13 3PM)
```
