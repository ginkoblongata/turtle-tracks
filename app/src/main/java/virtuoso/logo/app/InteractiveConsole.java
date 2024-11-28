/*
===============================================================================

	FILE:  ConsoleInterface.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Interface to console objects
	
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

import virtuoso.logo.*;


/**
 * Base class for interactive consoles. Provides a run method that implements
 * an interactive Logo interpreter.
 */

public abstract class InteractiveConsole
extends Console
{

	private boolean _goodbye;
	protected int _priority;


	/**
	 * Default constructor
	 */
	public InteractiveConsole()
	{
	}


	/**
	 * Notify the console that the machine threw "goodbye"
	 */
	public void goodbye()
	{
		_goodbye = true;
	}


	/**
	 * Default (interactive) run method
	 */
	public void run()
	{
		Tokenizer tokenizer = new Tokenizer(_mach.getTokenizerCommentFlags());
		_priority = Thread.currentThread().getPriority();
		_goodbye = false;
		
		putStatusMessage("");
		putStatusMessage("Turtle Tracks Interactive Interpreter");
		putStatusMessage("version 1.0  (13 November 1999)");
		putStatusMessage("Copyright (C) 1997-1999  Daniel Azuma  (dazuma@kagi.com)");
		putStatusMessage("Turtle Tracks comes with absolutely no warranty.");
		putStatusMessage("This is free software, and you are welcome to redistribute it under");
		putStatusMessage("certain conditions. For details, see the GNU General Public License.");
		putStatusMessage("");
		putStatusMessage("Welcome to Logo!");
		putStatusMessage("");
		
		while (!_goodbye)
		{
			try
			{
				if (eof())
				{
					break;
				}
				LogoList cmdLine = null;
				LanguageException lastException = null;
				StringBuffer cumulative = new StringBuffer();
				char promptChar = '?';
				while (cmdLine == null)
				{
					if (eof())
					{
						break;
					}
					String line = promptGetLine(promptChar);
					if (line == null)
					{
						_goodbye = true;
						if (lastException != null)
						{
							throw lastException;
						}
					}
					else
					{
						try
						{
							cumulative.append(line);
							if (cumulative.length() == 0)
							{
								cmdLine = new LogoList();
							}
							else
							{
								if (cumulative.charAt(cumulative.length()-1) == '~')
								{
									cumulative.setCharAt(cumulative.length()-1, ' ');
									promptChar = '~';
								}
								else
								{
									cmdLine = tokenizer.tokenize(cumulative.toString());
								}
							}
						}
						catch (LanguageException e)
						{
							lastException = e;
							promptChar = e.getContChar();
							if (promptChar == '|' || promptChar == '\\' || promptChar == '~')
							{
								cumulative.append(Machine.LINE_SEPARATOR);
							}
							else
							{
								throw e;
							}
						}
					}
				}
				if (cmdLine.length() > 0)
				{
					_mach.spawnMainThread(cmdLine);
					_mach.waitForAllTerminated();
				}
			}
			catch (LanguageException e)
			{
				putLine(e.generateMessage());
			}
			catch (Exception e)
			{
				System.err.println("Machine exception: " + e.toString());
				e.printStackTrace();
			}
		}
		
		putStatusMessage("");
		putStatusMessage("Exiting Turtle Tracks.");
	}

}



