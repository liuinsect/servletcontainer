/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午03:52:38 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import org.apache.log4j.Logger;

import com.liusoft.core.StandardServer;
import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleException;
import com.liusoft.sc.Server;

/** 
 * 模拟tomcat 的Bootstrap 类，实现逻辑完全不同
 * 只是简单的完成启动功能 
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-23 下午03:52:38 
 * @version V1.0  
 */
public class Bootstrap {
	
	private static Logger log = Logger.getLogger( Bootstrap.class );
	
	public void initServer(){
		StandardServer server = new StandardServer();
//		(Initialize)server.
		
	}
	
	
	public static void main(String[] asdf){
		
	}
	
}	
