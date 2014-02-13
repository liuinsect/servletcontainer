package com.liusoft.sc.core;

import com.liusoft.sc.InstanceListener;
import com.liusoft.sc.Wrapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

/**
 * 自己实现的简易的servlet包装类，
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-2-13
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
public class StandardWrapper extends ContainerBase implements Wrapper{

    private Servlet instance;

    /**
     * The fully qualified servlet class name for this servlet.
     */
    protected String servletClass = null;

    @Override
    public long getAvailable() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAvailable(long available) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getJspFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setJspFile(String jspFile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getLoadOnStartup() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLoadOnStartup(int value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getRunAs() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setRunAs(String runAs) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getServletClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setServletClass(String servletClass) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.servletClass = servletClass;
    }

    @Override
    public String[] getServletMethods() throws ServletException {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isUnavailable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addInitParameter(String name, String value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addInstanceListener(InstanceListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addMapping(String mapping) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addSecurityReference(String name, String link) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Servlet allocate() throws ServletException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deallocate(Servlet servlet) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Servlet getServlet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String findInitParameter(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] findInitParameters() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] findMappings() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String findSecurityReference(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String[] findSecurityReferences() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void incrementErrorCount() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void load() throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.

        instance = loadServlet();

    }

    /**
     * 根据servletClass 实例化 servlet 赋给instance
     * @return
     * @throws ServletException
     */
    public synchronized Servlet loadServlet() throws ServletException{

        //TODO 实现load Servlet的过程，注意loader的使用
        return null;
    }

    @Override
    public void removeInitParameter(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeInstanceListener(InstanceListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeMapping(String mapping) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeSecurityReference(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void unavailable(UnavailableException unavailable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void unload() throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
