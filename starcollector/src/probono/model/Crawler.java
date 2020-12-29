package probono.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static String apikey = "A9CAF26B128DB44DA671FA8334A73CE9";

	public static void main(String[] args) {
//		try {
//			System.out.println(imageCrawler("나무"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {
			relatedCrawler("나무");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public static String imageCrawler(String searchWord) throws IOException {
		String seaerchGoogle = "https://www.google.com/search?q=%EA%B5%AC%EA%B8%80+%ED%8A%B8%EB%A0%8C%EB%93%9C+%ED%81%AC%EB%A1%A4%EB%A7%81&newwindow=1&sxsrf=ALeKk00DB5Y0ne8W7f6kk16zyGyhsyCn3A:1609171963900&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi4nKnaiPHtAhUuzYsBHWeGCqYQ_AUoAnoECAcQBA&biw=1245&bih=967";
		String searchNaver = "https://m.search.naver.com/search.naver?where=m_image&sm=mtb_jum&query=나무";
		String searchPinterest = "https://www.pinterest.co.kr/search/pins/?q=" + searchWord;
		Document doc = Jsoup.connect(searchPinterest).get();
//        String folder = doc.title();
//        Element element = doc.select("rg_i.Q4LuWd").get(0);
//        Elements img = element.select("img");
		System.out.println(doc.getElementsByAttribute("img"));
		Elements scripts2 = doc.getElementsByTag("img");
		String url = null;
		System.out.println(scripts2);
//        int page = 0;
		for (Element e : scripts2) {
			url = e.getElementsByAttribute("src").attr("src");

//            URL imgUrl = new URL(url);
//            BufferedImage jpg = ImageIO.read(imgUrl);
//            File file = new File("경로"+folder+"\\"+page+".jpg");
//            ImageIO.write(jpg, "jpg", file);
//            page+=1;
		}
		return url;
	}

	public static void relatedCrawler(String asd) throws IOException {
		System.out.println("네이버 연관 검색어");

		String url;
		Document doc; 
		Response response;
		Elements elements;
		Element element;
		
		String searchKeyword;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("검색어를 입력하세요");
		searchKeyword = sc.nextLine();
		sc.close();
		
		String encoded = URLEncoder.encode(searchKeyword,"utf-8"); //﻿※
		url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+encoded;
		System.out.println(url);		
		
		doc = Jsoup.connect(url).execute().parse();
		String outerHtml = doc.outerHtml();
		
		Elements relatedwords = doc.select("._related_keyword_list ._related_keyword_ul li");
		
		System.out.println(relatedwords.size());
		
		for(Element e : relatedwords) {
			element = e.selectFirst("a");
			System.out.println(element.text()); //element의 text() -> text들을 묶어서 하나로 리턴
			System.out.println(element.attr("href"));
		}		
		System.out.println("\n프로그램 종료");
	}

}
