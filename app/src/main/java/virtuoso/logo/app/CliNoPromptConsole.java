/*
===============================================================================

	FILE:  CliNoPromptConsole.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		CLI console without prompts, for non-interactive sessions
	
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


package virtuoso.logo.app;

import virtuoso.logo.*;
import java.io.*;


/**
 * CLI console without prompts, for non-interactive sessions
 */

public class CliNoPromptConsole
extends CliConsole
{

	/**
	 * Write a string to the stream, terminated with a newline, as part of the setup
	 * process.
	 *
	 * @param str the string
	 */
	public void putSetupMessage(
		String str)
	{
	}


	/**
	 * Has the console encountered EOF?
	 *
	 * @return true iff eof encountered
	 */
	public synchronized boolean eof()
	{
		try
		{
			_reader.mark(2);
			int val = _reader.read();
			_reader.reset();
			return (val == -1);
		}
		catch (IOException e)
		{
			return true;
		}
	}


	/**
	 * Prompt and read a command
	 *
	 * @param prompt the prompt
	 */
	public synchronized String promptGetLine(
		char prompt)
	{
		return getLine();
	}

}



