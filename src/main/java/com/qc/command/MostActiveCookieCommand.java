package com.qc.command;

import com.qc.service.MostActiveCookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@Component
@Command(name = "mostactivecookie")
public class MostActiveCookieCommand implements Callable<Integer> {

    @Option(names = "-f", description = "filename to process with proper file path, only csv file", required = true, paramLabel = "FILEPATH")
    private File file;

    @Option(names = "-d", description = "date to find out most active cookie, in format yyyy-mm-dd", required = true, paramLabel = "DATE")
    private String date;

    @Spec
    private CommandSpec spec;
    private final MostActiveCookieService mostActiveCookieService;

    @Autowired
    public MostActiveCookieCommand(MostActiveCookieService mostActiveCookieService) {
        this.mostActiveCookieService = mostActiveCookieService;
    }

    @Override
    public Integer call() throws IOException {
        validateParams();
        mostActiveCookieService.getMostActiveCookie(file, LocalDate.parse(date))
                .forEach(System.out::println);
        return 0;
    }

    private void validateParams()
    {
        if(!file.exists() || !file.isFile() || !file.canRead() || file.length() == 0 ||
                !file.getName().toLowerCase().endsWith(".csv") )
        {
            throw new ParameterException(spec.commandLine(), String.format("Invalid value '%s' for option '-f': "
                    + "Either file doesn't exist or not a proper csv file", file.getName()));
        }

        try
        {
            LocalDate givenDate = LocalDate.parse(date);
            if(givenDate.isAfter(LocalDate.now()))
                throw new ParameterException(spec.commandLine(), String.format("Invalid value '%s' for option '-d': "
                        + "given date should not be after current date", date));
        }
        catch (DateTimeParseException e)
        {
            throw new ParameterException(spec.commandLine(),String.format("Invalid value '%s' for option '-d': "
                    + "Please provide a proper date in format yyyy-MM-dd", date));
        }
    }
}
