package com.course.calypso.java9.data_time;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateTimeDemoTest {

    @Test
    public void print_data_time() {
        DateTimeDemo.printDateTime();
    }

    @Test
    public void print_data_time_method() {
        DateTimeDemo.dataTimeMethod();

    }

    @Test
    public void localDatePlus() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start =
                LocalDate.of(2017, Month.FEBRUARY, 2);
        LocalDate end = start.plusDays(3);
        assertEquals("2017-02-05", end.format(formatter));
        end = start.plusWeeks(5);
        assertEquals("2017-03-09", end.format(formatter));
        end = start.plusMonths(7);
        assertEquals("2017-09-02", end.format(formatter));
        end = start.plusYears(2);
        assertEquals("2019-02-02", end.format(formatter));
    }

    @Test
    public void localTimePlus() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime start = LocalTime.of(11, 30, 0, 0);
        LocalTime end = start.plusNanos(1_000_000);
        assertEquals("11:30:00.001", end.format(formatter));
        end = start.plusSeconds(20);
        assertEquals("11:30:20", end.format(formatter));
        end = start.plusMinutes(45);
        assertEquals("12:15:00", end.format(formatter));
        end = start.plusHours(5);
        assertEquals("16:30:00", end.format(formatter));
    }

    @Test
    public void plus_minus() throws Exception {
        Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.plus(period);

        assertEquals("2019-05-06T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.plus(3, ChronoUnit.HALF_DAYS);
        assertEquals("2017-02-03T23:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.minus(period);
        assertEquals("2014-10-29T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.minus(2, ChronoUnit.CENTURIES);
        assertEquals("1817-02-02T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.plus(3, ChronoUnit.MILLENNIA);
        assertEquals("5017-02-02T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    }

    @Test
    public void with_methods_on_local_date_time() {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.withMinute(45);
        assertEquals("2017-02-02T11:45:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.withHour(16);

        assertEquals("2017-02-02T16:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.withDayOfMonth(28);

        assertEquals("2017-02-28T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.withDayOfYear(300);

        assertEquals("2017-10-27T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        end = start.withYear(2020);

        assertEquals("2020-02-02T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void temporalField() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);
        assertEquals("2017-02-28T11:30:00",
                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void given_date_convert_to_local_date() {
        Date date = new Date();
        DateTimeDemo.convertFromUtilDateUsingInstant(date);
    }

    @Test
    public void given_date_convert_date_to_sql_Date() {
        java.sql.Date sqlDate = new java.sql.Date(10000);
        DateTimeDemo.convertFromSqlDatetoLD(sqlDate);
    }

    @Test
    public void given_timestamp_convert_to_date() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        DateTimeDemo.convertFromTimestampToLDT(ts);
    }

    @Test
    public void given_date_test_formatter() {
        DateTimeDemo.dateTimeFormatter();
    }

    @Test
    public void given_date_test_formatter_locale() {
        DateTimeDemo.formatDateLocale();
    }

    @Test
    public void given_date_user_format() {
        DateTimeDemo.formatUserDatePattern();
    }

    @Test
    public void given_date_zone_id() {
        DateTimeDemo.findZoneId();
    }

    @Test
    public void given_region_names_for_system_default() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId zoneId = now.getZone();
        List<String> names = DateTimeDemo.getRegionNamesForZoneId(zoneId);
        assertTrue(names.contains(zoneId.getId()));
    }


}
