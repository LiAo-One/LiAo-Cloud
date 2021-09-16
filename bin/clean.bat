@echo off
echo.
echo [信息] 清空Object 缓存
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

pause