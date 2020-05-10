import com.gowthamvarma.vocab_selenium_video.Constants;
import com.gowthamvarma.vocab_selenium_video.Util;

public class Test {
	public static void main(String[] args) {
		
		
		String language = "te";
		String word = "race";
		
		String pathDownlaod = Constants.DOWNLAOD_DIR + language + "\\download\\" + word + ".html";
		String pathVidHtml = Constants.DOWNLAOD_DIR + language + "\\html\\" + word + ".html";
		String pathTemplate = System.getProperty(Constants.USER_DIR) + "\\src\\main\\resources\\static\\index.html" ;
		String dir = Constants.DOWNLAOD_DIR + language + "\\video\\";
		
		System.out.println(pathDownlaod);
		System.out.println(pathVidHtml);
		System.out.println(pathTemplate);
		System.out.println(dir);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		Util.createFile("E:\\github\\vocab-selenium-video\\src\\main\\resources\\static\\index.html", "E:\\vocab\\te\\html\\" , "virus.html", Constants.PLACE_HOLDER, "asdf");
		
		
	}
}
