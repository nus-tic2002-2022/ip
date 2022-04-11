package duke.commands;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Extended class of Command with methods involving finding certain condition given by user.
 */
public class FindCommand extends Command{

    /**
     * Returns Task after adding Task created based on userInput into the TaskList.
     *
     * @param userInput Input specify by user on UI.
     * @param taskList TaskList which specified Task should be added to.
     * @return Task as Task that has been added into TaskList.
     */
    public static String findFreeTime (Parser userInput, TaskList taskList) throws DukeException{
        String output = " ";

        LocalDate targetDate = Parser.dateParser(1);
        LocalTime freeTimeStart = LocalTime.of(0,0,0);
        LocalTime freeTimeEnd = LocalTime.of(23,59,0);
        ArrayList<LocalTime> freeTime = new ArrayList<LocalTime>();
        freeTime.add(freeTimeStart);
        freeTime.add(freeTimeEnd);

        for (int i = 0; i < taskList.size(); i++) {
            Task curTask = taskList.get(i);
            LocalDateTime curDateTime = null;
            LocalDate curDate = null;

            boolean isDeadline = false;
            boolean isEvent = false;

            if (curTask.toString().charAt(1) == 'D') {
                Deadline curDeadline = (Deadline)curTask;
                curDateTime = curDeadline.getBy();
                curDate = curDateTime.toLocalDate();
                isDeadline = true;
            }
            else if (curTask.toString().charAt(1) == 'E') {
                Event curDeadline = (Event)curTask;
                curDateTime = curDeadline.getAt();
                curDate = curDateTime.toLocalDate();
                isEvent = true;
            }
            else {
                continue;
            }

            if (targetDate.equals(curDate)) {
                if (isEvent){
                    freeTime.add(curDateTime.toLocalTime());
                    LocalDateTime temp = curDateTime.plusHours(2);
                    if (temp.toLocalDate() == curDateTime.toLocalDate()) {
                        freeTime.add(curDateTime.toLocalTime().plusHours(2));
                    }
                    else {
                        freeTime.add(freeTimeEnd);
                    }
                }
                else {
                    freeTime.add(curDateTime.toLocalTime());
                    LocalDateTime temp = curDateTime.minusHours(2);
                    if (temp.toLocalDate() == curDateTime.toLocalDate()) {
                        freeTime.add(curDateTime.toLocalTime().minusHours(2));
                    }
                    else {
                        freeTime.add(freeTimeStart);
                    }
                }
            }
        }

        Collections.sort(freeTime);

        boolean isFirstElement = true;
        for(int j = 0; j < freeTime.size();) {
            String start = null;
            String end = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            start = freeTime.get(j).format(formatter);
            end = freeTime.get(j + 1).format(formatter);
            if (!start.equalsIgnoreCase(end)) {
                if (isFirstElement) {
                    isFirstElement = false;
                }
                else {
                    output = output + " , ";
                }
                output = output + start + " - " + end;
            }
            j = j + 2;
        }

        String finalOutput = null;
        if (output.equalsIgnoreCase(" ")) {
            finalOutput = "The specified date is fully scheduled from 12AM to 12PM";
        }
        else {
            finalOutput = "Free time for specified date :" + output;
        }
        return finalOutput;
    }
}
