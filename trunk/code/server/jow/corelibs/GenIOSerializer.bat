@rem # Éú³ÉIOSerializer
@rem #--------------------------------------------------------------------------------------
@rem # param1: find package
@rem # param2: target dir

@rem # connsrv
java -cp ./bin;./../common/bin;./../corelibs/*;./../libs/*;./../config/ org.jow.core.gen.serializer.GenIOSerializer org.jow.dbsrv /gen/

@if "%1" == "" pause