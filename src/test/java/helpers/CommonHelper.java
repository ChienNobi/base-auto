package helpers;


import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CommonHelper {
    /**
     * @return get current directory
     */
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static <T> String getCurrentClassName(Class<T> type) {
        return type.getSimpleName();
    }

    public static boolean isEmptyString(String text) {
        return text == null || text.isBlank() || text.isEmpty();
    }

    public static String getCurrentLocalDateTime(String pattern) {
        LocalDateTime localDate = LocalDateTime.now();
        return String.valueOf(DateTimeFormatter.ofPattern(pattern).format(localDate));
    }

    public static String getCurrentLocalDateTime() {
        return getCurrentLocalDateTime("dd-MM-yy HH:mm");
    }

    public static Calendar validateDateTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    public static void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    };
}
