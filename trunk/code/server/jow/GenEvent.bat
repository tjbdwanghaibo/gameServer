@rem # Éú³ÉEvent
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

@rem # gamesrv
java -cp ./gamesrv/bin;./dbsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.event.GenEvent org.jow.gamesrv /gamesrv/gen/

@if "%1" == "" pause