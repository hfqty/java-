package com.util;

import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.pojo.QuanZhi;

public class DBhelper {
	
	
	
	
	
	
	
	/**
	 * 将数据添加到数据表中
	 * @param quanzhilist
	 */
	public static void insertQuanzhi(List<QuanZhi> quanzhilist){
		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		
		try{
			
			//获取数据库链接
			conn = DBDao.getConnection();
			//关闭自动提交
			conn.setAutoCommit(false);
			//将数据插入到数据库
			String sql = "insert into quanzhi values(0,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//将sql语句预编译
			pstm = conn.prepareStatement(sql);
		    //初始化一个int类型变量,存储添加到数据库的数据
			int yes =0;
			//for循环遍历list
			for(QuanZhi qz : quanzhilist){
				//判断数据库中是否含有此数据
				if(had(qz.getHref())){
					System.out.println("数据库中已有此数据");
					//若果有,就跳过
					continue;
				}
			//设置公司名称
			pstm.setString(1,qz.getCompanyName() );
			pstm.setString(2, qz.getCity());//设置城市名称
			pstm.setString(3, qz.getEduReq());//设置学历
			pstm.setString(4, qz.getSalary());//设置薪水
			pstm.setString(5, qz.getJob_name());//设置工作名
			pstm.setString(6, qz.getDate());//设置日期
			pstm.setString(7, qz.getWorkExc());//设置工作经验
			pstm.setString(8, qz.getJobDesc());//设置工作描述
			pstm.setString(9, qz.getCompanyAddress());//设置工作地址2
			pstm.setString(10, qz.getCompanyScale());//设置公司规模
			pstm.setString(11, qz.getCompanyNature());//设置公司性质
			pstm.setString(12, qz.getCompanyType());//设置公司类型
			pstm.setString(13, qz.getHref());//设置公司链接地址
			//提交此条数据
			pstm.addBatch();
		   
			
			
			}
			pstm.executeBatch();
			conn.commit();
			System.out.println("已将数据添加到数据库!");
		}catch(Exception e){
			
		}
	}
	
	/**
	 * 判断是否含有此条数据,如果有返回TRUE
	 * @param url
	 * @return
	 */
	public static boolean had(String url){
		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		try{
			conn = DBDao.getConnection();//连接到数据库
			String sql = "select * from quanzhi where url = ? ";//设置语句
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, url);
			java.sql.ResultSet rs = pstm.executeQuery();
			if(rs.next())
				return true;
		}catch(Exception e){
			
		}finally{
			DBDao.myClose(null, pstm, conn);
		}
		return false;
	}
	/**
	 * 从数据表中查询数据
	 * @param 查询条件
	 */
	public static void selectQuanzhi(String s){
		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		try{
			conn = DBDao.getConnection();
			String sql = "select id,company_name,"
					+ "city,eduReq,salary,job_name,"
					+ "date,workExc,companyAdress,"
					+ "companyScale,companyNature,"
					+ "companyType from quanzhi "+s;
			pstm = conn.prepareStatement(sql);
			
			java.sql.ResultSet rs = pstm.executeQuery();
			
			System.out.println("***********************************************"
					+ "********************************************************"
					+ "********************************************************");
			while(rs.next()){
				int id = rs.getInt(1);
				String company_name = rs.getString(2);
				String city = rs.getString(3);
				String eduReq = rs.getString(4);
				String salary = rs.getString(5);
				String job_name = rs.getString(6);
				String date = rs.getString(7);
				String workExc = rs.getString(8);
				String companyAdress = rs.getString(9);
				String companyScale = rs.getString(10);
				String companyNature = rs.getString(11);
				String companyType = rs.getString(12);
				System.out.println(id+"******"+company_name+"*****"+city+"********"+eduReq+"********"+salary+"********"+job_name+"********"+date+"********"+workExc+"*******"+companyAdress+"*********"+companyScale+"********"+companyNature);
			}
		}catch(Exception e){
			
		}
	}

}
