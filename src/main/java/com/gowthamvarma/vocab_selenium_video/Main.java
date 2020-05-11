package com.gowthamvarma.vocab_selenium_video;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gowthamvarma.vocab_selenium_video.model.Meaning;
import com.gowthamvarma.vocab_selenium_video.model.Translation;
import com.gowthamvarma.vocab_selenium_video.model.Word;
import com.gowthamvarma.vocab_selenium_video.recorder.VideoReord;

public class Main {


	private static String string;

	public static void main(String[] args) {
		
		String word = "acclaim";
		String language = "te";
		
		List<String> words = new ArrayList<String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(Constants.WORD_LIST_PATH))) {
			stream.forEach(words::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("words size :: " + words.size());
		
		// one time copy 
		/*
		File source = new File(System.getProperty(Constants.USER_DIR) + "\\src\\main\\resources\\static\\");
		File dest = new File(Constants.DOWNLAOD_DIR);
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		*/
		
		// process a single word
		/*
		word = "virus";
		procesWord(word,language);
		*/
		
		// inspect downloads
		//inspect(language,words);
		
		// process all words
		/*
		for (int i = 0; i < words.size(); i++) {
			word = words.get(i);
			procesWord(word,language);
		}
		*/
		
		//create
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < alphabet.length(); i++) {
			makeVideoList(language,words,alphabet.charAt(i));
		}
		
		//cetateThumbnailIntroOurto();
	}
	
	private static void cetateThumbnailIntroOurto() {
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		for (int i = 0; i < alphabet.length(); i++) {
			VideoReord.record("file:///" + "E:\\vocab\\te\\assets\\thumbnail\\" + (i+ 1) + ".html", 
					1000 * 8 , "E:\\vocab\\te\\assets\\video-intro-ourto\\","" + alphabet.charAt(i));
		}
		
		VideoReord.record("file:///" + "E:\\vocab\\te\\assets\\intro\\1.html", 
				1000 * 8 , "E:\\vocab\\te\\assets\\video-intro-ourto\\","intro-1");
		VideoReord.record("file:///" + "E:\\vocab\\te\\assets\\intro\\2.html", 
				1000 * 8 , "E:\\vocab\\te\\assets\\video-intro-ourto\\","intro-2");
		VideoReord.record("file:///" + "E:\\vocab\\te\\assets\\intro\\3.html", 
				1000 * 8 , "E:\\vocab\\te\\assets\\video-intro-ourto\\","intro-3");
		
		VideoReord.record("file:///" + "E:\\vocab\\te\\assets\\outro\\1.html", 
				1000 * 10 , "E:\\vocab\\te\\assets\\video-intro-ourto\\","outro-1");
		
	}

	private static void makeVideoList(String language, List<String> words, char charAt) {
		String path = Constants.DOWNLAOD_DIR + language + "\\assets\\scripts\\" + "list-" + charAt + ".txt" ;
		List<String> content = new ArrayList<>();
		
		List<String> files = new ArrayList<>();
		List<String> wordsList = new ArrayList<>();
		
		wordsList.add("thumbnail");
		wordsList.add("intro-1");
		wordsList.add("intro-2");
		wordsList.add("intro-3");
		
		files.add(Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\" +  charAt + ".avi");
		files.add(Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-1.avi");
		files.add(Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-2.avi");
		files.add(Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-3.avi");
		
		// intro
		
		content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\" +  charAt + ".avi'");
		
		content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-1.avi'");
		content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-2.avi'");
		content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\intro-3.avi'");
		
		for (int i = 0; i < words.size(); i++) {
			if(words.get(i).charAt(0) == charAt) {
				File file = new File(Constants.DOWNLAOD_DIR + language + "\\video\\" + words.get(i) + ".avi");
				if(file.exists()) {
					content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\video\\" +  words.get(i) + ".avi'");
					
					wordsList.add(words.get(i));
					files.add(Constants.DOWNLAOD_DIR + language + "\\video\\" +  words.get(i) + ".avi");
				}
			}
		}
		
		// outro
		content.add("file '" + Constants.DOWNLAOD_DIR + language + "\\assets\\video-intro-ourto\\outro-1.avi'");
		
		Util.writeArrayToFile(path, content);
		
		String pathScript = Constants.DOWNLAOD_DIR + language + "\\assets\\scripts\\" + "run-" + charAt + ".bat";
		List<String> contentScript = new ArrayList<>();
		contentScript.add("E:\\housie\\ffmpeg\\bin\\ffmpeg.exe -f concat -safe 0 -i " + path + " -c copy "
				+ Constants.DOWNLAOD_DIR + language + "\\assets\\raw\\out-" + charAt + ".avi");
		Util.writeArrayToFile(pathScript, contentScript);
		
		List<String> contentDuration = new ArrayList<>();
		//video lengths
		int duration = 0;
		for (int i = 0; i < wordsList.size(); i++) {
			//System.out.println(wordsList.get(i) +  " " + formatDuration(duration));
			contentDuration.add(wordsList.get(i) +  " " + formatDuration(duration));
			duration += extractDuration(files.get(i));
		}
		String pathDuration = Constants.DOWNLAOD_DIR + language + "\\assets\\duration\\" + "run-" + charAt + ".txt";
		Util.writeArrayToFile(pathDuration, contentDuration);
	}

	private static String formatDuration(int timeInSeconds) {
		int hours = timeInSeconds / 3600;
	    int secondsLeft = timeInSeconds - hours * 3600;
	    int minutes = secondsLeft / 60;
	    int seconds = secondsLeft - minutes * 60;

	    String formattedTime = "";
	    if (hours < 10) {
	    	formattedTime += "0";
	    }
	        
	    formattedTime += hours + ":";

	    if (minutes < 10) {
	    	formattedTime += "0";
	    }
	    formattedTime += minutes + ":";

	    if (seconds < 10) {
	    	formattedTime += "0";
	    }
	    formattedTime += seconds ;

	    return formattedTime;
	}

	private static int extractDuration(String file) {
		int dur = 0;
		String duration = "";
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
					"E:\\housie\\ffmpeg\\bin\\ffprobe -i " + file + " -show_format");
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				if(line.indexOf("duration") >= 0) {
					//System.out.println(line);
					duration = line;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(duration);
		duration = duration.replaceAll("duration=", "");
		try {
			double temp = Double.valueOf(duration);
			dur = (int) temp;
			//System.out.println(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dur;
	}

	private static void inspect(String language, List<String> words) {
		
		int count = 0;
		
		for (int k = 0; k < words.size(); k++) {
			String word = words.get(k);
			
			String pathDownlaod = Constants.DOWNLAOD_DIR + language + "\\download\\" + word + ".html";
			String pathVidHtml = Constants.DOWNLAOD_DIR + language + "\\html\\";
			
			String pathTemplate = System.getProperty(Constants.USER_DIR) + "\\src\\main\\resources\\static\\index.html";;
			String dir = Constants.DOWNLAOD_DIR + language + "\\video\\";
			
			try {
				Word returnWord = parse(pathDownlaod);
				//System.out.println(returnWord);
				// create HTML
				Set<String> set = new LinkedHashSet<String>();
				set.add(returnWord.getOriginal());
				for (Meaning meaning : returnWord.getMeanings()) {
					set.add(meaning.getPartsOfSpeech());
				}
				set.add(returnWord.getTransaltion());
				for (Translation trans22 : returnWord.getTransaltions()) {
					set.add(trans22.getTranslation());
				}
				set.add(" ");
				
				String replace = "";
				
				int i = 1;
				for (String string : set) {
					string = string.replace("\n", "").replace("\r", "");
					if(string.length() > 160) {
						count++;
						System.out.println(":: word " + word);
						System.out.println(string);
						string = string.substring(0,160);
						System.out.println(string);
					}
					int a = i++;
					//System.out.println("<h2 class=\"frame-" + (a) +"\">" + string + "</h2>");
					replace += "<h2 class=\"frame-" + (a) +"\">" + string + "</h2>";
				}
				Util.createFile(pathTemplate, pathVidHtml, word + ".html", Constants.PLACE_HOLDER, replace);
				// call video
				//System.out.println(Constants.DURATION * (set.size() - 1));
				//VideoReord.record("file:///" + pathVidHtml, Constants.DURATION * (set.size() - 1), dir,word);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("parse failed :: " + word);
			}
		}
		System.out.println("count :: " + count);
	}
	
	private static void procesWord(String word, String language) {
		downloadWord(word,language);
		makeVideo(word,language);
	}

	private static void downloadWord(String word,String language) {
		
		System.out.println("start :: " + word);
		
		String pathDownlaod = Constants.DOWNLAOD_DIR + language + "\\download\\" + word + ".html";
		
		try {
			File temp = new File(pathDownlaod);
			if(!temp.exists()) {
				String baseUrl = "https://translate.google.co.in/#view=home&op=translate&sl=en&text=" + word + "&tl=" + language;
				String content = Util.download(baseUrl);
				content = "<!DOCTYPE html> <html>" + content + "</html>";
				Util.writeToFile(pathDownlaod,content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void makeVideo(String word, String language) {
		
		String pathDownlaod = Constants.DOWNLAOD_DIR + language + "\\download\\" + word + ".html";
		String pathVidHtml = Constants.DOWNLAOD_DIR + language + "\\html\\";
		
		String pathTemplate = System.getProperty(Constants.USER_DIR) + "\\src\\main\\resources\\static\\index.html";;
		String dir = Constants.DOWNLAOD_DIR + language + "\\video\\";
		
		try {
			File temp = new File(dir + "\\" + word + ".avi");
			if(temp.exists()) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Word returnWord = parse(pathDownlaod);
			System.out.println(returnWord);
			// create HTML
			Set<String> set = new LinkedHashSet<String>();
			set.add(returnWord.getOriginal());
			for (Meaning meaning : returnWord.getMeanings()) {
				set.add(meaning.getPartsOfSpeech());
			}
			set.add(returnWord.getTransaltion());
			for (Translation trans22 : returnWord.getTransaltions()) {
				set.add(trans22.getTranslation());
			}
			set.add(" ");
			
			String replace = "";
			
			int i = 1;
			for (String string : set) {
				string = string.replace("\n", "").replace("\r", "");
				if(string.length() > 160) {
					string = string.substring(0,160) + "...";
				}
				int a = i++;
				//System.out.println("<h2 class=\"frame-" + (a) +"\">" + string + "</h2>");
				replace += "<h2 class=\"frame-" + (a) +"\">" + string + "</h2>";
			}
			Util.createFile(pathTemplate, pathVidHtml, word + ".html", Constants.PLACE_HOLDER, replace);
			// call video
			//System.out.println(Constants.DURATION * (set.size() - 1));
			VideoReord.record("file:///" + pathVidHtml + word + ".html", Constants.DURATION * (set.size() - 1), dir, word);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("parse failed :: " + word);
		}
	    
	    //System.out.println("stop :: " + word);
		
	}

	public static Word parse(String path) throws IOException {
		
		Word returnWord = new Word();
		
		String html = new String(Files.readAllBytes(Paths.get(path)));
		
		Document doc = Jsoup.parse(html);
		
		String original = doc.getElementsByClass("gt-ct-text").html();
		String pronounciation = "";
		try {
			pronounciation= doc.getElementsByClass("tlid-transliteration-content").get(0).html();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String transaltion = "";
		try {
			transaltion = doc.select("span.tlid-translation").get(0).child(0).html();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("transaltion " + transaltion);
		
		List<String> usages = new ArrayList<>();
		List<Meaning> meanings = new ArrayList<>();
		List<Translation> transaltions = new ArrayList<>();
		
		returnWord.setOriginal(original);
		returnWord.setPronounciation(pronounciation);
		returnWord.setTransaltion(transaltion);
		
		//returnWord.setUsages(usages);
		returnWord.setTransaltions(transaltions);
		returnWord.setMeanings(meanings);
		
		Elements content = doc.getElementsByClass("gt-ex-text");
		
		//System.out.println(content.size());
		
		for (Element element : content) {
			try {
				String word = element.html();
				usages.add(word);
				//System.out.println(word);
			} catch (Exception e) {
				System.err.println(path);
			}
		}
		
		int i;
		
		content = doc.select("tr.gt-baf-entry");
		i = 0;
		for (Element element : content) {
			Translation tr = new Translation();
			transaltions.add(tr);
			List<String> reverse = new ArrayList<>();
			tr.setReverse(reverse);
			try {
				String transalt = element.child(0).child(0).child(0).child(0).html();
				tr.setTranslation(transalt);
				//System.out.println("---  " + i);
				//System.out.println(transalt);
				for (int j = 0; j < 100; j++) {
					try {
						reverse.add(element.child(1).child(0).child(j).html());
						//System.out.println(element.child(1).child(0).child(j).html());
					} catch (Exception e) {
						break;
					}
				}
			} catch (Exception e) {
				System.err.println(path);
			}
		}
		
		content = doc.select("div.gt-def-row");
		//content2 = content.get(0).parent();
		i = 0;
		for (Element element : content) {
			Meaning meaning = new Meaning();
			meanings.add(meaning);
			//System.out.println("---  " + i++);
			//System.out.println(element.html().split("<div")[0]);
			meaning.setPartsOfSpeech(element.html().split("<div")[0]);
		}
		
		return returnWord;
	}

}
