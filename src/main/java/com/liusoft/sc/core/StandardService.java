/**  
* @Package com.liusoft.core 
* @author A18ccms A18ccms_gmail_com  
* @date 2013-9-23 下午04:09:35 
* @version V1.0  
*/ 
package com.liusoft.sc.core;

import com.liusoft.sc.*;
import org.apache.log4j.Logger;

import com.liusoft.sc.connector.Connector;
import com.liusoft.sc.startup.Initialize;

/**  
 * 一个service包含一个connector和container
 * @Package com.liusoft.core 
 * @author liukunyang
 * @date 2013-9-23 下午04:09:35 
 * @version V1.0  
 */
public class StandardService implements Service,Initialize,Lifecycle{
		
	private Logger log = Logger.getLogger( StandardService.class );
	/**
	 * 连接器，暂时至于HTTP连接器
	 */
	private Connector[] connectors;
	
	/**
	 * 容器
	 */
	private Container container;
	
	private Server server;
	
	
	/**
     * Has this component been started?
     */
    private boolean started = false;
	
	/**
	 * connector需要读取监听的端口号，
	 * 所以，应该先初始化container然后初始化connector
	 */
	@Override
	public Object initialize() {
		
		container = new StandardEngine();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Service#getServer()
	 */
	@Override
	public Server getServer() {
		return this.server;
	}

	/* (non-Javadoc)
	 * @see com.liusoft.sc.Service#setServer(com.liusoft.sc.Server)
	 */
	@Override
	public void setServer(Server server) {
		this.server = server;
	}


	public void addConnector(Connector connector) {
		if( connectors == null  ){
			this.connectors = new Connector[1];
            this.connectors[0] = connector;
		}else{
            //先扩容
            Connector results[] = new Connector[connectors.length + 1];
            System.arraycopy(connectors, 0, results, 0 , connectors.length);

            results[connectors.length] = connector;
            this.connectors = results;
        }

	   if (started && (connector instanceof Lifecycle)) {
           try {
               ((Lifecycle) connector).start();
           } catch (LifecycleException e) {
               log.error( "standardService.connector.startFailed", e);
           }
       }
	   
	}

	public Container getContainer() {
		return container;
	}

    /**
     * 给servic设置容器
     * @param container
     */
	public void setContainer(Container container) {
		this.container = container;
        if (started && (container instanceof Lifecycle)) {
            try {
                ((Lifecycle) container).start();
            } catch (LifecycleException e) {
                log.error( "standardService.connector.startFailed", e);
            }
        }

    }


    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start() throws LifecycleException {
       if(started){
           if (log.isInfoEnabled()) {
               log.info("standardService.start.started");
           }
           return;
       }

       started = true;
       if( this.container != null ){
           if (container instanceof Lifecycle) {
               ((Lifecycle) container).start();
           }
       }

        if( this.connectors != null ){
            for (int i = 0; i < connectors.length; i++) {
                ((Lifecycle)connectors[i]).start();
            }
        }

    }

    @Override
    public void stop() throws LifecycleException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
