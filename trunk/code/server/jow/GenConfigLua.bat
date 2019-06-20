@rem # 生成lua配置文件
@rem #--------------------------------------------------------------------------------------
@rem # param1: inputFilePath 输入excel文件的路径
@rem # param2: outputFilePath 输出java文件的路径

java -cp ./core/bin;./libs/*;./config/ org.jow.core.gen.excel.GenConfigLua ../../../public/config/ ./data/json/

@if "%1" == "" pause