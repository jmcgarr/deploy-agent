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
 * Exception that is thrown when a Command fails. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class FailedCommandException extends Exception
{
    /** Serializable UID. */
    private static final long serialVersionUID = 8816013480137334851L;

    public FailedCommandException ()
    {
    	super();
    }
    
    public FailedCommandException (String message)
    {
    	super(message);
    }
    
    public FailedCommandException (String message, Throwable cause)
    {
    	super(message, cause);
    }

    public FailedCommandException (Throwable cause)
    {
    	super(cause);
    }
}
