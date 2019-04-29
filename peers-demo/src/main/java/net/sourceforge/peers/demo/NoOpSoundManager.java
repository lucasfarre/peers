package net.sourceforge.peers.demo;

import net.sourceforge.peers.media.AbstractSoundManager;

public class NoOpSoundManager extends AbstractSoundManager{

    @Override
    public void init() {
        // nothing to do
    }

    @Override
    public void close() {
        // nothing to do
    }

    @Override
    public int writeData(final byte[] buffer, final int offset, final int length) {
        return 0;
    }

    @Override
    public byte[] readData() {
        return new byte[0];
    }
}
