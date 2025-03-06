package util.logger;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLogger {
    private final Logger logger;

    public MyLogger(Class<?> clazz) {
        this.logger = Logger.getLogger(clazz.getName());
//        logger.setLevel(Level.INFO);

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String mode = dotenv.get("MODE");
        if (mode.isBlank()) {
            throw new RuntimeException("mode is missing");
        }
        switch (mode) {
            case "DEV":
                this.logger.setLevel(Level.INFO);
                break;
            case "PROD":
                this.logger.setLevel(Level.SEVERE);
                break;
        }
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
