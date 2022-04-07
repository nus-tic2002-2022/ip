package duke.command;

import duke.Storage.fileaccess;
import duke.Tasklist.Deadlines;
import duke.Tasklist.Events;
import duke.Tasklist.Task;
import duke.Tasklist.ToDo;
import duke.UI.UI;

import java.util.ArrayList;
import java.util.Collections;


/**
 * T0D0 will be the first few in list, followed by the most urgent task and so on.
 * if first_task c1 = deadlines and first_task c2 = event ; compare_type = 3
 * if first_task c1 = event and first_task c2 = deadline ; compare_type = 2
 * if both task are event or deadline; compare_type = 1
 * jf c1 = event/deadline and c2 = todotask
 * other than above, compare _type = -1;
 */
public class SortCommand extends Command {

    public SortCommand(String passed) {
        super(passed);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isSort() {
        return true;
    }

    public Integer checkTasktype(Task c1, Task c2) {

        if (c2 instanceof Events && c1 instanceof Deadlines) {
            return 3;
        }
        if (c1 instanceof Events && c2 instanceof Deadlines) {
            return 2;
        }
        if ((c1 instanceof Events && c2 instanceof Events) || (c2 instanceof Deadlines && c1 instanceof Deadlines)) {
            return 1;
        }
        if ((c2 instanceof ToDo) && !(c1 instanceof ToDo)) {
            return 0;
        }
        return -1;
    }

    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) {
        for (int i = tasklist.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                Task c1 = tasklist.get(j);
                Task c2 = tasklist.get(j + 1);
                Integer compare_type = checkTasktype(c1, c2);
                if (compare_type == -1) {
                    continue;
                }
                if (compare_type == 0) {

                    Collections.swap(tasklist, j, j + 1);
                }
                if (compare_type == 1) {
                    // c1, c2 = event
                    if (c1 instanceof Events && c2 instanceof Events) {
                        boolean c1_is_later = ((Events) c1).getDate().isAfter(((Events) c2).getDate());
                        if (c1_is_later) {
                            Collections.swap(tasklist, j, j + 1);
                        }
                    }
                    if (c1 instanceof Deadlines && c2 instanceof Deadlines) {
                        // c1,c2 = deadline
                        boolean c1_is_later = ((Deadlines) c1).getDate().isAfter(((Deadlines) c2).getDate());
                        if (c1_is_later) {
                            Collections.swap(tasklist, j, j + 1);
                        } else {
                            boolean both_date_equal = ((Deadlines) c1).getDate().isEqual(((Deadlines) c2).getDate());
                            if (both_date_equal) {
                                int c1_time_is_later = ((Deadlines) c1).getTime().compareTo(((Deadlines) c2).getTime());
                                if (c1_time_is_later > 0) {
                                    Collections.swap(tasklist, j, j + 1);
                                }
                            }
                        }
                    }
                }
                if (compare_type == 2) {
                    // c1 = event, c2 =deadline
                    if (c1 instanceof Events && c2 instanceof Deadlines) {
                        boolean c1_is_later = ((Events) c1).getDate().isAfter(((Deadlines) c2).getDate());
                        if (c1_is_later) {
                            Collections.swap(tasklist, j, j + 1);
                        }
                    }

                }
                if (compare_type == 3) {
                    // c1= deadline, c2 = event
                    if (c2 instanceof Events && c1 instanceof Deadlines) {
                        boolean c1_is_later = ((Deadlines) c1).getDate().isAfter(((Events) c2).getDate());
                        if (c1_is_later) {
                            Collections.swap(tasklist, j, j + 1);
                        }
                    }

                }

            }
        }
        System.out.println("Yes, The list has been sorted");
    }

}
