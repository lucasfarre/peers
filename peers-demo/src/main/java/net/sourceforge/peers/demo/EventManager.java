package net.sourceforge.peers.demo;

import java.net.SocketException;
import net.sourceforge.peers.Config;
import net.sourceforge.peers.sip.core.useragent.SipListener;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;
import org.apache.logging.log4j.LogManager;

public class EventManager implements SipListener {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private UserAgent userAgent;
    private SipRequest sipRequest;

    public EventManager() throws SocketException {
        final Config config = new CustomConfig("192.168.86.25", "192.168.86.21");
        userAgent = new UserAgent(this, config, new Log4J2Logger(), new NoOpSoundManager());
        try {
            userAgent.register();
        } catch (final SipUriSyntaxException e) {
            LOGGER.error(e);
        }
    }

    // SipListener methods
    
    @Override
    public void registering(SipRequest sipRequest) {
        LOGGER.info("Registering...");
    }

    @Override
    public void registerSuccessful(SipResponse sipResponse) {
        LOGGER.info("Successfully registered.");
    }

    @Override
    public void registerFailed(SipResponse sipResponse) {
        LOGGER.info("Registration failed.");
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

    public static void main(String[] args) {
        try {
            new EventManager();
        } catch (final SocketException e) {
            LOGGER.error(e);
        }
    }
}
