package com.liusoft.sc.core;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 13-12-6
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class StandardContext extends ContainerBase  {

    private boolean reloadable = false;

    private String docBase = null;

    private String workDir = null;


    public String getDocBase() {
        return docBase;
    }

    public void setDocBase(String docBase) {
        this.docBase = docBase;
    }

    public boolean isReloadable() {
        return reloadable;
    }

    public void setReloadable(boolean reloadable) {
        this.reloadable = reloadable;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }
}
