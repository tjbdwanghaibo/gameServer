@rem # Éú³ÉCommonSerializer
@rem #--------------------------------------------------------------------------------------
@rem # param1: find package
@rem # param2: gen package
@rem # param3: target dir

@rem # gamesrv
java -cp ./gamesrv/bin;./corelibs/*;./common/bin;./libs/*;./config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow org.jow.gamesrv /gamesrv/gen/

@rem # loginsrv
java -cp ./loginsrv/bin;./corelibs/*;./common/bin;./libs/*;./config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow org.jow.loginsrv /loginsrv/gen/

@rem # matchsrv
java -cp ./matchsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow org.jow.matchsrv /matchsrv/gen/

@rem # centralsrv
java -cp ./centralsrv/bin;./corelibs/*;./common/bin;./libs/*;./config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow org.jow.centralsrv /centralsrv/gen/

@rem # roomsrv
java -cp ./roomsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.serializer.GenCommonSerializer org.jow org.jow.roomsrv /roomsrv/gen/

@if "%1" == "" pause