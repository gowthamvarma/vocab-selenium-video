package com.gowthamvarma.vocab_selenium_video;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Util {
	
	public static void createFile(String templatePath, String destinationPath, String newName, String placeHolder, String textToReplace) {
		File template = new File(templatePath);
		File destination = new File(destinationPath + newName);
		try {
			if(destination.exists()) {
				destination.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			File directory = new File(destinationPath);
			directory.mkdirs();
			Files.copy(template.toPath(), destination.toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Path path = Paths.get(destination.getAbsolutePath());
			Stream<String> lines = Files.lines(path);
			List<String> replaced = lines.map(line -> line.replaceAll(placeHolder, textToReplace))
					.collect(Collectors.toList());
			Files.write(path, replaced);
			lines.close();
			//System.out.println("Find and Replace done!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String download(String baseUrl) {
    	System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
		WebDriver driver = new ChromeDriver();

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        // get the actual value of the title
        //String content = driver.getPageSource();

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
	
	public static void writeArrayToFile(String path, List<String> content) {
		String newline = System.getProperty("line.separator");
		try {
			File fout = new File(path);
			fout.getParentFile().mkdirs();
			
			FileOutputStream fos = new FileOutputStream(fout);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
		 
			for (String string : content) {
				osw.write(string);
				osw.write(newline);
			}
			
			osw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
