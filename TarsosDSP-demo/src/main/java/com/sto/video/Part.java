package com.sto.video;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//Part类
	public class Part {
	private static Logger log = LoggerFactory.getLogger(MediaUtil.class);
		public boolean partMP3(String osName, String oldfilepath, String mp3outpath) {
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/

			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//分离mp3
			commend.add(oldfilepath);
			commend.add("-vn");
			commend.add("-ar");
			commend.add("44100");
			commend.add("-ac");
			commend.add("2");
			commend.add("-ab");
			commend.add("192");
			commend.add("-f");
			commend.add("mp3");

			commend.add(mp3outpath);
			return executeCommand(commend);
		}

		public boolean partMP4(String osName, String oldfilepath, String mp4outpath) {
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/

			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//获取无声视频
			commend.add(oldfilepath);
			commend.add("-vcodec");
			commend.add("copy");
			commend.add("-an");

			commend.add(mp4outpath);
			return executeCommand(commend);
		}

		public boolean changeTs(String osName, String oldfilepath, String tsoutpath) {
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/

			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//MP4更换为ts
			commend.add(oldfilepath);
			commend.add("-vcodec");
			commend.add("copy");
			commend.add("-vbsf");
			commend.add("h264_mp4toannexb");

			commend.add(tsoutpath);

			try {
				ProcessBuilder builder = new ProcessBuilder(commend);
				builder.command(commend);
				Process p = builder.start();
				doWaitFor(p);
				p.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public boolean tsToMP4(String osName, String[] oldfilepath, String tsoutpath) {
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/
			String list = "\"concat:" + oldfilepath[0];
			if (oldfilepath.length > 2) {
				for (int i = 1; i < oldfilepath.length; i++) {
					list = list + "|" + oldfilepath[i];
				}
			}
			list = list + "\"";
			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//ts合成新的MP4
			commend.add(list);
			commend.add("-vcodec");
			commend.add("copy");
			commend.add("-vbsf");
			commend.add("h264_mp4toannexb");

			commend.add(tsoutpath);

			try {
				ProcessBuilder builder = new ProcessBuilder(commend);
				builder.command(commend);
				Process p = builder.start();
				doWaitFor(p);
				p.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public boolean partOneMP4(String osName, String oldfilepath, String starTime, String endTime, String mp4outpath) {
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/

			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//截取视频段
			commend.add(oldfilepath);
			commend.add("-ss");
			commend.add(starTime);//start
			commend.add("-t");
			commend.add(endTime);//end
			commend.add("-acodec");
			commend.add("copy");
			commend.add("-vcodec");
			commend.add("copy");
			commend.add(mp4outpath);

			try {
				ProcessBuilder builder = new ProcessBuilder(commend);
				builder.command(commend);
				Process p = builder.start();
				doWaitFor(p);
				p.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public boolean ComMP3MP4(String osName, String mp3path, String mp4path, String mp4outpath) {
		
		/*if (!checkfile(mp4path)) {
			System.out.println(mp4path + " is not file");
			return false;
		}
		
		if (!checkfile(mp3path)) {
			System.out.println(mp3path + " is not file");
			return false;
		}
		*/
			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//合成音视频
			commend.add(mp3path);
			commend.add("-i");
			commend.add(mp4path);
			commend.add("-f");
			commend.add("mp4");
			commend.add("-y");
			commend.add(mp4outpath);

			return executeCommand(commend);
		}

		public boolean partPic(String osName, String oldfilepath, String picpath) {//,String starTime,String endTime
		
		/*if (!checkfile(oldfilepath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}*/
			//D:\\ffmpeg-20161110-872b358-win64-static\\bin\\ffmpeg.exe -i f:\\pics\\test2.mp4 f:\\pics\\%d.jpg -vcodec mjpeg -ss 0:00:00 -t 00:00:05
			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-i");//分帧
			commend.add(oldfilepath);
			commend.add(picpath);
			commend.add("-vcodec");
			commend.add("mjpeg");
		/*commend.add("-ss");
		commend.add(starTime);
		commend.add("-t");
		commend.add(endTime);*/

			try {
				ProcessBuilder builder = new ProcessBuilder(commend);
				builder.command(commend);
				Process p = builder.start();
				doWaitFor(p);
				p.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		public boolean ComPicMP4(String osName, String oldpicpath, String mp4outpath) {
		
		/*if (!checkfile(oldpicpath)) {
			System.out.println(oldpicpath + " is not file");
			return false;
		}*/

			List<String> commend = new ArrayList<String>();
			commend.add(osName);
			commend.add("-y");//图片合成
			commend.add("-r");
			commend.add("30");
			commend.add("-i");
			commend.add(oldpicpath);
			commend.add("-absf");
			commend.add("aac_adtstoasc");
			commend.add(mp4outpath);

			try {
				ProcessBuilder builder = new ProcessBuilder(commend);
				builder.command(commend);
				Process p = builder.start();
				doWaitFor(p);
				p.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		private boolean executeCommand(List<String> commonds){
			log.info("--- 待执行的FFmpeg指令为：---" + commonds);
			Runtime runtime = Runtime.getRuntime();
			Process ffmpeg = null;
			try {
				ProcessBuilder builder = new ProcessBuilder(commonds);
				builder.command(commonds);
				ffmpeg = builder.start();
				doWaitFor(ffmpeg);
//				ffmpeg.destroy();
				//new File(oldfilepath).delete();
				return true;
			} catch (Exception e) {
				log.error("--- FFmpeg命令执行出错！ --- 出错信息： " + e.getMessage());
				return false;
			} finally {
				if (null != ffmpeg) {
					ProcessKiller ffmpegKiller = new ProcessKiller(ffmpeg);
					// JVM退出时，先通过钩子关闭FFmepg进程
					runtime.addShutdownHook(ffmpegKiller);
				}
			}
		}

		private int doWaitFor(Process p) {
			InputStream in = null;
			InputStream err = null;
			int exitValue = -1; // returned to caller when p is finished
			try {
				System.out.println("comeing");
				in = p.getInputStream();
				err = p.getErrorStream();
				boolean finished = false; // Set to true when p is finished

				while (!finished) {
					try {
						while (in.available() > 0) {
							Character c = new Character((char) in.read());
							System.out.print(c);
						}
						while (err.available() > 0) {
							Character c = new Character((char) err.read());
							System.out.print(c);
						}

						exitValue = p.exitValue();
						finished = true;

					} catch (IllegalThreadStateException e) {
						Thread.currentThread().sleep(500);
					}
				}
			} catch (Exception e) {
				System.err.println("doWaitFor();: unexpected exception - "
						+ e.getMessage());
			} finally {
				try {
					if (in != null) {
						in.close();
					}

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				if (err != null) {
					try {
						err.close();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			return exitValue;
		}

	/**
	 * 在程序退出前结束已有的FFmpeg进程
	 */
	private static class ProcessKiller extends Thread {
		private Process process;

		public ProcessKiller(Process process) {
			this.process = process;
		}

		@Override
		public void run() {
			this.process.destroy();
			log.info("--- 已销毁FFmpeg进程 --- 进程名： " + process.toString());
		}
	}

		private boolean checkfile(String path) {
			File file = new File(path);
			if (!file.isFile()) {
				return false;
			}
			return true;
		}
	}