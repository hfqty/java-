package demo;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo2 {

	public static void main(String[] args) {

		try {
			File input=new File("src\\demo\\2.html");
			Document doc=Jsoup.parse(input, "UTF-8");
			Elements resultLinks = doc.select(".aaa");
			for (Element a: resultLinks) {
				System.out.println(a.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		}

}
