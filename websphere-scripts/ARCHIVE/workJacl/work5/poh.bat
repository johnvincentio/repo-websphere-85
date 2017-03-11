@echo off
rem
rem  script to create the Capstone Project resources
rem
rem AdminConfig can be run without server running
rem AdminControl and AdminApp require the server be running
rem
rem C:\Program Files\IBM\Rational\SDP\6.0\runtimes\base_v6\profiles\AppSrv02\bin

set JVWSADMIN="C:\Program Files\IBM\Rational\SDP\6.0\runtimes\base_v6\profiles\AppSrv02\bin\wsadmin.bat"

rem
rem	AppSrv02
rem
rem bootstrap = 2811
rem soap connector = 8882
rem wc_admin = 9062
rem browser = 9082
rem

echo Before wsadmin
%JVWSADMIN% -f irac.jacl -conntype SOAP -port 8883
echo After wsadmin
