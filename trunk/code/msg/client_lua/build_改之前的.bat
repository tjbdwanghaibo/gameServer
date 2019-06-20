echo off
chcp 65001

SET IN = "%1"
SET OUT= "%2"


echo import "options.proto"; > protobuf\msg.proto
rem @more +3 %IN2% >> protobuf\msg.proto

rem all proto files

rem for /f "delims=" %%i in ('dir %"%1%" /s /b *.proto') do (
rem copy /Y /A protobuf\msg.proto + %1\*.proto  protobuf\msg.proto
for %%i in (%1\*.proto) do (
	@type %%i >> protobuf\msg.proto
	@echo. >> protobuf\msg.proto
)




cd protobuf
protoc --descriptor_set_out=options.protobin --include_imports options.proto


"protoc.exe" --plugin=protoc-gen-lua="..\..\..\plugin\protoc-gen-lua.bat" --lua_out=../../file msg.proto


java -jar createMsgId.jar msg.proto ../../file/