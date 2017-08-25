package za.co.mossco.myweatherapp.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import za.co.mossco.myweatherapp.R;

public class DateUtil {
    public static String getCurrentDayOfWeek(Integer unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        String formattedDate = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()); // the format of your date
        return formattedDate;
    }

    public static String toCelsius(double degrees) {
        return String.valueOf((int) (degrees - 273.15D));
    }

    public static String getDayAndMonth(Integer unixSeconds) {

        Date date = new Date(unixSeconds * 1000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = new SimpleDateFormat("MMMM").format(cal.getTime());

        int day = cal.get(Calendar.DAY_OF_MONTH);
        String monthAndDay = String.valueOf(day) + " " + month;
        return monthAndDay;
    }

    public static int setImageIcon(String weatherDescription) {
        int iconId = 0;
        switch (weatherDescription) {
            case "Clear":
                iconId = R.drawable.ic_clear;
                break;
            case "Rain":
                iconId = R.drawable.ic_rain;
                break;
            case "Light Rain":
               iconId= R.drawable.ic_light_rain;
                break;
            case "Cloudy":
                iconId = R.drawable.ic_cloudy;
                break;
            case "Clouds":
                iconId = R.drawable.ic_light_clouds;
                break;
            case "Fog":
                iconId = R.drawable.ic_fog;
                break;
            case "Storm":
                iconId = R.drawable.ic_storm;
                break;
            case "Snow":
                iconId = R.drawable.ic_snow;
                break;

        }
        return iconId;
    }
}
