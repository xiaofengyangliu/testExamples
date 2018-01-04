package getM.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class HttpURLConnectionUtil {
	
	private static final Logger logger = Logger.getLogger(HttpURLConnectionUtil.class);
	
	public static void main(String[] args) {
		
		String param = "33689type@=loginreq/roomid@=58839/\0";
		String s = "" ;
		try {
			//openbarrage.douyutv.com
			s = sendServiceCallRequest("http://openbarrage.douyutv.com:8601",param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
	}
	
	/**
	 * 按照对象 json串 传递
	 * @param url
	 * @param content
	 * @return
	 * @throws ErrorStatusException
	 */
	public static String sendServiceCallRequest(String url,String content){
		HttpURLConnection connection = getConnection(url);
		DataOutputStream dops =null;
		try {
			dops = new DataOutputStream(connection.getOutputStream());
			dops.writeBytes(content);
			dops.flush();
			String result = prinResponseData(connection);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(dops!=null){
				try {
					dops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			connection.disconnect();
		}
	}
	
	/**
	 * 按照对象字段为参数传递
	 * @param url
	 * @param o
	 * @return
	 * @throws ErrorStatusException
	 */
/*	public static String sendServiceCallRequest(String url,Object o) throws ErrorStatusException{
		String result = null;
		try {
			byte[] data = HttpClientUtil.sendFormHttp(url, objToList(o), 
					Integer.valueOf(ConfigUtil.getConfig("connectTimeOut")),Integer.valueOf(ConfigUtil.getConfig("requestTimeOut")));
			if(data!=null&&data.length>0){
				result = new String(data,"utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("http接口调用失败,原因"+e.getMessage());
		}
		return result;
	}*/
	
	/**
	 * 获得url连接
	 * @param urlStr
	 * @return
	 * @throws ErrorStatusException
	 */
	private static HttpURLConnection getConnection(String urlStr){
		URL url;
		HttpURLConnection urlConn = null;
		try {
			url = new URL(urlStr);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(true);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(5000);
			urlConn.connect();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("http接口调用失败");
		}
		return urlConn;
	}
	
	/**
	 * 接收返回结果
	 * @param urlConn
	 * @return
	 */
	private static String prinResponseData(HttpURLConnection urlConn){
		BufferedReader reader = null;
		String resultData = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream(), "utf-8"));
			String inputLine = null;
			resultData = "";
			while ((inputLine = reader.readLine()) != null) {
				resultData += inputLine + "\n";
				logger.info("循环查询中结果：" + resultData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			urlConn.disconnect();
		}
		String formatJson =resultData;
		logger.info("查询结果"+formatJson);
		return formatJson;
	}
	
	/**
	 * 对象转换为ArrayList<String[]>集合
	 * @param obj
	 * @return
	 */
	public static ArrayList<String[]> objToList(Object obj) {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					String attrName = fields[i].getName();
					Field f = obj.getClass().getDeclaredField(attrName);
					f.setAccessible(true);
					Object o = f.get(obj);
					list.add(new String[]{attrName,o.toString()});
				} catch (NoSuchFieldException e) {
					System.out.println("NoSuchFieldException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("IllegalArgumentException");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.out.println("IllegalAccessException");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
