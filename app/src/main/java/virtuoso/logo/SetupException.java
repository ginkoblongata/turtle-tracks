/*
===============================================================================

	FILE:  SetupException.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Problem during setup
	
	PROGRAMMERS:
	
		Daniel Azuma (DA)  <dazuma@kagi.com>
	
	COPYRIGHT:
	
		This software licensed with 3-Clause BSD License.
		Copyright (C) 1997-1999  Daniel Azuma  (dazuma@kagi.com)
		SPDX-License-Identifier: BSD-3-Clause
	
	VERSION:
	
		Turtle Tracks 1.0  (13 November 1999)
	
===============================================================================
*/


package virtuoso.logo;


/**
 * Problem during setup. This exception should be thrown by a Console or
 * PrimitiveGroup if a fatal error occurs during initialization.
 */

public final class SetupException
extends Exception
{

	/**
	 * Unknown error during setup
	 */
	public static final int unknownErr = 0;
	
	/**
	 * Unable to find method for reflection
	 */
	public static final int noSuchMethodErr = 1;
	
	/**
	 * Unable to find primitive for aliasing from another primitive group
	 */
	public static final int noSuchPrimitiveErr = 2;
	
	/**
	 * Security error
	 */
	public static final int securityError = 3;


	private int _code;


	/**
	 * Constructor with a message and a code
	 *
	 * @param s detail message
	 * @param c error code
	 */
	public SetupException(
		String s,
		int c)
	{
		super(s);
		_code = c;
	}


	/**
	 * Constructor with a message
	 *
	 * @param s detail message
	 */
	public SetupException(
		String s)
	{
		super(s);
		_code = unknownErr;
	}


	/**
	 * Constructor with a code
	 *
	 * @param c error code
	 */
	public SetupException(
		int c)
	{
		super("SetupException");
		_code = c;
	}


	/**
	 * Constructor with no information
	 */
	public SetupException()
	{
		super("SetupException");
		_code = unknownErr;
	}


	/**
	 * Gets the code
	 *
	 * @return the error code
	 */
	public final int getCode()
	{
		return _code;
	}

}



