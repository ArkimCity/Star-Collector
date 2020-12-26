package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupCrawlKakao {
	public static void main(String[] args) {
		System.out.println(crawler("서울특별시 마포구 잔다리로3안길 27"));
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

	public static String crawler(String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://m.map.kakao.com/actions/searchView?q="+ search +"&wxEnc=LVSOTP&wyEnc=QNLTTMN&lvl=4")
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements scripts = doc.getElementsByTag("em");
		System.out.println(scripts);
		return scripts.toString().substring(21, 24);
	}
}