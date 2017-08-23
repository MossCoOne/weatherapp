package za.co.mossco.myweatherapp.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String getCurrentDate(Integer unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        String formattedDate = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()); // the format of your date
        return formattedDate;
    }

    public static String toCelsius(double degrees) {
        return String.valueOf((int) (degrees - 273.15D));
    }
}
