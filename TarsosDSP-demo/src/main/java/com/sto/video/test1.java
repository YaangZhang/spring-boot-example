/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: test1
 * Author:   admin
 * Date:     2020/8/26 15:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.video;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd;
import be.tarsos.dsp.effects.DelayEffect;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.resample.RateTransposer;
import be.tarsos.dsp.writer.WaveHeader;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import javax.sound.sampled.*;
import java.io.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author admin
 * @create 2020/8/26
 * @since 1.0.0
 */
public class test1 {

    /**
     * 变声
     * @param rawPcmInputStream 原始PCM数据输入流
     * @param speedFactor 变速率 (0,2) 大于1为加快语速，小于1为放慢语速
     * @param rateFactor 音调变化率 (0,2) 大于1为降低音调（深沉），小于1为提升音调（尖锐）
     * @return 变声后的PCM数据输入流
     */
    public static InputStream speechPitchShift(final InputStream rawPcmInputStream, double speedFactor, double rateFactor) {
        // 这里根据自己PCM格式修改对应参数。我们项目里音频格式是固定的，所以写死
        TarsosDSPAudioFormat format = new TarsosDSPAudioFormat(16000,16,1,true,false);
        AudioInputStream inputStream = new AudioInputStream(rawPcmInputStream, JVMAudioInputStream.toAudioFormat(format), AudioSystem.NOT_SPECIFIED);
        JVMAudioInputStream stream = new JVMAudioInputStream(inputStream);

        WaveformSimilarityBasedOverlapAdd w = new WaveformSimilarityBasedOverlapAdd(WaveformSimilarityBasedOverlapAdd.Parameters.speechDefaults(speedFactor, 16000));
        int inputBufferSize = w.getInputBufferSize();
        int overlap = w.getOverlap();
        AudioDispatcher dispatcher = new AudioDispatcher(stream, inputBufferSize ,overlap);
        w.setDispatcher(dispatcher);

        AudioOutputToByteArray out = new AudioOutputToByteArray();

        dispatcher.addAudioProcessor(w);
        dispatcher.addAudioProcessor(new RateTransposer(rateFactor));
        dispatcher.addAudioProcessor(out);
        dispatcher.run();

        return new ByteArrayInputStream(out.getData());
    }

    /**
     * 播放PCM
     *
     * 不要在非桌面环境调用。。。鬼知道会发生什么
     * @param rawPcmInputStream 原始PCM数据输入流
     * @throws LineUnavailableException
     */
    public static void play(final InputStream rawPcmInputStream) throws LineUnavailableException {
        // 这里根据自己PCM格式修改对应参数。我们项目里音频格式是固定的，所以写死
        TarsosDSPAudioFormat format = new TarsosDSPAudioFormat(16000,16,1,true,false);
        AudioInputStream inputStream = new AudioInputStream(rawPcmInputStream, JVMAudioInputStream.toAudioFormat(format),AudioSystem.NOT_SPECIFIED);
        JVMAudioInputStream stream = new JVMAudioInputStream(inputStream);
        AudioDispatcher dispatcher = new AudioDispatcher(stream, 1024 ,0);
        dispatcher.addAudioProcessor(new AudioPlayer(format,1024));
        dispatcher.run();
    }
    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) throws Exception, IOException {
        AudioInputStream ain = new MpegAudioFileReader().getAudioInputStream(new File(mp3filepath));
        AudioFormat sourceFormat = ain.getFormat();

        AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16,

                sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);

        AudioInputStream audioInputStream_Pcm = AudioSystem.getAudioInputStream(targetFormat, ain);

        return audioInputStream_Pcm;

    }
    public static void main(String[] args) throws Exception{
        String ma3filepath = "D:/data/video/22条商规.mp3";
        File file = new File(ma3filepath);

        // new Player(resourceAsStream).play();
        AudioInputStream inputStream = getPcmAudioInputStream(ma3filepath);

        speechPitchShift(inputStream, 0.6, 0.6);
        
        play(inputStream);
    }
}
