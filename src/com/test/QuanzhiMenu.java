package com.test;

public class QuanzhiMenu {
	public void QueryMenu(){
		p("请选择查询方式:");
		p("1.通过公司名字查询");
		p("2.通过最低学历查询");
		p("3.通过所在城市查询");
		p("4.通过工作名称查询");
		p("5.通过工作描述查询");
		p("6.退出");
		p("其他,查询所有");
		
	}
	public void p(String s){
		System.out.println(s);
	}

}
