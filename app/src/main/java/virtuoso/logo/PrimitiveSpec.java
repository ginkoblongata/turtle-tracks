/*
===============================================================================

	FILE:  PrimitiveSpec.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Primitive identification
	
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


import java.lang.reflect.*;


/**
 * Specification of a primitive definition
 */

public final class PrimitiveSpec
{

	private PrimitiveGroup _group;
	private Method _method;
	private int _defaultArgs;


	/**
	 * Constructor
	 *
	 * @param g the PrimitiveGroup defining the primitive
	 * @param m the method within the group
	 * @param da the number of default arguments
	 */
	public PrimitiveSpec(
		PrimitiveGroup g,
		Method m,
		int da)
	{
		_group = g;
		_method = m;
		_defaultArgs = da;
	}


	/**
	 * Extractor for primitive group
	 *
	 * @return the primitive group
	 */
	public final PrimitiveGroup group()
	{
		return _group;
	}


	/**
	 * Extractor for method
	 *
	 * @return the method
	 */
	public final Method method()
	{
		return _method;
	}


	/**
	 * Extractor for default number of arguments
	 *
	 * @return the default number of arguments
	 */
	public final int numArgs()
	{
		return _defaultArgs;
	}

}



