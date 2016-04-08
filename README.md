# [ eclipse_GIT2 ]

About eclipse_GIT2
----------------------------------------------------------------------------------------
KANG01 is a new and very fast sample java source. It aims to be a lightweight source available
through all major platforms. This project has been originally started only for educational purposes.
But from its start, KANG01 has grown into a feature-rich source.

KANG01 has all standard package you expect from a source. It includes bookmarks, history (both also in sidebar)
and tabs. Above that, it has by default enabled blocking ads with a built-in plugin.

	<?xml version="1.0" encoding="UTF-8"?>
	<classpath>
		<classpathentry kind="src" path="src"/>
		<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
		<classpathentry kind="con" path="org.eclipse.jdt.USER_LIBRARY/KANG01_libs"/>
		<classpathentry kind="output" path="bin"/>
	</classpath>

	ant.jar
	commons-net-3.3.jar
	log4j-1.2.17.jar

You have a choice -> Window > Preferences > type filter text : `encod`
 * General > Content Types
 * General > Workspace > Text file encoding : EUC-KR, New text file line delimiter : unix
 * General > Content Types > Text > Runtime log files > *.log (locked) : Default encoding : UTF-8 -> Update, *.log files is set encoding character set to be UTF-8.

You have to set Preferences > Java > Code Style > Code Templates > Import : codetemplates_20160201.xml 

You have to set Preferences > General > Appearance > Colors and Fonts

 * Basic > Text Editor Block Selection Font  : FONT-9
 * Basic > Text Font                         : FONT-9

You have to set Preferences > Java > Editor > Content Assist > Syntax Coloring     To Good Viewer

----------------------------------------------------------------------------------------

01. [Subversion-Tools - http://pyrasis.com/private/2014/07/19/open-windows-project-essential-utility-book](http://pyrasis.com/private/2014/07/19/open-windows-project-essential-utility-book)
02. [Subversion - http://subversion.tigris.org/servlets/ProjectDocumentList?folderID=91](http://subversion.tigris.org/servlets/ProjectDocumentList?folderID=91)
03. [MAVEN-jar - http://central.maven.org/maven2](http://central.maven.org/maven2/)
04. [JAVA7 - http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
05. [w3schools - www.w3schools.com](http://www.w3schools.com/)
06. [devdocs - devdocs.io](http://devdocs.io/)
07. [jQuery - jquery.com](http://jquery.com/)
08. [tomcat - tomcat.apache.org](http://tomcat.apache.org/)
09. [commons apache - commons.apache.org](http://commons.apache.org/)
10. [eclipse - www.eclipse.org](http://www.eclipse.org/)
11. [java oracle - www.oracle.com/java/](https://www.oracle.com/java/index.html)
12. [node.js - nodejs.org](https://nodejs.org/en/)
13. [KornShell - www.kornshell.com](http://www.kornshell.com/) [cafe.naver.com/itupandup/3490](http://cafe.naver.com/itupandup/3490)






----------------------------------------------------------------------------------------

Copyright 2014, 2015, 2016 TAIN, Inc. all rights reserved.

Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.gnu.org/licenses/

Unless reguired by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

----------------------------------------------------------------------------------------

Before you start compiling, make sure that you have installed the Qt (>= 5.6) development libraries
and you have read the [BUILDING](https://github.com/QupZilla/qupzilla/blob/master/BUILDING) information.

**Linux**

 * OpenSSL (libcrypto) is required
 * xcb libraries when building without NO_X11

**Windows**
 * OpenSSL (libeay32) is required

Then you can start compiling by running this commands:

    $ qmake
    $ make

After a successful compilation the executable binary can be found in the bin/ directory.
On Fedora and possibly other Linux distributions you need to replace `qmake` with `qmake-qt5`.

On Linux/Unix: To install QupZilla, run this command: (it may be necessary to run it as root)

    $ make install

On Mac OS X: To deploy QupZilla in dmg image, run this command:

    $ ./mac/macdeploy.sh full-path-to-macdeployqt

You need to specify path to `macdeployqt` only if it is not in PATH.


QupZilla Web Browser - QtWebEngine
----------------------------------------------------------------------------------------

[![Build Status](https://travis-ci.org/QupZilla/qupzilla.svg?branch=master)](https://travis-ci.org/QupZilla/qupzilla)  
Homepage: [http://www.qupzilla.com](http://www.qupzilla.com)  
Blog: [http://blog.qupzilla.com](http://blog.qupzilla.com)  
IRC: `#qupzilla` at `irc.freenode.net`  
Translations: [https://www.transifex.com](https://www.transifex.com/projects/p/qupzilla)

THIS IS QTWEBENGINE BRANCH
----------------------------------------------------------------------------------------

Master branch is now for QtWebEngine (>= 5.6).
  
For QtWebEngine (= 5.5), please use [qt5.5](https://github.com/QupZilla/qupzilla/tree/qt5.5)  
For QtWebKit, please use [v1.8](https://github.com/QupZilla/qupzilla/tree/v1.8)

About QupZilla
----------------------------------------------------------------------------------------

QupZilla is a new and very fast QtWebEngine browser. It aims to be a lightweight web browser
available through all major platforms. This project has been originally started only
for educational purposes. But from its start, QupZilla has grown into a feature-rich browser.

QupZilla has all standard functions you expect from a web browser. It includes bookmarks,
history (both also in sidebar) and tabs. Above that, it has by default enabled blocking ads
with a built-in AdBlock plugin.

History
----------------------------------------------------------------------------------------

The very first version of QupZilla has been released in December 2010 and it was written
in Python with PyQt4 bindings. After a few versions, QupZilla has been completely rewritten
in C++ with the Qt Framework. The Windows version of QupZilla was compiled using MinGW, but due to
a huge problem with Flash, it is now compiled with Microsoft Visual C++ Compiler 2008.
First public release was 1.0.0-b4.

Until version 2.0, QupZilla was using QtWebKit. QtWebKit is now deprecated and new versions
are using QtWebEngine.

Compiling
----------------------------------------------------------------------------------------

Before you start compiling, make sure that you have installed the Qt (>= 5.6) development libraries
and you have read the [BUILDING](https://github.com/QupZilla/qupzilla/blob/master/BUILDING) information.

**Linux**

 * OpenSSL (libcrypto) is required
 * xcb libraries when building without NO_X11

**Windows**
 * OpenSSL (libeay32) is required

Then you can start compiling by running this commands:

    $ qmake
    $ make

After a successful compilation the executable binary can be found in the bin/ directory.
On Fedora and possibly other Linux distributions you need to replace `qmake` with `qmake-qt5`.

On Linux/Unix: To install QupZilla, run this command: (it may be necessary to run it as root)

    $ make install

On Mac OS X: To deploy QupZilla in dmg image, run this command:

    $ ./mac/macdeploy.sh full-path-to-macdeployqt

You need to specify path to `macdeployqt` only if it is not in PATH.

Current version
----------------------------------------------------------------------------------------

The current stable version of QupZilla is 1.8.9. You can download precompiled packages
and the sources from the download section at [homepage](http://www.qupzilla.com/download).
However, if you want the latest revision, just take the latest code snapshot either by
downloading a tarball or running:

    $ git clone git://github.com/QupZilla/qupzilla.git

If you are using Ubuntu, you can download QupZilla from PPA:

    $ sudo add-apt-repository ppa:nowrep/qupzilla
    $ sudo apt-get update
    $ sudo apt-get install qupzilla

FAQ and Changelog
----------------------------------------------------------------------------------------

If you are experiencing some sort of problem, please read the FAQ before you open an issue.

[FAQ](https://github.com/QupZilla/qupzilla/wiki/FAQ) | [Changelog](https://github.com/QupZilla/qupzilla/blob/master/CHANGELOG) | [Bug Reports](https://github.com/QupZilla/qupzilla/wiki/Bug-Reports)
