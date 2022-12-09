package com.qc.service;

import com.qc.parser.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MostActiveCookieServiceImpl implements MostActiveCookieService
{
    private final FileParser fileParser;
    private final LogAnalyzer logAnalyzer;

    @Autowired
    public MostActiveCookieServiceImpl(FileParser fileParser, LogAnalyzer logAnalyzer)
    {
        this.fileParser = fileParser;
        this.logAnalyzer = logAnalyzer;
    }

    @Override
    public List<String> getMostActiveCookie(File file, LocalDate date) throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            return logAnalyzer.findMostActiveCookie(fileParser.parse(reader), date);
        }
        catch(IOException e)
        {
            throw new IOException(String.format("Error while reading log file: %s" , e.getMessage()));
        }
    }
}
