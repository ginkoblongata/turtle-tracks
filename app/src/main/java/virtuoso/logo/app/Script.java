/*
===============================================================================

	FILE:  Script.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Runs logo with a non-interactive command line console
	
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


/**
 * Runs logo with a command line console
 */

public class Script
{

	/**
	 * No instantiation of this class
	 */
	private Script()
	{
	}


	/**
	 * Main program
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		String[] realArgs = new String[args.length+3];
		realArgs[0] = "-c";
		realArgs[1] = "virtuoso.logo.app.CliNoPromptConsole";
		realArgs[2] = "-std";
		System.arraycopy(args, 0, realArgs, 3, args.length);
		
		Run.main(realArgs);
	}

}



