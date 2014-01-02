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
 * 对应于tomcat安装目录的conf下的server.xml
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
        // Initialize the digester
        digester.setValidating(false);

        // Configure the actions we will be using
        //初始化server对象
        digester.addObjectCreate("Server", "com.liusoft.sc.core.StandardServer", "className");

        digester.addSetProperties("Server");
        //调用setServer方法将server对象赋值给当前对象对象
        digester.addSetNext("Server","setServer","com.liusoft.sc.Server");

        digester.addObjectCreate("Server/Service", "com.liusoft.sc.core.StandardService", "className");
        digester.addSetProperties("Server/Service");

        digester.addObjectCreate("Server/Service/Connector", "com.liusoft.sc.connector.Connector", "className");
        digester.addSetProperties("Server/Service/Connector");
        digester.addRule("Server/Service/Connector", new ConnectorCreateRule());

        digester.addSetNext("Server/Service/Connector", "addConnector", "com.liusoft.sc.connector.Connector");

//
//        digester.addRuleSet(new EngineRuleSet("Server/Service/"));
        digester.addObjectCreate("Server/Service/Engine", "com.liusoft.sc.core.StandardEngine", "className");
        digester.addSetProperties("Server/Service/Engine");
        digester.addRuleSet(new HostRuleSet("Server/Service/Engine/"));
        digester.addRuleSet(new ContextRuleSet("Server/Service/Engine/Host/"));
        digester.addSetNext("Server/Service/Engine", "setContainer", "com.liusoft.sc.core.StandardEngine");

        digester.addSetNext("Server/Service", "addService", "com.liusoft.sc.Service");
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
