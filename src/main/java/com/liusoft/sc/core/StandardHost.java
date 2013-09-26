/**  
* @Package com.liusoft.sc.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-26 下午04:16:24 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

/**  
 * @Package com.liusoft.sc.core 
 * @author liukunyang
 * @date 2013-9-26 下午04:16:24 
 * @version V1.0
 * name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true"
            xmlValidation="false" xmlNamespaceAware="false"  
 */
public class StandardHost {
	
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
	
	
}
