/*
===============================================================================

	FILE:  ParsePrimitive.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Primitive node in a parse tree
	
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


import java.lang.reflect.*;


/**
 * Primitive node in a parse tree
 */

public class ParsePrimitive
extends ParseObject
{

	private PrimitiveSpec _spec;
	private String _name;
	private ParseObject[] _args;


	/**
	 * Construct the ParsePrimitive
	 *
	 * @param spec the primitive definition specification
	 * @param name the name of the primitive, given in the code
	 * @param args the arguments given to the primitive, as parse tree nodes
	 */
	public ParsePrimitive(
		PrimitiveSpec spec,
		String name,
		ParseObject[] args)
	{
		_spec = spec;
		_name = name;
		_args = args;
	}


	/**
	 * Clone the object
	 *
	 * @return a clone of this object
	 */
	public Object clone()
	{
		return new ParsePrimitive(_spec, _name, _args);
	}


	/**
	 * Determine if another object is equal to this one
	 *
	 * @param obj what to compare with
	 *
	 * @return true iff equal
	 */
	public boolean equals(
		Object obj)
	{
		return false;
	}


	/**
	 * The name of the procedure
	 *
	 * @return the name
	 */
	String procName()
	{
		return _name;
	}


	/**
	 * Evaluate this object in the given environment
	 *
	 * @param interp the environment
	 *
	 * @return the return value
	 *
	 * @exception virtuoso.logo.LanguageException error thrown
	 * @exception virtuoso.logo.ThrowException exception thrown
	 */
	LogoObject evaluate(
		InterpEnviron interp)
	throws
		LanguageException,
		ThrowException
	{
		// Evaluate arguments
		LogoObject[] evalArgs = new LogoObject[_args.length];
		Object[] params = new Object[2];
		for (int i=0; i<_args.length; i++)
		{
			evalArgs[i] = _args[i].evaluate(interp);
			if (evalArgs[i] == LogoVoid.obj)
			{
				throw new LanguageException(_args[i].procName().toUpperCase() +
					" didn't output to " + _name.toUpperCase());
			}
		}
		
		// Check for thread suspension and termination
		Thread.yield();
		interp.mach().checkSuspend();
		if (interp.thread().stopping())
		{
			throw new ThrowException(".SUDDENSTOPTHREAD");
		}
		
		// Invoke primitive
		params[0] = interp;
		params[1] = evalArgs;
		LogoObject ret = LogoVoid.obj;
		try
		{
			ret = (LogoObject)(_spec.method().invoke(_spec.group(), params));
		}
		catch (IllegalAccessException e)
		{
			throw new LanguageException(e.toString());
		}
		catch (InvocationTargetException e)
		{
			Throwable ex = e.getTargetException();
			if (ex instanceof LanguageException)
			{
				LanguageException lex = (LanguageException)ex;
				if (lex.getPrimName() == null && lex.getProcName() == null)
				{
					throw new LanguageException(lex.getMessage(),
						_name, null, lex.getContChar());
				}
				else
				{
					throw lex;
				}
			}
			else if (ex instanceof ThrowException)
			{
				throw (ThrowException)ex;
			}
			else if (ex instanceof SecurityException)
			{
				throw new LanguageException(ex.toString(), _name, null);
			}
			else if (ex instanceof ThreadDeath)
			{
				throw (ThreadDeath)ex;
			}
			else
			{
				throw new LanguageException(ex.toString(), _name);
			}
		}
		return ret;
	}

}



