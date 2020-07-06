package com.course.calypso.java9.data_time;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

public class DateTimeDemo {

    /*
    All output values are using the ISO 8601 standard formatting. For dates, the basic format is yyyy-MM-dd.
    For times, the format is hh:mm:ss.sss. The format for LocalDate Time combines the two, using a capital T as a separator.
    Date/times with a time zone append a numerical offset (here, -04:00) using UTC as a base, as well as a so-called region
    name (here, America/New_York). The output of the toString method in Instant shows the time to nanosecond precision,
    in Zulu time.The now method also appears in the classes Year, YearMonth, and ZoneId.
    The static of factory method is used to produce new values. For LocalDate, the arguments are the year, month
    (either the enum or an int), and the day of month.
    */
    public static void printDateTime() {

        System.out.println("Instant.now(): " + Instant.now());//Instant.now(): 2020-07-06T12:19:39.409132Z
        System.out.println("LocalDate.now(): " + LocalDate.now()); //2020-07-06
        System.out.println("LocalTime.now(): " + LocalTime.now()); //13:19:39.435627
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());//2020-07-06T13:19:39.435869
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());//2020-07-06T13:19:39.436187+01:00[Europe/London]
    }

    public static void dataTimeMethod() {

        System.out.println("First landing on the Moon:");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 18);
        System.out.println("Date: " + moonLandingDate);
        System.out.println("Time: " + moonLandingTime);
        System.out.println("Neil Armstrong steps onto the surface: ");
        LocalTime walkTime = LocalTime.of(20, 2, 56, 150_000_000);
        LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
        System.out.println(walk);

        /*First landing on the Moon:
        Date: 1969-07-20
        Time: 20:18
        Neil Armstrong steps onto the surface:
        1969-07-20T20:02:56.150
        */
    }

    public static LocalDate convertFromUtilDateUsingInstant(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Local Date : " + localDate);
        return localDate;
    }

    public static LocalDate convertFromSqlDatetoLD(java.sql.Date sqlDate) {
        LocalDate localDate = sqlDate.toLocalDate();
        System.out.println("Local Date : " + localDate);
        return localDate;
    }

    public static java.sql.Date convertToSqlDateFromLD(LocalDate localDate) {
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        System.out.println("Sql Date : " + date);
        return date;
    }

    public static LocalDateTime convertFromTimestampToLDT(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println("Timestamp : " + localDateTime);
        return localDateTime;
    }

    public static Timestamp convertToTimestampFromLDT(LocalDateTime localDateTime) {
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println("Timestamp : " + timestamp);
        return timestamp;
    }

    public static void dateTimeFormatter() {
        LocalDate date = LocalDate.of(2017, Month.MARCH, 13);
        System.out.println("Full   : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println("Long   : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("Medium : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println("Short  : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println("France : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.FRANCE)));
        System.out.println("India  : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(new Locale("hin", "IN"))));
        System.out.println("Brazil : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(new Locale("pt", "BR"))));
        System.out.println("Japan : " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.JAPAN)));

    }

    public static void formatDateLocale() {

        LocalDate date = LocalDate.of(2017, Month.MARCH, 13);
        Locale loc = new Locale.Builder().setLanguage("sr")
                .setScript("Latn")
                .setRegion("RS")
                .build();
        System.out.println("Serbian: " +
                date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(loc)));
    }

    public static void formatUserDatePattern() {
        //Defining your own format pattern
        ZonedDateTime moonLanding = ZonedDateTime.of(
                LocalDate.of(1969, Month.JULY, 20),
                LocalTime.of(20, 18),
                ZoneId.of("UTC")
        );
        System.out.println(moonLanding.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("uuuu/MMMM/dd hh:mm:ss a zzz GG");
        System.out.println(moonLanding.format(formatter));
        formatter = DateTimeFormatter.ofPattern("uuuu/MMMM/dd hh:mm:ss a VV xxxxx");
        System.out.println(moonLanding.format(formatter));
    }

    public static void findZoneId() {

        Instant instant = Instant.now();
        ZonedDateTime current = instant.atZone(ZoneId.systemDefault());
        System.out.printf("Current time is %s%n%n", current);
        System.out.printf("%10s %20s %13s%n", "Offset", "ZoneId", "Time");
        ZoneId.getAvailableZoneIds().stream().map(ZoneId::of)
                .filter(zoneId -> {
                    ZoneOffset offset = instant.atZone(zoneId).getOffset();
                    return offset.getTotalSeconds() % (60 * 60) != 0;
                })
                .sorted(comparingInt(zoneId ->
                        instant.atZone(zoneId).getOffset().getTotalSeconds()))
                .forEach(zoneId -> {
                    ZonedDateTime zdt = current.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n",
                            zdt.getOffset(), zoneId,
                            zdt.format(DateTimeFormatter.ofLocalizedTime(
                                    FormatStyle.SHORT)));
                });

    }

    public static List<String> getRegionNamesForOffset(ZoneOffset offset) {
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId -> now.atZone(zoneId).getOffset().equals(offset))
                .map(ZoneId::toString)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> getRegionNamesForZoneId(ZoneId zoneId) {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zdt = now.atZone(zoneId);
        ZoneOffset offset = zdt.getOffset();
        return getRegionNamesForOffset(offset);
    }


}
