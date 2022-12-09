package com.qc.command;

import com.qc.service.MostActiveCookieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MostActiveCookieCommandTest {
    @Mock
    private MostActiveCookieService mostActiveCookieService;

    @Mock
    private CommandSpec spec;
    @InjectMocks
    private MostActiveCookieCommand mostActiveCookieCommand;

    @Test
    public void invalidFile_shouldThrowParameterException(){
        File file = new File("logfile.txt");
        String date = "2018-12-09";
        ReflectionTestUtils.setField(mostActiveCookieCommand, "file", file);
        ReflectionTestUtils.setField(mostActiveCookieCommand, "date", date);
        when(spec.commandLine()).thenReturn(new CommandLine(mostActiveCookieCommand));

        assertThrows(ParameterException.class, () -> mostActiveCookieCommand.call());
    }

    @Test
    public void invalidDate_shouldThrowParameterException(){
        File file = new File("src/test.csv");
        String date = "2018-13-09";
        ReflectionTestUtils.setField(mostActiveCookieCommand, "file", file);
        ReflectionTestUtils.setField(mostActiveCookieCommand, "date", date);
        when(spec.commandLine()).thenReturn(new CommandLine(mostActiveCookieCommand));

        assertThrows(ParameterException.class, () -> mostActiveCookieCommand.call());
    }

    @Test
    public void validArguments_shouldReturnZero() throws IOException {
        File file = new File("src/test.csv");
        String date = "2018-12-09";
        ReflectionTestUtils.setField(mostActiveCookieCommand, "file", new File("src/test.csv"));
        ReflectionTestUtils.setField(mostActiveCookieCommand, "date", "2018-12-09");
        when(mostActiveCookieService.getMostActiveCookie(file, LocalDate.parse(date))).thenReturn(List.of("AtY0laUfhglK3lC7"));

        Integer exitCode = mostActiveCookieCommand.call();

        assertEquals(0, exitCode);
    }
}
