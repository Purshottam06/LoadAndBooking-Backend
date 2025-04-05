package com.liveasy.LoadAndBookingOperations.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerClass {
    public static Logger getLogger(String className){
        return LoggerFactory.getLogger(String.valueOf(className));
    }
}
