package net.sourceforge.peers.demo;

import java.net.SocketException;
import net.sourceforge.peers.sip.core.useragent.SipListener;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;
import org.apache.logging.log4j.LogManager;

public class EventManager implements SipListener {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private UserAgent userAgent;
    private int currentHost = 1;

    public EventManager() throws SocketException {
        userAgent = buildUserAgent(buildServerIp());
        try {
            userAgent.register();
        } catch (final SipUriSyntaxException e) {
            LOGGER.error(e);
        }
    }

    private UserAgent buildUserAgent(final String domain) throws SocketException {
        return new UserAgent(this, new CustomConfig("192.168.86.25", domain),
            new Log4J2Logger(), new NoOpSoundManager());
    }

    private String buildServerIp() {
        return "192.168.86." + currentHost;
    }

    // SipListener methods
    
    @Override
    public void registering(SipRequest sipRequest) {
        LOGGER.info("Registering...");
    }

    @Override
    public void registerSuccessful(SipResponse sipResponse) {
        LOGGER.info("Successfully registered at {}", this::buildServerIp);
        userAgent.close();
    }

    @Override
    public void registerFailed(final SipResponse sipResponse) {
        LOGGER.info("Registration failed at {}", this::buildServerIp);
        try {
            currentHost++;
            userAgent.close();
            userAgent = buildUserAgent(buildServerIp());
            userAgent.register();
        } catch (final SipUriSyntaxException | SocketException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void incomingCall(SipRequest sipRequest, SipResponse provResponse) { }

    @Override
    public void remoteHangup(SipRequest sipRequest) { }

    @Override
    public void ringing(SipResponse sipResponse) { }

    @Override
    public void calleePickup(SipResponse sipResponse) { }

    @Override
    public void error(SipResponse sipResponse) { }

    public static void main(final String[] args) {
        try {
            new EventManager();
        } catch (final SocketException e) {
            LOGGER.error(e);
        }
    }
}
