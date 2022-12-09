package com.qc.parser;

import com.qc.model.CookieLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class CSVFileParserTest
{
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String HEADER = "cookie,timestamp";

    private static final String VALID_LINE_1 = "AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00";
    private static final String VALID_LINE_2 = "SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00";
    private static final String INVALID_LINE = "AtY0laUfhglK3lC72018-12-09T";

    private static final String CSV_FILE =
            HEADER + NEW_LINE_SEPARATOR +
                    VALID_LINE_1 + NEW_LINE_SEPARATOR +
                    VALID_LINE_2 + NEW_LINE_SEPARATOR +
                    INVALID_LINE + NEW_LINE_SEPARATOR;

    private BufferedReader reader;
    @InjectMocks
    private CSVFileParser csvFileParser;

    @Before
    public void setup() {
        reader = new BufferedReader(new StringReader(CSV_FILE));
    }

    @Test
    public void shouldReturn_ValidCookieLogList()
    {
        List<CookieLog> cookieLogs = csvFileParser.parse(reader);
        assertEquals(2, cookieLogs.size());
        assertEquals(new CookieLog("AtY0laUfhglK3lC7", LocalDate.parse("2018-12-09T14:19:00+00:00",
                ISO_OFFSET_DATE_TIME)), cookieLogs.get(0));
        assertEquals(new CookieLog("SAZuXPGUrfbcn5UA", LocalDate.parse("2018-12-09T10:13:00+00:00",
                ISO_OFFSET_DATE_TIME)), cookieLogs.get(1));
    }
}
