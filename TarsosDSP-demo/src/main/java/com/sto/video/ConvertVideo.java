package com.sto.video;

import java.util.Calendar;
import java.util.Properties;

public class ConvertVideo {

	static Properties props = System.getProperties(); //获得系统属性集
	static String osName = props.getProperty("os.name"); //操作系统名称    
	static String osArch = props.getProperty("os.arch"); //操作系统构架    
	static String osVersion = props.getProperty("os.version"); //操作系统版本 

	static String WINDOWSPATH = "D://soft/ffmpeg-4.3-win64-static/bin/ffmpeg.exe";
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

		new Part().partMP3(osNames, "D:/data/images/fjsp.m4v", "D:/data/images/fjsp1mp3.mp3");//分离MP3
		new Part().partMP4(osNames, "D:/data/images/fjsp.m4v", "D:/data/images/fjsp1mp4.mp4");//分离MP4
//		new Part().partOneMP4(osNames, "F:\\IMG_0490.mp4", "00:00:00", "00:00:03", "F:\\one.mp4");//分割第一段
//		new Part().partOneMP4(osNames, "F:\\IMG_0490.mp4", "00:00:03", "00:00:04", "F:\\two.mp4");//分割第二段
//		new Part().partOneMP4(osNames, "F:\\IMG_0490.mp4", "00:00:04", "00:00:26", "F:\\three.mp4");//分割第三段
//		new Part().partPic(osNames, "F:\\two.mp4", "F:\\%d.jpg");//生成要改变的MP4帧
//		if (new File("F:\\two.mp4").exists()) {
//			new File("F:\\two.mp4").delete();
//		}
		/*
		这里进行图片的处理 
		*/
//		new Part().ComPicMP4(osNames, "F:\\%d.jpg", "F:\\two.mp4");//经过处理后的图片合成新的第二段视频
//
//		new Part().changeTs(osNames, "F:\\one.mp4", "F:\\one.ts");//分别转码 来进行拼接
//		new Part().changeTs(osNames, "F:\\two.mp4", "F:\\two.ts");
//		new Part().changeTs(osNames, "F:\\three.mp4", "F:\\three.ts");
//
//		String shipin[] = {"F:\\one.ts", "F:\\two.ts", "F:\\three.ts"};
//
//		new Part().tsToMP4(osNames, shipin, "F:\\out.mp4");//拼接成新的MP4
		new Part().ComMP3MP4(osNames, "D:/data/images/fjsp1mp3.mp3", "D:/data/images/fjsp1mp4.mp4", "D:/data/images/fjsp1mp3mp4.mp4");//声音和新视频进行合成

		System.out.println("ok");


	}


}