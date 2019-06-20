@rem # 一键生成全部代码
@rem #--------------------------------------------------------------------------------------

@rem # 清除全部生成的代码
call GenClean.bat 1

@rem # 生成CommonSerializer
call GenCommonSerializer.bat 1

@rem # 生成Proxy
call GenProxy.bat 1

@rem # 生成IOSerializer
call GenIOSerializer.bat 1

@if "%1" == "" pause