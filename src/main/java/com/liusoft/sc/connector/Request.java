package com.liusoft.sc.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import com.liusoft.sc.constant.CharConstant;


/**
 * 并不是HTTP servlet request 
 * 充当coyote 框架中的request
 * 实现从socket流到Response对象的转换
 * @author liukunyang
 *
 */
public class Request implements HttpServletRequest {
	
	//TODO 考虑这里处理cookie是否合适
	private Cookie[] cookies;
	
	public Cookie[] getCookies() {
		return cookies;
	}
	
	public void setCookie(String requestCookie){
		this.cookies = parseCookie(requestCookie);
	}
	
	private Cookie[] parseCookie(String requestCookie){
		String[] cookieArray = StringUtils.split(requestCookie, CharConstant.SEMICOLONS);
		Cookie[] cookies = new Cookie[cookieArray.length];
		
		Cookie cookie = null;
		String cookieName = null;
		String cookieValue = null;
		int i = 0;
		for( String tmp : cookieArray ){
			if( StringUtils.isNotBlank(tmp) ){
				
				cookieName = StringUtils.substringBefore(tmp, CharConstant.EQUEAL);
				cookieValue = StringUtils.substringAfter(tmp, CharConstant.EQUEAL);
				cookie = new Cookie(cookieName,cookieValue);
				cookies[i] = cookie;
				i++;
			}
		}
		return cookies;
	}
	
	public String getAuthType() {
		return null;
	}

	public String getContextPath() {
		return null;
	}


	public long getDateHeader(String name) {
		return 0;
	}

	public String getHeader(String name) {
		return null;
	}

	public Enumeration getHeaderNames() {
		return null;
	}

	public Enumeration getHeaders(String name) {
		return null;
	}

	public int getIntHeader(String name) {
		return 0;
	}

	public String getMethod() {
		return null;
	}

	public String getPathInfo() {
		return null;
	}

	public String getPathTranslated() {
		return null;
	}

	public String getQueryString() {
		return null;
	}

	public String getRemoteUser() {
		return null;
	}

	public String getRequestURI() {
		return null;
	}

	public StringBuffer getRequestURL() {
		return null;
	}

	public String getRequestedSessionId() {
		return null;
	}

	public String getServletPath() {
		return null;
	}

	public HttpSession getSession() {
		return null;
	}

	public HttpSession getSession(boolean create) {
		return null;
	}

	public Principal getUserPrincipal() {
		return null;
	}

	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	public boolean isRequestedSessionIdValid() {
		return false;
	}

	public boolean isUserInRole(String role) {
		return false;
	}

	public Object getAttribute(String name) {
		return null;
	}

	public Enumeration getAttributeNames() {
		return null;
	}

	public String getCharacterEncoding() {
		return null;
	}

	public int getContentLength() {
		return 0;
	}

	public String getContentType() {
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	public String getLocalAddr() {
		return null;
	}

	public String getLocalName() {
		return null;
	}

	public int getLocalPort() {
		return 0;
	}

	public Locale getLocale() {
		return null;
	}

	public Enumeration getLocales() {
		return null;
	}

	public String getParameter(String name) {
		return null;
	}

	public Map getParameterMap() {
		return null;
	}

	public Enumeration getParameterNames() {
		return null;
	}

	public String[] getParameterValues(String name) {
		return null;
	}

	public String getProtocol() {
		return null;
	}

	public BufferedReader getReader() throws IOException {
		return null;
	}

	public String getRealPath(String path) {
		return null;
	}

	public String getRemoteAddr() {
		return null;
	}

	public String getRemoteHost() {
		return null;
	}

	public int getRemotePort() {
		return 0;
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		return null;
	}

	public String getScheme() {
		return null;
	}

	public String getServerName() {
		return null;
	}

	public int getServerPort() {
		return 0;
	}

	public boolean isSecure() {
		return false;
	}

	public void removeAttribute(String name) {

	}

	public void setAttribute(String name, Object o) {

	}

	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

	}

}
