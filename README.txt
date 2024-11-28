
Turtle Tracks source distribution
version 1.0 (13 November 1999)
Copyright (C) 1997-2024  Daniel Azuma
All rights reserved worldwide


==============================================================================


CONTENTS

   1) Introduction
   
   2) Terms and conditions
   
   3) Release notes 1.0
   
   4) Compiling the source
   
   5) For more information


==============================================================================


INTRODUCTION


    Turtle Tracks is a full-featured Logo interpreter for the Java
    platform. It supports the same basic Logo language constructs that
    other common implementations such as Berkeley Logo provide, including
    the basic list processing operations, and of course, turtle graphics.
    Turtle Tracks also includes a number of additional features such as
    threads and networking. And because it uses Java technology, Turtle
    Tracks can be run on many different platforms, including Linnux and
    other unix implementations, Macintosh, and Windows. You can even run
    it as an applet in your web browser.
    
    Turtle Tracks is also designed to be extensible. If you are a Java
    programmer, you can write your own customized primitive sets in Java
    and easily integrate them into the Turtle Tracks Logo environment.
    It is even possible to write your own "front-end" user interface to
    Turtle Tracks, or to embed a copy of the interpreter into your own
    Java project for use as, for example, a scripting language.
    
    Turtle Tracks is free software, distributed under the GNU General
    Public License. This means you may download the source code, modify
    it, or write your own programs derived from it, subject to certain
    restrictions.


==============================================================================


TERMS AND CONDITIONS


    BSD 3-Clause License

    Copyright (C) 1997-2024  Daniel Azuma  (dazuma@kagi.com)
    
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
    
    1. Redistributions of source code must retain the above copyright notice, this
       list of conditions and the following disclaimer.
    
    2. Redistributions in binary form must reproduce the above copyright notice,
       this list of conditions and the following disclaimer in the documentation
       and/or other materials provided with the distribution.
    
    3. Neither the name of the copyright holder nor the names of its
       contributors may be used to endorse or promote products derived from
       this software without specific prior written permission.
    
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
    FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
    DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
    CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


==============================================================================


RELEASE NOTES


    Turtle Tracks - source distribution
    Version 1.0 - 13 November 1999
    These notes detail the differences between versions 1.0fc2 and 1.0.
    Earlier notes can be found on the Turtle Tracks web site.


Features added
--------------

    None.


Features changed
----------------

    Cleaned up the wording of a few of the error messages.

    Default window size for turtle graphics is now 400x400.


Bugs fixed
----------

    The graphical console and editor windows were not resizeable. Fixed.

    Under some X11-based window systems, the close-window confirmation
    dialogs filled up the screen. Fixed.


Internal changes
----------------

    None.


Known issues
------------

    If you open a text file with Mac-style newlines (CR only) using random
    access (OPENUPDATE), the primitives READLIST and READWORD fail to
    recognize newlines under JDK 1.1. This is a bug in the JDK, and is
    fixed under Java 2 or later.

    Some web browsers have bugs or missing features that prevent them from
    running the Turtle Tracks applet well. Under Windows-based systems,
    Microsoft Internet Explorer's java implementation is buggy and behaves
    strangely in some cases when running Turtle Tracks. Windows users
    should use Netscape Communicator 4.5 or later instead. On Macintosh
    systems, Netscape products do not fully implement Java 1.1, and
    therefore will not run Turtle Tracks at all. Mac users should instead
    use Internet Explorer 4.0 or later, with the Java virtual machine set
    to Mac OS Runtime for Java. Other platforms may exhibit similar issues.
    See the applet page on the Turtle Tracks home page for details and
    up-to-date information. These issue affect only the applet, not the
    stand-alone application.


==============================================================================


COMPILING THE SOURCE


    Java Development Kit (JDK)
    --------------------------

    This section describes how to build Turtle Tracks using Sun's Java
    Development Kit (JDK).
    
    The source for Turtle Tracks is split into three packages. The
    package "virtuoso.logo" contains the core interpreter classes, which
    are necessary for any program based directly on the Turtle Tracks
    virtual machine. The package "virtuoso.logo.lib" contains the
    standard plug-in primitive sets used by the interactive interpreter.
    The package "virtuoso.logo.app" contains the classes that implement
    the interactive interpreter itself.
    
    You will need to javac *.java each of the three packages to perform
    a complete build of Turtle Tracks. There does not exist one single
    class that may be used as a bootstrap, because some dependencies
    exist only through ClassLoader.forName() classloader semantics.
    
    You may then wish to use the jar tool to generate a jar file
    containing all the classes generated.


    Metrowerks Codewarrior for Mac
    ------------------------------

    I do most of the Turtle Tracks development work using Metrowerks
    Codewarrior Pro for the Apple Macintosh. As such, the Mac source
    distribution includes a preconfigured Codewarrior project file that
    directly generates a jar file. The project is intended for use with
    Codewarrior Pro release 5 with the version 5.2 factory update (IDE
    version 4.0.2) or later. Metrowerks can be reached at
    http://www.metrowerks.com/


==============================================================================


FOR MORE INFORMATION

    For up-to-date information, please visit the Turtle Tracks home page,
    located at http://www.ugcs.caltech.edu/~dazuma/turtle/
    
    The author can be reached via email at dazuma@kagi.com


