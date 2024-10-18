package helpers;

import org.testng.annotations.Optional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class RandomHelper {

    public static String generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }

    public static String generateRandomString() {
        return generateRandomString(8);
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        String[] providers = {"john", "mike", "emma", "sarah", "alex", "jessica", "david", "laura", "tom", "chris"};
        Random random = new Random();
        String username = providers[random.nextInt(providers.length)] + random.nextInt(1000);
        String domain = domains[random.nextInt(domains.length)];
        return username + "@" + domain;
    }

    public static String generateRandomPhoneNumber(int length) {
        StringBuilder phoneNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    public static String randomTime(){
        Random random = new Random();
        int hour = random.nextInt(24);
        int min = random.nextInt(60);
        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(min);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String randomDay(){
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2030, 12, 31);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static String randomDay(String goDate, String backDate){
        LocalDate startDate = LocalDate.parse(goDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(backDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String randomPastDate(@Optional String format){
        if(format == null || format.isEmpty()) format = "dd/MM/yyyy";
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.now();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() - 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String randomFutureDate(){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2030, 12, 31);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String generateRandomDateOfBirth() {
        int year = randBetween(1950, 2003); // Ngày sinh trong khoảng từ 1950 đến 2003
        int month = randBetween(1, 12);
        int day = randBetween(1, 28); // Giả sử tháng 2 chỉ tối đa 28 ngày
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
