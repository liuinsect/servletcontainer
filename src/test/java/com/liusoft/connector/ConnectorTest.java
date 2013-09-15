package com.liusoft.connector;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.liusoft.sc.connector.Connector;
import com.liusoft.sc.http.RequestHeader;

public class ConnectorTest {
	
	/**
	 * 按HTTP 响应 报文格式相应内容测试
	 */
	public  void reponse(){
		
		ServerSocket ss = null;
		try {
			//TODO 把需要配置的东西抽出来，server.xml
			ss = new ServerSocket(8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while( true ){
			
				try {
					Socket socket = ss.accept();
					OutputStream op = socket.getOutputStream();
					op.write( getResponseData().getBytes() );
					op.flush();
					op.close();
					socket.close();
				} catch (IOException e) {
				}
				
		}
		
	};
	
	private String getResponseData(){
		
		String tmp = "HTTP/1.1 200 OK\n"+
					"Date: Sat, 31 Dec 20010 23:59:59 GMT\n"+
					"Content-Type: text/html;charset=UTF-8\n"+
					"Content-Length: 122\n"+	
					"\n"+	//注意这里只是换行而已
					"<html>\n"+
					"<head>\n"+
					"<title>SC Homepage</title>\n"+
					"</head>\n"+
					"<body>\n"+
					"Hello world\n"+
					"</body>\n"+
					"</html>\n";
		return tmp;
	}
	
	
	public static void main(String[] sdfas){
		
		ConnectorTest c = new ConnectorTest();
		c.reponse();
		
		
	}
	
}
