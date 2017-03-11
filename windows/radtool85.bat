@echo off
rem
rem script to run RadTool
rem
set JAVA_HOME=C:\Program Files (x86)\Java\jre7
set path=%JAVA_HOME%\bin;%PATH%
rem
set DEV_HOME=C:\jvDevelopment\repo_websphere_85\eclipse_jee\RadTool
rem
set Classpath=%DEV_HOME%\RadTool\bin;
set Classpath=%Classpath%;%JAVA_HOME%\lib
rem
java com.idc.rad.gui.App 85 C:\jvDevelopment\repo_websphere_85\windows\radtool85.properties
