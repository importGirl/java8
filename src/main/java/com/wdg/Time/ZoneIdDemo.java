package com.wdg.Time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class ZoneIdDemo {

    /**
     * ====ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则====
     *
     * 时间和面向机器的时间直接的转换
     * atStartOfDay():      LocalDate -> ZonedDateTime
     * atZone():            Instant -> ZonedDateTime
     *
     */
    @Test
    public void test1(){
        ZoneId zone = ZoneId.of("Europe/Paris");
        System.out.println(ZoneId.of("Europe/Paris"));// Europe/Paris
        LocalDate date = LocalDate.parse("2017-09-01");
        ZonedDateTime zonedDateTime = date.atStartOfDay(zone);
        System.out.println(zonedDateTime);// 2017-09-01T00:00+02:00[Europe/Paris]
        Instant instant = Instant.now();
        System.out.println(instant.atZone(zone));// 2017-09-11T17:22:34.029+02:00[Europe/Paris]
    }
}
