package com.qc.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface MostActiveCookieService {
    List<String> getMostActiveCookie(File file, LocalDate date) throws IOException;
}
