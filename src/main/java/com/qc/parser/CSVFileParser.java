package com.qc.parser;

import com.qc.model.CookieLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVFileParser implements FileParser {

    private static final Logger log = LoggerFactory.getLogger(CSVFileParser.class);

    private static final String COMMA_SEPARATOR = ",";
    @Override
    public List<CookieLog> parse(BufferedReader reader)
    {
        List<CookieLog> cookieLogs = new ArrayList<>();
        try
        {
            reader.readLine(); //skip column header
            readAllLines(reader, cookieLogs);
            return cookieLogs;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void readAllLines(BufferedReader reader, List<CookieLog> cookieLogs) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split(COMMA_SEPARATOR);
                cookieLogs.add(new CookieLog(parts[0], LocalDate.parse(parts[1], DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                log.error(String.format("Error parsing log entry: %s because %s", line, e.getMessage()));
            }
        }
    }
}
