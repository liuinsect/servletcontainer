/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:06:07 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import java.util.Random;

import org.apache.log4j.Logger;

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
	
	private Logger log = Logger.getLogger( StandardServer.class );
	
	private Service[]	 services;
	
	/**
     * The port number on which we wait for shutdown commands.
     */
    private int port = 8005;

    /**
     * The shutdown command string we are looking for.
     */
    private String shutdown = "SHUTDOWN";
	
	/**
	 * 初始化services数组
	 * 并初始化每个sevice
	 */
	@Override
	public Object initialize() {
		if( services == null || services.length == 0 ){
			services = new StandardService[1];
		}
		((Initialize)services[0]).initialize();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Server#addService(com.liusoft.sc.Service)
	 */
	@Override
	public void addService(Service service) {
		if( services == null || services.length == 0 ){
			services = new StandardService[1];
		}
		
		Service[] result = new Service[ services.length+1 ];
		
		System.arraycopy(service, 0, result, 0, services.length);
		result[ services.length + 1 ] = service;	
		//FIXME 这里需要启动servcie吗？
		if( service != null && service instanceof Lifecycle ){
			try {
				( (Lifecycle)service ).start();
			} catch (LifecycleException e) {
				log.error("service启动失败了", e);
			}
		}
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

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Server#getServer()
	 */
	@Override
	public Server getServer() {
		return this;
	}
	
	
	
	/**
	 * digester mapping  开始 
	 */
	
	 /**
     * Set the port number we listen to for shutdown commands.
     *
     * @param port The new port number
     */
    public void setPort(int port) {

        this.port = port;

    }


    /**
     * Return the shutdown command string we are waiting for.
     */
    public String getShutdown() {

        return (this.shutdown);

    }
	

	
}
