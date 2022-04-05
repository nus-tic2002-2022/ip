package parser;

import com.joestelmach.natty.Parser;

import java.util.Date;
import java.util.List;

public class DateParser {

    public static Date parseDate(String stringDate) throws IndexOutOfBoundsException{
        try {
            List<Date> dates = new Parser().parse(stringDate).get(0).getDates();
            Date date = dates.get(0);
            return date;
        }catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }
}
