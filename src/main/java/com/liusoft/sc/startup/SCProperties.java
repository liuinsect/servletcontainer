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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Utility class to read the bootstrap Catalina configuration.
 *
 * @author Remy Maucherat
 * @version $Id: CatalinaProperties.java 939353 2010-04-29 15:50:43Z kkolinko $
 */

public class SCProperties {


    // ------------------------------------------------------- Static Variables

    private static Logger log = Logger.getLogger( SCProperties.class );

    private static Properties properties = null;


    static {

        loadProperties();

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return specified property value.
     */
    public static String getProperty(String name) {
	
        return properties.getProperty(name);

    }


    /**
     * Return specified property value.
     */
    public static String getProperty(String name, String defaultValue) {

        return properties.getProperty(name, defaultValue);

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Load properties.
     */
    private static void loadProperties() {

        InputStream is = null;
        Throwable error = null;

        try {
            String configUrl = getConfigUrl();
            if (configUrl != null) {
                is = (new URL(configUrl)).openStream();
            }
        } catch (Throwable t) {
            // Ignore
        }

        if (is == null) {
            try {
                File home = new File(getSCBase());
                File conf = new File(home, "conf");
                File properties = new File(conf, "sc.properties");
                is = new FileInputStream(properties);
            } catch (Throwable t) {
                // Ignore
            }
        }

        if (is == null) {
            try {
                is = SCProperties.class.getResourceAsStream
                    ("/com/liusoft/sc/startup/sc.properties");
            } catch (Throwable t) {
                // Ignore
            }
        }

        if (is != null) {
            try {
                properties = new Properties();
                properties.load(is);
                is.close();
            } catch (Throwable t) {
                error = t;
            }
        }

        if ((is == null) || (error != null)) {
            // Do something
            log.warn("Failed to load catalina.properties", error);
            // That's fine - we have reasonable defaults.
            properties=new Properties();
        }

        // Register the properties as system properties
        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = properties.getProperty(name);
            if (value != null) {
                System.setProperty(name, value);
            }
        }

    }


    /**
     * Get the value of the catalina.home environment variable.
     */
    private static String getSCHome() {
        return System.getProperty("sc.home",
                                  System.getProperty("user.dir"));
    }
    
    
    /**
     * Get the value of the catalina.base environment variable.
     */
    private static String getSCBase() {
        return System.getProperty("sc.base", getSCHome());
    }


    /**
     * Get the value of the configuration URL.
     */
    private static String getConfigUrl() {
        return System.getProperty("sc.config");
    }


}
