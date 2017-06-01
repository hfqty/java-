package com.test;

import java.util.Scanner;

import com.util.DBhelper;

public class QueryQuanzhi {
	
	
	public void query(){
		
		String zhi;
		QuanzhiMenu qzm = new QuanzhiMenu();
		
		while(true){
			qzm.QueryMenu();
			String choice = getInput();
			switch(choice){
			case "1":
				System.out.println("输入公司名:");
				zhi = getInput();
				String company_name = "where company_name like '%"+zhi+"%'";
				DBhelper.selectQuanzhi(company_name);
				
				break;
			case "2":
				System.out.println("输入学历");
				zhi = getInput();
				String sql = "where eduReq like '%"+zhi+"%'";
				DBhelper.selectQuanzhi(sql);
				break;
			case "3":
				System.out.println("输入所在城市");
				zhi = getInput() ;
				String city = "where city like '%"+zhi+"%'";
				DBhelper.selectQuanzhi(city);
				break;
			case "4":
				System.out.println("输入工作名称");
				zhi = getInput();
				String job_name = "where job_name like '%"+zhi+"%'";
				DBhelper.selectQuanzhi(job_name);
				break;
			case "5":
				System.out.println("输入职位描述");
				zhi = getInput();
				String desc = "where jobDesc like '%"+zhi+"%'";
				DBhelper.selectQuanzhi(desc);
				break;
			case "6":
				System.out.println("退出");
				return;
			default:
				sql="";
				DBhelper.selectQuanzhi(sql);
				break;
			}
		}
	}

	public String getInput() {
		Scanner input = new Scanner(System.in);
		
		return input.next();
	}
	

	
	

}
