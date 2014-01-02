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

    private String prefix = null;

    public ContextRuleSet(){

    }

    public ContextRuleSet(String prefix) {

        super();
        this.namespaceURI = null;
        this.prefix = prefix;

    }

	@Override
	/**
	 * 配置xml文件的映射关系
	 */
	public void addRuleInstances(Digester digester) {
		// TODO Auto-generated method stub
        digester.addObjectCreate(prefix + "Context",  "com.liusoft.sc.core.StandardContext","className");
        digester.addSetProperties(prefix + "Context");
        digester.addSetNext(prefix + "Context", "addChild", "com.liusoft.sc.Container");
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
