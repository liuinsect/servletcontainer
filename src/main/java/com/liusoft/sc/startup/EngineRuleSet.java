/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.liusoft.sc.startup;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;


/**
 * <p><strong>RuleSet</strong> for processing the contents of a
 * Engine definition element.  This <code>RuleSet</code> does NOT include
 * any rules for nested Host or DefaultContext elements, which should
 * be added via instances of <code>HostRuleSet</code> or
 * <code>ContextRuleSet</code>, respectively.</p>
 *
 * @author Craig R. McClanahan
 * @version $Id: EngineRuleSet.java 939353 2010-04-29 15:50:43Z kkolinko $
 */

public class EngineRuleSet extends RuleSetBase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The matching pattern prefix to use for recognizing our elements.
     */
    protected String prefix = null;


    // ------------------------------------------------------------ Constructor


    /**
     * Construct an instance of this <code>RuleSet</code> with the default
     * matching pattern prefix.
     */
    public EngineRuleSet() {

        this("");

    }


    /**
     * Construct an instance of this <code>RuleSet</code> with the specified
     * matching pattern prefix.
     *
     * @param prefix Prefix for matching pattern rules (including the
     *  trailing slash character)
     */
    public EngineRuleSet(String prefix) {

        super();
        this.namespaceURI = null;
        this.prefix = prefix;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Add the set of Rule instances defined in this RuleSet to the
     * specified <code>Digester</code> instance, associating them with
     * our namespace URI (if any).  This method should only be called
     * by a Digester instance.</p>
     *
     * @param digester Digester instance to which the new Rule instances
     *  should be added.
     */
    public void addRuleInstances(Digester digester) {
        
        digester.addObjectCreate(prefix + "Engine", "com.liusoft.sc.core.StandardEngine","className");
        digester.addSetProperties(prefix + "Engine");
//        digester.addRule(prefix + "Engine", new LifecycleListenerRule("com.liusoft.sc.startup.EngineConfig", "engineConfigClass"));
        digester.addSetNext(prefix + "Engine",
                            "setContainer",
                            "com.liusoft.sc.Container");


    }


}
