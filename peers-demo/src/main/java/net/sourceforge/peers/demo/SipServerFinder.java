package net.sourceforge.peers.demo;

import java.net.SocketException;
import net.sourceforge.peers.sip.core.useragent.SipListener;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;
import org.apache.logging.log4j.LogManager;

public final class SipServerFinder implements SipListener {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private UserAgent userAgent;
    private int currentHost;

    private SipServerFinder() throws SocketException {
        currentHost = 1;
        userAgent = buildUserAgent();
        try {
            userAgent.register();
        } catch (final SipUriSyntaxException e) {
            LOGGER.error(e);
        }
    }

    private UserAgent buildUserAgent() throws SocketException {
        return new UserAgent(this, new ConnectionConfig.Builder()
            .withLocalIp("192.168.86.25")
            .withUsername("User1")
            .withPassword("user1")
            .withServerDomain(currentServerDomain()).build(),
            new Log4J2Logger(), new NoOpSoundManager());
    }

    private String currentServerDomain() {
        return "192.168.86." + currentHost;
    }

    @Override
    public void registering(final SipRequest sipRequest) {
        LOGGER.info("Registering...");
    }

    @Override
    public void registerSuccessful(final SipResponse sipResponse) {
        LOGGER.info("Successfully registered at {}", this::currentServerDomain);
        userAgent.close();
    }

    @Override
    public void registerFailed(final SipResponse sipResponse) {
        LOGGER.info("Registration failed at {}", this::currentServerDomain);
        try {
            currentHost++;
            userAgent.close();
            userAgent = buildUserAgent();
            userAgent.register();
        } catch (final SipUriSyntaxException | SocketException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void incomingCall(final SipRequest sipRequest, final SipResponse provResponse) {
        // nothing to do
    }

    @Override
    public void remoteHangup(final SipRequest sipRequest) {
        // nothing to do
    }

    @Override
    public void ringing(final SipResponse sipResponse) {
        // nothing to do
    }

    @Override
    public void calleePickup(final SipResponse sipResponse) {
        // nothing to do
    }

    @Override
    public void error(final SipResponse sipResponse) {
        // nothing to do
    }

    public static void main(final String[] args) {
        try {
            new SipServerFinder();
        } catch (final SocketException e) {
            LOGGER.error(e);
        }
    }
}
