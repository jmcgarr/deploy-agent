/*
 * Copyright 2010
 * Excella Consulting Inc.
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of the Excella Consulting Inc.
 * Use is subject to license terms.
 *
 * Created On: Nov 17, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class Agent
{  
    /** Logger. */
    private static Log log = LogFactory.getLog(Agent.class);
    
    private List<Listener> listeners = null;
    
    /**
     * Constructor.  
     */
    public Agent ()
    {
        log.info("agent created...");
    }

    /**
     * Mutator method for the listeners.
     *
     * @param listeners collection of listeners to use at startup.
     */
    public void setListeners (List<Listener> listeners)
    {
        this.listeners = listeners;
    }

    /**
     * Initializes the Agent, which should startup all Listeners.
     */
    public void init ()
    {
        log.debug("Initializing the Agent...");
        initListeners();
    }

    /**
     * Initializes all of the Listeners.
     */
    private void initListeners ()
    {
        if (listeners != null)
        {
        	log.debug("Initializing all listeners [" + listeners.size() + "]");
        	for (Listener listener : listeners)
	        {
	            initListener(listener);
	        }
        }
    }

    /**
     * Initializes a listener.
     *
     * @param listener the listener to initialize.
     */
    private void initListener (Listener listener)
    {
    	if (listener != null)
    	{
    		try
	        {
	            log.debug("Initializing listener: " + listener);
	            listener.init();
	        }
	        catch (Exception e)
	        {
	            log.error("Unable to start listener: " + listener, e);
	        }
    	}
    }
}
