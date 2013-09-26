/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-24 上午10:06:14 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.liusoft.sc.constant.DebugConstant;
import com.liusoft.sc.core.StandardService;
import com.sun.org.apache.xml.internal.resolver.Catalog;

/**  
 * sc 容器启动类
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-24 上午10:06:14 
 * @version V1.0  
 */
public class SC extends StandardService {
	
    protected ClassLoader parentClassLoader = SC.class.getClassLoader();
    
    protected Logger log = Logger.getLogger(SC.class);
	
    /**
     * Pathname to the server configuration file.
     */
    protected String configFile = "conf/server.xml";
    
    protected Digester digester;
    
    /**
     * Set the shared extensions class loader.
     *
     * @param parentClassLoader The shared extensions class loader.
     */
    public void setParentClassLoader(ClassLoader parentClassLoader) {

        this.parentClassLoader = parentClassLoader;

    }
    
    /**
     * 启动
     */
    public void start(){
    	//如果server没有启动，这
    	if( getServer() == null ){
    		load();
    	}
    	
    
    	
    	
    }

	/**
	 * @author liukunyang
	 * @date 2013-9-24	
	 */
	private void load() {
		// TODO Auto-generated method stub
		//开始读取文件
		if( digester == null ){
    		digester = new Digester();
    	}
    	digester.addRuleSet( new SCRuleSet() );
    	
		File configFile = configFile();
        try {
			
        	digester.push(this);
        	digester.parse( configFile );
		} catch (IOException e) {
			log.error("解析server.xml出错了", e);
		} catch (SAXException e) {
			log.error("解析server.xml出错了", e);
		}
		
		
		
	}
	
	
	/**
	 * 获取配置文件的地址
	 * @author liukunyang
	 * @date 2013-9-26	
	 * @return
	 */
    protected File configFile() {

        File file = new File(configFile);
        if (!file.isAbsolute())
            file = new File(System.getProperty("sc.base"), configFile);
        return (file);

    }
    
    public static void main(String[] sdfasd){
    	DebugConstant.init();
    	SC sc = new SC();
    	sc.load();
    	
    }
	
}
