/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:09:35 
* @version V1.0  
*/ 
package com.liusoft.core;

import com.liusoft.sc.Container;
import com.liusoft.sc.Service;
import com.liusoft.sc.connector.Connector;
import com.liusoft.sc.startup.Initialize;

/**  
 * 一个service包含一个connector和container
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午04:09:35 
 * @version V1.0  
 */
public class StandardService implements Service,Initialize{
	
	/**
	 * 连接器，暂时至于HTTP连接器
	 */
	private Connector connector;
	
	/**
	 * 容器
	 */
	private Container container;
	
	
	@Override
	public Object initialize() {
		
		container = new StandardEngine();
		
		return null;
	}
	
}
