package probono.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static String apikey = "A9CAF26B128DB44DA671FA8334A73CE9";

	public static void main(String[] args) {
		System.out.println(imageCrawler("웅"));
		System.out.println("끝");
	}

	public static ArrayList<HashMap<String, String>> crawler(String inputnumber) {
		String url = "https://stdict.korean.go.kr/api/view.do?key=" + apikey + "&advanced=y&method=TARGET_CODE&q=";
		ArrayList<HashMap<String, String>> resultlist = new ArrayList<HashMap<String, String>>();
		Document doc = null;
		try {
			while (resultlist.size() < Integer.valueOf(inputnumber)) {
				HashMap<String, String> finder = new HashMap<String, String>();
				String number = String.valueOf((int) (Math.random() * 550000));
				doc = Jsoup.connect(url + number).timeout(10000).get();

				Elements scripts2 = doc.getElementsByTag("word");
				for (Element e : scripts2) {
					finder.put("word", StringUtils.substringBetween(e.toString(), "CDATA[", "]").replace("-", "")
							.replace("^", "").replace(":", ""));
					break;
				}
				Elements scripts3 = doc.getElementsByTag("definition");
				for (Element e : scripts3) {
					finder.put("definition", StringUtils.substringBetween(e.toString(), "CDATA[", "]").replace("-", "")
							.replace("^", "").replace(":", ""));
					break;
				}
				if (finder.get("word") != null) {
					resultlist.add(finder);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultlist;
	}
	
	public static ArrayList<String> imageCrawler(String searchWord) {
		ArrayList<String> resultlist = new ArrayList<String>();
		String googleimageurl = "https://www.google.com/search?q=" + searchWord + "&newwindow=1&sxsrf=ALeKk01ezVLp37O7JPCPhH5yZIowNl5yfA:1609144363268&source=lnms&tbm=isch&sa=X&ved=2ahUKEwimvKjxofDtAhWLad4KHXXwBGoQ_AUoAXoECAwQAw&biw=1245&bih=967";
		Document doc = null;
		try {
			while (resultlist.size() < 10) {
				String finder = new String();
				String number = String.valueOf((int) (Math.random() * 550000));
				doc = Jsoup.connect(googleimageurl).timeout(10000).get();

				Elements scripts2 = doc.getElementsByTag(".rg_i.Q4LuWd");
				for (Element e : scripts2) {
					finder = e.toString();
//					finder.put("word", StringUtils.substringBetween(e.toString(), "CDATA[", "]"));
					break;
				}
				resultlist.add(finder);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultlist;
	}
}
