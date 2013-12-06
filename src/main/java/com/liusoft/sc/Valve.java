/**
 * 
 */
package com.liusoft.sc;

/**
 * @author liukunyang
 *
 */

import java.io.IOException;


import com.liusoft.sc.connector.Request;
import com.liusoft.sc.connector.Response;



/**
 * 阀
 * @author liukunyang
 *
 */
public interface Valve {


    //-------------------------------------------------------------- Properties


    /**
     * Return descriptive information about this Valve implementation.
     */
    public String getInfo();


    /**
     * Return the next Valve in the pipeline containing this Valve, if any.
     */
    public Valve getNext();


    /**
     * Set the next Valve in the pipeline containing this Valve.
     *
     * @param valve The new next valve, or <code>null</code> if none
     */
    public void setNext(Valve valve);


    //---------------------------------------------------------- Public Methods


    /**
     * Execute a periodic task, such as reloading, etc. This method will be
     * invoked inside the classloading context of this container. Unexpected
     * throwables will be caught and logged.
     */
    public void backgroundProcess();

    /**
     * 类型调用链方式的调用
     * @param request
     * @param response
     * @throws IOException
     */
    public void invoke(Request request, Response response)
        throws IOException;

//
//    
//    public void event(Request request, Response response, CometEvent event)
//        throws IOException, ServletException;


}

