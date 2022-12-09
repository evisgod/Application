package com.qc.application;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE, classes = Application.class,
        args = {"-f=src/test.csv", "-d=2018-12-09"})
class ApplicationTests {
    @InjectMocks
    private Application application;

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
        assertEquals(0, application.getExitCode());
    }
}
