package com.qc.model;

import java.time.LocalDate;
import java.util.Objects;

public class CookieLog {
    private String cookie;
    private LocalDate timestamp;

    public CookieLog(String cookie, LocalDate timestamp) {
        this.cookie = cookie;
        this.timestamp = timestamp;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieLog cookieLog = (CookieLog) o;
        return cookie.equals(cookieLog.cookie) && timestamp.equals(cookieLog.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cookie, timestamp);
    }

    @Override
    public String toString() {
        return "CookieLog{" +
                "cookie='" + cookie + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
