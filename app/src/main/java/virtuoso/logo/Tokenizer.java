/*
===============================================================================

	FILE:  Tokenizer.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		List builder object
	
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

import java.util.Vector;


/**
 * List builder
 */

public final class Tokenizer
{

	private int _index;
	private String _str;
	private char _ch;
	private int _flags;


	public final static int HONOR_COMMENTS = 1;
	public final static int HASH_COMMENT = 2;


	/**
	 * Constructor
	 *
	 * @param flags flags used for tokenizing
	 */
	public Tokenizer(
		int flags)
	{
		_flags = flags;
	}


	/**
	 * Interface method: build a list given a string
	 *
	 * @param s the string to parse
	 *
	 * @return the LogoList generated
	 *
	 * @exception virtuoso.logo.LanguageException parse error
	 */
	public LogoList tokenize(
		String s)
	throws
		LanguageException
	{
		int i;
		
		_index = 0;
		_str = s;
		nextChar();
		
		return new LogoList(build(true));
	}


	/**
	 * Interface method: build a runnable list given a LogoList
	 *
	 * @param ll the list to reparse
	 *
	 * @return the LogoRunnableList generated, in LogoList form
	 *
	 * @exception virtuoso.logo.LanguageException parse error
	 */
	final LogoList tokenizeRunnable(
		LogoList ll)
	throws
		LanguageException
	{
		Vector lis = new Vector();
		int i;
		
		for (i=0; i<ll.length(); i++)
		{
			LogoObject obj = ll.pickInPlace(i);
			if (obj instanceof LogoList)
			{
				lis.addElement(obj);
			}
			else if (obj instanceof LogoWord)
			{
				if (((LogoWord)obj).getType() == LogoWord.TYPE_WORD)
				{
					String str = obj.toString();
					if (str.length() > 0) if (str.charAt(0) == ';')
					{
						break;
					}
					_index = 0;
					_str = obj.unparse();
					nextChar();
					buildRunnable(lis);
				}
				else
				{
					lis.addElement(obj);
				}
			}
		}
		return new LogoList(lis);
	}


	/**
	 * Internal method: build a list with current string and position
	 *
	 * @param toplevel true iff we're at the top level of the parse
	 *
	 * @return the LogoList generated
	 *
	 * @exception virtuoso.logo.LanguageException parse error
	 */
	final private Vector build(
		boolean toplevel)
	throws
		LanguageException
	{
		byte type;
		StringBuffer sb;
		StringBuffer usb;
		Vector lis = new Vector();
		
		while (true)
		{
			skipWhiteSpace();
			switch(_ch)
			{
				case '[':
					nextChar();
					lis.addElement(new LogoList(build(false)));
					break;
				case ']':
					if (toplevel)
					{
						throw new LanguageException("Unmatched closing bracket");
					}
					else
					{
						nextChar();
						return lis;
					}
				
				case 0:
					if (!toplevel)
					{
						throw new LanguageException("Unmatched opening bracket", '~');
					}
					else
					{
						return lis;
					}
				
				default:
					if (isComment())
					{
						while (_ch != '\n' && _ch != '\r' && _ch != 0)
						{
							nextChar();
						}
						break;
					}
					sb = new StringBuffer();
					usb = new StringBuffer();
					while (true)
					{
						if (isWhiteSpace() || _ch == '[' || _ch == ']' || _ch == 0)
						{
							break;
						}
						if (isComment())
						{
							break;
						}
						usb.append(_ch);
						if (_ch == '\\')
						{
							nextChar();
							if (_ch == 0)
							{
								throw new LanguageException("Hanging backslash", '\\');
							}
							usb.append(_ch);
							appendEscape(sb);
							nextChar();
						}
						else if (_ch == '|')
						{
							nextChar();
							while (true)
							{
								if (_ch == 0)
								{
									throw new LanguageException("Unmatched \"|\"", '|');
								}
								usb.append(_ch);
								if (_ch == '|')
								{
									nextChar();
									break;
								}
								else
								{
									sb.append(_ch);
									nextChar();
								}
							}
						}
						else
						{
							sb.append(_ch);
							nextChar();
						}
					}
					lis.addElement(new LogoWord(sb.toString(), usb.toString()));
					break;
			}
		}
	}


	/**
	 * Internal method: build a list with current string and position
	 *
	 * @param lis the LogoList generated
	 *
	 * @exception virtuoso.logo.LanguageException parse error
	 */
	final private void buildRunnable(
		Vector lis)
	throws
		LanguageException
	{
		StringBuffer sb;
		StringBuffer usb;
		boolean starting = true;
		
		while (true)
		{
			switch(_ch)
			{
				case '(':
				case ')':
				case '+':
				case '*':
				case '/':
				case '=':
					lis.addElement(new LogoWord(_ch));
					starting = (_ch == '(');
					nextChar();
					break;
				
				case '-':
					nextChar();
					if (starting && !isWhiteSpace() && _ch != '\0')
					{
						lis.addElement(new LogoWord("-", "-"));
					}
					else
					{
						lis.addElement(new LogoWord('-'));
					}
					starting = false;
					break;
				
				case '<':
					nextChar();
					if (_ch == '=')
					{
						lis.addElement(new LogoWord('<', '='));
						nextChar();
					}
					else
					{
						lis.addElement(new LogoWord('<'));
					}
					starting = false;
					break;
				
				case '>':
					nextChar();
					if (_ch == '=')
					{
						lis.addElement(new LogoWord('>', '='));
						nextChar();
					}
					else
					{
						lis.addElement(new LogoWord('>'));
					}
					starting = false;
					break;
				
				case 0:
					return;
				
				default:
					boolean quoted = (_ch == '\"');
					sb = new StringBuffer();
					usb = new StringBuffer();
					while (true)
					{
						if (isWhiteSpace() || _ch == 0 || _ch == '(' || _ch == ')')
						{
							break;
						}
						if (!quoted)
						{
							if (_ch == '*' || _ch == '/' || _ch == '+' || _ch == '-' ||
								_ch == '=' || _ch == '<' || _ch == '>')
							{
								break;
							}
						}
						usb.append(_ch);
						if (_ch == '\\')
						{
							nextChar();
							if (_ch == 0)
							{
								throw new LanguageException("Hanging backslash", '\\');
							}
							usb.append(_ch);
							appendEscape(sb);
							nextChar();
						}
						else if (_ch == '|')
						{
							nextChar();
							while (true)
							{
								if (_ch == 0)
								{
									throw new LanguageException("Unmatched \"|\"", '|');
								}
								usb.append(_ch);
								if (_ch == '|')
								{
									nextChar();
									break;
								}
								else
								{
									sb.append(_ch);
									nextChar();
								}
							}
						}
						else
						{
							sb.append(_ch);
							nextChar();
						}
					}
					lis.addElement(new LogoWord(sb.toString(), usb.toString()));
					starting = false;
					break;
			}
		}
	}


	/**
	 * Is current character a whitespace character
	 */
	final private boolean isWhiteSpace()
	{
		return (_ch == ' ' || _ch == '\t' || _ch == '\n' || _ch == '\r');
	}


	/**
	 * Is current character a comment delimiter
	 */
	final private boolean isComment()
	{
		return ((_flags & HONOR_COMMENTS) != 0 &&
			_ch == ';' || (_ch == '#' && (_flags & HASH_COMMENT) != 0));
	}


	/**
	 * Skip white space in the string
	 */
	final private void skipWhiteSpace()
	{
		while (isWhiteSpace())
		{
			nextChar();
		}
	}


	/**
	 * Get next character in the string
	 */
	final private void nextChar()
	{
		_ch = (_index == _str.length()) ? 0 : _str.charAt(_index++);
	}


	/**
	 * Append an escaped character to the stringbuffer
	 *
	 * @param sb the stringbuffer to add to
	 */
	final private void appendEscape(
		StringBuffer sb)
	{
		if (_ch == 'n')
		{
			sb.append('\n');
		}
		else if (_ch == 't')
		{
			sb.append('\t');
		}
		else if (_ch == 'r')
		{
			sb.append('\r');
		}
		else if (_ch == '0')
		{
			sb.append('\0');
		}
		else
		{
			sb.append(_ch);
		}
	}

}



