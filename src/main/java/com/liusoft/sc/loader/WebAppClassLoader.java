/**
 * 
 */
package com.liusoft.sc.loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 * sc 自己的classLoadeer 每个应用自己一个classLoader
 * 实现容器关闭时，类卸载等问题。
 * @author liukunyang
 *
 */
public class WebAppClassLoader  extends URLClassLoader {

	/**
	 * @param urls
	 */
	public WebAppClassLoader(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}

}
