/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-24 下午02:13:05 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.log4j.Logger;

import com.liusoft.sc.core.StandardServer;

/**  
 * sc 映射配置类
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-24 下午02:13:05 
 * @version V1.0  
 */
public class SCRuleSet implements RuleSet {

	private Logger log = Logger.getLogger( SCRuleSet.class );
	
	//TODO 先不配置规则，太复杂，删减后在配置
	@Override
	public void addRuleInstances(Digester digester) {
		  long t1=System.currentTimeMillis();
	        // Initialize the digester
	        digester.setValidating(false);
//	        digester.setRulesValidation(true);
//	        HashMap<Class, List<String>> fakeAttributes = new HashMap<Class, List<String>>();
//	        ArrayList<String> attrs = new ArrayList<String>();
//	        attrs.add("className");
//	        fakeAttributes.put(Object.class, attrs);
	        
//	        digester.setFakeAttributes(fakeAttributes);
	        
//	        digester.setClassLoader(StandardServer.class.getClassLoader());

	        // Configure the actions we will be using
	        //初始化server对象
	        digester.addObjectCreate("Server", "com.liusoft.sc.core.StandardServer", "className");
	        //调用setServer方法将server对象赋值给当前对象对象
	        digester.addSetProperties("Server");
	        digester.addSetNext("Server","setServer","com.liusoft.sc.Server");
//
//	        digester.addObjectCreate("Server/GlobalNamingResources", "com.liusoft.sc.deploy.NamingResources");
//	        digester.addSetProperties("Server/GlobalNamingResources");
//	        digester.addSetNext("Server/GlobalNamingResources", "setGlobalNamingResources", "com.liusoft.sc.deploy.NamingResources");
//	        
//	        // MUST be specified in the element
//	        digester.addObjectCreate("Server/Listener", null, "className");
//	        digester.addSetProperties("Server/Listener"); 
//	        digester.addSetNext("Server/Listener", "addLifecycleListener","com.liusoft.sc.LifecycleListener");
//
	        digester.addObjectCreate("Server/Service", "com.liusoft.sc.core.StandardService", "className");
	        digester.addSetProperties("Server/Service");
	        digester.addSetNext("Server/Service", "addService", "com.liusoft.sc.Service");
//
//	        digester.addObjectCreate("Server/Service/Listener",
//	                                 null, // MUST be specified in the element
//	                                 "className");
//	        digester.addSetProperties("Server/Service/Listener");
//	        digester.addSetNext("Server/Service/Listener",
//	                            "addLifecycleListener",
//	                            "com.liusoft.sc.LifecycleListener");
//
//	        //Executor
//	        digester.addObjectCreate("Server/Service/Executor",
//	                         "com.liusoft.sc.core.StandardThreadExecutor",
//	                         "className");
//	        digester.addSetProperties("Server/Service/Executor");
//
//	        digester.addSetNext("Server/Service/Executor",
//	                            "addExecutor",
//	                            "com.liusoft.sc.Executor");
//
//	        
	        digester.addRule("Server/Service/Connector", new ConnectorCreateRule());
//	        
//	        digester.addRule("Server/Service/Connector", 
//	                         new SetAllPropertiesRule(new String[]{"executor"}));
//	        
	        digester.addSetNext("Server/Service/Connector",
	                            "addConnector",
	                            "com.liusoft.sc.connector.Connector");
//	        
//	        
//
//
//	        digester.addObjectCreate("Server/Service/Connector/Listener",
//	                                 null, // MUST be specified in the element
//	                                 "className");
//	        digester.addSetProperties("Server/Service/Connector/Listener");
//	        digester.addSetNext("Server/Service/Connector/Listener",
//	                            "addLifecycleListener",
//	                            "com.liusoft.sc.LifecycleListener");
//
//	        // Add RuleSets for nested elements
//	        digester.addRuleSet(new NamingRuleSet("Server/GlobalNamingResources/"));
	        digester.addRuleSet(new EngineRuleSet("Server/Service/"));
	        digester.addRuleSet(new HostRuleSet("Server/Service/Engine/"));
	        digester.addRuleSet(new ContextRuleSet("Server/Service/Engine/Host/"));
	        
//	        digester.addRuleSet(ClusterRuleSetFactory.getClusterRuleSet("Server/Service/Engine/Host/Cluster/"));
//	        digester.addRuleSet(new NamingRuleSet("Server/Service/Engine/Host/Context/"));
//
//	        // When the 'engine' is found, set the parentClassLoader.
//	        digester.addRule("Server/Service/Engine",
//	                         new SetParentClassLoaderRule(parentClassLoader));
//	        digester.addRuleSet(ClusterRuleSetFactory.getClusterRuleSet("Server/Service/Engine/Cluster/"));
//
//	        long t2=System.currentTimeMillis();
//	        if (log.isDebugEnabled()){
//	        	   log.debug("Digester for server.xml created " + ( t2-t1 ));
//	        }
	         
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.digester.RuleSet#getNamespaceURI()
	 */
	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return null;
	}

}
