@echo off
:: ------------------------------------------------------------------

set MAIN_CLASS=tain.kr.test.dev2real.v01.Dev2RealTestMain

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

:: ------------------------------------------------------------------

set OPTION=-Xms512m -Xmx1024m
set OPTION=%OPTION% -cp %CP%
set OPTION=%OPTION% -Ddev.author=Kang_Seok
set OPTION=%OPTION% -Ddev.version=1.6.0_45

set OPTION=%OPTION% -Dtain.test.sas.hostinfo=matcmsapp
set OPTION=%OPTION% -Dtain.test.sas.transfer.root.path=D:/PROJ/TEMP/emart
set OPTION=%OPTION% -Dtain.test.sas.transfer.file.ext=.sas
set OPTION=%OPTION% -Dtain.test.sas.transfer.info=CMSVIEW_DEV:CMSVIEW;CMSDATA_DEV:CMSDATA



:: ------------------------------------------------------------------

@echo on

%JAVA_EXE% %OPTION%  %MAIN_CLASS%

::pause

