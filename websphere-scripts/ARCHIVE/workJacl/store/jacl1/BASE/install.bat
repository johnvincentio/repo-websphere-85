@echo off
call "%~dp0..\..\bin\wsadmin" -conntype none -f "%~dp0samplesMaster.jacl" %*
