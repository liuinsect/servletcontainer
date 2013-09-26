/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午03:52:38 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/** 
 * 模拟tomcat 的Bootstrap 类，实现逻辑完全不同
 * 只是简单的完成启动功能 
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-23 下午03:52:38 
 * @version V1.0  
 */
public class Bootstrap {
	
	private static Logger log = Logger.getLogger(Bootstrap.class);
	
    protected static final String CATALINA_HOME_TOKEN = "${catalina.home}";
    protected static final String CATALINA_BASE_TOKEN = "${catalina.base}";
	
    //TODO 三个classloader的区别在哪里？
	private ClassLoader commonLoader;
	
	private ClassLoader catalinaLoader;
	
	private ClassLoader sharedLoader;
	
    /**
     * Daemon reference.
     */
    private Object catalinaDaemon = null;
	


    private void initClassLoaders() {
        try {
            commonLoader = createClassLoader("common", null);
            if( commonLoader == null ) {
//                // no config file, default to this loader - we might be in a 'single' env.
                commonLoader=this.getClass().getClassLoader();
            }
            catalinaLoader = createClassLoader("server", commonLoader);
            sharedLoader = createClassLoader("shared", commonLoader);
        } catch (Throwable t) {
            log.error("Class loader creation threw exception", t);
            System.exit(1);
        }
    }


    private ClassLoader createClassLoader(String name, ClassLoader parent)
        throws Exception {

        String value = SCProperties.getProperty(name + ".loader");
        if ((value == null) || (value.equals("")))
            return parent;

        ArrayList repositoryLocations = new ArrayList();
        ArrayList repositoryTypes = new ArrayList();
        int i;
 
        StringTokenizer tokenizer = new StringTokenizer(value, ",");
        while (tokenizer.hasMoreElements()) {
            String repository = tokenizer.nextToken();

            // Local repository
            boolean replace = false;
            String before = repository;
            while ((i=repository.indexOf(CATALINA_HOME_TOKEN))>=0) {
                replace=true;
                if (i>0) {
                repository = repository.substring(0,i) + getSCHome() 
                    + repository.substring(i+CATALINA_HOME_TOKEN.length());
                } else {
                    repository = getSCHome() 
                        + repository.substring(CATALINA_HOME_TOKEN.length());
                }
            }
            while ((i=repository.indexOf(CATALINA_BASE_TOKEN))>=0) {
                replace=true;
                if (i>0) {
                repository = repository.substring(0,i) + getSCBase() 
                    + repository.substring(i+CATALINA_BASE_TOKEN.length());
                } else {
                    repository = getSCBase() 
                        + repository.substring(CATALINA_BASE_TOKEN.length());
                }
            }
            if (replace && log.isDebugEnabled())
                log.debug("Expanded " + before + " to " + repository);

            // Check for a JAR URL repository
            try {
                URL url=new URL(repository);
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_URL);
                continue;
            } catch (MalformedURLException e) {
                // Ignore
            }

            if (repository.endsWith("*.jar")) {
                repository = repository.substring
                    (0, repository.length() - "*.jar".length());
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_GLOB);
            } else if (repository.endsWith(".jar")) {
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_JAR);
            } else {
                repositoryLocations.add(repository);
                repositoryTypes.add(ClassLoaderFactory.IS_DIR);
            }
        }

        String[] locations = (String[]) repositoryLocations.toArray(new String[0]);
        Integer[] types = (Integer[]) repositoryTypes.toArray(new Integer[0]);
 
        ClassLoader classLoader = ClassLoaderFactory.createClassLoader(locations, types, parent);

        // Retrieving MBean server
//        MBeanServer mBeanServer = null;
//        if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
//            mBeanServer =
//                (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
//        } else {
//            mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        }

        // Register the server classloader
//        ObjectName objectName =
//            new ObjectName("Catalina:type=ServerClassLoader,name=" + name);
//        mBeanServer.registerMBean(classLoader, objectName);

        return classLoader;
    }
	

    /**
     * Get the value of the catalina.home environment variable.
     */
    public static String getSCHome() {
        return System.getProperty("sc.home",
                                  System.getProperty("user.dir"));
    }


    /**
     * Get the value of the catalina.base environment variable.
     */
    public static String getSCBase() {
        return System.getProperty("sc.base", getSCHome());
    }
    
	/**
	 * 1. 初始化目录等上下文变量
	 * 2. 初始化classloader 是的以下内容都使用同一个classloader去load
	 * @author liukunyang
	 * @throws Exception 
	 * @date 2013-9-24
	 */
	public void init() throws Exception{
		
		initClassLoaders();
		Thread.currentThread().setContextClassLoader(catalinaLoader);
		
		Class startupClass = catalinaLoader.loadClass("com.liusoft.sc.startup.SC");
        Object startupInstance = startupClass.newInstance();

        // Set the shared extensions class loader
        if (log.isDebugEnabled()){
        	log.debug("Setting startup class properties");
        }
            
        String methodName = "setParentClassLoader";
        Class paramTypes[] = new Class[1];
        paramTypes[0] = Class.forName("java.lang.ClassLoader");
        Object paramValues[] = new Object[1];
        paramValues[0] = sharedLoader;
        Method method = startupInstance.getClass().getMethod(methodName, paramTypes);
        method.invoke(startupInstance, paramValues);

        catalinaDaemon = startupInstance;
		
	}
	
	/**
	 * 1. 初始化SC,
	 * 2. 整个容器start
	 * @author liukunyang
	 * @date 2013-9-24
	 */
	public void start() throws Exception {
		if( catalinaDaemon==null ) init();

        Method method = catalinaDaemon.getClass().getMethod("start", (Class [] )null);
        method.invoke(catalinaDaemon, (Object [])null);
	}
	
	/**
	 * sc home ：E:\workspace2\servletcontainer\sc\
	 * @author liukunyang
	 * @date 2013-9-24	
	 * @param asdf
	 */
	public static void main(String[] asdf){
		try{
			
			System.setProperty("sc.home", "E:\\workspace2\\servletcontainer\\sc\\");
			System.setProperty("sc.base", "E:\\workspace2\\servletcontainer\\sc\\");
			
			Bootstrap b = new Bootstrap();
			b.init();
			
		}catch(Exception e){
			log.error( "启动出错了" , e);
		}
		
	}
	
}	
