@echo off

set SAG_HOME="%1"
if exist %SAG_HOME%\install\bin\setenv.bat call %SAG_HOME%\install\bin\setenv.bat
set ANT_HOME=%SAG_HOME%\common\lib\ant
%JAVA_HOME%\bin\java -jar "%ANT_HOME%\lib\ant-launcher.jar" -lib "%ANT_HOME%\ant-contrib-1.0b3.jar" -Dsag.install.dir="%1" -Dbuild.source.dir="%2" -Dbuild.output.dir="%3" -Dbuild.version="%4"

:end