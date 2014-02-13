/**
 * 
 */
package com.liusoft.sc.startup;

import com.liusoft.sc.*;
import com.liusoft.sc.core.StandardContext;
import com.liusoft.sc.core.StandardEngine;
import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author liukunyang
 * 1. 解析web.xml,初始化里面的元素
 * 2. 解析 docBase指定的工程里面的web.xml
 */
public class ContextConfig  implements Initialize,LifecycleListener {

    private Logger log = Logger.getLogger(ContextConfig.class);

    /**
     * Any parse error which occurred while parsing XML descriptors.
     */
    protected SAXParseException parseException = null;

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
     * Track any fatal errors during startup configuration processing.
     */
    protected boolean ok = false;
    
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
        defaultWebConfig();
    }

    /**
     * 解析tomcat_home/conf/web.xml文件
     */
    protected void defaultWebConfig() {
        long t1=System.currentTimeMillis();

        // Open the default web.xml file, if it exists
        if( defaultWebXml==null && context instanceof StandardContext ) {
            defaultWebXml=((StandardContext)context).getDefaultWebXml();
        }
        // set the default if we don't have any overrides
        if( defaultWebXml==null ) getDefaultWebXml();

        File file = new File(this.defaultWebXml);
        if (!file.isAbsolute()) {
            file = new File(getBaseDir(),
                    this.defaultWebXml);
        }

        InputStream stream = null;
        InputSource source = null;

        try {
            if ( ! file.exists() ) {
                // Use getResource and getResourceAsStream
                stream = getClass().getClassLoader()
                        .getResourceAsStream(defaultWebXml);
                if( stream != null ) {
                    source = new InputSource
                            (getClass().getClassLoader()
                                    .getResource(defaultWebXml).toString());
                }
                if( stream== null ) {
                    // maybe embedded
                    stream = getClass().getClassLoader()
                            .getResourceAsStream("web-embed.xml");
                    if( stream != null ) {
                        source = new InputSource
                                (getClass().getClassLoader()
                                        .getResource("web-embed.xml").toString());
                    }
                }

                if( stream== null ) {
                    log.info("No default web.xml");
                }
            } else {
                source =
                        new InputSource("file://" + file.getAbsolutePath());
                stream = new FileInputStream(file);
                // Important
//                context.addWatchedResource(file.getAbsolutePath());
            }
        } catch (Exception e) {
            log.error("contextConfig.defaultMissing"+ " " + defaultWebXml + " " + file , e);
        }

        if (stream != null) {
            processDefaultWebConfig(webDigester, stream, source);
            webRuleSet.recycle();
        }

       //省略了去加载 Constants.HostWebXml  web.xml.default 的步骤
    }


    /**
     * Process a default web.xml.
     */
    protected void processDefaultWebConfig(Digester digester, InputStream stream,
                                           InputSource source) {

        if (log.isDebugEnabled())
            log.debug("Processing context [" + context.getName()
                    + "] web configuration resource " + source.getSystemId());

        // Process the default web.xml file
        synchronized (digester) {
            try {
                source.setByteStream(stream);

//                if (context instanceof StandardContext)
//                    ((StandardContext) context).setReplaceWelcomeFiles(true);
                digester.setClassLoader(this.getClass().getClassLoader());
                digester.setUseContextClassLoader(false);
                digester.push(context);
                digester.setErrorHandler(new ContextErrorHandler());
                digester.parse(source);
                if (parseException != null) {
                    ok = false;
                }
            } catch (SAXParseException e) {
                log.error("contextConfig.defaultParse", e);
                log.error("contextConfig.defaultPosition"+ e.getLineNumber()+ e.getColumnNumber());
                ok = false;
            } catch (Exception e) {
                log.error("contextConfig.defaultParse", e);
                ok = false;
            } finally {
//                digester.reset();
                parseException = null;
                try {
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    log.error("contextConfig.defaultClose", e);
                }
            }
        }
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



    /**
     * Return the location of the default deployment descriptor
     */
    public String getDefaultWebXml() {
        if( defaultWebXml == null ) {
            defaultWebXml=Constants.DefaultWebXml;
        }

        return (this.defaultWebXml);

    }

    protected String getBaseDir() {
        Container engineC=context.getParent().getParent();
        if( engineC instanceof StandardEngine) {
            return ((StandardEngine)engineC).getBaseDir();
        }
        return System.getProperty("sc.base");
    }

    protected class ContextErrorHandler
            implements ErrorHandler {

        public void error(SAXParseException exception) {
            parseException = exception;
        }

        public void fatalError(SAXParseException exception) {
            parseException = exception;
        }

        public void warning(SAXParseException exception) {
            parseException = exception;
        }

    }


}
