/*
 * Copyright 2010
 * Excella Consulting Inc.
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of the Excella Consutling Inc.
 * Use is subject to license terms.
 *
 * Created On: Nov 17, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excella.deploy.agent.core.Agent;
import com.excella.deploy.agent.core.Listener;

/**
 * Unit Tests the Main start and stop methods for the server. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class AgentTest
{
    @Mock private Listener listener;
    private Agent agent;
    
    @Before public void setUp ()
    {
        this.agent = new Agent();
        
        List<Listener> listeners = new ArrayList<Listener>();
        listeners.add(listener);
        this.agent.setListeners(listeners);
    }
    
    @After public void tearDown ()
    {
        this.agent = null;
        this.listener = null;
    }
    
    @Test public void canCanInitializeListeners () throws Exception
    {        
        agent.init();
        verify(listener).init();
    }
    
    @Test public void canHandleABrokenListener () throws Exception
    {
    	doThrow(new Exception("Failed to init")).when(listener).init();
    	agent.init();
    }
    
    @Test public void canStartWithNullListeners () 
    {
    	agent.setListeners(null);
    	agent.init();
    }
}
