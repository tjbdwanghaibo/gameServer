@rem # 生成数据库和表
@rem # 目前自动建库有bug，需手动创建utf类型的库，未来需修复。
@rem # CREATE DATABASE IF NOT EXISTS yourdbname DEFAULT CHARSET utf8 COLLATE utf8_general_ci
@rem #--------------------------------------------------------------------------------------
@rem # param1: package

@rem # gamesrv
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.core game 0
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.game game 0

@rem # loginsrv
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.core login
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.login login

@rem # centralsrv
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.core central
java -cp ./corelibs/*;./common/bin;./config/;./libs/* org.jow.core.gen.entity.GenDB org.jow.common.entity.central central

@if "%1" == "" pause