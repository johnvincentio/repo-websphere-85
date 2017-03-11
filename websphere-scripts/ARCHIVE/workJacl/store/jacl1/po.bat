@echo off
rem
rem AdminConfig can be run without server running
rem
rem AdminControl and AdminApp require the server be running
rem
set JVServer=server1
set JVNode=localhost
set JVCell=localhost
echo "Before wsadmin"
rem
rem this is for testing
rem
wsadmin -f test1.jacl -node %JVNode% -cell %JVCell% -server %JVServer%
rem
rem failed - which server? - will default .... no good
rem
rem wsadmin -conntype none -f create.jacl -node %JVNode% -cell %JVCell% -server %JVServer%
rem
rem this works
rem
rem wsadmin -f create.jacl -node %JVNode% -cell %JVCell% -server %JVServer%
rem
rem turn on/off traces - example of 'server must be running'
rem
rem wsadmin -f server1.jacl -node %JVNode% -cell %JVCell% -server %JVServer%

echo "After wsadmin"
