/**
 * 
 */
package com.liusoft.sc;

/**
 * 
 * 生命周期  事件监听接口
 * @author liukunyang
 *
 */
public interface LifecycleListener {


    /**
     * Acknowledge the occurrence of the specified event.
     *
     * @param event LifecycleEvent that has occurred
     */
    public void lifecycleEvent(LifecycleEvent event);


}
