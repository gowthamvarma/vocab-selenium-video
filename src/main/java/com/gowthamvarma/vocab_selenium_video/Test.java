package com.gowthamvarma.vocab_selenium_video;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Test {


	public static void main(String[] args) {
		
		String word = "acclimate";
		String language = "te";
		
		String[] langs = { "af", "sq", "am", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "ny", "zh-CN",
				"zh-TW", "co", "hr", "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr", "fy", "gl", "ka", "de", "el",
				"gu", "ht", "ha", "haw", "iw", "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jw", "kn", "kk",
				"km", "ko", "ku", "ky", "lo", "la", "lv", "lt", "lb", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn",
				"my", "ne", "no", "ps", "fa", "pl", "pt", "pa", "ro", "ru", "sm", "gd", "sr", "st", "sn", "sd", "si",
				"sk", "sl", "so", "es", "su", "sw", "sv", "tg", "ta", "te", "th", "tr", "uk", "ur", "uz", "vi", "cy",
				"xh", "yi", "yo", "zu" };

		fileToList();
		
		System.out.println(words.size());
		
		// iterate the array list
		
		for (String lang : langs) {
			language = lang;
			for (int i = 0; i < words.size(); i++) {
				
				word = words.get(i);

				String path = "E:\\vocab\\" + language + "\\" + word + "\\download.html";
				
				System.out.println("start :: " + word);

				String content = download(word, language);
				content = "<!DOCTYPE html> <html>" + content + "</html>";
			    writeToFile(path,content);
			}
		}
		
		/*
		try {
			parse(path);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("parse failed :: " + word);
		}
		*/
	    
	    System.out.println("stop :: " + word);

	}
	
	static String WORD_LIST_PATH = "E:\\github\\vocabulary\\words.txt";
	static List<String> words = new ArrayList<String>();
	
	private static void fileToList() {
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(WORD_LIST_PATH))) {
			stream.forEach(words::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void parse(String path) throws IOException {
		
		String html = new String(Files.readAllBytes(Paths.get(path)));
		
		Document doc = Jsoup.parse(html);
		
		//System.out.println(doc);
		
		Elements content = doc.getElementsByClass("tlid-translation");
		
		//content = doc.getElementsByTag("div");
		
		System.out.println(content.size());
		
		for (Element element : content) {
			try {
				String word = element.html();
				System.out.println(word);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		/*
		
		Elements content = doc.getElementsByTag("div");
		for (Element element : content) {
			//String movie = element.text();
			//String url = element.attr("div#text");
			try {
				String word = element.getElementsByClass("col_a").get(0).getElementsByClass("text").text();
				set.add(word.split(" ")[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		StringBuilder out = new StringBuilder();
		
		for (String element : set) {
			out.append(element);
			out.append("\n");
		}
		
	    FileWriter fileWriter = new FileWriter("C:\\Users\\Gowtham\\Desktop\\out.txt", true); //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(out.toString());  //New line
	    printWriter.close();
		
		*/
	}
    
    private static String download(String word,String language) {
    	System.setProperty("webdriver.chrome.driver","E:\\tools\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
    	
		String baseUrl = "https://translate.google.co.in/#view=home&op=translate&sl=en&text=" + word + "&tl=" + language;
        String content = "";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        content = driver.getPageSource();

        //System.out.println("content :: " + content);
        
        String html = driver.findElement(By.tagName("html")).getAttribute("innerHTML");
        //System.out.println(html);

        //close
        driver.close();
        
        return html;
		
	}

	public static void writeToFile(String path, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(path);
			file.getParentFile().mkdirs();
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}