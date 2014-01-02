/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午01:16:33 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**  
 * /**
 * Standard implementation of <code>ServletContext</code> that represents
 * a web application's execution environment.  An instance of this class is
 * associated with each instance of <code>StandardContext</code>.
 *
 * @author Craig R. McClanahan
 * @author Remy Maucherat
 * @version $Id: ApplicationContext.java 1022315 2010-10-13 22:41:45Z kkolinko $
 *
 * 对应用中暴露的是 ApplicationContextFacade 包装后的对象
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午01:16:33 
 * @version V1.0  
 */
public class ApplicationContext implements ServletContext {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getAttributeNames()
	 */
	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getContext(java.lang.String)
	 */
	@Override
	public ServletContext getContext(String uripath) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getContextPath()
	 */
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getInitParameter(java.lang.String)
	 */
	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getInitParameterNames()
	 */
	@Override
	public Enumeration getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getMajorVersion()
	 */
	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getMimeType(java.lang.String)
	 */
	@Override
	public String getMimeType(String file) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getMinorVersion()
	 */
	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getNamedDispatcher(java.lang.String)
	 */
	@Override
	public RequestDispatcher getNamedDispatcher(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getRealPath(java.lang.String)
	 */
	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getRequestDispatcher(java.lang.String)
	 */
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getResource(java.lang.String)
	 */
	@Override
	public URL getResource(String path) throws MalformedURLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getResourceAsStream(java.lang.String)
	 */
	@Override
	public InputStream getResourceAsStream(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getResourcePaths(java.lang.String)
	 */
	@Override
	public Set getResourcePaths(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServerInfo()
	 */
	@Override
	public String getServerInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServlet(java.lang.String)
	 */
	@Override
	public Servlet getServlet(String name) throws ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServletContextName()
	 */
	@Override
	public String getServletContextName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServletNames()
	 */
	@Override
	public Enumeration getServletNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#getServlets()
	 */
	@Override
	public Enumeration getServlets() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#log(java.lang.String)
	 */
	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#log(java.lang.Exception, java.lang.String)
	 */
	@Override
	public void log(Exception exception, String msg) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#log(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void log(String message, Throwable throwable) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object object) {
		// TODO Auto-generated method stub

	}

}
