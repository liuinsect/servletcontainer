/**
 * 
 */
package com.liusoft.sc.startup;

import com.liusoft.sc.*;
import com.liusoft.sc.core.StandardContext;
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
     * 在lifecycleEvent 函数中赋值
     * The Context we are associated with.
     */
    protected Context context = null;

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
     * The <code>Rule</code> used to parse the web.xml
     */
    protected static WebRuleSet webRuleSet = new WebRuleSet();

    /**
     * Attribute value used to turn on/off XML validation
     */
    protected static boolean xmlValidation = false;


    /**
     * Attribute value used to turn on/off XML namespace awarenes.
     */
    protected static boolean xmlNamespaceAware = false;
    
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
       this.context = (Context) event.getLifecycle();
        // Process the event that has occurred
        if (event.getType().equals(Lifecycle.START_EVENT)) {
            start();
        } else if (event.getType().equals(StandardContext.BEFORE_START_EVENT)) {
//            beforeStart();
        } else if (event.getType().equals(StandardContext.AFTER_START_EVENT)) {
            // Restore docBase for management tools
//            if (originalDocBase != null) {
//                String docBase = context.getDocBase();
//                context.setDocBase(originalDocBase);
//                originalDocBase = docBase;
//            }
        } else if (event.getType().equals(Lifecycle.STOP_EVENT)) {
//            if (originalDocBase != null) {
//                String docBase = context.getDocBase();
//                context.setDocBase(originalDocBase);
//                originalDocBase = docBase;
//            }
//            stop();
        } else if (event.getType().equals(Lifecycle.INIT_EVENT)) {
            init();
        } else if (event.getType().equals(Lifecycle.DESTROY_EVENT)) {
//            destroy();
        }
    }

    private void start() {
        // Set properties based on DefaultContext
        Container container = context.getParent();
        if( !context.getOverride() ) {
            if( container instanceof Host ) {
                // Reset the value only if the attribute wasn't
                // set on the context.
                xmlValidation = context.getXmlValidation();
                if (!xmlValidation) {
                    xmlValidation = ((Host)container).getXmlValidation();
                }

                xmlNamespaceAware = context.getXmlNamespaceAware();
                if (!xmlNamespaceAware){
                    xmlNamespaceAware
                            = ((Host)container).getXmlNamespaceAware();
                }

                container = container.getParent();
            }
        }

        //这里开始对工程中的web.xml初始化的过程

    }

    /**
     * 解析tomcat_home/conf/web.xml文件
     */
    protected void defaultWebConfig() {

    }

    /**
     * ContextConfig的init方法
     */
    protected void init() {
        if (webDigester == null){
            webDigester = createWebDigester();
            webDigester.getParser();
        }
    }

    private Digester createWebDigester() {
        Digester webDigester =
                createWebXmlDigester(xmlNamespaceAware, xmlValidation);
        return webDigester;
    }

    /**
     * Create (if necessary) and return a Digester configured to process the
     * web application deployment descriptor (web.xml).
     */
    public static Digester createWebXmlDigester(boolean namespaceAware,
                                                boolean validation) {

        Digester webDigester =  DigesterFactory.newDigester(xmlValidation,
                xmlNamespaceAware,
                webRuleSet); //web.xml的解析规则类
        return webDigester;
    }

}
