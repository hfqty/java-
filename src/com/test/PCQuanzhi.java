package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pojo.QuanZhi;
import com.util.PinyinUtils;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PCQuanzhi {
	
	int numOfpage;
	List<QuanZhi> quanzhilist = new ArrayList<QuanZhi>();
	List<String> urllist = new ArrayList<String>();
	List<String> pageurlList = new ArrayList<String>();
	
	String []companys = {"达内","智游","奇酷","兄弟连","北大青鸟",
			"尚学堂","新东方","黑马","传智播客","AAA",
			"火星时代","潭州设计学院","千锋学院"};
	
	/**
	 * 设置url
	 * 通过用户输入的城市和工作名称获取链接地址
	 * 
	 */
	public String  setUrl(){
		System.out.println("请输入城市:");
		Scanner input = new Scanner(System.in);
		String  city = input.next();
		System.out.println("请输入工作名称或关键字");
		String workName = input.next();
		try {
			city = PinyinUtils.chineseToPinYinF(city).toLowerCase();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return "http://www.quanzhi.com/"+city+"/?kw="+workName;
	}
	
	/**
	 * 获取这个url的总页数
	 * 
	 */
	public int getPage(String url){
		
		System.out.println("正在获取页数>>>>>>>");
		String pageNum = null;
		try{
			//获取页面内容
			Document doc = Jsoup.connect(url).get();
			//获取页数链接
			Elements pages = doc.select("div .pages");
			//打印页信息
			//System.out.println(pages);
			//获取所有a标签内容
			 pageNum = pages.select("a").get(13).text();
			//第十三个a标签的属性就是最后一页的页数
			//打印页数
			System.out.println("总页数为:"+pageNum);
		}catch(Exception e){
			
		}
		return  Integer.parseInt(pageNum);
	}
	
	
	/**
	 * 根据用户输入的页数或页码来获得某页或某几页的信息
	 */
	public void getInfo(int pages,String url){
		
		if(pages<1){
			System.out.println("没有");
			return;
		}
		Scanner input = new Scanner(System.in);
		System.out.println("请选择获取方式:"
		        +"\n1.在"+pages+"范围内任意一页;"
				+"\n2.从第min页到第max页,min<=max<="+pages);
		int more = input.nextInt();
		//如果用户输入1
		if(more==1){
			System.out.println("请输入你要获取的页码:");
			int pageNo=0;
			while(true){
				 pageNo = input.nextInt();
			if(pageNo<pages)
				break;
			System.out.println("输入错误");
			}
			url = url+"&page="+pageNo+"#nogo";
			System.out.println("正在获取第"+pageNo+"页内的链接");
			getUrl(url);
			//如果用户输入2
		}else if(more==2){
			System.out.println("请输入min,max:");
			System.out.println("提示,最大页数为"+pages);
			while(true){
			System.out.print("min=");
			int min = input.nextInt();
			System.out.print("max=");
			int max = input.nextInt();
		    if(min<=max&&max<=pages){
		    	System.out.println("正在获取第"+min+"页到第"+max+"页内的链接");
		    	for(int pageNo=min;pageNo<=max;pageNo++){
		    		url = url+"&page="+pageNo+"#nogo";
		    		getUrl(url);
		    		
		         }
		    	break;
			}//结束循环
		    System.out.println("输入错误");
			}
		}//结束判断
		
		
		
	}//结束方法

	/**
	 * 获取本业所有url,并将url放入list中
	 */
	private  void getUrl(String url){
		//调用设置url方法设置url
		
		//获取url中所有需要的url链接
		try {
			Document doc = Jsoup.connect(url).get();
			Elements infos = doc.select("div .job-info");
			//打印所有信息
			//System.out.println(infos);
			Elements urls = infos.select("a");
			//打印所有url
			//System.out.println(urls);
			//获取所有url
			System.out.println("提取数据中.......");
			for(Element urll : urls ){
				//获取所有地址
				String ur = urll.attr("href");
				//将所有地址添加到urllist中
				urllist.add(ur);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//结束try-catch
	}//结束方法
	
	/**
	 * 获取urllist中所有url的招聘详情
	 */
	public boolean getOneInfo(){
		//初始化
		Document doc=null;
		//for循环遍历urllist
		int count = 0;
		for(String url : urllist){
			//try-catch
			try {
				doc = Jsoup.connect(url).get();
				Elements job_info = doc.select("div ");
				String job_name = job_info.select("h2.left").text();//成了`
				String salary = job_info.select("span[class=salary right]").text();//成了
				String companyName = job_info.select("p[class=com-name]").text();//成了
				Elements job_require = doc.select("p[class=job-require]");//成了
				String city = job_require.select("span[class=addr]").text();//成了
				String workExc = job_require.select("span[class=work-year]").text();//成了
				String eduReq = job_require.select("span[class=edu-bak]").text();//ok
				String date_info = job_require.select("span[class=pub-time]").text();//ok
				String date = date_info.substring(0,date_info.length()-2);//ok
				System.out.println(++count+companyName);//测试输出
				String jobDesc = doc.select("div .desc").text();//ok
				String companyAddress = doc.select("div .com-addr").text().substring(5);//ok
				Elements company_info = doc.select("div[class=com-info clear]");//ok
				String companyScale = company_info.select("li").get(3).text();//ok
				String companyNature = company_info.select("li").get(1).text();//ok
				String companyType = company_info.select("li").get(2).text();//ok
				//判断是不是培训机构
				//如果是就跳过
				if(pass(companyName)) continue;
				
				//初始化并创建一个QuanZhi对象,并将数据放入这个对象
				QuanZhi qz = new QuanZhi();
				qz.setCompanyName(companyName);//公司名称
				qz.setCity(city);//城市名称
				qz.setEduReq(eduReq);//学历
				qz.setSalary(salary);//薪水
				qz.setDate(date);//日期
				qz.setWorkExc(workExc);//工作经验
				qz.setJob_name(job_name);//工作名称
				qz.setJobDesc(jobDesc);//工作描述
				qz.setCompanyAddress(companyAddress);//工作地址
				qz.setCompanyNature(companyNature);//公司
				qz.setCompanyScale(companyScale);//公司规模
				qz.setCompanyType(companyType);//公司类型
				qz.setHref(url);
				
				//将这个对象放入quanzhilist中
				quanzhilist.add(qz);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//结束循环
	System.out.println("爬完了,共爬到"+count+"个符合条件的职位");
	if(count>0){//如果结果数大于0返回真
		return true;
	}
	System.out.println("没有数据,无法添加");
	return false;
	}//结束方法


	/**
	 * 判断此公司是否在黑名单中,如果有就返回TRUE
	 * @param com
	 * @return
	 */
	public boolean pass(String com){
		for(String c:companys){//for循环遍历
		 if(com.contains(c))
			 return true;
		}
		return false;
	}

}
