/**  
* @Package com.liusoft.sc.loader 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-24 上午10:38:27 
* @version V1.0  
*/ 
package com.liusoft.sc.loader;

import java.net.URL;
import java.net.URLClassLoader;

/**  
 * 系统自己的classloader 和 应用的classloader不同
 * @Package com.liusoft.sc.loader 
 * @author liukunyang
 * @date 2013-9-24 上午10:38:27 
 * @version V1.0  
 */
public class StandardClassLoader extends URLClassLoader{
	
	public StandardClassLoader(URL repositories[]) {
	    super(repositories);
	}
	
	public StandardClassLoader(URL repositories[], ClassLoader parent) {
	    super(repositories, parent);
	}

}
