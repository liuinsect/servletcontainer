/**  
* @Package com.liusoft.sc.constant 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-26 下午02:47:01 
* @version V1.0  
*/ 
package com.liusoft.sc.constant;

import java.io.File;

/**
 * 模拟一个tomcat目录
 * 用于解析配置文件
 * @Package com.liusoft.sc.constant 
 * @author liukunyang
 * @date 2013-9-26 下午02:47:01 
 * @version V1.0  
 */
public class DebugConstant {
	



	static{
		System.setProperty("sc.home", System.getProperty("user.dir") +"\\"+SystemConstant.PROJECT_NAME+"\\sc\\");
		System.setProperty("sc.base",  System.getProperty("user.dir") +"\\"+SystemConstant.PROJECT_NAME+"\\sc\\");
	}
	
	
	public static void init(){
		
	}


}
