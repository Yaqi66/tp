package seedu.pharmatracker.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Configures the application's logging to write to a file instead of the console.
 * This prevents log messages from cluttering the CLI user interface.
 *
 * <p>Call {@link #init()} once at application startup (in the main method)
 * before any commands are executed.</p>
 */
public class LoggerSetup {

    private static final String LOG_FILE = "pharmatracker.log";

    /**
     * Initialises file-based logging for the entire application.
     * Removes all console handlers from the root logger and attaches
     * a {@link FileHandler} that writes to {@value #LOG_FILE}.
     *
     * <p>If the log file cannot be created, a warning is printed to
     * {@code System.err} and the application continues without file logging.</p>
     */
    public static void init() {
        Logger rootLogger = Logger.getLogger("");

        // Remove default console handlers so logs don't appear in the CLI
        for (Handler handler : rootLogger.getHandlers()) {
            if (handler instanceof ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }

        try {
            // append = true so logs persist across runs
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            rootLogger.addHandler(fileHandler);
            rootLogger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Warning: Could not create log file. Logging disabled.");
        }
    }
}
