package com.qc.parser;

import com.qc.model.CookieLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface FileParser {
    List<CookieLog> parse(BufferedReader reader) throws IOException;
}
