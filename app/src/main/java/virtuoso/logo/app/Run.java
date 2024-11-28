/*
===============================================================================

	FILE:  Run.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Runs logo
	
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
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;


/**
 * Runs logo
 */

public class Run
{

	/**
	 * No instantiation of this class
	 */
	private Run()
	{
	}


	/**
	 * Main program
	 *
	 * @param args command line arguments
	 */
	public static void main(
		String[] args)
	{
		PrimitiveGroup[] primitiveGroups;
		Console console = null;
		boolean hashComments = false;
		
		Vector names = new Vector();
		Vector vals = new Vector();
		
		// Parse command line
		int i=0;
		String consoleName = "virtuoso.logo.lib.CliConsole";
		Vector primitiveNames = new Vector();
		while (i < args.length)
		{
			if (args[i].equals("-hash"))
			{
				i++;
				hashComments = true;
			}
			else if (args[i].equals("-c"))
			{
				i++;
				if (i == args.length)
				{
					System.err.println("Usage problem: -c expects a console class name");
					System.exit(-1);
				}
				if (args[i].startsWith("-"))
				{
					System.err.println("Usage problem: -c expects a console class name");
					System.exit(-1);
				}
				consoleName = args[i];
				i++;
			}
			else if (args[i].equals("-p"))
			{
				i++;
				boolean gotOne = false;
				while (true)
				{
					if (i == args.length)
						break;
					if (args[i].startsWith("-"))
						break;
					if (!primitiveNames.contains(args[i]))
					{
						primitiveNames.addElement(args[i]);
					}
					gotOne = true;
					i++;
				}
				if (!gotOne)
				{
					System.err.println("Usage problem: -p expects primitive group class names");
					System.exit(-1);
				}
			}
			else if (args[i].equals("-std"))
			{
				if (!primitiveNames.contains("virtuoso.logo.lib.StandardPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.StandardPrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.FilePrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.FilePrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.NetworkPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.NetworkPrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.ThreadPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.ThreadPrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.ShellPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.ShellPrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.LoaderPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.LoaderPrimitives");
				}
				if (!primitiveNames.contains("virtuoso.logo.lib.LibraryPrimitives"))
				{
					primitiveNames.addElement("virtuoso.logo.lib.LibraryPrimitives");
				}
				i++;
			}
			else if (args[i].equals("-dw"))
			{
				i++;
				if (i == args.length)
				{
					System.err.println("Usage problem: -dw expects a name");
					System.exit(-1);
				}
				names.addElement(new CaselessString(args[i]));
				i++;
				if (i == args.length)
				{
					System.err.println("Usage problem: -dw expects a value");
					System.exit(-1);
				}
				vals.addElement(new LogoWord(args[i]));
				i++;
			}
			else if (args[i].equals("-dl"))
			{
				i++;
				if (i == args.length)
				{
					System.err.println("Usage problem: -dl expects a name");
					System.exit(-1);
				}
				names.addElement(new CaselessString(args[i]));
				i++;
				if (i == args.length)
				{
					System.err.println("Usage problem: -dl expects a value");
					System.exit(-1);
				}
				try
				{
					vals.addElement(new Tokenizer(0).tokenize(args[i]));
				}
				catch (LanguageException e)
				{
					System.err.println("Usage problem: -dl parser: " + e.getMessage());
					System.exit(-1);
				}
				i++;
			}
			else
			{
				System.err.println("Usage problem: unknown option " + args[i]);
				System.exit(-1);
			}
		}
		
		// Load console
		try
		{
			Class theClass = Class.forName(consoleName);
			console = (Console)(theClass.newInstance());
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Console class " + consoleName + " not found.");
			System.exit(-1);
		}
		catch (ClassCastException e)
		{
			System.err.println("Console class " + consoleName + " isn't a Console.");
			System.exit(-1);
		}
		catch (InstantiationException e)
		{
			System.err.println("Console class " + consoleName + " couldn't be instantiated.");
			System.exit(-1);
		}
		catch (IllegalAccessException e)
		{
			System.err.println("Console class " + consoleName + " not accessible.");
			System.exit(-1);
		}
		
		// Load primitive groups
		primitiveGroups = new PrimitiveGroup[primitiveNames.size()];
		try
		{
			for (i=0; i<primitiveNames.size(); i++)
			{
				Class theClass = Class.forName((String)(primitiveNames.elementAt(i)));
				primitiveGroups[i] = (PrimitiveGroup)(theClass.newInstance());
			}
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("PrimitiveGroup class " + (String)(primitiveNames.elementAt(i)) +
				" not found.");
			System.exit(-1);
		}
		catch (ClassCastException e)
		{
			System.err.println("PrimitiveGroup class " + (String)(primitiveNames.elementAt(i)) +
				" isn't a PrimitiveGroup.");
			System.exit(-1);
		}
		catch (InstantiationException e)
		{
			System.err.println("PrimitiveGroup class " + (String)(primitiveNames.elementAt(i)) +
				" couldn't be instantiated.");
			System.exit(-1);
		}
		catch (IllegalAccessException e)
		{
			System.err.println("PrimitiveGroup class " + (String)(primitiveNames.elementAt(i)) +
				" not accessible.");
			System.exit(-1);
		}
		
		// Create machine
		Machine mach = new Machine(console, primitiveGroups);
		mach.setHashComments(hashComments);
		try
		{
			mach.setup();
		}
		catch (SetupException e)
		{
			System.err.println("The environment couldn't be started.");
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		
		// Define default symbols
		for (i=0; i<names.size(); i++)
		{
			mach.makeName((CaselessString)(names.elementAt(i)),
				(LogoObject)(vals.elementAt(i)));
		}
		names = null;
		vals = null;
		
		// Run
		console.run();
		
		// clean up
		mach.cleanup();
		try
		{
			System.exit(0);
		}
		catch (Exception e) {}
	}

}



