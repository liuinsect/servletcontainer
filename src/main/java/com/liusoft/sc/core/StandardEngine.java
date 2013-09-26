/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:43:30 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import com.liusoft.sc.Container;
import com.liusoft.sc.Engine;
import com.liusoft.sc.Host;
import com.liusoft.sc.startup.Initialize;

/**  
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午04:43:30 
 * @version V1.0  
 */
public class StandardEngine extends ContainerBase  implements Initialize , Engine {
	
	protected String name;
	
	protected String defaultHost;
	
	
	

	@Override
	public Object initialize() {
		
		
		
		return null;
	}
	
	
	/**
	 * 
	 */
    public void addChild(Container child) {

        if (!(child instanceof Host)){
        	throw new IllegalArgumentException("standardEngine.notHost");
        }
        super.addChild(child);

    }

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDefaultHost() {
		return defaultHost;
	}


	public void setDefaultHost(String defaultHost) {
		this.defaultHost = defaultHost;
	}
	
	
	
	
}
