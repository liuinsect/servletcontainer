package com.liusoft.sc.core;

import com.liusoft.sc.Container;
import com.liusoft.sc.Engine;
import com.liusoft.sc.Host;
import com.liusoft.sc.LifecycleException;
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

    /** Allow the base dir to be specified explicitely for
     * each engine. In time we should stop using catalina.base property -
     * otherwise we loose some flexibility.
     */
    private String baseDir = null;

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

    /**
     * //到父类中去启动子容器
     * @throws LifecycleException
     */
    public void start() throws LifecycleException {
        //到父类中去启动子容器
        super.start();
    }

    /**
     * 获取SC主目录
     * @return
     */
    public String getBaseDir() {
        if( baseDir==null ) {
            baseDir=System.getProperty("sc.base");
        }
        if( baseDir==null ) {
            baseDir=System.getProperty("sc.home");
        }
        return baseDir;
    }
}
