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


import java.lang.reflect.Method;
import java.util.ArrayList;

import com.liusoft.sc.Context;
import com.liusoft.sc.Wrapper;
import com.liusoft.sc.util.IntrospectionUtils;
import org.apache.commons.digester.*;
import org.xml.sax.Attributes;


/**
 * <p><strong>RuleSet</strong> for processing the contents of a web application
 * deployment descriptor (<code>/WEB-INF/web.xml</code>) resource.</p>
 *
 * @author Craig R. McClanahan
 * @version $Id: WebRuleSet.java 939353 2010-04-29 15:50:43Z kkolinko $
 */

public class WebRuleSet extends RuleSetBase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The matching pattern prefix to use for recognizing our elements.
     */
    protected String prefix = null;
    
    
    /**
     * The <code>SetSessionConfig</code> rule used to parse the web.xml
     */
    protected SetSessionConfig sessionConfig;
    
    
    /**
     * The <code>SetLoginConfig</code> rule used to parse the web.xml
     */
    protected SetLoginConfig loginConfig;

    
    /**
     * The <code>SetJspConfig</code> rule used to parse the web.xml
     */    
    protected SetJspConfig jspConfig;


    // ------------------------------------------------------------ Constructor


    /**
     * Construct an instance of this <code>RuleSet</code> with the default
     * matching pattern prefix.
     */
    public WebRuleSet() {

        this("");

    }


    /**
     * Construct an instance of this <code>RuleSet</code> with the specified
     * matching pattern prefix.
     *
     * @param prefix Prefix for matching pattern rules (including the
     *  trailing slash character)
     */
    public WebRuleSet(String prefix) {

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
        sessionConfig = new SetSessionConfig();
        jspConfig = new SetJspConfig();
        loginConfig = new SetLoginConfig();
        
        digester.addRule(prefix + "web-app", new SetPublicIdRule("setPublicId"));
        digester.addRule(prefix + "web-app", new IgnoreAnnotationsRule());

        digester.addCallMethod(prefix + "web-app/context-param",
                               "addParameter", 2);
        digester.addCallParam(prefix + "web-app/context-param/param-name", 0);
        digester.addCallParam(prefix + "web-app/context-param/param-value", 1);

        digester.addCallMethod(prefix + "web-app/display-name",
                               "setDisplayName", 0);

        digester.addRule(prefix + "web-app/distributable",
                         new SetDistributableRule());



        digester.addObjectCreate(prefix + "web-app/error-page",
                                 "org.apache.catalina.deploy.ErrorPage");
        digester.addSetNext(prefix + "web-app/error-page",
                            "addErrorPage",
                            "org.apache.catalina.deploy.ErrorPage");

        digester.addCallMethod(prefix + "web-app/error-page/error-code",
                               "setErrorCode", 0);
        digester.addCallMethod(prefix + "web-app/error-page/exception-type",
                               "setExceptionType", 0);
        digester.addCallMethod(prefix + "web-app/error-page/location",
                               "setLocation", 0);

        digester.addObjectCreate(prefix + "web-app/filter",
                                 "org.apache.catalina.deploy.FilterDef");
        digester.addSetNext(prefix + "web-app/filter",
                            "addFilterDef",
                            "org.apache.catalina.deploy.FilterDef");

        digester.addCallMethod(prefix + "web-app/filter/description",
                               "setDescription", 0);
        digester.addCallMethod(prefix + "web-app/filter/display-name",
                               "setDisplayName", 0);
        digester.addCallMethod(prefix + "web-app/filter/filter-class",
                               "setFilterClass", 0);
        digester.addCallMethod(prefix + "web-app/filter/filter-name",
                               "setFilterName", 0);
        digester.addCallMethod(prefix + "web-app/filter/large-icon",
                               "setLargeIcon", 0);
        digester.addCallMethod(prefix + "web-app/filter/small-icon",
                               "setSmallIcon", 0);

        digester.addCallMethod(prefix + "web-app/filter/init-param",
                               "addInitParameter", 2);
        digester.addCallParam(prefix + "web-app/filter/init-param/param-name",
                              0);
        digester.addCallParam(prefix + "web-app/filter/init-param/param-value",
                              1);

        digester.addObjectCreate(prefix + "web-app/filter-mapping",
                                 "org.apache.catalina.deploy.FilterMap");
        digester.addSetNext(prefix + "web-app/filter-mapping",
                                 "addFilterMap",
                                 "org.apache.catalina.deploy.FilterMap");

        digester.addCallMethod(prefix + "web-app/filter-mapping/filter-name",
                               "setFilterName", 0);
        digester.addCallMethod(prefix + "web-app/filter-mapping/servlet-name",
                               "addServletName", 0);
        digester.addCallMethod(prefix + "web-app/filter-mapping/url-pattern",
                               "addURLPattern", 0);

        digester.addCallMethod(prefix + "web-app/filter-mapping/dispatcher",
                               "setDispatcher", 0);

         digester.addCallMethod(prefix + "web-app/listener/listener-class",
                                "addApplicationListener", 0);



        digester.addRule(prefix + "web-app/jsp-config",
                         jspConfig);
        
        digester.addCallMethod(prefix + "web-app/jsp-config/jsp-property-group/url-pattern",
                               "addJspMapping", 0);

        digester.addRule(prefix + "web-app/login-config",
                         loginConfig);

        digester.addObjectCreate(prefix + "web-app/login-config",
                                 "org.apache.catalina.deploy.LoginConfig");
        digester.addSetNext(prefix + "web-app/login-config",
                            "setLoginConfig",
                            "org.apache.catalina.deploy.LoginConfig");

        digester.addCallMethod(prefix + "web-app/login-config/auth-method",
                               "setAuthMethod", 0);
        digester.addCallMethod(prefix + "web-app/login-config/realm-name",
                               "setRealmName", 0);
        digester.addCallMethod(prefix + "web-app/login-config/form-login-config/form-error-page",
                               "setErrorPage", 0);
        digester.addCallMethod(prefix + "web-app/login-config/form-login-config/form-login-page",
                               "setLoginPage", 0);

        digester.addCallMethod(prefix + "web-app/mime-mapping",
                               "addMimeMapping", 2);
        digester.addCallParam(prefix + "web-app/mime-mapping/extension", 0);
        digester.addCallParam(prefix + "web-app/mime-mapping/mime-type", 1);



        digester.addRule(prefix + "web-app/servlet",
                         new WrapperCreateRule());
        digester.addSetNext(prefix + "web-app/servlet",
                            "addChild",
                            "org.apache.catalina.Container");

        digester.addCallMethod(prefix + "web-app/servlet/init-param",
                               "addInitParameter", 2);
        digester.addCallParam(prefix + "web-app/servlet/init-param/param-name",
                              0);
        digester.addCallParam(prefix + "web-app/servlet/init-param/param-value",
                              1);

        digester.addCallMethod(prefix + "web-app/servlet/jsp-file",
                               "setJspFile", 0);
        digester.addCallMethod(prefix + "web-app/servlet/load-on-startup",
                               "setLoadOnStartupString", 0);
        digester.addCallMethod(prefix + "web-app/servlet/run-as/role-name",
                               "setRunAs", 0);

//        digester.addRule(prefix + "web-app/servlet/security-role-ref",
//                new SecurityRoleRefCreateRule());
//        digester.addCallMethod(
//                        prefix + "web-app/servlet/security-role-ref/role-link",
//                "setLink", 0);
//        digester.addCallMethod(
//                        prefix + "web-app/servlet/security-role-ref/role-name",
//                "setName", 0);

        digester.addCallMethod(prefix + "web-app/servlet/servlet-class",
                              "setServletClass", 0);
        digester.addCallMethod(prefix + "web-app/servlet/servlet-name",
                              "setName", 0);

        digester.addRule(prefix + "web-app/servlet-mapping",
                               new CallMethodMultiRule("addServletMapping", 2, 0));
        digester.addCallParam(prefix + "web-app/servlet-mapping/servlet-name", 1);
        digester.addRule(prefix + "web-app/servlet-mapping/url-pattern", new CallParamMultiRule(0));



        digester.addCallMethod(prefix + "web-app/welcome-file-list/welcome-file",
                "addWelcomeFile", 0);

    }

    /**
     * Reset counter used for validating the web.xml file.
     */
    public void recycle(){
        jspConfig.isJspConfigSet = false;
        sessionConfig.isSessionConfigSet = false;
        loginConfig.isLoginConfigSet = false;
    }
}


// ----------------------------------------------------------- Private Classes


/**
 * Rule to check that the <code>login-config</code> is occuring 
 * only 1 time within the web.xml
 */
final class SetLoginConfig extends Rule {
    protected boolean isLoginConfigSet = false;
    public SetLoginConfig() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        if (isLoginConfigSet){
            throw new IllegalArgumentException(
            "<login-config> element is limited to 1 occurrence");
        }
        isLoginConfigSet = true;
    }

}


/**
 * Rule to check that the <code>jsp-config</code> is occuring 
 * only 1 time within the web.xml
 */
final class SetJspConfig extends Rule {
    protected boolean isJspConfigSet = false;
    public SetJspConfig() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        if (isJspConfigSet){
            throw new IllegalArgumentException(
            "<jsp-config> element is limited to 1 occurrence");
        }
        isJspConfigSet = true;
    }

}


/**
 * Rule to check that the <code>session-config</code> is occuring 
 * only 1 time within the web.xml
 */
final class SetSessionConfig extends Rule {
    protected boolean isSessionConfigSet = false;
    public SetSessionConfig() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        if (isSessionConfigSet){
            throw new IllegalArgumentException(
            "<session-config> element is limited to 1 occurrence");
        }
        isSessionConfigSet = true;
    }

}


/**
 * Class that calls <code>setDistributable(true)</code> for the top object
 * on the stack, which must be a <code>org.apache.catalina.Context</code>.
 */

final class SetDistributableRule extends Rule {

    public SetDistributableRule() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        Context context = (Context) digester.peek();
        context.setDistributable(true);
        if (digester.getLogger().isDebugEnabled()) {
            digester.getLogger().debug
               (context.getClass().getName() + ".setDistributable( true)");
        }
    }

}


/**
 * Class that calls a property setter for the top object on the stack,
 * passing the public ID of the entity we are currently processing.
 */

final class SetPublicIdRule extends Rule {

    public SetPublicIdRule(String method) {
        this.method = method;
    }

    private String method = null;

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {

        Object top = digester.peek();
        Class paramClasses[] = new Class[1];
        paramClasses[0] = "String".getClass();
        String paramValues[] = new String[1];
        paramValues[0] = digester.getPublicId();

        Method m = null;
        try {
            m = top.getClass().getMethod(method, paramClasses);
        } catch (NoSuchMethodException e) {
            digester.getLogger().error("Can't find method " + method + " in "
                                       + top + " CLASS " + top.getClass());
            return;
        }

        m.invoke(top, (Object [])paramValues);
        if (digester.getLogger().isDebugEnabled())
            digester.getLogger().debug("" + top.getClass().getName() + "." 
                                       + method + "(" + paramValues[0] + ")");

    }

}


/**
 * A Rule that calls the factory method on the specified Context to
 * create the object that is to be added to the stack.
 */

final class WrapperCreateRule extends Rule {

    public WrapperCreateRule() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        Context context =
            (Context) digester.peek(digester.getCount() - 1);
        Wrapper wrapper = context.createWrapper();
        digester.push(wrapper);
        if (digester.getLogger().isDebugEnabled())
            digester.getLogger().debug("new " + wrapper.getClass().getName());
    }

    public void end(String namespace, String name)
        throws Exception {
        Wrapper wrapper = (Wrapper) digester.pop();
        if (digester.getLogger().isDebugEnabled())
            digester.getLogger().debug("pop " + wrapper.getClass().getName());
    }

}


/**
 * A Rule that can be used to call multiple times a method as many times as needed
 * (used for addServletMapping).
 */
final class CallParamMultiRule extends CallParamRule {

    public CallParamMultiRule(int paramIndex) {
        super(paramIndex);
    }

    public void end(String namespace, String name) {
        if (bodyTextStack != null && !bodyTextStack.empty()) {
            // what we do now is push one parameter onto the top set of parameters
            Object parameters[] = (Object[]) digester.peekParams();
            ArrayList params = (ArrayList) parameters[paramIndex];
            if (params == null) {
                params = new ArrayList();
                parameters[paramIndex] = params;
            }
            params.add(bodyTextStack.pop());
        }
    }

}


/**
 * A Rule that can be used to call multiple times a method as many times as needed
 * (used for addServletMapping).
 */
final class CallMethodMultiRule extends CallMethodRule {

    protected int multiParamIndex = 0;
    
    public CallMethodMultiRule(String methodName, int paramCount, int multiParamIndex) {
        super(methodName, paramCount);
        this.multiParamIndex = multiParamIndex;
    }

    public void end() throws Exception {

        // Retrieve or construct the parameter values array
        Object parameters[] = null;
        if (paramCount > 0) {
            parameters = (Object[]) digester.popParams();
        } else {
            super.end();
        }
        
        ArrayList multiParams = (ArrayList) parameters[multiParamIndex];
        
        // Construct the parameter values array we will need
        // We only do the conversion if the param value is a String and
        // the specified paramType is not String. 
        Object paramValues[] = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            if (i != multiParamIndex) {
                // convert nulls and convert stringy parameters 
                // for non-stringy param types
                if(parameters[i] == null || (parameters[i] instanceof String 
                        && !String.class.isAssignableFrom(paramTypes[i]))) {
                    paramValues[i] =
                        IntrospectionUtils.convert((String) parameters[i], paramTypes[i]);
                } else {
                    paramValues[i] = parameters[i];
                }
            }
        }

        // Determine the target object for the method call
        Object target;
        if (targetOffset >= 0) {
            target = digester.peek(targetOffset);
        } else {
            target = digester.peek(digester.getCount() + targetOffset);
        }

        if (target == null) {
            StringBuffer sb = new StringBuffer();
            sb.append("[CallMethodRule]{");
            sb.append("");
            sb.append("} Call target is null (");
            sb.append("targetOffset=");
            sb.append(targetOffset);
            sb.append(",stackdepth=");
            sb.append(digester.getCount());
            sb.append(")");
            throw new org.xml.sax.SAXException(sb.toString());
        }
        
        if (multiParams == null) {
            paramValues[multiParamIndex] = null;
            Object result = IntrospectionUtils.callMethodN(target, methodName,
                    paramValues, paramTypes);   
            return;
        }
        
        for (int j = 0; j < multiParams.size(); j++) {
            Object param = multiParams.get(j);
            if(param == null || (param instanceof String 
                    && !String.class.isAssignableFrom(paramTypes[multiParamIndex]))) {
                paramValues[multiParamIndex] =
                    IntrospectionUtils.convert((String) param, paramTypes[multiParamIndex]);
            } else {
                paramValues[multiParamIndex] = param;
            }
            Object result = IntrospectionUtils.callMethodN(target, methodName,
                    paramValues, paramTypes);   
        }
        
    }

}



/**
 * A Rule that check if the annotations have to be loaded.
 * 
 */

final class IgnoreAnnotationsRule extends Rule {

    public IgnoreAnnotationsRule() {
    }

    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
        Context context = (Context) digester.peek(digester.getCount() - 1);
        String value = attributes.getValue("metadata-complete");
        if ("true".equals(value)) {
            context.setIgnoreAnnotations(true);
        } else if ("false".equals(value)) {
            context.setIgnoreAnnotations(false);
        }
        if (digester.getLogger().isDebugEnabled()) {
            digester.getLogger().debug
                (context.getClass().getName() + ".setIgnoreAnnotations( " +
                    context.getIgnoreAnnotations() + ")");
        }
    }

}

/**
 * A Rule that sets soap headers on the ContextHandler.
 * 暂不支持soap
 */
//final class SoapHeaderRule extends Rule {
//
//    public SoapHeaderRule() {
//    }
//
//    public void body(String text)
//        throws Exception {
//        String namespaceuri = null;
//        String localpart = text;
//        int colon = text.indexOf(':');
//        if (colon >= 0) {
//            String prefix = text.substring(0,colon);
//            namespaceuri = digester.findNamespaceURI(prefix);
//            localpart = text.substring(colon+1);
//        }
//        ContextHandler contextHandler = (ContextHandler)digester.peek();
//        contextHandler.addSoapHeaders(localpart,namespaceuri);
//    }
//}

/**
 * A Rule that sets service qname on the ContextService.
 * 
 */
//final class ServiceQnameRule extends Rule {
//
//    public ServiceQnameRule() {
//    }
//
//    public void body(String text)
//        throws Exception {
//        String namespaceuri = null;
//        String localpart = text;
//        int colon = text.indexOf(':');
//        if (colon >= 0) {
//            String prefix = text.substring(0,colon);
//            namespaceuri = digester.findNamespaceURI(prefix);
//            localpart = text.substring(colon+1);
//        }
//        ContextService contextService = (ContextService)digester.peek();
//        contextService.setServiceqnameLocalpart(localpart);
//        contextService.setServiceqnameNamespaceURI(namespaceuri);
//    }
//}

/**
 * A Rule that adds a security-role-ref to a servlet, allowing for the fact that
 * role-link is an optional element.
 */
//final class SecurityRoleRefCreateRule extends Rule {
//
//    public SecurityRoleRefCreateRule() {
//    }
//
//    public void begin(String namespace, String name, Attributes attributes)
//            throws Exception {
//        SecurityRoleRef securityRoleRef = new SecurityRoleRef();
//        digester.push(securityRoleRef);
//        if (digester.getLogger().isDebugEnabled())
//            digester.getLogger().debug("new SecurityRoleRef");
//    }
//
//    public void end(String namespace, String name)
//            throws Exception {
//        SecurityRoleRef securityRoleRef = (SecurityRoleRef) digester.pop();
//        Wrapper wrapper = (Wrapper) digester.peek();
//
//        wrapper.addSecurityReference(securityRoleRef.getName(),
//                securityRoleRef.getLink());
//
//        if (digester.getLogger().isDebugEnabled())
//            digester.getLogger().debug("pop SecurityRoleRef");
//    }
//}
