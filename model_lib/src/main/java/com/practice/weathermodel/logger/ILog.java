package com.practice.weathermodel.logger;

public interface ILog {
    Logger log(String message);
    void withCause(Exception cause);
}
