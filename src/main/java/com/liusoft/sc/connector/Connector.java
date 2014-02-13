package com.liusoft.sc.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleException;
import com.liusoft.sc.LifecycleListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.liusoft.sc.constant.CharConstant;
import com.liusoft.sc.constant.HttpHeaderConstant;
import com.liusoft.sc.exception.ExceptionFactory;
import com.liusoft.sc.http.RequestHeader;
import com.liusoft.sc.startup.Initialize;

/**
 *servlet 容器 连接器，负责侦听HTTP请求
 * 对应于server.xml的connector标签
 * port="8080" protocol="HTTP/1.1" 
               connectionTimeout="20000" 
               redirectPort="8443"
 **/

//TODO 方法整理
public class Connector implements Initialize,Lifecycle{
	
	/**
	 * 监听端口
	 */
	private int port;
	
	/**
	 * 协议
	 */
	private String protocol;
	
	/**
	 * 连接的超时时间
	 */
	private long connectionTimeout;
	
	/**
	 * ssl重定向端口
	 * 需要安全通信的场合,将把客户请求转发至SSL的redirectPort端口
	 */
	private int redirectPort;
	

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
			e.printStackTrace();
		}
		while( !done ){
			
				try {
					Socket socket = ss.accept();
					//这里应该把socket包装到多线程中去。否则，只能接受一个请求
					//
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public long getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getRedirectPort() {
		return redirectPort;
	}

	public void setRedirectPort(int redirectPort) {
		this.redirectPort = redirectPort;
	}


    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 被service 调用，启动sokect监听器，监听HTTP请求 或ajp请求
     * @throws LifecycleException
     */
    @Override
    public void start() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
