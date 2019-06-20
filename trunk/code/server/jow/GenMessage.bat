@rem # 生成Protobuf协议
@rem #--------------------------------------------------------------------------------------
@rem # param1: outPath 输出文件的路径
@rem # param2: outPackage 生成文件的包名

java -cp ./corelibs/*;./libs/* org.jow.core.gen.proto.GenProto ./common/gen/ org.jow.common.msg

@rem # patch协议文件
@rem #--------------------------------------------------------------------------------------
@rem # param1: outPath 协议文件的路径=上面的outPath + 上面的outPackage把.换成/

java -cp ./corelibs/*;./libs/* org.jow.core.gen.proto.MsgPatcher ./common/gen/org/jow/common/msg/

@if "%1" == "" pause