package com.qc.service;

import com.qc.model.CookieLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class LogAnalyzerTest
{
    @InjectMocks
    private LogAnalyzer logAnalyzer;

    @Test
    public void testFindMostActiveCookie_withValidLogEntries_shouldReturnMostActiveCookieList() {
        List<CookieLog> logEntries = Arrays.asList(
                new CookieLog("AtY0laUfhglK3lC7", LocalDate.parse("2018-12-09")),
                new CookieLog("SAZuXPGUrfbcn5UA", LocalDate.parse("2018-12-09")),
                new CookieLog("AtY0laUfhglK3lC7", LocalDate.parse("2018-12-09")),
                new CookieLog("SAZuXPGUrfbcn5UA", LocalDate.parse("2018-12-08")),
                new CookieLog("4sMM2LxV07bPJzwf", LocalDate.parse("2018-12-07"))
        );

        List<String> mostActiveCookies = logAnalyzer.findMostActiveCookie(logEntries, LocalDate.parse("2018-12-09"));

        assertEquals(1, mostActiveCookies.size());
        assertEquals("AtY0laUfhglK3lC7", mostActiveCookies.get(0));
    }

    @Test
    public void testFindMostActiveCookie_withEmptyLogEntries_shouldReturnEmptyList() {
        List<String> mostActiveCookies = logAnalyzer.findMostActiveCookie(Collections.emptyList(), LocalDate.now());

        assertTrue(mostActiveCookies.isEmpty());
    }
}
