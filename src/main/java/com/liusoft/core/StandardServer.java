/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:06:07 
* @version V1.0  
*/ 
package com.liusoft.core;

import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleException;
import com.liusoft.sc.LifecycleListener;
import com.liusoft.sc.Server;
import com.liusoft.sc.Service;
import com.liusoft.sc.startup.Initialize;

/**	
 * 模拟tomcat的server 
 * 实现一个server包含多个service
 * 每个service包含多个connector和container
 *   
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午04:06:07 
 * @version V1.0  
 * 
 * 
 */
public class StandardServer implements Server, Lifecycle,Initialize {
	
	private Service[]	 services;
	
	/**
	 * 初始化services数组
	 * 并初始化每个sevice
	 */
	@Override
	public Object initialize() {
		if( services == null || services.length == 0 ){
			services = new Service[1];
		}
		((Initialize)services[0]).initialize();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Server#addService(com.liusoft.sc.Service)
	 */
	@Override
	public void addService(Service service) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Server#findService(java.lang.String)
	 */
	@Override
	public Service findService(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Lifecycle#addLifecycleListener(com.liusoft.sc.LifecycleListener)
	 */
	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Lifecycle#findLifecycleListeners()
	 */
	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Lifecycle#removeLifecycleListener(com.liusoft.sc.LifecycleListener)
	 */
	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Lifecycle#start()
	 */
	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Lifecycle#stop()
	 */
	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
