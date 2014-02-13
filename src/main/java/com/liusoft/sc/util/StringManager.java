package com.liusoft.sc.util;

/**
 *
 * 代替catalina中国际化的实现StringManager。
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-2-13
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class StringManager {

    public synchronized static StringManager getManager(String packageName) {
        return  new StringManager();
    }

    public String getString(String key, Object arg) {
        return key +"{"+arg+"}";
    }
}
