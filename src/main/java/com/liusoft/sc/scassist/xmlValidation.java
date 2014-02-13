package com.liusoft.sc.scassist;

/**
 * xml 验证方式接口 用于degester映射xml文件
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-2
 * Time: 下午6:14
 * To change this template use File | Settings | File Templates.
 */
public interface XMLValidation {

    /**
     * Get the server.xml <context> attribute's xmlValidation.
     * @return true if validation is enabled.
     *
     */
    public boolean getXmlValidation();

    /**
     * Get the server.xml <context> attribute's xmlNamespaceAware.
     * @return true if namespace awarenes is enabled.
     *
     */
    public boolean getXmlNamespaceAware();


}
