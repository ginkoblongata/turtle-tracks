/*
===============================================================================

	FILE:  SymbolTable.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		A symbol table
	
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

import java.util.*;


/**
 * A symbol table for a single scope
 */

public final class SymbolTable
{

	private Hashtable _hash;
	private SymbolTable _next;


	/**
	 * Construct an empty symbol table
	 */
	public SymbolTable()
	{
		_hash = new Hashtable(4);
		_next = null;
	}


	/**
	 * Push this table onto the given table, return new top
	 */
	SymbolTable pushOn(
		SymbolTable s)
	{
		_next = s;
		return this;
	}


	/**
	 * Return next table
	 */
	SymbolTable next()
	{
		return _next;
	}


	/**
	 * Declare symbol
	 *
	 * @param name symbol name
	 * @param thread the machine being run on
	 */
	public final synchronized void declare(
		CaselessString name,
		Machine mach)
	{
		if (!_hash.containsKey(name))
		{
			LogoObject obj = mach.resolveName(name);
			_hash.put(name, (obj == null) ? LogoVoid.obj : obj);
		}
	}


	/**
	 * Make symbol
	 *
	 * @param name symbol name
	 * @param obj the object to link to
	 *
	 * @return true iff symbol was declared.
	 */
	public final synchronized boolean make(
		CaselessString name,
		LogoObject obj)
	{
		if (_hash.remove(name) == null)
		{
			return false;
		}
		else
		{
			_hash.put(name, obj);
			return true;
		}
	}


	/**
	 * Make symbol, forcing a declare if not declared
	 *
	 * @param name symbol name
	 * @param obj the object to link to
	 */
	public final synchronized void makeForced(
		CaselessString name,
		LogoObject obj)
	{
		_hash.remove(name);
		_hash.put(name, obj);
	}


	/**
	 * Resolve symbol
	 *
	 * @param name symbol name
	 *
	 * @return the object, or null if not found
	 */
	public final synchronized LogoObject resolve(
		CaselessString name)
	{
		LogoObject obj = (LogoObject)(_hash.get(name));
		return (obj == LogoVoid.obj) ? null : obj;
	}


	/**
	 * Symbol exists at this level
	 *
	 * @param name symbol name
	 *
	 * @return true iff symbol exists
	 */
	public final synchronized boolean exists(
		CaselessString name)
	{
		return _hash.containsKey(name);
	}


	/**
	 * Delete symbol
	 *
	 * @param name symbol name
	 *
	 * @return true iff symbol found and deleted
	 */
	public final synchronized boolean erase(
		CaselessString name)
	{
		return _hash.remove(name) != null;
	}


	/**
	 * Clear table level
	 */
	public final synchronized void eraseAll()
	{
		_hash.clear();
	}


	/**
	 * Get a list of names from this level
	 *
	 * @return the list
	 */
	public final synchronized LogoList getNames()
	{
		Vector v = new Vector();
		Enumeration anEnumeration = _hash.keys();
		
		while (anEnumeration.hasMoreElements())
		{
			v.addElement(new LogoWord((CaselessString)(anEnumeration.nextElement())));
		}
		
		return new LogoList(v);
	}


	/**
	 * Get an enumeration of names from this level
	 *
	 * @return the enumeration
	 */
	final synchronized Enumeration getEnumeratedNames()
	{
		return _hash.keys();
	}

}



