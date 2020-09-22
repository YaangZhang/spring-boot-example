package com.sto.video.audio;/* This file was written by Bill Cox in 2011, and is licensed under the Apache
   2.0 license. */


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.sun.media.sound.WaveFileWriter;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestSonic {

    static ByteArrayDataOutput out = ByteStreams.newDataOutput();

    // Run sonic.
    private static void runSonic(
            AudioInputStream audioStream,
            SourceDataLine line,
            float speed,
            float pitch,
            float rate,
            float volume,
            boolean emulateChordPitch,
            int quality,
            int sampleRate,
            int numChannels) throws IOException {
        Sonic sonic = new Sonic(sampleRate, numChannels);
        int bufferSize = line.getBufferSize();
        byte inBuffer[] = new byte[bufferSize];
        byte outBuffer[] = new byte[bufferSize];
        int numRead, numWritten;

        sonic.setSpeed(speed);
        sonic.setPitch(pitch);
        sonic.setRate(rate);
        sonic.setVolume(volume);
        sonic.setChordPitch(emulateChordPitch);
        sonic.setQuality(quality);
        do {
            numRead = audioStream.read(inBuffer, 0, bufferSize);
            if (numRead <= 0) {
                sonic.flushStream();
            } else {
                sonic.writeBytesToStream(inBuffer, numRead);
            }
            do {
                numWritten = sonic.readBytesFromStream(outBuffer, bufferSize);
                if (numWritten > 0) {
                    line.write(outBuffer, 0, numWritten);
                    byte[] target = new byte[numWritten];
                    System.arraycopy(outBuffer, 0, target, 0, numWritten);
                    out.write(target);
                }
            } while (numWritten > 0);
        } while (numRead > 0);
        saveFile(out.toByteArray());
    }

    public static void saveFile(byte[] bytes) {
        String fileName = "c:/" + System.currentTimeMillis() + ".wav";
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File(fileName));
            WaveFileWriter writer = new WaveFileWriter();
            AudioFormat frmt = new AudioFormat(16000, 16, 1, true, false);
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            InputStream in = new AudioInputStream(bi, frmt, bytes.length);
            writer.write((AudioInputStream) in, AudioFileFormat.Type.WAVE, outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        float speed = 1.5f;//1.5倍速度播放
        float pitch = 1.5f;
        float rate = 1.0f;
        float volume = 1.0f;
        boolean emulateChordPitch = false;
        int quality = 0;

        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("D://data/audio/熊孩子/xiaoqiao-2b01.mp3"));
        AudioFormat format = stream.getFormat();
        int sampleRate = (int) format.getSampleRate();
        int numChannels = format.getChannels();
        SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, format,
                ((int) stream.getFrameLength() * format.getFrameSize()));
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(stream.getFormat());
        line.start();
        runSonic(stream, line, speed, pitch, rate, volume, emulateChordPitch, quality,
                sampleRate, numChannels);
        line.drain();
        line.stop();
    }
}