@REM Author: Barry Searle
@REM
@REM (C) Copyright IBM Corp. 2004,2005 - All Rights Reserved.
@REM DISCLAIMER:
@REM The following source code is sample code created by IBM Corporation.
@REM This sample code is not part of any standard IBM product and is provided
@REM to you solely for the purpose of assisting you in the development of your
@REM applications. The code is provided 'AS IS', without warranty or condition
@REM of any kind. IBM shall not be liable for any damages arising out of your
@REM use of the sample code, even if IBM has been advised of the possibility of
@REM such damages.
@REM
@REM Change History:
@REM 1.3 (22apr2005) eliminated D_WAS param, D_DIST optional 
@REM 1.2 (14jan2005) use automatically determined D_XXXX envars
@REM 1.1 (17nov2004) initial version
@REM

setlocal
@rem ####### ONLY 'WAS_ROOT' MUST BE SET #######
set WAS_ROOT=F:\WAS60\Appsvr\profiles\ND6

@rem ####### disable ('@REM') to bypass browser check #######
set SERVERHTTP=bcsearle2:9080
set APP_CONTEXT=AdderWAR/AdderTest.jsp

@rem ####### NOT NORMALLY USED #######
@rem set JACLbaseDir=G:/RAD60/rwd/eclipse/plugins/com.ibm.etools.j2ee.ant_6.0.0.002/Example/AdderDeploy
@rem set JACLdistDir=%JACLbaseDir%/dist

@rem ####### NO LONGER USED #######
@rem set JACLWASROOT=F:/WAS60/Appsvr

@rem removes any previous WAS_USER_SCRIPT (affects wsadmin)
@set WAS_USER_SCRIPT=

@set D_ROOT=%~dp0
@set script=%D_ROOT%automatedDeploy.jacl
@set D_ACTION=install
@set D_STAGE=-pilot
@set D_FAIL=true
@set D_DIST=%~dp0dist

@if $%1$==$ant$ goto ant

:jacl
@rem                                         action     stage   fail   "%JACLdistDir%"
@echo FIRST install ...
call "%WAS_ROOT%\bin\wsadmin" -f "%script%"  %D_ACTION% %D_STAGE% %D_FAIL%

@echo.
@echo SIMULATE OPERATOR MANUAL CHECK (BROWSE APP) ...
@if not $%SERVERHTTP%$==$$ call EXPLORER.EXE http://%SERVERHTTP%/%APP_CONTEXT%
@if not $%SERVERHTTP%$==$$ pause

@echo SECOND update ...
call "%WAS_ROOT%\bin\wsadmin" -f "%script%"  update    -pilot   false

@echo.
@echo THIRD confirm ...
call "%WAS_ROOT%\bin\wsadmin" -f "%script%"  confirm   -pilot

@echo.
@echo FOURTH uninstall ...
call "%WAS_ROOT%\bin\wsadmin" -f "%script%"  uninstall

@goto done

:ant
REM ####### edit automatedD_xml  to set WASROOT location #######
call "%WAS_ROOT%\bin\ws_ant" -buildfile "%D_ROOT%/automatedD_xml"

:done
@echo DONE.
