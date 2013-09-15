package com.liusoft.sc.http;

import java.util.Map;

import com.liusoft.sc.constant.HttpHeaderConstant;
import com.liusoft.sc.exception.ExceptionFactory;

/**
 * 请求头对应实体
* @Package com.liusoft.http 
* @author liukunyang
* @date 2013-9-2 下午04:20:21 
* @version V1.0
 */
public class RequestHeader {
	
	private String method;
	
	private String requestURI;
	
	private String protocol;
	
	private String protocolVersion;
	
	private String accept;
	
	private String acceptEncoding;
	
	private String acceptLanguage;
	
	private String connection;
	
	private String cookie;
	
	private String host;
	
	private String referer;
	
	private String userAgent;
	
	public void init(Map<String,String> map){
		if( map == null || map.isEmpty() ){
			throw ExceptionFactory.throwRunTimeException("map is null or empty");
		}
		this.setMethod( map.get( HttpHeaderConstant.METHOD ) );
		this.setRequestURI( map.get( HttpHeaderConstant.REQUEST_URI ) );
		this.setProtocol( map.get( HttpHeaderConstant.PROTOCOL ) );
		this.setProtocolVersion( map.get( HttpHeaderConstant.PROTOCOL_VERSION ) );
		this.setAccept( map.get( HttpHeaderConstant.ACCEPT ) );
		this.setAcceptEncoding( map.get( HttpHeaderConstant.ACCEPT_ENCODING ) );
		this.setAcceptLanguage( map.get( HttpHeaderConstant.ACCEPT_LANGUAGE ) );
		this.setConnection( map.get( HttpHeaderConstant.CONNECTION ) );
		this.setCookie( map.get( HttpHeaderConstant.COOKIE ) );
		this.setHost( map.get( HttpHeaderConstant.HOST ) );
		this.setReferer( map.get( HttpHeaderConstant.REFERER ) );
		this.setUserAgent( map.get( HttpHeaderConstant.USER_AGENT ) );
	}
	
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAcceptEncoding() {
		return acceptEncoding;
	}

	public void setAcceptEncoding(String acceptEncoding) {
		this.acceptEncoding = acceptEncoding;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	
}
