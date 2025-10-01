package ex2.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger 
{
    private static volatile Logger instance;
    private Logger() {}
    public static Logger getInstance() 
    {
        if (instance == null) 
        {
            synchronized (Logger.class) 
            {
                if (instance == null) instance = new Logger();
            }
        }
        return instance;
    }
    public void info(String msg) 
    { 
        log("INFO", msg); 
    }
    public void warn(String msg) 
    { 
        log("WARN", msg); 
    }
    public void error(String msg) 
    { 
        log("ERROR", msg); 
    }
    private void log(String level, String message) 
    {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[" + ts + "] " + level + ": " + message);
    }
}
