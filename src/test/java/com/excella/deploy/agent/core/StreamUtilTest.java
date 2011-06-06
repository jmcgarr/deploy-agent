/*
 * Copyright 2010
 * Excella Consulting
 * All Rights Reserved.
 *
 * This software is the proprietary information
 * of Excella Consulting.
 * Use is subject to license terms.
 *
 * Created On: Nov 26, 2010
 * Created By: J. Michael McGarr
 */
package com.excella.deploy.agent.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit Tests the StreamUtil class. 
 * 
 * @author <a href="email:mike.mcgarr@excella.com">J. Michael McGarr</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class StreamUtilTest
{
	@Mock private Closeable item;
	
	@Test public void canCloseObject () throws IOException
	{
		StreamUtil.close(item);
		verify(item, times(1)).close();
	}
	
	@Test public void canHandleANullObject ()
	{
		StreamUtil.close(null);
	}
	
	@Test public void canHandleAnIOExceptionUponClose() throws IOException
	{
		doThrow(new IOException("should catch me")).when(item).close();
		StreamUtil.close(item);
		verify(item, times(1)).close();
	}
	
	@Test public void canCloseAnObjectWithoutNullifyingIt () throws IOException
	{
		StreamUtil.close(item,false);
		verify(item, times(1)).close();
	}
}
