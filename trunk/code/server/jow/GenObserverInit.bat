@rem # 生成MsgReceiverInit和ListenerInit（消息监听和事件监听）
@rem #--------------------------------------------------------------------------------------
@rem # param1: package
@rem # param2: target dir

@rem # gamesrv
java -cp ./gamesrv/bin;./dbsrv/bin;./common/bin;./core/bin;./libs/*;./config/ org.jow.core.gen.observer.GenObserverInit org.jow.gamesrv /gamesrv/gen/

@rem # loginsrv
java -cp ./loginsrv/bin;./dbsrv/bin;./common/bin;./core/bin;./libs/*;./config/ org.jow.core.gen.observer.GenObserverInit org.jow.loginsrv /loginsrv/gen/

@rem # roomsrv
java -cp ./roomsrv/bin;./common/bin;./core/bin;./libs/*;./config/ org.jow.core.gen.observer.GenObserverInit org.jow.roomsrv /roomsrv/gen/

@if "%1" == "" pause