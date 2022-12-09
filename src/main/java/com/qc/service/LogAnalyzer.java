package com.qc.service;

import com.qc.model.CookieLog;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogAnalyzer
{
    private static final int INCREMENT_BY_ONE = 1;
    public List<String> findMostActiveCookie(List<CookieLog> logEntries, LocalDate date)
    {
        Map<String, Integer> cookies = new HashMap<>();
        logEntries.stream().filter( cookieLog -> cookieLog.getTimestamp().equals(date)).
                forEach(cookieLog -> cookies.put(cookieLog.getCookie(),
                        cookies.getOrDefault(cookieLog.getCookie(),0) + INCREMENT_BY_ONE));

       List<String> mostActiveCookies = new ArrayList<>();
        var maxCount = 0;
        for (Map.Entry<String, Integer> cookie : cookies.entrySet()) {
          if(cookie.getValue() > maxCount)
          {
              mostActiveCookies.clear();
              mostActiveCookies.add(cookie.getKey());
              maxCount = cookie.getValue();
          } else if(cookie.getValue() == maxCount)
          {
              mostActiveCookies.add(cookie.getKey());
          }
        }

        return mostActiveCookies;
    }
}
