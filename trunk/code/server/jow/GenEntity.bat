@rem # Éú³ÉDB entity
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

java -cp ./corelibs/*;./common/bin;./libs/* org.jow.core.gen.entity.GenEntity org.jow.common.entity /common/gen/

@if "%1" == "" pause