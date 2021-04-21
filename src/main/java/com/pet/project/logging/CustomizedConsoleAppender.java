package com.pet.project.logging;

import org.apache.log4j.ConsoleAppender;

import java.util.Collections;
import java.util.List;

public class CustomizedConsoleAppender extends ConsoleAppender {

    private boolean shouldLogToConsole(String message) {
        //Do not log to the console messages for Report portal
        List<String> messagesNotLog = Collections.singletonList("RP_MESSAGE");
        for (String messageToSkip : messagesNotLog) {
            if (message.contains(messageToSkip)) {
                return false;
            }
        }
        return true;
    }
}
