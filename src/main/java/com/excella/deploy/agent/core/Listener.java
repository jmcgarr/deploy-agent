/*
 * Copyright 2010
 * Excella Consulting Inc.
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of the Excella Consulting Inc.
 * Use is subject to license terms.
 *
 * Created On: Nov 22, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

/**
 * Defines the interface for an Agent Listener. Listeners are responsible for
 * handling requests and executing the appropriate server command based on the arguments. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public interface Listener
{    
    /**
     * Executed when the Listener is started up.
     *
     * @throws Exception
     */
    public abstract void init () throws Exception;

    /**
     * Executed when the listener is shutdown by the Agent.
     *
     * @throws Exception if there was a problems shutting down the listener.
     */
    public abstract void shutdown () throws Exception;

}
