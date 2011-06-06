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

/**
 * Interface defining a command that can be executed on the native machine. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public interface Command
{
    /**
     * Used to execute the command.
     *
     * @return
     * @throws FailedCommandException
     */
    String execute () throws FailedCommandException;
    
    String execute (String arg) throws FailedCommandException;
}
