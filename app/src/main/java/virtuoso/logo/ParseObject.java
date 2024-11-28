/*
===============================================================================

	FILE:  ParseObject.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Node in a parse tree
	
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
 * General parsetree node
 */

public abstract class ParseObject
implements
	Cloneable
{

	/**
	 * Clone the object
	 *
	 * @return a clone of this object
	 */
	public abstract Object clone();


	/**
	 * Determine if another object is equal to this one
	 *
	 * @param obj what to compare with
	 *
	 * @return true iff equal
	 */
	public abstract boolean equals(
		Object obj);


	/**
	 * Evaluate this object in the given environment
	 *
	 * @param interp the environment
	 *
	 * @return the value
	 *
	 * @exception virtuoso.logo.LanguageException error thrown
	 * @exception virtuoso.logo.ThrowException exception thrown
	 */
	abstract LogoObject evaluate(
		InterpEnviron interp)
	throws
		LanguageException,
		ThrowException;


	/**
	 * The name of the procedure
	 *
	 * @return the name
	 */
	String procName()
	{
		return new String();
	}

}



