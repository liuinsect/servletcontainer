/**  
* @Package com.liusoft.sc 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:11:11 
* @version V1.0  
*/ 
package com.liusoft.sc;

/**  
 * @Package com.liusoft.sc 
 * @author liukunyang
 * @date 2013-9-23 下午04:11:11 
 * @version V1.0  
 */
public interface Server {
	
	/**
	 * 查找一个service
	 * @author liukunyang
	 * @date 2013-9-23	
	 * @param serviceName
	 * @return
	 */
	public Service findService(String serviceName);
	
	/**
	 * 增加一个service
	 * @author liukunyang
	 * @date 2013-9-23
	 */
	public void addService(Service service);
	

	
}
