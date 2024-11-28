/*
===============================================================================

	FILE:  LoaderPrimitives.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Loader primitives
	
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
import java.util.*;
import java.io.*;


/**
 * Names of primitives (keywords)
 */

public final class LoaderPrimitives
extends PrimitiveGroup
{

	/**
	 * Set up primitive group
	 */
	protected void setup(
		Machine mach,
		Console console)
	throws
		SetupException
	{
		registerPrimitive("LOADPRIMITIVES", "pLOADPRIMITIVES", 1);
		registerPrimitive("UNLOADPRIMITIVES", "pUNLOADPRIMITIVES", 1);
		
		console.putStatusMessage("Turtle Tracks dynamic loading primitives v1.0");
	}


	/**
	 * Primitive implementation for LOADPRIMITIVES
	 */
	public final LogoObject pLOADPRIMITIVES(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Classname expected");
		}
		interp.mach().loadPrimitives(params[0].toString());
		return LogoVoid.obj;
	}


	/**
	 * Primitive implementation for UNLOADPRIMITIVES
	 */
	public final LogoObject pUNLOADPRIMITIVES(
		InterpEnviron interp,
		LogoObject[] params)
	throws
		LanguageException
	{
		testNumParams(params, 1);
		if (!(params[0] instanceof LogoWord))
		{
			throw new LanguageException("Classname expected");
		}
		interp.mach().unloadPrimitives(params[0].toString());
		return LogoVoid.obj;
	}

}



