/*
===============================================================================

	FILE:  LanguageException.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Miscellaneous language exception
	
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
 * General-purpose language exception. Represents an error condition. If not
 * caught, it will cause the machine to report an error to the console.
 */

public final class LanguageException
extends Exception
{

	private String _prim;
	private String _proc;
	private char _cont;


	/**
	 * Default constructor
	 */
	public LanguageException()
	{
		super();
		_prim = null;
		_proc = null;
		_cont = '\0';
	}


	/**
	 * Constructor with a string
	 *
	 * @param s detail string
	 */
	public LanguageException(
		String s)
	{
		super(s);
		_prim = null;
		_proc = null;
		_cont = '\0';
	}


	/**
	 * Constructor with a string and a continue
	 *
	 * @param s detail string
	 * @param cont continue prompt character (for tokenizer errors)
	 */
	LanguageException(
		String s,
		char cont)
	{
		super(s);
		_prim = null;
		_proc = null;
		_cont = cont;
	}


	/**
	 * Constructor with a string and a primitive name
	 *
	 * @param s detail string
	 * @param primitive which threw the exception
	 */
	public LanguageException(
		String s,
		String prim)
	{
		super(s);
		_prim = prim;
		_proc = null;
		_cont = '\0';
	}


	/**
	 * Constructor with a string, a primitive name, and a procedure name
	 *
	 * @param s detail string
	 * @param primitive which threw the exception
	 * @param procedure which threw the exception
	 */
	LanguageException(
		String s,
		String prim,
		String proc)
	{
		super(s);
		_prim = prim;
		_proc = proc;
		_cont = '\0';
	}


	/**
	 * Full constructor
	 *
	 * @param s detail string
	 * @param primitive which threw the exception
	 * @param procedure which threw the exception
	 * @param cont continue prompt character (for tokenizer errors)
	 */
	LanguageException(
		String s,
		String prim,
		String proc,
		char cont)
	{
		super(s);
		_prim = prim;
		_proc = proc;
		_cont = cont;
	}


	/**
	 * Accessor for primitive name
	 *
	 * @return the primitive which threw the exception
	 */
	public final String getPrimName()
	{
		return _prim;
	}


	/**
	 * Accessor for procedure name
	 *
	 * @return the procedure which threw the exception
	 */
	public final String getProcName()
	{
		return _proc;
	}


	/**
	 * Accessor for continue character
	 *
	 * @return the continue prompt character
	 */
	public final char getContChar()
	{
		return _cont;
	}


	/**
	 * Generate error message
	 *
	 * @return a string representation of the error message
	 */
	public final String generateMessage()
	{
		if (_proc != null && _prim != null)
		{
			return getMessage() + Machine.LINE_SEPARATOR +
				"... while executing " + _prim.toUpperCase() + Machine.LINE_SEPARATOR +
				"... in procedure " + _proc.toUpperCase();
		}
		else if (_prim != null)
		{
			return getMessage() + Machine.LINE_SEPARATOR +
				"... while executing " + _prim.toUpperCase();
		}
		else if (_proc != null)
		{
			return getMessage() + Machine.LINE_SEPARATOR +
				"... in procedure " + _proc.toUpperCase();
		}
		else
		{
			return getMessage();
		}
	}

}



