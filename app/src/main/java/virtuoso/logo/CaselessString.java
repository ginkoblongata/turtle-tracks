/*
===============================================================================

	FILE:  CaselessString.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		String with caseless compare
	
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


/**
 * String with caseless compare
 * <p>
 * This class implements a subset of java.lang.string, in which the comparison
 * functions, particularly equals(), can be set to ignore case.
 */

public final class CaselessString
implements
	Cloneable
{
	
	// I really want to make this private static, but applets seem to
	// want access to it, presumably to construct the member when the class
	// is loaded.
	protected static boolean _caseSensitive = false;
	
	private int _hashCode;
	
	/**
	 * The string represented by this CaselessString
	 */
	public String str;
	
	
	/**
	 * Constructs an empty caseless string
	 */
	public CaselessString()
	{
		str = new String();
		_hashCode = 0;
	}


	/**
	 * Constructs a caseless string whose value is given
	 *
	 * @param s the value
	 */
	public CaselessString(
		String s)
	{
		str = s;
		calcHashCode();
	}


	/**
	 * Constructs a caseless string whose value is a single character given
	 *
	 * @param ch the value
	 */
	public CaselessString(
		char ch)
	{
		char[] value = new char[1];
		value[0] = ch;
		str = new String(value);
		calcHashCode();
	}


	/**
	 * Constructs a caseless string whose value is the character array given
	 *
	 * @param value the value
	 */
	public CaselessString(
		char[] value)
	{
		str = new String(value);
		calcHashCode();
	}


	/**
	 * Constructs a caseless string whose value is the StringBuffer given
	 *
	 * @param buffer the value
	 */
	public CaselessString(
		StringBuffer buffer)
	{
		str = new String(buffer);
		calcHashCode();
	}


	/**
	 * Precalculate hash code
	 */
	final private void calcHashCode()
	{
		_hashCode = str.toUpperCase().hashCode();
	}


	/**
	 * Return hash code
	 *
	 * @return hash code for the string
	 */
	public int hashCode()
	{
		return _hashCode;
	}


	/**
	 * Clone this CaselessString
	 *
	 * @return a copy of the object
	 */
	public Object clone()
	{
		return new CaselessString(str);
	}


	/**
	 * Set case sensitivity for all CaselessStrings
	 *
	 * @param sensitivity should compares be case sensitive?
	 */
	public final static void setSensitivity(
		boolean sensitivity)
	{
		_caseSensitive = sensitivity;
	}


	/**
	 * Get case sensitivity
	 *
	 * @return are compares case sensitive?
	 */
	public final static boolean getSensitivity()
	{
		return _caseSensitive;
	}


	/**
	 * Extract string
	 *
	 * @return the string
	 */
	public String toString()
	{
		return str;
	}


	/**
	 * Special equals method
	 *
	 * @param obj the object to compare
	 *
	 * @return true if and only if the given object is equal in value to
	 * this object, subject to the current case sensitivity setting
	 */
	public boolean equals(
		Object obj)
	{
		return staticEquals(str, obj.toString());
/*		if (obj instanceof CaselessString)
		{
			if (_caseSensitive)
				return str.equals(((CaselessString)obj).str);
			else
				return str.equalsIgnoreCase(((CaselessString)obj).str);
		}
		else if (obj instanceof String)
		{
			if (_caseSensitive)
				return str.equals((String)obj);
			else
				return str.equalsIgnoreCase((String)obj);
		}
		else
		{
			return false;
		}*/
	}


	/**
	 * Special compare method
	 *
	 * @param anotherString the object to compare
	 *
	 * @return 0 if the given object's string representation is the same as
	 * this CaselessString. An integer >0 if the given object is lexically
	 * greater, or an integer <0 if the given object is lexically less.
	 * Comparison is subject to the current case sensitivity setting.
	 */
	public final int compareTo(
		Object anotherString)
	{
		return staticCompare(str, anotherString.toString());
	}


	/**
	 * Static compare method. Compares two given strings, subject to the
	 * current case sensitivity setting.
	 *
	 * @param str1 the first string
	 * @param str2 the second string
	 *
	 * @return 0 if the strings are equal, a negative integer if str1<str2,
	 * or a positive integer if str1>str2.
	 */
	public final static int staticCompare(
		String str1,
		String str2)
	{
		if (_caseSensitive)
		{
			return str1.compareTo(str2);
		}
		else
		{
	  		char v1[] = str1.toCharArray();
			char v2[] = str2.toCharArray();
			int len1 = v1.length;
			int len2 = v2.length;
			int n = Math.min(len1, len2);
			int i = 0;
			
			for (i=0; i<n; i++)
			{
				char c1 = Character.toUpperCase(v1[i]);
				char c2 = Character.toUpperCase(v2[i]);
				if (c1 != c2)
				{
					return c1 - c2;
				}
			}
			return len1 - len2;
	  	}
	}


	/**
	 * Static eauzls method. Compares two given strings, subject to the current
	 * case sensitivity setting.
	 *
	 * @param str1 the first string
	 * @param str2 the second string
	 *
	 * @return true if and only if the two strings are equal
	 */
	public final static boolean staticEquals(
		String str1,
		String str2)
	{
		if (_caseSensitive)
		{
			return str1.equals(str2);
		}
		else
		{
			return str1.equalsIgnoreCase(str2);
		}
	}


	/**
	 * Get "unparsed" form of this string
	 *
	 * @return unparsed string
	 */
	public final String unparse()
	{
		return staticUnparse(str);
	}


	/**
	 * Get "unparsed" form of the given string
	 *
	 * @param str the string to unparse
	 *
	 * @return unparsed string
	 */
	public final static String staticUnparse(
		String str)
	{
		int i;
		
		StringBuffer sb = new StringBuffer();
		
		for (i=0; i<str.length(); i++)
		{
			char ch = str.charAt(i);
			switch (ch)
			{
				case ' ':
				case '[':
				case ']':
				case '|':
				case ';':
				case '\\':
					sb.append('\\').append(ch);
					break;
				
				case '\t':
					sb.append('\\').append('t');
					break;
				case '\r':
					sb.append('\\').append('r');
					break;
				case '\n':
					sb.append('\\').append('n');
					break;
				default:
					sb.append(ch);
					break;
			}
		}
		
		return sb.toString();
	}

}



