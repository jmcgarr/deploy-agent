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

import static com.excella.deploy.agent.core.StreamUtil.close;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Unix implementation of the Command interface that can execute a Shell Script. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class UnixShellCommand implements Command
{
    private static Log log = LogFactory.getLog(UnixShellCommand.class);
    
    private String command = "$JBOSS_HOME/bin/appdeploy.sh";
    private String stdout;
    private String stderr = "/dev/null";
    private boolean executeInBackground = false;
    
    /**
	 * {@inheritDoc}
	 */
	public String execute() throws FailedCommandException {
		return execute("");
	}

	/**
	 * {@inheritDoc}
	 */
	public String execute (String argument) throws FailedCommandException
	{
	    String line = "";
	    StringBuffer buffer = new StringBuffer();
	    String command = getJBossHome() + "/bin/appdeploy.sh " + argument + " 2>/dev/null";
	    InputStreamReader inputStream = null;
	    BufferedReader input = null;
	    
	    log.info("Running an RSM Deployment using the following command: " + command);
	    try
	    {
	        Runtime runtime = Runtime.getRuntime();
	        Process process = runtime.exec(command);
	        int exitValue = process.waitFor();
	        inputStream = new InputStreamReader(process.getInputStream());
	        input = new BufferedReader(inputStream);
	        while ((line = input.readLine()) != null)
	        {
	            log.debug("> " + line);
	            buffer.append(line);
	            buffer.append("<br/>");
	        }
	        
	        log.info("RSM Deployment Kicked off.  Exited with [" + exitValue + "]");
	    }
	    catch (Exception e)
	    {
	        throw new FailedCommandException("Unable to execute the command.", e);
	    }
	    finally
	    {
	        close(inputStream);
	        close(input);
	    }
	    return buffer.toString();
	}

	private String getJBossHome ()
    {
        String home = "/opt/data/jboss";
        String env = System.getenv("JBOSS_HOME");
        log.debug("JBOSS_HOME is set to [" + env + "]");
        if (env != null)
        {
            home = env;
        }
        return home;
    }
}
