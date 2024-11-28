/*
===============================================================================

	FILE:  IOWriteFile.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Write file object
	
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
 * Stream that writes to a file
 */

public class IOWriteFile
extends IOStream
{

	private static LogoObject _kind;


	static
	{
		_kind = new LogoWord("WRITEFILE");
	}


	/**
	 * Constructor
	 *
	 * @param f file to open
	 *
	 * @exception virtuoso.logo.LanguageException unable to open file
	 */
	public IOWriteFile(
		File f)
	throws
		LanguageException
	{
		try
		{
			open(new LogoWord(f.getPath()), new BufferedWriter(new FileWriter(f)));
		}
		catch (IOException e)
		{
			throw new LanguageException("Couldn't open file: \"" + f.getPath() +
				"\" I/O: " + e.getMessage());
		}
		catch (SecurityException e)
		{
			throw new LanguageException("Couldn't open file: \"" + f.getPath() +
				"\" Security: " + e.toString());
		}
	}


	/**
	 * Get the kind of this object
	 *
	 * @return the kind as a LogoObject
	 */
	public synchronized LogoObject kind()
	{
		return _kind;
	}

}



