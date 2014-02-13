package com.liusoft.sc;

import com.liusoft.sc.scassist.XMLValidation;


/**
 * A <b>Context</b> is a Container that represents a servlet context, and
 * therefore an individual web application, in the Catalina servlet engine.
 * It is therefore useful in almost every deployment of Catalina (even if a
 * Connector attached to a web server (such as Apache) uses the web server's
 * facilities to identify the appropriate Wrapper to handle this request.
 * It also provides a convenient mechanism to use Interceptors that see
 * every request processed by this particular web application.
 * <p>
 * The parent Container attached to a Context is generally a Host, but may
 * be some other implementation, or may be omitted if it is not necessary.
 * <p>
 * The child containers attached to a Context are generally implementations
 * of Wrapper (representing individual servlet definitions).
 * <p>
 *
 * @author Craig R. McClanahan
 * @version $Id: Context.java 1137629 2011-06-20 13:38:23Z markt $
 */
/**
 *
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-2
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
public interface Context extends Container,XMLValidation {

    /**
     *
     * Return the override flag for this web application.
     */
    boolean getOverride();

    /**
     * Set the distributable flag for this web application.
     *
     * @param distributable The new distributable flag
     */
    public void setDistributable(boolean distributable);

    /**
     * 创建servlet Wrapper
     * @return
     */
    public Wrapper createWrapper();


    /**
     * Set the boolean on the annotations parsing for this web
     * application.
     *
     * @param ignoreAnnotations The boolean on the annotations parsing
     */
    public void setIgnoreAnnotations(boolean ignoreAnnotations);



    /**
     * Return the boolean on the annotations parsing.
     */
    public boolean getIgnoreAnnotations();



}
