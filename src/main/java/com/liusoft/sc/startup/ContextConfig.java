/**
 * 
 */
package com.liusoft.sc.startup;

import org.apache.commons.digester.Digester;

/**
 * @author liukunyang
 * 解析web.xml,初始化里面的元素
 */
public class ContextConfig  implements Initialize {
	
	
    /**
     * The default web application's deployment descriptor location.
     */
    protected String defaultWebXml = null;
	
    /**
     * The <code>Digester</code> we will use to process web application
     * deployment descriptor files.
     */
    protected static Digester webDigester = null;
    
    /**
     *初始化 digester
     */
	@Override
	public Object initialize() {
		configDigester();
		return null;
	} 
	
	/**
	 * 配置digester
	 * @author liukunyang
	 * @date 2013-9-23
	 */
	private void configDigester(){
		webDigester = new Digester();
		webDigester.addRuleSet( new ContextRuleSet() );
	}
	
	

}
