@rem # Éú³ÉMsgSerializer
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

java -cp ./common/bin;./core/bin;./libs/*;./config/ org.jow.core.gen.serializer.GenMsgSerializer org.jow.common.msg /common/gen/

@if "%1" == "" pause