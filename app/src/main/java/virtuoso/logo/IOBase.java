/*
===============================================================================

	FILE:  IOBase.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Interface to logo stream objects
	
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
 * IO interface. This is the base interface for all stream objects
 */

public interface IOBase
{

	/**
	 * Close the stream
	 *
	 * @exception virtuoso.logo.LanguageException can't close
	 */
	public abstract void close()
	throws
		LanguageException;


	/**
	 * Is the stream open?
	 *
	 * @return true iff can interact with stream
	 */
	public abstract boolean isOpen();


	/**
	 * Get the name of this object
	 *
	 * @return the name as a LogoObject
	 */
	public abstract LogoObject name();


	/**
	 * Get the kind of this object
	 *
	 * @return the kind as a LogoObject
	 */
	public abstract LogoObject kind();


	/**
	 * Has the stream encountered EOF?
	 *
	 * @return true iff eof encountered
	 *
	 * @exception virtuoso.logo.LanguageException stream closed
	 */
	public abstract boolean eof()
	throws
		LanguageException;


	/**
	 * Get a line from the stream
	 *
	 * @return the string
	 *
	 * @exception virtuoso.logo.LanguageException read not allowed, or io closed
	 */
	public abstract String getLine()
	throws
		LanguageException;


	/**
	 * Get all available data from stream
	 *
	 * @param buf buffer to read into
	 *
	 * @return how much data was actually read
	 *
	 * @exception virtuoso.logo.LanguageException read not allowed, or io closed
	 */
	public abstract int getAvailable(
		char[] buf)
	throws
		LanguageException;


	/**
	 * Get a character from the stream
	 *
	 * @return char the character
	 *
	 * @exception virtuoso.logo.LanguageException read not allowed, or io closed
	 */
	public abstract char getChar()
	throws
		LanguageException;


	/**
	 * Character available on stream?
	 *
	 * @return true iff available
	 *
	 * @exception virtuoso.logo.LanguageException read not allowed, or io closed
	 */
	public abstract boolean charAvail()
	throws
		LanguageException;


	/**
	 * Write a string to the stream
	 *
	 * @param str the string
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void put(
		String str)
	throws
		LanguageException;


	/**
	 * Write a char to the stream
	 *
	 * @param c the char
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void put(
		char c)
	throws
		LanguageException;


	/**
	 * Write a char array to the stream
	 *
	 * @param buf the buffer
	 * @param num number of characters
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void put(
		char[] buf,
		int num)
	throws
		LanguageException;


	/**
	 * Write a string to the stream, terminated with a newline
	 *
	 * @param str the string
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void putLine(
		String str)
	throws
		LanguageException;


	/**
	 * Flush the stream
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public abstract void flush()
	throws
		LanguageException;

}



