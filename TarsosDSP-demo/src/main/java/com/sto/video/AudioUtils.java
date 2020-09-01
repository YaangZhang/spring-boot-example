package com.sto.video;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.WaveformSimilarityBasedOverlapAdd;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.resample.RateTransposer;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期:2018年1月14日
 * 创建时间:下午10:09:39
 * 创建者    :yellowcong
 * 机能概要:MP3转PCM Java方式实现
 * http://ai.baidu.com/forum/topic/show/496972
 */
public class AudioUtils {
    private static AudioUtils audioUtils = null;
    private AudioUtils(){}
 
    //双判断，解决单利问题
    public static AudioUtils getInstance(){
        if(audioUtils == null){
            synchronized (AudioUtils.class) {
                if(audioUtils == null){
                    audioUtils = new AudioUtils();
                }
            }
        }
        return audioUtils;
    }
 
    /**
     * MP3转换PCM文件方法
     * 
     * @param mp3filepath 原始文件路径
     * @param pcmfilepath 转换文件的保存路径
     * @return 
     * @throws Exception
     */
    public InputStream convertMP32Pcm(String mp3filepath, String pcmfilepath){
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
     * 播放MP3方法
     * 
     * @param mp3filepath
     * @throws Exception
     */
    public void playMP3(String mp3filepath) throws Exception {
        //获取音频为pcm的格式
        AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
 
        // 播放
        if (audioInputStream == null){
            System.out.println("null audiostream");
            return;
        }
        //获取音频的格式
        AudioFormat targetFormat = audioInputStream.getFormat();
        DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, targetFormat, AudioSystem.NOT_SPECIFIED);
        //输出设备
        SourceDataLine line = null;
        try {
            line = (SourceDataLine) AudioSystem.getLine(dinfo);
            line.open(targetFormat);
            line.start();
 
            int len = -1;
//            byte[] buffer = new byte[8192];
            byte[] buffer = new byte[1024];
            //读取音频文件
            while ((len = audioInputStream.read(buffer)) > 0) {
                //输出音频文件
                line.write(buffer, 0, len);
            }
 
            // Block等待临时数据被输出为空
            line.drain();
 
            //关闭读取流
            audioInputStream.close();
 
            //停止播放
            line.stop();
            line.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("audio problem " + ex);
 
        }
    }
 
    /**
     * 创建日期:2018年1月14日<br/>
     * 创建时间:下午9:53:14<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:获取文件的音频流
     * @param mp3filepath
     * @return
     */
    private AudioInputStream getPcmAudioInputStream(String mp3filepath) {
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
    //获取流文件
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        AudioUtils utils  = AudioUtils.getInstance();
        // utils.playMP3("D:/data/video/22条商规.mp3");

        InputStream inputStream = utils.convertMP32Pcm("D:/data/images/22条商规.mp3", "D:/data/images/22条商规pcm.pcm");
        InputStream inputStream1 = speechPitchShift(inputStream, 0.6, 0.6);
        //将音频转化为  pcm的格式保存下来
        // inputStreamToFile(inputStream1, new File("D:/data/video/22条商规pcm.pcm"));

        convertAudioFiles("D:/data/images/22条商规2pcm.pcm", "D:/data/images/22条商规2mp32.mp3");
        // convertAudioFiles("D:/data/video/xx22New.pcm", "D:/data/video/xx22New.mp3");
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
        header.SamplesPerSec = 16000;//正常速度是8000，这里写成了16000，速度加快一倍
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