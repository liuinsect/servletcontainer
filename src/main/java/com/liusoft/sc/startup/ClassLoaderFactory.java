/**  
* @Package com.liusoft.sc.startup 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-24 上午11:46:56 
* @version V1.0  
*/ 
package com.liusoft.sc.startup;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.liusoft.sc.loader.StandardClassLoader;

/** 
 * classloader 工厂类 
 * @Package com.liusoft.sc.startup 
 * @author liukunyang
 * @date 2013-9-24 上午11:46:56 
 * @version V1.0  
 */
public final class ClassLoaderFactory {
	
	private static Logger log = Logger.getLogger( ClassLoaderFactory.class );
	
    protected static final Integer IS_DIR = new Integer(0);
    protected static final Integer IS_JAR = new Integer(1);
    protected static final Integer IS_GLOB = new Integer(2);
    protected static final Integer IS_URL = new Integer(3);
	
    /**
     * 遍历Location 根据type转化成URL 
     * 用于实例化classloader
	 * @author liukunyang
	 * @date 2013-9-24	
	 * @param locations
	 * @param types
	 * @param parent
	 * @return
     * @throws Exception 
	 */
	public static ClassLoader createClassLoader(String[] locations,	Integer[] types, ClassLoader parent) throws Exception {
		
		 Set<URL> set = new LinkedHashSet<URL>();
		 
		 //遍历Location
		 for( int i=0 ; i < locations.length ; i++ ){
			 String location = locations[i];
			 
			 if( types[i] == IS_URL ){
				 URL url = new URL( location );
				 set.add(url);
				 continue;
			 }
			 
			 if( types[i] == IS_DIR || types[i] == IS_JAR ){
				 
				 File file = new File( location );
//				 file = file.getCanonicalFile();做什么用的？
				 URL url = file.toURI().toURL();
				 set.add(url);
				 continue;
			 }
			 
			 //is_global
			 File directory = new File( location );
			 
			 String[] filenames = directory.list();
			 
			 for (int j = 0; j < filenames.length; j++) {
                 String filename = filenames[j].toLowerCase();
                 if (!filename.endsWith(".jar"))
                     continue;
                 File file = new File(directory, filenames[j]);
//                 file = file.getCanonicalFile();
                 
                 if (log.isDebugEnabled()){
                	 log.debug("    Including glob jar file " + file.getAbsolutePath());
                 }
                    
                 URL url = file.toURI().toURL();
                 set.add(url);
             }
			 
		 }  
		 
	    URL[] array = set.toArray(new URL[set.size()]);
        if (log.isDebugEnabled())
            for (int i = 0; i < array.length; i++) {
                log.debug("  location " + i + " is " + array[i]);
            }
        StandardClassLoader classLoader = null;
        if (parent == null)
            classLoader = new StandardClassLoader(array);
        else
            classLoader = new StandardClassLoader(array, parent);
        return (classLoader);
	}

	
}
