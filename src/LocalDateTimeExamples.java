
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalDateTimeExamples {

    public static void main(String args[]) {

        System.out.println("----\nLocalDateTime\n-----");

        LocalDate today = LocalDate.now();
        System.out.println("Today:" + today);

        LocalTime currentTime = LocalTime.now();
        System.out.println("Current Time:" + currentTime);

        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current Date and Time:" + currentDateTime);

        LocalDateTime different = currentDateTime.withMinute(14).withMonth(10).plusHours(12);
        System.out.println("Different:" + different);

        System.out.println("----\nInstant, Period, Duration\n----");
        Instant instant = Instant.now();
        System.out.println("Instant(UTC time): " + instant);

        Period period = Period.between(LocalDate.of(2023, 11, 19), today);
        System.out.println("Period between 19th Nov 2023 and today: " + period);
        System.out.println(period.getYears() + " years " + period.getMonths() + " month " + period.getDays() + " days");

        Duration duration = Duration.ofDays(2);
        System.out.println("Duration: " + duration);
        long seconds = duration.minusMinutes(15).getSeconds();
        System.out.println("Duration of 2 days minus 15 minutes in seconds:" + seconds);

        System.out.println("----\nZoned Date Time\n----");
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println("Zoned Date Time:" + zdt);
        ZoneId zid = ZoneId.of("Europe/London");
        zdt = ZonedDateTime.now(zid);
        System.out.println("Current Time for " + zid + ": " + zdt);
        zdt = ZonedDateTime.of(LocalDateTime.now(), zid); //doesn't convert the time, just changes the timezone
        System.out.println("Zoned Date Time of " + zid + ":" + zdt);

    }

}
