package com.qc.service;

import com.qc.parser.FileParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MostActiveCookieServiceImplTest {
    @Mock
    private FileParser fileParser;

    @Mock
    private LogAnalyzer logAnalyzer;

    @InjectMocks
    private MostActiveCookieServiceImpl mostActiveCookieService;

    @Test(expected = IOException.class)
    public void shouldThrowException_ForInvalidFile() throws IOException {
        mostActiveCookieService.getMostActiveCookie(new File("src/load.csv"), LocalDate.now());
    }

    @Test
    public void getMostActiveCookie_ForValidValues() throws IOException {
        File file = new File("src/test.csv");
        LocalDate date = LocalDate.of(2018, 12, 9);
        when(logAnalyzer.findMostActiveCookie(fileParser.parse(any(BufferedReader.class)), date)).thenReturn(Collections.singletonList("AtY0laUfhglK3lC7"));
        List<String> mostActiveCookies = mostActiveCookieService.getMostActiveCookie(new File("src/test.csv"), date);

        assertEquals(1, mostActiveCookies.size());
        assertEquals("AtY0laUfhglK3lC7", mostActiveCookies.get(0));
    }

}
