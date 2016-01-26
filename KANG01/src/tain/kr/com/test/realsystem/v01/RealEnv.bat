@echo off
:: ------------------------------------------------------------------

set MAIN_CLASS=tain.kr.test.realsystem.v01.RealSystem

:: ------------------------------------------------------------------

set JAVA_HOME=K:\PROG\jdk1.6.0_45
set JRE_HOME=%JAVA_HOME%

:: set JAVA_EXE=%JAVA_HOME%\bin\javaw.exe
set JAVA_EXE=%JAVA_HOME%\bin\java.exe

:: ------------------------------------------------------------------

set HOME_PATH=K:\WORK\workspace\KANG
set MAIN_PATH=%HOME_PATH%\bin

:: ------------------------------------------------------------------

set CP=%MAIN_PATH%
:: set CP=
set CP=%CP%;%JAVA_HOME%\lib\tools.jar
set CP=%CP%;%JRE_HOME%\lib\rt.jar
set CP=%CP%;%JRE_HOME%\lib\resources.jar
set CP=%CP%;%JRE_HOME%\lib\jsse.jar
set CP=%CP%;%JRE_HOME%\lib\jce.jar
set CP=%CP%;%JRE_HOME%\lib\charsets.jar
set CP=%CP%;%JRE_HOME%\lib\ext\dnsns.jar
set CP=%CP%;%JRE_HOME%\lib\ext\localedata.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunjce_provider.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunmscapi.jar
set CP=%CP%;%JRE_HOME%\lib\ext\sunpkcs11.jar

set CP=%CP%;.\modules.tain.0.151031.jar

:: ------------------------------------------------------------------

set OPTION=-Xms512m -Xmx1024m
set OPTION=%OPTION% -cp %CP%
set OPTION=%OPTION% -Ddev.author="Kang Seok a director of TAIN Inc."
set OPTION=%OPTION% -Ddev.version=1.7.0.80
set OPTION=%OPTION% -Dcatalina.base=N:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0

:: ------------------------------------------------------------------

@echo on

%JAVA_EXE% %OPTION%  %MAIN_CLASS%

pause

