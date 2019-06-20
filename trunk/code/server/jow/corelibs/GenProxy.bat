@rem # Éú³ÉProxy
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

@rem # connsrv
java -cp ./bin;./../common/bin;./../corelibs/*;./../libs/*;./../config/ org.jow.core.gen.proxy.GenProxy org.jow.dbsrv /gen/

@if "%1" == "" pause