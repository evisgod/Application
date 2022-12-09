package com.qc.application;

import com.qc.command.MostActiveCookieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication(scanBasePackages = "com.qc")
public class Application implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;
    private final MostActiveCookieCommand mostActiveCookieCommand;
    private int exitCode;

    @Autowired
    public Application(IFactory factory, MostActiveCookieCommand mostActiveCookieCommand) {
        this.factory = factory;
        this.mostActiveCookieCommand = mostActiveCookieCommand;
    }

    @Override
    public void run(String... args) {
        exitCode = new CommandLine(mostActiveCookieCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(Application.class, args)));
    }
}
