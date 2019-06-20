@rem # Éú³ÉCommonSerializer
@rem #--------------------------------------------------------------------------------------
@rem # param1: find package
@rem # param2: gen package
@rem # param3: target dir

@rem # connsrv
java -cp ./bin;./../common/bin;./../corelibs/*;./../libs/*;./../config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow.core org.jow.dbsrv /gen/

@if "%1" == "" pause