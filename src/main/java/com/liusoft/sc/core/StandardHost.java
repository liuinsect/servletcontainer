/**  
* @Package com.liusoft.sc.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-26 下午04:16:24 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import com.liusoft.sc.Host;
import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleException;
import com.liusoft.sc.LifecycleListener;

/**
 * @Package com.liusoft.sc.core 
 * @author liukunyang
 * @date 2013-9-26 下午04:16:24 
 * @version V1.0
 * name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true"
            xmlValidation="false" xmlNamespaceAware="false"  
 */
public class StandardHost  extends  ContainerBase implements Host {
	
	//digester 映射属性
	private String name;
	
	private String appBase;
	
	private boolean unpackWARs;
	
	private boolean autoDeploy;
	
	private boolean xmlValidation;
	
	private boolean xmlNamespaceAware;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppBase() {
		return appBase;
	}

	public void setAppBase(String appBase) {
		this.appBase = appBase;
	}

	public boolean isUnpackWARs() {
		return unpackWARs;
	}

	public void setUnpackWARs(boolean unpackWARs) {
		this.unpackWARs = unpackWARs;
	}

	public boolean isAutoDeploy() {
		return autoDeploy;
	}

	public void setAutoDeploy(boolean autoDeploy) {
		this.autoDeploy = autoDeploy;
	}

	public boolean isXmlValidation() {
		return xmlValidation;
	}

	public void setXmlValidation(boolean xmlValidation) {
		this.xmlValidation = xmlValidation;
	}

	public boolean isXmlNamespaceAware() {
		return xmlNamespaceAware;
	}

	public void setXmlNamespaceAware(boolean xmlNamespaceAware) {
		this.xmlNamespaceAware = xmlNamespaceAware;
	}

    @Override
    public void start() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
        //TODO 这里读取valve 并且添加到管道中去
        super.start();
    }


    @Override
    public boolean getXmlValidation() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getXmlNamespaceAware() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
