/*
 * Copyright 2011
 * Excella Consulting
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of Excella Consulting.
 * Use is subject to license terms.
 *
 * Created On: Jan 19, 2011
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Request;

public class StatusServlet extends HttpServlet {

    /** Serializable Unique ID. */
    private static final long serialVersionUID = 3240070499280655732L;
    
    /** Logger. */
    private static final Log log = LogFactory.getLog(StatusServlet.class);
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("Initializing the Status Servlet...");
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy () {
        log.info("Destroying the Status Servlet...");
    }
	
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("Recieved status request command over http [" + request.getRequestURI() + "]");
        try
        {
            log.debug("Reporting Status...");
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.println("<h1>Deploy Agent Status</h1>");
            writer.println("<p>");
            writer.println("Everything is Great!");
            writer.println("</p>");
            ((Request)request).setHandled(true);
        }
        catch (Exception e)
        {
            log.error("Failed to report Status.", e);
        }
    }
}
