package parser;

import java.util.Date;
import java.util.List;

import com.joestelmach.natty.Parser;

import exceptions.TooManyDatesException;

public class DateParser {

    /**
     * Parses a string of text into a Usable Date
     * Makes use of the Natty Library
     *
     * @param stringDate String that is to be parsed into a date
     * @return a new Date Object
     * @exception IndexOutOfBoundsException On input that is not recognized as a date
     * @exception TooManyDatesException When too many dates provided
     */

    public static Date parseDate(String stringDate) throws IndexOutOfBoundsException, TooManyDatesException {
        try {
            List<Date> dates = new Parser().parse(stringDate).get(0).getDates();
            if (dates.size() > 1) {
                throw new TooManyDatesException("Too many dates provided");
            }
            Date date = dates.get(0);
            return date;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }
}
