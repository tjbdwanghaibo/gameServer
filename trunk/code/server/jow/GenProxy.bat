@rem # Éú³ÉProxy
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

@rem # connsrv
java -cp ./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.corelibs /core/gen/

@rem # gamesrv
java -cp ./gamesrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.gamesrv /gamesrv/gen/

@rem # loginsrv
java -cp ./loginsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.loginsrv /loginsrv/gen/

@rem # matchsrv
java -cp ./matchsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.matchsrv /matchsrv/gen/

@rem # centralsrv
java -cp ./centralsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.centralsrv /centralsrv/gen/

@rem # roomsrv
java -cp ./roomsrv/bin;./common/bin;./corelibs/*;./libs/*;./config/ org.jow.core.gen.proxy.GenProxy org.jow.roomsrv /roomsrv/gen/

@if "%1" == "" pause