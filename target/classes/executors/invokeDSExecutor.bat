cd/
set JarLocation=%1
set MainClassName=%2
set roleConstant=%3
set roleName=%4
set ProjectPath=%5

cd /d %JarLocation%
java -cp PINFO-0.0.1-SNAPSHOT-jar-with-dependencies.jar %MainClassName%  %roleConstant% %roleName% %ProjectPath%
exit