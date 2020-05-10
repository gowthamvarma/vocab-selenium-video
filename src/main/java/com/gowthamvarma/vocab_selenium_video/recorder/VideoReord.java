package com.gowthamvarma.vocab_selenium_video.recorder;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

//import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
//import java.awt.Toolkit;
import java.io.File;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class VideoReord {

	public static final String USER_DIR = "user.dir";
	public static final String DOWNLOADED_FILES_FOLDER = "recorded";

	private ScreenRecorder screenRecorder;

	public static void main(String[] args) {
		String htmlPath = "file:///C:/Users/gowtham/Downloads/css3-text-animation-effect/dist/index.html";
		int recordingTimeMilli = 15000;
		String file = System.getProperty(USER_DIR) + File.separator + DOWNLOADED_FILES_FOLDER;
		file = "E:\\vocab\\";
		String name = "aegis";
		record(htmlPath,recordingTimeMilli,file,name);
	}

	public static void record(String htmlPath, int recordingTimeMilli, String file, String name) {
		
		try {
			VideoReord videoReord = new VideoReord();

			System.setProperty("webdriver.chrome.driver", "E:\\tools\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--kiosk");
			WebDriver driver = new ChromeDriver(options);

			driver.get(htmlPath);

			// this will make recorder to record only after page rendered
			driver.findElement(By.className("sp-content"));
			videoReord.startRecording(new File(file),name);

			Thread.sleep(recordingTimeMilli);
			
			videoReord.stopRecording();

			driver.quit();
		} catch (Exception e) {
			System.err.println("error creating video");
			e.printStackTrace();
		}
		
		
	}

	public void startRecording(File directory, String name) throws Exception {
		//File file = new File(System.getProperty(USER_DIR) + File.separator + DOWNLOADED_FILES_FOLDER);

		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int width = screenSize.width;
		//int height = screenSize.height;

		// Rectangle captureSize = new Rectangle(0, 0, width, height);
		// 1366 768
		// 1280 720
		Rectangle captureSize = new Rectangle(44, 48, 1280, 720);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, directory,name);
		
		// removing mouse pointer from video
		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				null, null, directory,name);
		
		this.screenRecorder.start();

	}

	public void stopRecording() throws Exception {
		this.screenRecorder.stop();
	}
}