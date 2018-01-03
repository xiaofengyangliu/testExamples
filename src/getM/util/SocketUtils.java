package getM.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

public class SocketUtils {
	
	private static final Logger logger = Logger.getLogger(SocketUtils.class); 
	
	/**
	 * 
	 * @return
	 */
	public static String getFeature(String ip,Integer port,byte[] jsonStr,int size,String fileName){
		String results = null;
		//客户端
		Socket socket =null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
	    BufferedOutputStream bos;
	    BufferedInputStream bis;
	    
		long fetchFeathureStart = System.currentTimeMillis();
		try {
			//1、创建客户端Socket，指定服务器地址和端口
			socket =new Socket(ip,port);
			
            bos = new BufferedOutputStream(socket.getOutputStream());
            bis= new BufferedInputStream(socket.getInputStream());
            
            
//			socket.setSoTimeout(5000);
			//2、获取输出流，向服务器端发送信息
			dos = new DataOutputStream(socket.getOutputStream());
			
			
			
			dos.writeInt(35);
			dos.writeInt(35);
			dos.writeInt(689);
			dos.writeBytes("type@=loginreq/roomid@=58839/\0");
//			dos.write(jsonStr);
			socket.shutdownOutput();
			//3、获取输入流，并读取服务器端的响应信息
			dis = new DataInputStream(socket.getInputStream());
			int info;
			//前8位 确定回复正确
			byte [] readFlag = new byte[8];
			if((info=dis.read(readFlag))!=-1){
				String flag = new String(readFlag,"utf-8");
				logger.info(flag);
				if(!"HsScript".equals(flag)){
					return null;
				}
			}
			//前4位 要读取的数量
			int num = dis.readInt();
			//读取内容
			byte [] readInfo = new byte[num];
			//要读取的字符数量
			int sum = num;
			//已读取数量
			int readNum = 0;
			//readNum<sum 没读到指定数量
			while((info=dis.read(readInfo,readNum,num))!=-1){
				results = new String(readInfo,"utf-8").trim();
				readNum = results.getBytes("utf-8").length;
				//下次要读取的数量
				num = sum - readNum;
				if(num<=0) break;
			}
			socket.shutdownInput();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			long fetchFeathureEnd = System.currentTimeMillis();
			logger.info(fileName+":提取特征耗时:"+(fetchFeathureEnd - fetchFeathureStart)+" ms");
			//4、关闭资源
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("特征："+results);
		return results;
	}

	//测试
	public static void main(String[] args) {
		try {
			System.out.println(getFeature("openbarrage.douyutv.com",8601, "".getBytes(), 2000, ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
