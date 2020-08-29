package com.sto.video;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;

import java.io.ByteArrayOutputStream;

public class AudioOutputToByteArray implements AudioProcessor {
    private boolean isDone = false;
    private byte[] out = null;
    private ByteArrayOutputStream bos;
 
    public AudioOutputToByteArray() {
        bos = new ByteArrayOutputStream();
    }
 
    public byte[] getData() {
        while (!isDone && out == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
 
        return out;
    }
 
    @Override
    public boolean process(AudioEvent audioEvent) {
        bos.write(audioEvent.getByteBuffer(),0,audioEvent.getByteBuffer().length);
        return true;
    }
 
    @Override
    public void processingFinished() {
        out = bos.toByteArray().clone();
        bos = null;
        isDone = true;
    }
}