package util.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLogger {
    private final Logger logger;

    public MyLogger(Class<?> clazz) {
        this.logger = Logger.getLogger(clazz.getName());
        logger.setLevel(Level.INFO);
    }

    public void info(String msg) {
        LogRecord logRecord = new LogRecord(Level.INFO, msg);
        logRecord.setSourceClassName(logger.getName());
        logger.log(logRecord);
    }
    public void severe(String msg) {
        LogRecord logRecord = new LogRecord(Level.SEVERE, msg);
        logRecord.setSourceClassName(logger.getName());
        logger.log(logRecord);
    }
}
