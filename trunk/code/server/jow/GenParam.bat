@rem # 生成ConstParam.java
@rem #--------------------------------------------------------------------------------------
@rem # param1: inputFilePath 输入excel文件的路径
@rem # param2: outputFilePath 输出java文件的路径

java -cp ./corelibs/*;./libs/*;./config/ org.jow.core.gen.excel.GenParam ../../../public/config/ ./common/gen/org/jow/common/config/

@if "%1" == "" pause