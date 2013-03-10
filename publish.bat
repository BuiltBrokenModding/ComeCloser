::UNIVERSAL ELECTRICITY BUILD SCRIPT
@echo off
echo Promotion Type? x bugged * recommended @stable
set /p PROMOTION=

set SERVER=
set USERNAME=
set PASSWORD=

set /p MODVERSION=<modversion.txt
set /p CurrentBuild=<buildnumber.txt
set /a BUILD_NUMBER=%CurrentBuild%+1
echo %BUILD_NUMBER% >buildnumber.txt

if %PROMOTION%==* (
	echo %MODVERSION% >recommendedversion.txt
)

set FILE_NAME=IllustriousElements_#%BUILD_NUMBER%.jar

echo Starting to build %FILE_NAME%

::BUILD
runtime\bin\python\python_mcp runtime\recompile.py %*
runtime\bin\python\python_mcp runtime\reobfuscate.py %*

::ZIP-UP
cd reobf\minecraft\
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "elrath18"
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "universalelectricity"
cd ..\..\

echo Done building %FILE_NAME%

pause