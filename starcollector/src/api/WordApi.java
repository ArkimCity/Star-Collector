package api;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordApi {
	static String apikey = "A9CAF26B128DB44DA671FA8334A73CE9";
	static String url = "https://stdict.korean.go.kr/api/view.do?key=" + apikey + "&method=TARGET_CODE&q=";
	
	//랜덤을 하더라도 없을 경우 검증을 필수로 해줘야 함
	
	public static void main(String[] args) {
		Long before = System.currentTimeMillis();
		System.out.println(crawler(10));
		Long after = System.currentTimeMillis();
		System.out.println(after - before);
	}
	
	public static ArrayList<String> crawler(int inputnumber) {
		ArrayList<String> resultlist = new ArrayList<String>();
		Document doc = null;
		while(resultlist.size() < inputnumber) {
			String number = String.valueOf((int)(Math.random()*500000));
			try {
				doc = Jsoup.connect(url + number).timeout(10000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements scripts2 = doc.getElementsByTag("word");
			
			for (Element e : scripts2) {
				String finder = StringUtils.substringBetween(e.toString(), "CDATA[", "]");
				resultlist.add(finder);
				break;
			}
		}
		return resultlist;
	}
}