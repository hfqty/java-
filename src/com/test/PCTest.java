package com.test;

import java.util.List;
import java.util.Scanner;

import com.pojo.QuanZhi;
import com.util.DBhelper;

public class PCTest {
	public static void main(String[] args) {
		con();
	}
	
	public static void choice(){
		System.out.println("请选择功能:");
		System.out.println("1.爬虫功能");
		System.out.println("2.查询功能");
		PCQuanzhi pcq = new PCQuanzhi();
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice){
		case 1:
			String url = pcq.setUrl();
			//获取用户输入的网页
			int page = pcq.getPage(url);
			//获取网页中的链接
			pcq.getInfo(page,url);
			//获取链接中的信息
			if(pcq.getOneInfo()){
			insert(pcq.quanzhilist);
			}
			else{
			System.out.println("没有数据╮(╯_╰)╭");
			}
			break;
		case 2:
			QueryQuanzhi qqz = new QueryQuanzhi();
			qqz.query();
			break;
			default:
				break;
		}
	
	}
	
	public static void insert(List<QuanZhi> list){
		
		System.out.println("是否将数据添加到数据库中");
		System.out.println("1.是");
		System.out.println("2.否");
		Scanner input = new Scanner(System.in);
		int i = input.nextInt();
		if(i==1)
			DBhelper.insertQuanzhi(list);
		else
			return;
		
	}
	public static void con(){
		Scanner input = new Scanner(System.in);
		while(true){
			choice();
			System.out.println("是否继续?\n1.继续\n2.退出");
			int a = input.nextInt();
			if(a!=1){
				break;
			}
		}
	}

}
