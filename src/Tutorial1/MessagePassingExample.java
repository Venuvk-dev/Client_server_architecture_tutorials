package Tutorial1;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagePassingExample {
    public static void main(String[] args) {
        configureLogger();  // Call the logger configuration

        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        sender.sendMessage(new Message("Hello This is from Venukanth"), receiver);
    }

    private static void configureLogger() {
        try {
            // Configure global logging level
            Logger globalLogger = Logger.getLogger("");
            Handler[] handlers = globalLogger.getHandlers();
            for (Handler handler : handlers) {
                globalLogger.removeHandler(handler);
            }
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            globalLogger.addHandler(consoleHandler);
            globalLogger.setLevel(Level.ALL);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error configuring logger: " + e.getMessage(), e);
        }
    }
}