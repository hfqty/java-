package com.util;
  
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * 网络访问工具类
 *
 */
public class PureNetUtil {
    /**
     * get方法直接调用post方法
     * @param url 网络地址
     * @return 返回网络数据
     */
    public static String get(String url){
        return post(url,null);
    }
    /**
     * 设定post方法获取网络资源,如果参数为null,实际上设定为get方法
     * @param url 网络地址
     * @param param 请求参数键值对
     * @return 返回读取数据
     */
   public static String post(String  url,Map param){
        HttpURLConnection conn=null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection)u.openConnection();
            conn.connect();//建立连接
            
            StringBuffer sb=new StringBuffer();
            //获取连接状态码
            int recode=conn.getResponseCode();
            BufferedReader reader=null;
            if(recode==200){
                //从连接中获取输入流
                InputStream in=conn.getInputStream();
                //对输入流进行封装
                reader=new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String str=null;
                sb=new StringBuffer();
                //从输入流中读取数据
                while((str=reader.readLine())!=null){
                    sb.append(str).append(System.getProperty("line.separator"));
                }
                //关闭输入流
                reader.close();
                if (sb.toString().length() == 0) {
                    return null;
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            if(conn!=null)//关闭连接
                conn.disconnect();
        }
        return null;
    }
  
   
   public static void main(String[] args) {
	   String url="http://www.open-open.com/jsoup";
	   //String url="http://sou.zhaopin.com/jobs/searchresult.ashx?pd=7&jl=%E9%83%91%E5%B7%9E&kw=java&sm=0&p=1&source=0";
	   //String url=//此处以返回json格式数据示例,所以format=2,以根据城市名称为例,cityName传入中文
               //"http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E9%83%91%E5%B7%9E&kw=java&sm=0&sg=57a1f48c24c848a79508ba9e057b2bc7&p=1";
       String html = get(url);//通过工具类获取返回数据
       System.out.println(html);
   }
}