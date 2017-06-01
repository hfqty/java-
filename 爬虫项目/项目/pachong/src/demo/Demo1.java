package demo;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo1 {

	public static void main(String[] args) {
		try {
			File input = new File("src\\demo\\1.html");
			Document doc = Jsoup.parse(input, "UTF-8");
			Elements resultLinks = doc.select("h3");
			for (Element a : resultLinks) {
				System.out.println(a.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
