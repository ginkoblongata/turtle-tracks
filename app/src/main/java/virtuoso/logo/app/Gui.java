/*
===============================================================================

	FILE:  Gui.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Runs logo with an AWT-based console
	
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
 * Runs logo with an AWT-based console
 */

public class Gui
{

	/**
	 * No instantiation of this class
	 */
	private Gui()
	{
	}


	/**
	 * Main program
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		String[] realArgs = new String[args.length+5];
		realArgs[0] = "-std";
		realArgs[1] = "-p";
		realArgs[2] = "virtuoso.logo.lib.TurtlePrimitives";
		realArgs[3] = "-c";
		realArgs[4] = "virtuoso.logo.app.GuiConsole";
		System.arraycopy(args, 0, realArgs, 5, args.length);
		
		Run.main(realArgs);
	}

}



