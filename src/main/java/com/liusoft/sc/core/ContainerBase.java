/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午01:43:27 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;

import com.liusoft.sc.*;
import com.liusoft.sc.connector.Request;
import com.liusoft.sc.connector.Response;
import com.liusoft.sc.startup.Constants;
import com.liusoft.sc.util.LifecycleSupport;
import com.liusoft.sc.util.StringManager;

/**  
 * 容器基础类
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午01:43:27 
 * @version V1.0  
 */
public abstract class ContainerBase implements Container,Lifecycle {
	
    /**
     * The child Containers belonging to this Container, keyed by name.
     */
    protected HashMap children = new HashMap();
    
    /**
     * The human-readable name of this Container.
     */
    protected String name = null;
    
    /**
     * Loader接口实例
     */
    protected Loader loader;
    
    /**
     * 父容器
     * The parent Container to which this Container is a child.
     */
    protected Container parent = null;

    /**
     * The lifecycle event support for this component.
     */
    protected LifecycleSupport lifecycle = new LifecycleSupport(this);

    /**
     * The string manager for this package.
     */
    protected static StringManager sm =
            StringManager.getManager(Constants.Package);


	@Override
	public void addChild(Container child) {
		
		synchronized (children) {
			if( children.get( child.getName() ) != null ){
				throw new IllegalArgumentException("addChild: child name "+child.getName()+ "is not unique");
			}	
			child.setParent(this);
			children.put( child.getName() , child );
			
			//TODO 是否在这里启动子容器
			
		}
		
	}

	/**
	 * WebApploader 在用这个功能，但是不知道有什么用处
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		

	}

	
	@Override
	public void backgroundProcess() {
		// TODO Auto-generated method stub

	}

	@Override
	public Container findChild(String name) {
		return (Container) children.get( name );
	}

	@Override
	public Container[] findChildren() {
		   synchronized (children) {
	            Container results[] = new Container[children.size()];
	            return ((Container[]) children.values().toArray(results));
	        }
	}

	@Override
	public int getBackgroundProcessorDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getInfo()
	 */
	@Override
	public String getInfo() {
	    return this.getClass().getName();
	}


	@Override
	public Loader getLoader() {
		// TODO Auto-generated method stub
		if( this.loader != null ){
			return this.loader;
		}
		
		if( parent != null ){
			return parent.getLoader();
		}
	
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getMappingObject()
	 */
	@Override
	public Object getMappingObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getParent()
	 */
	@Override
	public Container getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getParentClassLoader()
	 */
	@Override
	public ClassLoader getParentClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getPipeline()
	 */
	@Override
	public Pipeline getPipeline() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#getResources()
	 */
	@Override
	public DirContext getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#invoke(com.liusoft.sc.connector.Request, com.liusoft.sc.connector.Response)
	 */
	@Override
	public void invoke(Request request, Response response) throws IOException,
			ServletException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#logAccess(com.liusoft.sc.connector.Request, com.liusoft.sc.connector.Response, long, boolean)
	 */
	@Override
	public void logAccess(Request request, Response response, long time,
			boolean useDefault) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#removeChild(com.liusoft.sc.Container)
	 */
	@Override
	public void removeChild(Container child) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setBackgroundProcessorDelay(int)
	 */
	@Override
	public void setBackgroundProcessorDelay(int delay) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setLoader(com.liusoft.sc.Loader)
	 */
	@Override
	public void setLoader(Loader loader) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setParent(com.liusoft.sc.Container)
	 */
	@Override
	public void setParent(Container container) {
		// TODO Auto-generated method stub
        this.parent = container;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setParentClassLoader(java.lang.ClassLoader)
	 */
	@Override
	public void setParentClassLoader(ClassLoader parent) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Container#setResources(javax.naming.directory.DirContext)
	 */
	@Override
	public void setResources(DirContext resources) {
		// TODO Auto-generated method stub

	}

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.lifecycle.addLifecycleListener(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * ContainBase启动
     * 启动Manager
     * 启动子容器
     *
     * 简单起见，只启动子容器
     * @throws LifecycleException
     */
    @Override
    public void start() throws LifecycleException {

        Container[] children = this.findChildren();
        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof Lifecycle){
                ((Lifecycle)children[i]).start();
            }
        }

    }

    @Override
    public void stop() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
