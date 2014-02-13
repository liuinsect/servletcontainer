package com.liusoft.sc.core;

import com.liusoft.sc.Context;
import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleException;
import com.liusoft.sc.Wrapper;
import com.liusoft.sc.startup.ContextConfig;

import java.util.HashMap;

/**
 * 一个Context里面包含一个applicationContext
 * ContextConfig会去读取tomcat工程目录下的web.xml和docBase中指定的web.xml 使用webdigester
 * tomcat_home下的context.xml 使用contextDigester
 * 解析到本示例中来
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-6
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class StandardContext extends ContainerBase implements Context {

    private boolean reloadable = false;

    private String docBase = null;

    private String workDir = null;

    /**
     * Attribute value used to turn on/off XML validation
     */
    private boolean webXmlValidation = false;


    /**
     * Attribute value used to turn on/off XML namespace validation
     */
    private boolean webXmlNamespaceAware = false;

    /**
     * The DefaultContext override flag for this web application.
     */
    private boolean override = false;

    /**
     * The distributable flag for this web application.
     */
    private boolean distributable = false;

    /**
     * The servlet mappings for this web application, keyed by
     * matching pattern.
     */
    private HashMap servletMappings = new HashMap();

    /**
     * tomcat_home/conf/web.xml
     * The MIME mappings for this web application, keyed by extension.
     */
    private HashMap mimeMappings = new HashMap();

    /**
     * The ServletContext implementation associated with this Context.
     */
    protected transient ApplicationContext context = null;

    public String getDocBase() {
        return docBase;
    }

    public void setDocBase(String docBase) {
        this.docBase = docBase;
    }

    public boolean isReloadable() {
        return reloadable;
    }

    public void setReloadable(boolean reloadable) {
        this.reloadable = reloadable;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    @Override
    public void start() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
        //TODO 这里init 方法（）；去初始化contextConfig， 里面去读取工程路径，初始化具体的工程
        this.init();
        super.start();

        this.lifecycle.fireLifecycleEvent(Lifecycle.START_EVENT,null);

    }

    /**
     * 初始化ContextConfig
     */
    private void init(){
        this.addLifecycleListener( new ContextConfig() );
    }


    @Override
    public boolean getXmlValidation() {
        return this.webXmlValidation;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getXmlNamespaceAware() {
        return this.webXmlNamespaceAware;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getOverride() {
        return this.override;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDistributable(boolean distributable) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.distributable = distributable;
    }

    @Override
    public Wrapper createWrapper() {
        return new StandardWrapper();  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void setIgnoreAnnotations(boolean ignoreAnnotations) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getIgnoreAnnotations() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
