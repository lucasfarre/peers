package net.sourceforge.peers.demo;

import net.sourceforge.peers.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4J2Logger implements Logger {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @Override
    public void debug(final String message) {
        // LOGGER.debug(message);
    }

    @Override
    public void info(final String message) {
        // LOGGER.info(message);
    }

    @Override
    public void error(final String message) {
        // LOGGER.error(message);
    }

    @Override
    public void error(final String message, final Exception exception) {
        // LOGGER.error(message, exception);
    }

    @Override
    public void traceNetwork(final String message, final String direction) {
        // LOGGER.info(message, direction);
    }
}
