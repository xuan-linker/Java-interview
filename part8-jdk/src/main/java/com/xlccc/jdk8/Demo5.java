package com.xlccc.jdk8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Linker
 * @date 10/15/2023 3:07 AM
 * @description：
 */
public class Demo5 {
    public static void main(String[] args) {
        // LocalDate 示例
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2023, 10, 14);
        System.out.println(date1); // 输出 2023-10-14
        System.out.println(date2); // 输出 2023-10-14

        // LocalTime 示例
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(19, 22, 00);
        System.out.println(time1); // 输出 19:22:00.462775
        System.out.println(time2); // 输出 19:22:00

        // LocalDateTime 示例
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.of(2023, 10, 14, 19, 22, 00);
        System.out.println(dateTime1); // 输出 2023-10-14T19:22:00.463213
        System.out.println(dateTime2); // 输出 2023-10-14T19:22:00

        // ZonedDateTime 示例
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2023, 10, 14, 19, 22, 0, 0, ZoneId.systemDefault());
        System.out.println(zonedDateTime1); // 输出 2023-10-14T19:22:00.463285+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime2); // 输出 2023-10-14T19:22+08:00[Asia/Shanghai]

        // Instant 示例
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(instant1); // 输出 2023-10-14T11:22:00.463341Z
        System.out.println(instant2); // 输出 2023-10-14T11:22:00.463345Z

        // Duration 示例
        Instant start = Instant.parse("2023-10-14T19:22:00Z");
        Instant end = Instant.parse("2023-10-14T19:23:00Z");
        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();
        System.out.println(seconds); // 输出 60

        // Period 示例
        LocalDate date3 = LocalDate.parse("2023-10-14");
        LocalDate date4 = LocalDate.of(2024, 1, 14);
        Period period = Period.between(date3, date4);
        long days = period.getDays();
        System.out.println(days); // 输出 92

        // DateTimeFormatter 示例
        LocalDateTime dateTime3 = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime3.format(formatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        System.out.println(formattedDateTime); // 输出 "2023-10-14 19:22:00"
        System.out.println(parsedDateTime); // 输出 2023-10-14T19:22:00

        // ChronoUnit 示例
        LocalDateTime dateTime4 = LocalDateTime.of(2023, 10, 14, 19, 22, 0);
        LocalDateTime dateTime5 = LocalDateTime.of(2023, 10, 15, 19, 22, 0);
        long daysBetween = ChronoUnit.DAYS.between(dateTime4, dateTime5);
        System.out.println(daysBetween); // 输出 1
    }


}
