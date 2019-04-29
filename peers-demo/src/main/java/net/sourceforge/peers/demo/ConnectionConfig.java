package net.sourceforge.peers.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import net.sourceforge.peers.Config;
import net.sourceforge.peers.media.MediaMode;
import net.sourceforge.peers.sip.syntaxencoding.SipURI;
import org.apache.logging.log4j.LogManager;

public final class ConnectionConfig implements Config {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    private InetAddress publicIpAddress;

    private final String localIp;
    private final String serverDomain;
    private final String username;
    private final String password;

    private ConnectionConfig(final Builder builder) {
        localIp = builder.localIp;
        serverDomain = builder.serverDomain;
        username = builder.username;
        password = builder.password;
    }

    @Override
    public InetAddress getLocalInetAddress() {
        InetAddress inetAddress;
        try {
            // if you have only one active network interface, getLocalHost()
            // should be enough
            //inetAddress = InetAddress.getLocalHost();
            // if you have several network interfaces like I do,
            // select the right one after running ipconfig or ifconfig
            inetAddress = InetAddress.getByName(localIp);
        } catch (final UnknownHostException e) {
            LOGGER.error(e);
            return null;
        }
        return inetAddress;
    }

    @Override
    public InetAddress getPublicInetAddress() {
        return publicIpAddress;
    }

    @Override
    public String getUserPart() {
        return username;
    }

    @Override
    public String getDomain() {
        return serverDomain;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public MediaMode getMediaMode() {
        return MediaMode.captureAndPlayback;
    }

    @Override
    public String getAuthorizationUsername() {
        return getUserPart();
    }

    @Override
    public void setPublicInetAddress(InetAddress inetAddress) {
        publicIpAddress = inetAddress;
    }

    @Override
    public SipURI getOutboundProxy() {
        return null;
    }

    @Override
    public int getSipPort() {
        return 0;
    }

    @Override
    public boolean isMediaDebug() {
        return false;
    }

    @Override
    public String getMediaFile() {
        return null;
    }

    @Override
    public int getRtpPort() {
        return 0;
    }

    @Override
    public void setLocalInetAddress(InetAddress inetAddress) {
    }

    @Override
    public void setUserPart(String userPart) {
    }

    @Override
    public void setDomain(String domain) {
    }

    @Override
    public void setPassword(String password) {
    }

    @Override
    public void setOutboundProxy(SipURI outboundProxy) {
    }

    @Override
    public void setSipPort(int sipPort) {
    }

    @Override
    public void setMediaMode(MediaMode mediaMode) {
    }

    @Override
    public void setMediaDebug(boolean mediaDebug) {
    }

    @Override
    public void setMediaFile(String mediaFile) {
    }

    @Override
    public void setRtpPort(int rtpPort) {
    }

    @Override
    public void save() {
    }

    @Override
    public void setAuthorizationUsername(String authorizationUsername) {
    }

    public static class Builder {

        private String localIp;
        private String serverDomain;
        private String username;
        private String password;

        public Builder withLocalIp(final String localIp) {
            this.localIp = localIp;
            return this;
        }

        public Builder withServerDomain(final String serverDomain) {
            this.serverDomain = serverDomain;
            return this;
        }

        public Builder withUsername(final String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(final String password) {
            this.password = password;
            return this;
        }

        public ConnectionConfig build() {
            return new ConnectionConfig(this);
        }
    }
}
