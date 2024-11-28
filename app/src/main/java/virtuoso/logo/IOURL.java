/*
===============================================================================

	FILE:  IOURL.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		URL stream object
	
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
import java.net.*;


/**
 * Stream that reads from a URL
 */

public class IOURL
extends IOStream
{

	private static LogoObject _kind;


	static
	{
		_kind = new LogoWord("URL");
	}


	/**
	 * Constructor
	 *
	 * @param url the URL to open
	 *
	 * @exception virtuoso.logo.LanguageException can't open URL
	 */
	public IOURL(
		String url)
	throws
		LanguageException
	{
		try
		{
			URL _theURL = new URL(url);
			open(new LogoWord(_theURL.toString()),
				new BufferedReader(new InputStreamReader(_theURL.openStream())));
		}
		catch (MalformedURLException e)
		{
			throw new LanguageException("Couldn't open URL \"" + url +
				"\" because it is malformed.");
		}
		catch (IOException e)
		{
			throw new LanguageException("Couldn't open URL \"" + url +
				"\" I/O:" + e.toString());
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



