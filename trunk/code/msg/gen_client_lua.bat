@echo on
rem chcp 65001
cd client_lua
call build.bat ..\protol example/msg/file
cd ..
::del client_lua\file\msg_pb.lua
::del client_lua\file\GetMessage.lua
XCOPY client_lua\file\* ..\..\code\client\goe\client\Lua\protocol /s  /e
pause