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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * Simple implementation of the Agent interface.
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class SimpleHttpListener implements Listener
{
    private static Log log = LogFactory.getLog(SimpleHttpListener.class);
    private static final int PORT = 8090;
    private static final String CONTEXT_ROOT = "/";
    
    private Server server;

    /**
     * {@inheritDoc}
     */
    public void init () throws Exception
    { 
    	initServer();
        Context root = new Context(server, CONTEXT_ROOT, Context.SESSIONS);
        initServlets(root);
        server.start();
    }
    
    private void initServer ()
    {
    	if (server == null)
    	{
            log.info("Starting a SimpleHttpListener[" + PORT + "]");     
    		server = new Server(PORT);
    	}
    }
    
    /**
     * Loads the configuration for all the servlet listeners.
     * 
     * @param root the server's root context.
     */
    private void initServlets (Context root)
    {
        log.debug("Initializing servlets...");
        root.addServlet(new ServletHolder(new DynamicCommandServlet()), "/deploy");
        root.addServlet(new ServletHolder(new StatusServlet()), "/status");
    }

    /**
     * {@inheritDoc}
     */
    public void shutdown () throws Exception
    {
        log.debug("Shutting down SimpleHttpListener[" + PORT + "]");
        server.stop();
    }

    /**
     * The server instance to use.
     *
     * @param the server instance to use
     */
    public void setServer (Server server)
    {
        this.server = server;
    }
}
