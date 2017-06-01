package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.PureNetUtil;

public class Demo3 {

	public static void main(String[] args) {
		String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?pd=1&jl=%E9%83%91%E5%B7%9E&kw=java&sm=0&p=1&source=0";
		//String[] pass={"ÖÇÓÎ", "²©´´»ªÓî", "Ææ¿á"};
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		try {
			//Document doc = Jsoup.connect(url).get();
			Document doc = Jsoup.parse(PureNetUtil.get(url));
			
			Elements links = doc.getElementsByClass("gsmc");
			Elements links2 = doc.getElementsByAttributeValue("class", "zwmc");
			for(Element e : links){
				list1.add(e.text());
			}
			for(Element e : links2){
				list2.add(e.text());
				
				String c = e.getElementsByTag("a").attr("href");
				list3.add(c);				
			}
			for(int i = 0;i<list1.size();i++){
				System.out.println(list1.get(i)+"------"+list2.get(i)+"------"+list3.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
