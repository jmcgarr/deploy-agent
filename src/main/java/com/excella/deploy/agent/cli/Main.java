/*
 * Copyright 2010
 * Excella Consulting Inc.
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of the Excella Consulting Inc.
 * Use is subject to license terms.
 *
 * Created On: Nov 23, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.cli;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.excella.deploy.agent.core.Agent;
import com.excella.deploy.agent.core.Listener;
import com.excella.deploy.agent.core.SimpleHttpListener;

/**
 * Command line entry point for Agent.
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class Main
{
    private static final Log log = LogFactory.getLog(Main.class);
    
    public static void main (String [] args) throws Exception
    {
        startAgent();
    }

    /**
     * Starts up the agent
     */
    private static void startAgent ()
    {
        Agent agent = new Agent();
        agent.setListeners(loadListeners());
        agent.init();
    }    

    /**
     *  FIXME replace this method call with a Spring initialization.
     */
    private static List<Listener> loadListeners ()
    {
        List<Listener> listeners = new ArrayList<Listener>();
        try
        {
            log.debug("Creating a SimpleHttpListener...not a long term feature");
            Listener listener = new SimpleHttpListener();
            listeners.add(listener);
        }
        catch (Exception e)
        {
            log.error("Error occurred when loading a listener.", e);
        }
        return listeners;
    }

}
