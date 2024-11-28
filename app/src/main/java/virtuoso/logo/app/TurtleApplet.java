/*
===============================================================================

	FILE:  TurtleApplet.java
	
	PROJECT:
	
		Turtle Tracks
	
	CONTENTS:
	
		Applet frame
	
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
import virtuoso.logo.lib.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


/**
 * Applet frame
 */

public class TurtleApplet
extends Applet
implements
	Runnable,
	ActionListener
{

	private Thread machineThread;
	private Machine machine;
	private Label label;
	private Button button;
	private Thread myThread;


	/**
	 * Constructor
	 */
	public TurtleApplet()
	{
		machineThread = null;
		machine = null;
	}


	/**
	 * Initialize
	 */
	public void init()
	{
		setLayout(new GridLayout(0, 1));
		label = new Label("Initializing...", Label.CENTER);
		button = new Button("Start");
		button.setEnabled(false);
		button.addActionListener(this);
		add(label);
		add(button);
		myThread = new Thread(this);
		myThread.start();
	}


	/**
	 * Destroy
	 */
	public void destroy()
	{
		if (machineThread != null)
		{
			if (!machineThread.isAlive())
			{
				machine.console().goodbye();
				machine.terminateAllThreads();
				try
				{
					machineThread.join();
				}
				catch (InterruptedException e) {}
			}
		}
	}


	/**
	 * Called on action
	 *
	 * @param event the event
	 */
   	public void actionPerformed(
		ActionEvent event)
	{
		if (machineThread != null)
			if (machineThread.isAlive())
				return;
		
		synchronized (this)
		{
			label.setText("Turtle Tracks Loading...");
			button.setEnabled(false);
			
			notifyAll();
		}
	}


	/**
	 * Run initializer thread
	 */
	public void run()
	{
		while (true)
		{
			synchronized (this)
			{
				label.setText("Turtle Tracks Stopped");
				button.setEnabled(true);
				try
				{
					wait();
				}
				catch (InterruptedException e) {}
			}
			
			Console console = new GuiConsole();
			PrimitiveGroup[] primitiveGroups = new PrimitiveGroup[5];
			primitiveGroups[0] = new StandardPrimitives();
			primitiveGroups[1] = new NetworkPrimitives();
			primitiveGroups[2] = new ThreadPrimitives();
			primitiveGroups[3] = new LibraryPrimitives();
			primitiveGroups[4] = new TurtlePrimitives();
			
			machine = new Machine(console, primitiveGroups);
			try
			{
				machine.setup();
			}
			catch (SetupException e)
			{
				System.err.println("The environment couldn't be started.");
				System.err.println(e.getMessage());
				return;
			}
			
			machineThread = new Thread(console);
			machineThread.start();
			
			label.setText("Turtle Tracks Running");
			
			try
			{
				machineThread.join();
			}
			catch (InterruptedException e) {}
			
			machine.cleanup();
			
			label.setText("Turtle Tracks Stopped");
		}
	}

}



