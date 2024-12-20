/*
===============================================================================
	
	FILE: InterpreterThread.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Interpreter thread
	
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

import java.util.Stack;


/**
 * Interpreter thread
 */

public final class InterpreterThread
extends Thread
{

	private Machine _mach;
	private Console _console;
	
	private CaselessString _id;
	private IOBase _ins;
	private IOBase _outs;
	private CaselessString _insid;
	private CaselessString _outsid;
	private IOBase _savedins;
	private boolean _isRootThread;
	private SymbolTable _symStack;
	private LogoList _toInterpret;
	private ParseTree _treeInterpret;
	private boolean _isStopping;


	/**
	 * Constructs a new InterpreterThread
	 *
	 * @param id a unique id word for the thread
	 * @param ll list to interpret
	 * @param isid stream name for the input stream
	 * @param is the actual input stream
	 * @param osid stream name for the output stream
	 * @param os the actual output stream
	 * @param syms the toplevel SymbolTable to use
	 * @param m the machine in use
	 */
	InterpreterThread(
		CaselessString id,
		LogoList ll,
		CaselessString isid,
		IOBase is,
		CaselessString osid,
		IOBase os,
		SymbolTable syms,
		Machine m)
	{
		super();
		_toInterpret = ll;
		_treeInterpret = null;
		_id = id;
		_mach = m;
		_console = m.console();
		_insid = isid;
		_outsid = osid;
		_ins = is;
		_outs = os;
		_symStack = syms;
		_isRootThread = id.equals(Machine.MAIN_THREAD_NAME);
		_savedins = null;
		_isStopping = false;
	}


	/**
	 * Constructs a new InterpreterThread
	 *
	 * @param id a unique id word for the thread
	 * @param pt ParseTree to interpret
	 * @param isid stream name for the input stream
	 * @param is the actual input stream
	 * @param osid stream name for the output stream
	 * @param os the actual output stream
	 * @param syms the toplevel SymbolTable to use
	 * @param m the machine in use
	 */
	InterpreterThread(
		CaselessString id,
		ParseTree pt,
		CaselessString isid,
		IOBase is,
		CaselessString osid,
		IOBase os,
		SymbolTable syms,
		Machine m)
	{
		super();
		_toInterpret = null;
		_treeInterpret = pt;
		_id = id;
		_mach = m;
		_console = m.console();
		_insid = isid;
		_outsid = osid;
		_ins = is;
		_outs = os;
		_symStack = syms;
		_isRootThread = id.equals(Machine.MAIN_THREAD_NAME);
		_savedins = null;
		_isStopping = false;
	}


	/**
	 * Constructs a new InterpreterThread for use by a console to do internal
	 * tasks such as loading environment files in response to menu commands.
	 *
	 * @param m the Machine in use
	 * @param is the input stream to interpret
	 */
	public InterpreterThread(
		Machine m,
		IOBase is)
	{
		super();
		_toInterpret = null;
		_mach = m;
		_console = m.console();
		_id = new CaselessString(_mach.PRIVATE_NAME);
		_insid = new CaselessString(_mach.PRIVATE_NAME);
		_outsid = new CaselessString(_mach.PRIVATE_NAME);
		_ins = is;
		_outs = _mach.console();
		_symStack = new SymbolTable();
		_isRootThread = true;
		_savedins = null;
		_isStopping = false;
	}


	/**
	 * Is thread stopping?
	 *
	 * @return true if the thread is stopping
	 */
	public final boolean stopping()
	{
		return _isStopping;
	}


	/**
	 * Signal thread to stop
	 */
	public final void signalStop()
	{
		_isStopping = true;
	}


	/**
	 * Accessor for machine
	 *
	 * @return the Machine running this thread
	 */
	public final Machine mach()
	{
		return _mach;
	}


	/**
	 * Accessor for threadid
	 *
	 * @return the thread ID
	 */
	public final CaselessString threadID()
	{
		return _id;
	}


	/**
	 * Accessor for inStream
	 *
	 * @return the current input stream in use by this thread
	 */
	public final IOBase inStream()
	{
		return _ins;
	}


	/**
	 * Accessor for inStreamID
	 *
	 * @return the id of the current input stream in use by this thread
	 */
	public final CaselessString inStreamID()
	{
		return _insid;
	}


	/**
	 * Accessor for outStream
	 *
	 * @return the current output stream in use by this thread
	 */
	public final IOBase outStream()
	{
		return _outs;
	}


	/**
	 * Accessor for outStreamID
	 *
	 * @return the id of the current output stream in use by this thread
	 */
	public final CaselessString outStreamID()
	{
		return _outsid;
	}


	/**
	 * Mutator for inStream
	 *
	 * @param isid new input stream ID
	 * @param is new input stream
	 */
	public final void setInStream(
		CaselessString isid,
		IOBase is)
	{
		_insid = isid;
		_ins = is;
		if (_isRootThread && _toInterpret != null)
		{
			_mach.setInStream(isid, is);
		}
	}


	/**
	 * Mutator for outStream
	 *
	 * @param osid new output stream ID
	 * @param os new output stream
	 */
	public final void setOutStream(
		CaselessString osid,
		IOBase os)
	{
		_outsid = osid;
		_outs = os;
		if (_isRootThread && _toInterpret != null)
		{
			_mach.setOutStream(osid, os);
		}
	}


	/**
	 * Starts loading. Used by the Machine to signal starting to load an environment
	 * file. Should normally not be called any other time
	 *
	 * @param is stream to read
	 */
	public final void startLoading(
		IOBase is)
	{
		_savedins = _ins;
		_ins = is;
	}


	/**
	 * Ends loading. Used by the Machine to signal finishing load of an environment
	 * file. Should normally not be called any other time
	 */
	public final void endLoading()
	{
		_ins = _savedins;
		_savedins = null;
	}


	/**
	 * Is thread loading?
	 *
	 * @return true if the thread is currently loading an environment file
	 */
	public final boolean isLoading()
	{
		return (_savedins != null);
	}


	/**
	 * Enters a procedure in this thread
	 *
	 * @param level local symbol table
	 */
	public final void enterProcedure(
		SymbolTable level)
	{
		_symStack = level.pushOn(_symStack);
	}


	/**
	 * Exits a procedure in this thread
	 */
	public final void exitProcedure()
	{
		_symStack = _symStack.next();
	}


	/**
	 * Declares a local variable
	 *
	 * @param name the name for the new variable
	 *
	 * @exception virtuoso.logo.LanguageException not in a procedure
	 */
	public final void localName(
		CaselessString name)
	throws
		LanguageException
	{
		_symStack.declare(name, _mach);
	}


	/**
	 * Make a variable. If not declared locally, delegates to global symbol table.
	 *
	 * @param name the name of the variable
	 * @param obj value of the variable
	 */
	public final void makeName(
		CaselessString name,
		LogoObject obj)
	{
		for (SymbolTable st = _symStack; st != null; st = st.next())
		{
			if (st.make(name, obj))
			{
				return;
			}
		}
		_mach.makeName(name, obj);
	}


	/**
	 * Get value of a variable. If no local exists, delegates to global symbol table
	 *
	 * @param name the name of the variable
	 *
	 * @return the resolved symbol, or null if not found
	 */
	public final LogoObject resolveName(
		CaselessString name)
	{
		for (SymbolTable st = _symStack; st != null; st = st.next())
		{
			LogoObject obj = st.resolve(name);
			if (obj != null)
			{
				return obj;
			}
		}
		return _mach.resolveName(name);
	}


	/**
	 * Erase a variable in all scopes
	 *
	 * @param name the name of the variable
	 */
	public final void eraseName(
		CaselessString name)
	{
		for (SymbolTable st = _symStack; st != null; st = st.next())
		{
			st.erase(name);
		}
		_mach.eraseName(name);
	}


	/**
	 * Run method of this thread. Interprets the LogoList or ParseTree given
	 * to the thread.
	 */
	public void run()
	{
		try
		{
			LogoObject ret = LogoVoid.obj;
			try
			{
				if (_toInterpret == null && _treeInterpret == null)
				{
					_mach.executeStream(_ins, this);
				}
				else
				{
					if (_treeInterpret == null)
					{
						_treeInterpret = _toInterpret.getRunnable(_mach);
					}
					ret = _treeInterpret.execute(new InterpEnviron(this));
				}
			}
			catch (ThrowException e)
			{
				CaselessString tag = e.getTag();
				if (tag.equals("TOPLEVEL"))
				{
					_mach.terminateAllThreads();
				}
				else if (tag.equals("GOODBYE"))
				{
					_mach.terminateAllThreads();
					_console.goodbye();
				}
				else if (tag.equals("STOP"))
				{
					throw new LanguageException("Can use STOP or OUTPUT only inside a procedure");
				}
				else if (tag.equals("STOPTHREAD") || tag.equals(".SUDDENSTOPTHREAD"))
				{
				}
				else
				{
					_console.putLine("Uncaught: " + tag);
					_console.putLine("... with value " + e.getObj().toString());
					if (!_isRootThread)
					{
						_console.putLine("... in thread " + _id.str);
					}
					_mach.terminateAllThreads();
				}
			}
			if (!_mach.isAutoIgnore() && ret != LogoVoid.obj)
			{
				throw new LanguageException("You don't say what to do with " + ret);
			}
		}
		catch (LanguageException e)
		{
			_console.putLine(e.generateMessage());
			if (!_isRootThread)
			{
				_console.putLine("... in thread " + _id.str);
			}
			_mach.terminateAllThreads();
		}
		catch (Throwable e)
		{
			_console.putLine(e.toString());
			if (!_isRootThread)
			{
				_console.putLine("... in thread " + _id.str);
			}
			e.printStackTrace();
			_mach.terminateAllThreads();
		}
		finally
		{
			_mach.threadEnding(_id);
		}
	}


}



