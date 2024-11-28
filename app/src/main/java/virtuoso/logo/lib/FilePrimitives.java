/*
===============================================================================

	FILE:  FilePrimitives.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		File access primitives
	
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


package virtuoso.logo.lib;

import virtuoso.logo.*;
import virtuoso.logo.Console;
import java.io.*;


/**
 * Names of primitives (keywords)
 */

public final class FilePrimitives
extends PrimitiveGroup
{

	public static String WDDATAID = "virtuoso.logo.wd";


	/**
	 * Set up primitive group
	 */
	protected void setup(
		Machine mach,
		Console console)
	throws
		SetupException
	{
		registerPrimitive("CD", "pCD", 1);
		registerPrimitive("CDUP", "pCDUP", 0);
		registerPrimitive("LOAD", "pLOAD", 1);
		registerPrimitive("OPENREAD", "pOPENREAD", 1);
		registerPrimitive("OPENWRITE", "pOPENWRITE", 1);
		registerPrimitive("OPENUPDATE", "pOPENUPDATE", 1);
		registerPrimitive("PWD", "pPWD", 0);
		registerPrimitive("SAVE", "pSAVE", 1);
		
		mach.setData(WDDATAID, System.getProperty("user.dir"));
		
		console.putStatusMessage("Turtle Tracks file system primitives v1.0");
	}


	/**
	 * Primitive implementation for CD
	 */
	public final synchronized LogoObject pCD(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Path expected");
		}
		File f = absolutePath(interp.mach(), params[0].toString());
		if (!f.exists())
		{
			throw new LanguageException("Directory not found");
		}
		if (!f.isDirectory())
		{
			throw new LanguageException(params[0] + " isn't a directory");
		}
		try
		{
			interp.mach().setData(WDDATAID, f.getCanonicalPath());
		}
		catch (IOException e)
		{
			throw new LanguageException("Unable to change directory");
		}
		return LogoVoid.obj;
	}


	/**
	 * Primitive implementation for CDUP
	 */
	public final synchronized LogoObject pCDUP(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 0);
		String s = new File((String)(interp.mach().getData(WDDATAID))).getParent();
		if (s == null)
		{
			throw new LanguageException("Already at top of directory structure");
		}
		interp.mach().setData(WDDATAID, s);
		return LogoVoid.obj;
	}


	/**
	 * Primitive implementation for LOAD
	 */
	public final LogoObject pLOAD(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		if (interp.thread().isLoading())
		{
			throw new LanguageException("Attempt to load from within a load");
		}
		interp.mach().executeStream(
			new IOReadFile(absolutePath(interp.mach(), params[0])), interp.thread());
		return LogoVoid.obj;
	}


	/**
	 * Primitive implementation for OPENREAD
	 */
	public final LogoObject pOPENREAD(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1,2);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		CaselessString streamid;
		if (params.length == 2)
		{
			if (!(params[1] instanceof LogoWord))
			{
				throw new LanguageException("Stream id expected");
			}
			streamid = params[1].toCaselessString();
		}
		else
		{
			streamid = new CaselessString("__f" + interp.mach().getUniqueNum());
		}
		interp.mach().registerIO(streamid, new IOReadFile(absolutePath(interp.mach(), params[0])));
		if (params.length == 2)
		{
			return LogoVoid.obj;
		}
		else
		{
			return new LogoWord(streamid);
		}
	}


	/**
	 * Primitive implementation for OPENWRITE
	 */
	public final LogoObject pOPENWRITE(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1, 2);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		CaselessString streamid;
		if (params.length == 2)
		{
			if (!(params[1] instanceof LogoWord))
			{
				throw new LanguageException("Stream id expected");
			}
			streamid = params[1].toCaselessString();
		}
		else
		{
			streamid = new CaselessString("__f" + interp.mach().getUniqueNum());
		}
		interp.mach().registerIO(streamid, new IOWriteFile(absolutePath(interp.mach(), params[0])));
		if (params.length == 2)
		{
			return LogoVoid.obj;
		}
		else
		{
			return new LogoWord(streamid);
		}
	}


	/**
	 * Primitive implementation for OPENUPDATE
	 */
	public final LogoObject pOPENUPDATE(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1, 2);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		CaselessString streamid;
		if (params.length == 2)
		{
			if (!(params[1] instanceof LogoWord))
			{
				throw new LanguageException("Stream id expected");
			}
			streamid = params[1].toCaselessString();
		}
		else
		{
			streamid = new CaselessString("__f" + interp.mach().getUniqueNum());
		}
		interp.mach().registerIO(streamid, new IORandomFile(absolutePath(interp.mach(), params[0])));
		if (params.length == 2)
		{
			return LogoVoid.obj;
		}
		else
		{
			return new LogoWord(streamid);
		}
	}


	/**
	 * Primitive implementation for PWD
	 */
	public final LogoObject pPWD(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 0);
		return new LogoWord((String)(interp.mach().getData(WDDATAID)));
	}


	/**
	 * Primitive implementation for SAVE
	 */
	public final LogoObject pSAVE(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		IOWriteFile file = new IOWriteFile(absolutePath(interp.mach(), params[0])); 
		interp.mach().printout(file, null, null, null);
		file.close();
		return LogoVoid.obj;
	}


	/**
	 * Gets absolute path from string
	 */
	public static final File absolutePath(
		Machine mach,
		String path)
	{
		File f = new File(path);
		if (f.isAbsolute())
			return f;
		else
			return new File((String)(mach.getData(WDDATAID)), path);
	}


	/**
	 * Gets absolute path from LogoObject
	 */
	public static final File absolutePath(
		Machine mach,
		LogoObject path)
	throws
		LanguageException
	{
		if (!(path instanceof LogoWord))
		{
			throw new LanguageException("Filename or path expected");
		}
		String s = path.toString();
		File f = new File(s);
		if (f.isAbsolute())
			return f;
		else
			return new File((String)(mach.getData(WDDATAID)), s);
	}

}



