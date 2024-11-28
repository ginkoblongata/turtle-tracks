/*
===============================================================================

	FILE:  IOSocket.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Socket stream object
	
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
import java.util.Vector;


/**
 * Stream that reads from and writes to a socket
 */

public class IOSocket
extends IOStream
{

	private Socket _theSocket;

	private static LogoObject _kind;


	static
	{
		_kind = new LogoWord("SOCKET");
	}


	/**
	 * Constructor
	 *
	 * @param h hostname
	 * @param p port number
	 *
	 * @exception virtuoso.logo.LanguageException can't open socket
	 */
	public IOSocket(
		String h,
		int p)
	throws
		LanguageException
	{
		try
		{
			_theSocket = new Socket(h, p);
			LogoObject[] a = new LogoObject[3];
			a[0] = new LogoWord(_theSocket.getLocalPort());
			a[1] = new LogoWord(_theSocket.getInetAddress().getHostName());
			a[2] = new LogoWord(_theSocket.getPort());
			open(new LogoList(a),
				new BufferedReader(new InputStreamReader(_theSocket.getInputStream())),
				new BufferedWriter(new OutputStreamWriter(_theSocket.getOutputStream())));
		}
		catch (IOException e)
		{
			throw new LanguageException("Couldn't open socket: " + e.getMessage());
		}
		catch (SecurityException e)
		{
			throw new LanguageException(e.toString());
		}
	}


	/**
	 * Constructor
	 *
	 * @param s socket to use
	 *
	 * @exception virtuoso.logo.LanguageException unable to open socket
	 */
	public IOSocket(
		Socket s)
	throws
		LanguageException
	{
		try
		{
			_theSocket = s;
			LogoObject[] a = new LogoObject[3];
			a[0] = new LogoWord(_theSocket.getLocalPort());
			a[1] = new LogoWord(_theSocket.getInetAddress().getHostName());
			a[2] = new LogoWord(_theSocket.getPort());
			open(new LogoList(a),
				new BufferedReader(new InputStreamReader(_theSocket.getInputStream())),
				new BufferedWriter(new OutputStreamWriter(_theSocket.getOutputStream())));
		}
		catch (IOException e)
		{
			throw new LanguageException("Couldn't open socket: " + e.getMessage());
		}
		catch (SecurityException e)
		{
			throw new LanguageException(e.toString());
		}
	}


	/**
	 * Close the stream
	 *
	 * @exception virtuoso.logo.LanguageException can't close
	 */
	public synchronized void close()
	throws
		LanguageException
	{
		super.close();
		try
		{
			_theSocket.close();
		}
		catch (IOException e) {}
		_theSocket = null;
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


	/**
	 * Write a string to the stream, terminated with a network newline.<br>
	 * Because Mac-style newlines (\r) are incompatible with network newlines (\r\n)
	 *
	 * @param str the string
	 *
	 * @exception virtuoso.logo.LanguageException write not allowed, or io closed
	 */
	public synchronized void putLine(
		String str)
	throws
		LanguageException
	{
		put(str+"\r\n");
	}

}



