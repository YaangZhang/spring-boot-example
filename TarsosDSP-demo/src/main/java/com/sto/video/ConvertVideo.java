package com.sto.video;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Properties;

public class ConvertVideo {

	static Properties props = System.getProperties(); //获得系统属性集
	static String osName = props.getProperty("os.name"); //操作系统名称    
	static String osArch = props.getProperty("os.arch"); //操作系统构架    
	static String osVersion = props.getProperty("os.version"); //操作系统版本 

	static String WINDOWSPATH = "D://tools/ffmpeg-4.3-win64-static/bin/ffmpeg.exe";
	static String LINUXPATH = "ffmpeg";
	static String WINDOWS = "Windows";
	static String LINUX = "Linux";
	static String RANDOMNAME = String.valueOf(Calendar.getInstance().getTimeInMillis()) + Math.round(Math.random() * 100000);
	static String ABSOULTPATH = props.getProperty("java.io.tmpdir");
//	static String MP3OUTPATH = props.getProperty("java.io.tmpdir") + PATH.substring(PATH.length() - 24, PATH.length()) + ".mp3";
//	static String MP4OUTPATH = props.getProperty("java.io.tmpdir") + PATH.substring(PATH.length() - 24, PATH.length()) + ".mp4";
//	static String MP4COMPATH = props.getProperty("java.io.tmpdir") + PATH.substring(PATH.length() - 24, PATH.length()) + "s.mp4";
//	static String PICOUTPATH = props.getProperty("java.io.tmpdir") + PATH.substring(PATH.length() - 24, PATH.length()) + "%d.jpg";
	static String PICTUREPATH = props.getProperty("java.io.tmpdir") + RANDOMNAME + ".png";


	public ConvertVideo() {
	}

	public static void main(String[] args) throws Exception {
		/*System.out.println(osName);
		System.out.println(osArch);
		System.out.println(osVersion);*/
//		System.out.println(MP3OUTPATH);

		String osNames = "";
		if (osName.startsWith(WINDOWS)) {
			osNames = WINDOWSPATH;
		} else {
			osNames = LINUXPATH;
		}
		Time time = new Time(0, 0, 10);
		System.out.println(time.toString());
		long time1 = time.getTime();
		System.out.println(time1);
		int seconds = time.getSeconds();
		System.out.println(seconds);
		time.setSeconds(70);
		long time2 = time.getTime();
		System.out.println(time2);
		int seconds2 = time.getSeconds();
		System.out.println(seconds2);

		// new Part().partOneMP4(osNames, "D:/data/images/VID_20190915.mp4", "00:00:00", "13", "D:/data/images/one.mp4");//分割第一段
		// new Part().partOneMP4(osNames, "D:/data/images/VID_20190915.mp4", "00:00:13", "20", "D:/data/images/two.mp4");//分割第二段
		// new Part().partOneMP4(osNames, "D:/data/images/VID_20190915.mp4", "00:00:13", "00:00:25", "D:/data/images/three.mp4");//分割第三段
		// new Part().partPic(osNames, "D:/data/images/two.mp4", "D:/data/images/two-jpg6.jpg");//生成要改变的MP4帧
		// if (new File("D:/data/images/two.mp4").exists()) {
		// 	new File("D:/data/images/two.mp4").delete();
		// }
		// /*
		// 这里进行图片的处理
		// */
		// new Part().ComPicMP4(osNames, "D:/data/images/test-gif.gif", "D:/data/images/test-gif-mp4.mp4");//经过处理后的图片合成新的第二段视频
		// new Part().comGiftoMP4(osNames, "D:/data/images/test-gif.gif", "D:/data/images/test-gif.mp4");//经过处理后的图片合成新的第二段视频
		//
		// new Part().changeTs(osNames, "D:/data/images/one.mp4", "D:/data/images/one.ts");//分别转码 来进行拼接
		// new Part().changeTs(osNames, "D:/data/images/two.mp4", "D:/data/images/two.ts");
		// new Part().changeTs(osNames, "D:/data/images/test-gif.mp4", "D:/data/images/test-gif.ts");
		// new Part().changeTs(osNames, "D:/data/images/05d6.mp4", "D:/data/images/05d6mp4.ts");

		// String shipin[] = {"D:/data/images/test-gif.ts", "D:/data/images/05d6mp4.ts"};
		// new Part().tsToMP4(osNames, shipin, "D:/data/images/05d6-new1.mp4");//拼接成新的MP4
		// new Part().addPicMP4(osNames, "D:/data/images/test-200.jpg", "D:/data/images/two.mp4", "D:/data/images/two-logo.mp4");//拼接成新的MP4
		// new Part().addPicMP4(osNames, "D:/data/images/test-gif.gif",
		// 		"D:/data/images/test.mp4", "D:/data/images/two-gif-pic.mp4");//拼接成新的MP4
        // new Part().addGifMP4(osNames, "D:/data/images/test-gif.gif",
        //         "D:/data/images/test.mp4", "D:/data/images/two-gif.mp4", 3, 6);//拼接成新的MP4


		// new Part().partMP3(osNames, "D:/data/audio/熊孩子/xiaoqiao-2b01.mp4", "D:/data/audio/熊孩子/xiaoqiao-2b01.mp3");//分离MP3
		// new Part().partMP4(osNames, "D:/data/audio/熊孩子/xiaoqiao-2b01.mp4", "D:/data/audio/熊孩子/xiaoqiao-2b01-mp4.mp4");//分离MP4
		// //TarsosDSP 变音
		// AudioUtils.speechPitchShiftMp3("D:/data/audio/熊孩子/xiaoqiao-2b01.mp3",
		// 		0.73, 0.73, "D:/data/audio/熊孩子/xiaoqiao-2b01pcm.pcm");
		// AudioUtils.convertAudioFiles("D:/data/audio/熊孩子/xiaoqiao-2b01pcm.pcm",
		// 		"D:/data/audio/熊孩子/xiaoqiao-2b01-mp3.mp3", 8000);
		// new Part().ComMP3MP4(osNames, "D:/data/images/05d6.mp3",
		// 		"D:/data/images/05d6-mp4-new.mp4", "D:/data/images/05d6-new.mp4");//声音和新视频进行合成

		System.out.println("ok");
	}


}