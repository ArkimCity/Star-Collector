package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupCrawlMangoPlate {
	public static void main(String[] args) {
		System.out.println(crawler("시골향기", "경기도 김포시 고촌읍 신곡리 447-75"));
	}

	public static JSONObject jsonParser(String content) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			Object obj = parser.parse(content);
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String crawler(String search, String address) {
		Document doc2 = null;
		try {
			doc2 = Jsoup.connect("https://www.mangoplate.com/search/" + search)
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = doc2.getElementsByTag("ul");
		
		String a = null;
		
		for (Element element : elements) {
			if (element.data().contains("<a href")) {
				Pattern pattern = Pattern.compile(".*<a href =([^;]*);");
				Matcher matcher = pattern.matcher(element.data());
				if (matcher.find()) {
					a = matcher.group(1);
					break;
				} else {
					System.err.println("No match found!");
				}
				break;
			}
		}

		return a;
	}
}