::UNIVERSAL ELECTRICITY BUILD SCRIPT
@echo off
echo Promotion Type? x bugged * recommended @stable
set /p PROMOTION=

set SERVER=
set USERNAME=
set PASSWORD=

set /p CCVERSION=<ccversion.txt
set /p IEVERSION=<ieversion.txt
set /p MCVERSION=<mcversion.txt
set /p CurrentBuild=<buildnumber.txt
set /a BUILD_NUMBER=%CurrentBuild%+1
echo %BUILD_NUMBER% >buildnumber.txt

if %PROMOTION%==* (
	echo %MODVERSION% >recommendedversion.txt
)

set FILE_ELRATH=[%MCVERSION%]IllustriousElements_v%IEVERSION%#%BUILD_NUMBER%.jar
set FILE_CC=[%MCVERSION%]ComeCloser_v%CCVERSION%#%BUILD_NUMBER%.jar

echo Starting to build %FILE_NAME%

::BUILD
runtime\bin\python\python_mcp runtime\recompile.py %*
runtime\bin\python\python_mcp runtime\reobfuscate.py %*

::ZIP-UP
cd reobf\minecraft\
"..\..\..\7za.exe" a "..\..\builds\IllustriousElements\%FILE_ELRATH%" "elrath18"
"..\..\..\7za.exe" a "..\..\builds\IllustriousElements\%FILE_ELRATH%" "universalelectricity"
"..\..\..\7za.exe" a "..\..\builds\ComeCloser\%FILE_CC%" "dark/comecloser"
cd ..\..\

echo Done building Files

pause