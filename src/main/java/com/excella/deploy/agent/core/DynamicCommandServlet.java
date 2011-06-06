/*
 * Copyright 2010
 * Excella Consulting
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of Excella Consulting.
 * Use is subject to license terms.
 *
 * Created On: Nov 23, 2010
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

/**
 * HttpServlet that translates the HTTP request to a execution request. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class DynamicCommandServlet extends HttpServlet
{
    /** Serializable Unique ID. */
    private static final long serialVersionUID = 3240070499280655732L;
    
    /** Logger. */
    private static final Log log = LogFactory.getLog(DynamicCommandServlet.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void init (ServletConfig config) throws ServletException
    {
        log.info("Initializing the DynamicCommandServlet...");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy ()
    {
        log.info("Destroying the DynamicCommandServlet...");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("Recieved command over http [" + request.getRequestURI() + "]");
        String argument = grabArguments(request);
        Command command = findCommand(request);
        try
        {
            success(command.execute(argument), request, response);
        }
        catch (Exception e)
        {
            fail(command, e, request, response);
        }
    }

    /**
	 * COMMENT DynamicCommandServlet.grabArguments()
	 *
	 * @param request
	 * @return
	 */
	private String grabArguments(HttpServletRequest request) {
		String arg = "";
		if (request.getParameter("buildno") != null) {
			arg = request.getParameter("buildno");
			log.info("Found the following value for param [buildno][" + arg + "]");
		}
		return arg;
	}

	/**
     * COMMENT DynamicCommandServlet.success()
     *
     * @param execute
     * @param response 
     * @param request 
     */
    protected void success (String execute, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            log.debug("Attempting to respond with a success...");
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.println("<h1>SUCCESS</h1>");
            writer.println("<p>");
            writer.println(execute);
            writer.println("</p>");
            ((Request)request).setHandled(true);
        }
        catch (Exception e)
        {
            log.error("Failed to respond with a success.", e);
        }
    }

    /**
     * COMMENT DynamicCommandServlet.fail()
     *
     * @param command
     * @param e
     * @param request
     * @param response
     */
    protected void fail (Command command, Exception cause, HttpServletRequest request,
        HttpServletResponse response)
    {
        try
        {
            log.debug("Attempting to respond with a failure...");
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>FAIL</h1>");
            response.getWriter().println("<p>");
            cause.printStackTrace(response.getWriter());
            response.getWriter().println("<p>");
            ((Request)request).setHandled(true);
        }
        catch (Exception e)
        {
            log.error("Failed to respond with a failure.", e);
        }
    }

    /**
     * COMMENT DynamicCommandServlet.findCommand()
     *
     * @param req
     * @return
     */
    private Command findCommand (HttpServletRequest req)
    {
        log.debug("Loading the correct command...");
        return new UnixShellCommand();
    }
}
