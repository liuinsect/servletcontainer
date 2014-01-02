/**
 * 
 */
package com.liusoft.sc.startup;

import com.liusoft.sc.LifecycleEvent;
import com.liusoft.sc.LifecycleListener;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;


/**
 * @author liukunyang
 * 1. 解析web.xml,初始化里面的元素
 * 2. 解析 docBase指定的工程里面的web.xml
 */
public class ContextConfig  implements Initialize,LifecycleListener {

    private Logger log = Logger.getLogger(ContextConfig.class);

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


    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if( log.isInfoEnabled() ){
            log.info("ContextConfig监听到事件："+event.getLifecycle());
        }

    }
}
