/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-24 下午09:27:55 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

import com.liusoft.sc.Service;
import com.liusoft.sc.connector.Connector;

/**  
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-24 下午09:27:55 
 * @version V1.0  
 */
public class ConnectorCreateRule extends Rule{
	
	public void begin(Attributes attributes) throws Exception {
//		Service svc = (Service)digester.peek();
//		Executor ex = null;
//		if ( attributes.getValue("executor")!=null ) {
//		    ex = svc.getExecutor(attributes.getValue("executor"));
//		}
//		Connector con = new Connector(attributes.getValue("protocol"));
//		if ( ex != null )  _setExecutor(con,ex);
//		
//		digester.push(con);
	}

}
