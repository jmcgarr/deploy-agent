/*
 * Copyright 2010
 * Excella Consulting
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of Excella Consulting.
 * Use is subject to license terms.
 *
 * Created On: Dec 1, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit Tests for FailedCommandException class 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
public class FailedCommandExceptionTest
{
	@Test public void canCreateAnExceptionWithNoArgs ()
	{
		FailedCommandException e = new FailedCommandException();
		assertNotNull(e);
	}
	
	@Test public void canCreateAnExceptionWtihAMessage ()
	{
		FailedCommandException e = new FailedCommandException("This is a test.");
		assertEquals("This is a test.", e.getMessage());
	}
	
	@Test public void canCreateAnExceptionWithACause ()
	{
		Exception cause = new Exception("This is the cause.");
		FailedCommandException e = new FailedCommandException(cause);
		assertEquals("This is the cause.", e.getCause().getMessage());
	}
	
	@Test public void canCreateAnExceptionWithAMessageAndACause ()
	{
		Exception cause = new Exception("This is the cause.");
		FailedCommandException e = new FailedCommandException("This is a test.", cause);
		assertEquals("This is a test.", e.getMessage());
		assertEquals("This is the cause.", e.getCause().getMessage());
	}
}
