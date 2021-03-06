/**
 * Copyright (C), 2015-2020, 优度宽带有限公司
 * FileName: test1
 * Author:   admin
 * Date:     2020/8/26 15:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sto.video;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.resample.RateTransposer;
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
     * MP3转换PCM文件方法
     *
     * @param mp3filepath 原始文件路径
     * @param pcmfilepath 转换文件的保存路径
     * @return
     * @throws Exception
     */
    public static InputStream convertMP32Pcm(String mp3filepath, String pcmfilepath){
        try {
            //获取文件的音频流，pcm的格式
            AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
            //将音频转化为  pcm的格式保存下来
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmfilepath));
            return audioInputStream;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 机能概要:获取文件的音频流
     * 创建日期:2018年1月14日<br/>
     * 创建时间:下午9:53:14<br/>
     * 创建用户:yellowcong<br/>
     * @param mp3filepath
     * @return
     */
    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) {
        File mp3 = new File(mp3filepath);
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
            AudioInputStream in = null;

            //读取音频文件的类
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(mp3);
            AudioFormat baseFormat = in.getFormat();

            //设定输出格式为pcm格式的音频文件
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

            //输出到音频
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    /**
     * 变声
     * @param rawPcmInputStream 原始PCM数据输入流
     * @param speedFactor 变速率 (0,2) 大于1为加快语速，小于1为放慢语速
     * @param rateFactor 音调变化率 (0,2) 大于1为降低音调（深沉），小于1为提升音调（尖锐）
     * @return 变声后的PCM数据输入流
     */
    public static InputStream speechPitchShift(final InputStream rawPcmInputStream, double speedFactor, double rateFactor) {
        // 这里根据自己PCM格式修改对应参数。我们项目里音频格式是固定的，所以写死
        TarsosDSPAudioFormat format = new TarsosDSPAudioFormat(8000,16,1,true,false);
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

    public static void main(String[] args) throws Exception{
        // 声音变音处理
         InputStream inputStream = convertMP32Pcm("D:/data/images/22条商规.mp3", "D:/data/images/22条商规pcm.pcm");
         InputStream inputStream1 = speechPitchShift(inputStream, 1.55, 1.55);
        convertAudioFiles("D:/data/images/22条商规pcm.pcm", "D:/data/images/22条商规mp32.mp3");

        //这里返回的是pcm格式的音频
//        byte[] bytes = speechPitchShiftMp3("D:/data/images/bxqy.mp3", 1.55, 1.55);
//        File tempFile = new File("D:/data/images/bxqypcm.pcm");
//        //如果需要转成wav则需要给pcmBytes增加一个头部信息  116.31.48 37.48.39
//        //TarsosDSP中也有输出Wav格式音频的处理器，这里没有使用。
//        byte[] wavHeader = pcm2wav(bytes);
//        OutputStream wavOutPut = new FileOutputStream(tempFile);
//        wavOutPut.write(wavHeader);
//        wavOutPut.write(bytes);
//        wavOutPut.flush();
//        wavOutPut.close();
//
//        // 对于各种声音类型，以及所需添加的处理器，还有处理器参数代码，将在本文最后给出。
//        //如果需要转mp3格式的，也可以给我留言，我会加上。
//        convertAudioFiles("D:/data/images/bxqypcm.pcm", "D:/data/images/bxqymp3.mp3");
    }

    /**
     * 变声
     * @param speedFactor 变速率 (0,2) 大于1为加快语速，小于1为放慢语速
     * @param rateFactor 音调变化率 (0,2) 大于1为降低音调（深沉），小于1为提升音调（尖锐）
     * @return 变声后的MP3数据输入流
     */
    public static byte[] speechPitchShiftMp3(String fileUrl, double rateFactor, double speedFactor) throws IOException, UnsupportedAudioFileException {

        WaveformSimilarityBasedOverlapAdd w = new WaveformSimilarityBasedOverlapAdd(WaveformSimilarityBasedOverlapAdd.Parameters.speechDefaults(rateFactor, 16000));
        int inputBufferSize = w.getInputBufferSize();
        int overlap = w.getOverlap();

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromPipe(fileUrl,16000,inputBufferSize,overlap);
        w.setDispatcher(dispatcher);
        dispatcher.addAudioProcessor(w);

        /** 采样率转换器。 使用插值更改采样率, 与时间拉伸器一起可用于音高转换。 **/
        dispatcher.addAudioProcessor(new RateTransposer(speedFactor));
        AudioOutputToByteArray out = new AudioOutputToByteArray();


        /** 声音速率转换器 -- 失败 **/
        /*SoundTouchRateTransposer soundTouchRateTransposer = new SoundTouchRateTransposer(2);
        soundTouchRateTransposer.setDispatcher(dispatcher);
        dispatcher.addAudioProcessor(soundTouchRateTransposer);*/

        /** 正弦波发生器 -- 无反应 **/
        /*SineGenerator sineGenerator = new SineGenerator(0.5, 0.5);
        dispatcher.addAudioProcessor(sineGenerator);*/

        /** 音调转换器 -- 无效果 **/
//        dispatcher.addAudioProcessor(new PitchShifter(0.1,16000,448,overlap));

        /** 制粒机使用颗粒合成回放样本。方法可用于控制播放速率，音高，颗粒大小， -- 无效果 **/
//        dispatcher.addAudioProcessor(new OptimizedGranulator(16000, 448));

        /** 噪音产生器 -- 有效果 **/
//        dispatcher.addAudioProcessor(new NoiseGenerator(0.2   ));

        /** 增益处理器  增益为1，则无任何反应。 增益大于1表示音量增加a -- 有反应 **/
       // dispatcher.addAudioProcessor(new GainProcessor(10));

        /**镶边效果 -- 有反应 **/
       // dispatcher.addAudioProcessor(new FlangerEffect(64, 0.3, 16000, 16000));// 回声效果
//        dispatcher.addAudioProcessor(new FlangerEffect(1 << 4, 0.8, 8000, 2000));// 感冒
//        dispatcher.addAudioProcessor(new ZeroCrossingRateProcessor());//感冒

        /** 淡出 --声音慢慢变小 **/
//        dispatcher.addAudioProcessor(new FadeOut(5));

        /** 淡入-- 声音慢慢变大 **/
//        dispatcher.addAudioProcessor(new FadeIn(5));

        /** 在信号上添加回声效果。echoLength以秒为单位  elay回声的衰减，介于0到1之间的值。1表示无衰减，0表示立即衰减 **/
        // dispatcher.addAudioProcessor(new DelayEffect(0.2, 0.24, 12000) );

        /** 调幅噪声 -- 将声音转换为噪声**/
//        dispatcher.addAudioProcessor(new AmplitudeModulatedNoise());

        /** 振幅LFO -- 声音波动 **/
//        dispatcher.addAudioProcessor(new AmplitudeLFO());

        dispatcher.addAudioProcessor(out);

        dispatcher.run();



//        return new ByteArrayInputStream(out.getData());
        return out.getData();
    }


    public static byte[] pcm2wav(byte[] bytes) throws IOException {
        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader();

        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = bytes.length + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 16000;
        header.BlockAlign = (short)(header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = bytes.length;

        byte[] h = header.getHeader();
        assert h.length == 44; //WAV标准，头部应该是44字节
        return h;
    }
    /**
     * PCM 转 MP3
     * @param src    待转换文件路径
     * @param target 目标文件路径
     * @throws IOException 抛出异常
     */
    public static String convertAudioFiles(String src, String target) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);

        //计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();

        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader();
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 8000;//正常速度是8000，这里写成了16000，速度加快一倍
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;

        byte[] h = header.getHeader();

        assert h.length == 44; //WAV标准，头部应该是44字节
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(src);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("PCM Convert to MP3 OK!");
        return "ok";
    }
}
