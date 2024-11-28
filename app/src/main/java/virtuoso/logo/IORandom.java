/*
===============================================================================

	FILE:  IORandom.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Interface to random-access logo stream objects
	
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

import java.io.*;


/**
 * Random-access IO interface.
 */

public interface IORandom
extends IOBase
{

	/**
	 * Seek to a location in the file
	 *
	 * @param pos position in the stream
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void seek(
		long pos)
	throws
		LanguageException;


	/**
	 * Tell current location in the file
	 *
	 * @return position in the stream
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract long tell()
	throws
		LanguageException;


	/**
	 * Get length of file
	 *
	 * @return length
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract long length()
	throws
		LanguageException;

}



