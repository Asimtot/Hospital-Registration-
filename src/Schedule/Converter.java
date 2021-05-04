package Schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converter class that convert time to necessary format for DATABASE
 * @author  Emre Uğur
 */
public class Converter {

    // do not change the pattern to uppercase/lowercase
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * it converts formatted type of time to LocalDateTime object
     * @param str must be of the form YYYY-MM-DD hh:mm:ss[.nnn] (SQL dateTime format)
     */
    public static LocalDateTime toLocalDateTime(String str){
        int year = Integer.parseInt(str.substring(0,4));
        int month = Integer.parseInt(str.substring(5,7));
        int dayOfMonth = Integer.parseInt(str.substring(8,10));
        int hour = Integer.parseInt(str.substring(11,13));
        int minute = Integer.parseInt(str.substring(14,16));

        return LocalDateTime.of(year,month,dayOfMonth,hour,minute);
    }

    public static String toString(LocalDateTime date){
        return date.format(FORMATTER);
    }
}
