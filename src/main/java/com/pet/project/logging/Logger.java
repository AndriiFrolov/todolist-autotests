package com.pet.project.logging;


import org.slf4j.LoggerFactory;

public class Logger {

    public static org.slf4j.Logger log() {
        final int stackTraceId = 2;
        return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[stackTraceId].getClassName());
    }

    public static org.slf4j.Logger log(String name) {
        return LoggerFactory.getLogger(name);
    }
}
