package probono.model;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;

@Slf4j

public class Crawler {
	static String apikey = "A9CAF26B128DB44DA671FA8334A73CE9";

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

	public static ArrayList<String> relatedNaverCrawler(String input) throws IOException {
		System.out.println("네이버 연관 검색어");
		ArrayList<String> resultList = new ArrayList<String>();
		String url;
		Document doc; 
		
		String encoded = URLEncoder.encode(input,"utf-8"); //﻿※
		url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+encoded;
		System.out.println(url);		
		
		doc = Jsoup.connect(url).execute().parse();
		
		Elements relatedwords = doc.select("div.tit");
		
		System.out.println(relatedwords.size());
		
		for(Element e : relatedwords) {
			resultList.add(StringUtils.substringBetween(e.toString(), "tit\">", "<"));
		}
		
		return resultList;
	}
	
	public static ArrayList<String> relatedGoogleCrawler(String input) throws IOException {
		System.out.println("구글 연관 검색어");
		ArrayList<String> resultList = new ArrayList<String>();
		String url;
		Document doc; 
		
		String encoded = URLEncoder.encode(input,"utf-8"); //﻿※
		url = "https://www.google.com/search?q="+encoded;
		System.out.println(url);		
		
		doc = Jsoup.connect(url).execute().parse();
		
		Elements relatedwords = doc.select("p.nVcaUb");
		System.out.println(relatedwords);
		System.out.println(relatedwords.size());
		
		for(Element e : relatedwords) {
			resultList.add(StringUtils.substringBetween(StringUtils.substringBetween(e.toString(), "\">", "</p>"), "\">", "</a>"));
		}
		log.warn("relatedNaverCrawler 실행 기록");
		return resultList;
	}
	
	public static ArrayList<HashMap<String, String>> googleImageCrawler(String input) throws IOException {
		System.out.println("구글 이미지 검색중");
		ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		String url;
		Document doc; 
		
		String encoded = URLEncoder.encode(input,"utf-8"); //﻿※
		url = "https://www.google.com/search?q="+ encoded + "&newwindow=1&sxsrf=ALeKk015_AA4LJD6gWU_Az6s8DGuVZZMPA:1609206583430&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjv2ZnWifLtAhUQG6YKHaBCBkUQ_AUoAXoECBMQAw&biw=1247&bih=616";
		System.out.println(url);		
		
		doc = Jsoup.connect(url).execute().parse();
		
		Elements images = doc.select("img");
		System.out.println(images.size());
		
		for(Element e : images) {
			HashMap<String, String> minimap = new HashMap<String, String>();
			minimap.put("title", StringUtils.substringBetween(e.toString(), "alt=\"", "\""));
			minimap.put("source", e.toString().replace("data-src", "src"));
			resultList.add(minimap);
		}
		resultList.remove(0);
		log.warn("googleImageCrawler 실행 기록");
		return resultList;
	}
	
	public static ArrayList<HashMap<String, String>> naverImageCrawler(String input) throws IOException {
		System.out.println("네이버 이미지 검색중");
		ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		String url;
		Document doc; 
		
		String encoded = URLEncoder.encode(input,"utf-8"); //﻿※
		url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=image&query="+ encoded;
		System.out.println(url);		
		
		doc = Jsoup.connect(url).execute().parse();
		
		Elements relatedwords = doc.select("div.thumb");
		System.out.println(relatedwords.size());
		
		for(Element e : relatedwords) {
			HashMap<String, String> minimap = new HashMap<String, String>();
			minimap.put("title", StringUtils.substringBetween(e.toString(), "alt=\"", "\">"));
			minimap.put("source", e.toString());
			resultList.add(minimap);
		}
		resultList.remove(0);
		log.warn("naverImageCrawler 실행 기록");
		return resultList;
	}
}
