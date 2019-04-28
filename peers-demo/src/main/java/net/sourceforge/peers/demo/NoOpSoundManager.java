package net.sourceforge.peers.demo;

import net.sourceforge.peers.media.AbstractSoundManager;

public class NoOpSoundManager extends AbstractSoundManager{
    public void init() {
        // nothing to do
    }

    public void close() {
        // nothing to do
    }

    public int writeData(final byte[] buffer, final int offset, final int length) {
        return 0;
    }

    public byte[] readData() {
        return new byte[0];
    }
}
