/**  
* @Package com.liusoft.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午01:25:55 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.apache.commons.digester.RuleSetBase;

/**  
 * @Package com.liusoft.startup 
 * @author liukunyang
 * @date 2013-9-23 下午01:25:55 
 * @version V1.0  
 */
public class ContextRuleSet extends RuleSetBase {


    public ContextRuleSet(){

    }

    public ContextRuleSet(String prefix) {

        super();
        this.namespaceURI = null;

    }

	@Override
	/**
	 * 配置xml文件的映射关系
	 */
	public void addRuleInstances(Digester arg0) {
		// TODO Auto-generated method stub

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
