package parser;

import exceptions.TooManyDatesException;
import com.joestelmach.natty.Parser;

import java.util.Date;
import java.util.List;

public class DateParser {

    public static Date parseDate(String stringDate) throws IndexOutOfBoundsException, TooManyDatesException{
        try {
            List<Date> dates = new Parser().parse(stringDate).get(0).getDates();
            if(dates.size() > 1){
                throw new TooManyDatesException("Too many dates provided");
            }
            Date date = dates.get(0);
            return date;
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }
}
