@rem # 清除全部生成的代码
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

@rem # connsrv
java -cp ./bin;./../common/bin;./../corelibs/*;./../libs/*;./../config/ org.jow.core.gen.GenClean org.jow.dbsrv /gen/

@if "%1" == "" pause