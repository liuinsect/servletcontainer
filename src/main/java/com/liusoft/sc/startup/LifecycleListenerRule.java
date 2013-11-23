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


import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

import com.liusoft.sc.Container;
import com.liusoft.sc.Lifecycle;
import com.liusoft.sc.LifecycleListener;

/**
 * 去读配置文件中的attr
 * 否则使用默认的config文件
 * 将该config实例化后添加到当前类中去，是的当前类出发时间的时候配置文件类可以做事
 */
public class LifecycleListenerRule extends Rule {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this Rule.
     *
     * @param listenerClass Default name of the LifecycleListener
     *  implementation class to be created
     * @param attributeName Name of the attribute that optionally
     *  includes an override name of the LifecycleListener class
     */
    public LifecycleListenerRule(String listenerClass, String attributeName) {

        this.listenerClass = listenerClass;
        this.attributeName = attributeName;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The attribute name of an attribute that can override the
     * implementation class name.
     */
    private String attributeName;


    /**
     * The name of the <code>LifecycleListener</code> implementation class.
     */
    private String listenerClass;


    // --------------------------------------------------------- Public Methods


    /**
     * 去读配置文件中的arrt
     * 否则使用默认的config文件
     * 将该config实例化后添加到当前类中去，是的当前类出发时间的时候配置文件类可以做事
     */
    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {

        Container c = (Container) digester.peek();
        Container p = null;
        Object obj = digester.peek(1);
        if (obj instanceof Container) {
            p = (Container) obj;
        }

        String className = null;
        
        // Check the container for the specified attribute
        if (attributeName != null) {
            String value = attributes.getValue(attributeName);
            if (value != null)
                className = value;
        }

        // Check the container's parent for the specified attribute
        // 看不懂先注释掉
//        if (p != null && className == null) {
//            String configClass =
//                (String) IntrospectionUtils.getProperty(p, attributeName);
//            if (configClass != null && configClass.length() > 0) {
//                className = configClass;
//            }
//        }
        
        // Use the default
        if (className == null) {
            className = listenerClass;
        }
        
        // Instantiate a new LifecyleListener implementation object
        Class<?> clazz = Class.forName(className);
        LifecycleListener listener =
            (LifecycleListener) clazz.newInstance();

        // Add this LifecycleListener to our associated component
        ((Lifecycle) c).addLifecycleListener(listener);
    }


}
