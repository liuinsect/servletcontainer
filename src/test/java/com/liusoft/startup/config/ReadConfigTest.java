/**
 * 
 */
package com.liusoft.startup.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @author liukunyang
 *
 */
public class ReadConfigTest {

	static class Simple{
		
		private Server server;

		public Server getServer() {
			return server;
		}

		public void setServer(Server server) {
			this.server = server;
		}
		
	}
	
	static class Server{
		
		private List<Listener> listener;

		public List<Listener> getListener() {
			return listener;
		}

		public void setListener(List<Listener> listener) {
			this.listener = listener;
		}
		
		
	}
	
	static class Listener{
		
		private String  className;
		private String  SSLEngine;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getSSLEngine() {
			return SSLEngine;
		}
		public void setSSLEngine(String sSLEngine) {
			SSLEngine = sSLEngine;
		}
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		 String tfilePath = ReadConfigTest.class.getResource("/com/liusoft/startup/config/server.xml").getPath();  
        System.out.println("tfilePath = \t"+tfilePath);  
        File file = new File(tfilePath);
        
		 SAXBuilder sb = new SAXBuilder();
	        Document doc = sb.build( new FileInputStream(file) ); //构造文档对象
	        Element root = doc.getRootElement(); //获取根元素
	        List listenerList = root.getChildren("Listener");//取名字为disk的所有元素 
	        for (int i = 0; i < listenerList.size(); i++) {
	            Element element = (Element) listenerList.get(i);
	            System.out.println( element.getName() );  
//	            String name = element.getAttributeValue("name");
//	            String capacity = element.getChildText("capacity");//取disk子元素capacity的内容 
//	            String directories = element.getChildText("directories");
//	            String files = element.getChildText("files");
//	            System.out.println("磁盘信息:");
//	            System.out.println("分区盘符:" + name);
//	            System.out.println("分区容量:" + capacity);
//	            System.out.println("目录数:" + directories);
//	            System.out.println("文件数:" + files);
//	            System.out.println("-----------------------------------");

	        }

	}

}
