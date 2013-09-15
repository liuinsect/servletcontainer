package com.liusoft.sc.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.liusoft.sc.constant.CharConstant;
import com.liusoft.sc.constant.HttpHeaderConstant;
import com.liusoft.sc.exception.ExceptionFactory;
import com.liusoft.sc.http.RequestHeader;
import com.liusoft.startup.Initialize;

/**
 *servlet 容器 连接器，负责侦听HTTP请求
 **/
public class Connector implements Initialize{
	
	/**
	 * 
	 */
	private volatile boolean done = false;
	private Logger log = Logger.getLogger(Connector.class);
	
	public Object initialize(){
		ServerSocket ss = null;
		try {
			//TODO 把需要配置的东西抽出来，server.xml
			ss = new ServerSocket(9001);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while( !done ){
			
				try {
					Socket socket = ss.accept();
					RequestHeader header = this.parseRquest(socket);
					
				} catch (IOException e) {
					log.error("等待HTTP请求失败了",e);
					done = true;
				}
				
		}
		
		return null;
	}

//	GET /afasfasdf.html HTTP/1.1
//	Host: localhost
//	User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0
//	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q
	private RequestHeader parseRquest(Socket socket){
		if( socket == null){
			throw ExceptionFactory.throwRunTimeException("socket is empty");
		} 
		RequestHeader header = new RequestHeader();
		String tempLine;
		boolean isFirstLine = true;
		Map<String,String> headerMap = new HashMap<String,String>();
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
			while( null != ( tempLine = br.readLine() ) ){
				
				if( isFirstLine ){
					String[] tmpArray = StringUtils.split( tempLine, CharConstant.SPACE);
					headerMap.put( HttpHeaderConstant.METHOD ,  tmpArray[0] );
					headerMap.put( HttpHeaderConstant.REQUEST_URI , tmpArray[1] );
					headerMap.put( HttpHeaderConstant.PROTOCOL , StringUtils.substringBefore(tmpArray[2], CharConstant.SLASH) );
					headerMap.put( HttpHeaderConstant.PROTOCOL_VERSION, StringUtils.substringAfterLast(tmpArray[2], CharConstant.SLASH) );
					isFirstLine = false;
					continue;
				}
				
				String key = StringUtils.substringBefore( tempLine , CharConstant.COLON);
				String value = StringUtils.substringAfterLast(tempLine, CharConstant.COLON);
				headerMap.put(key, value);
			}
			if( headerMap == null || headerMap.isEmpty()  ){
				throw ExceptionFactory.throwRunTimeException("解析请求头出错");
			}
			header.init(headerMap);
		} catch (IOException e) {
			throw ExceptionFactory.throwRunTimeException("解析请求头出错");
		}
		return header;
	}
	
	
	
	public void setDone(boolean done) {
		this.done = done;
	}

	
}
