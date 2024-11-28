/*
===============================================================================

	FILE:  ThrowException.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Encountered command to throw
	
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
 * Exception corresponding to a Logo exception
 */

public final class ThrowException
extends Exception
{

	private LogoObject _obj;


	/**
	 * Constructor with a tag and an output value
	 *
	 * @param s the tag
	 * @param o the output value
	 */
	public ThrowException(
		String s,
		LogoObject o)
	{
		super(s);
		_obj = o;
	}


	/**
	 * Constructor with a tag and no output value
	 *
	 * @param s the tag
	 */
	public ThrowException(
		String s)
	{
		super(s);
		_obj = LogoVoid.obj;
	}


	/**
	 * Gets the tag
	 *
	 * @return the tag
	 */
	public final CaselessString getTag()
	{
		return new CaselessString(getMessage());
	}


	/**
	 * Gets the output value
	 *
	 * @return the object
	 */
	public final LogoObject getObj()
	{
		return _obj;
	}


	/**
	 * Changes to string
	 *
	 * @return the string
	 */
	public String toString()
	{
		return "ThrowException: tag=" + getMessage() + " obj=" + _obj.toString();
	}

}



