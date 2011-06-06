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

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class packed with helpful methods. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public final class StreamUtil
{
    /** Logger. */
    private static final Log log = LogFactory.getLog(StreamUtil.class);
    
    /**
     * Helper method for safely closing a object that implements the Closeable interface.
     * This method is null safe.  This method assumes that the item passed can can also 
     * be set to null upon successful closing.
     *
     * @param item item that needs to be closed.
     */
    public static final void close (Closeable item)
    {
    	close(item, true);
    }
    
    /**
     * Helper method for safely closing an object that implements the Closeable interface.
     * This method is null safe.  
     *
     * @param item object that should be closed.
     * @param nullify true if the object should be set to null, false if it should merely
     * be closed.
     */
    public static final void close (Closeable item, boolean nullify)
    {
        try
        {
            if (item != null)
            {
                item.close();
            }
        }
        catch (IOException e)
        {
            log.error("Unable to close the connection");
        }
        finally
        {
        	if (nullify) item = null;
        }
    }
}
