#!/bin/bash

LANG="zh_CN.GB2312"
SUPPORTED="en_AU.UTF-8:zh_CN:zh:en_AU.UTF-8:en_AU:en"
SYSFONT="latarcyrheb-sun16"

echo "正在生成msg文件......"
echo "import \"options.proto\";" > protobuf/msg.proto

echo "正在写入msg文件......"
filelist=`ls $1*.proto`
for file in $filelist 
do 
	cat "$file" >> protobuf/msg.proto

	echo "import \"options.proto\";" > "protobuf/${file##*_}"
	cat "../protol/00000_00000_def.proto" >> "protobuf/${file##*_}"
	cat "$file" >> "protobuf/${file##*_}"

	# echo "生成protobuf/${file##*/}"
done

rm "protobuf/def.proto"

cd protobuf

echo "正在生成options.proto......"
protoc --descriptor_set_out=options.protobin --include_imports options.proto

echo "正在写入自定义模块......"
filelist=`ls *.proto`
for file in $filelist 
do 
	if [ "$file" != "msg.proto" ]; then
		echo "生成$file.lua"
		protoc --plugin=protoc-gen-lua="../../../plugin/protoc-gen-lua.sh" --lua_out=../../file "$file"
	fi
done

protoc -I=. --descriptor_set_out=msg.desc msg.proto
protoc --plugin=protoc-gen-lua="../../../plugin/protoc-gen-lua.sh" --lua_out=../../file msg.proto

echo "生成msgID......"
java -jar createMsgIdForMac.jar msg.proto ../../file/
