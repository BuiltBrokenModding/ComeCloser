::UNIVERSAL ELECTRICITY BUILD SCRIPT
@echo off
echo Promotion Type? x bugged * recommended @stable
set /p PROMOTION=

set SERVER=calclavia.com
set USERNAME=fluidmechanics@calclavia.com
set PASSWORD=dWgi0Rll02Hu

set /p MODVERSION=<modversion.txt
set /p CurrentBuild=<buildnumber.txt
set /a BUILD_NUMBER=%CurrentBuild%+1
echo %BUILD_NUMBER% >buildnumber.txt

if %PROMOTION%==* (
	echo %MODVERSION% >recommendedversion.txt
)

set FILE_NAME=Fluid-Mechanics_v%MODVERSION%.%BUILD_NUMBER%.jar
set BACKUP_NAME=Fluid-Mechanics_v%MODVERSION%.%BUILD_NUMBER%_backup.zip
set API_NAME=hydraulic_v%MODVERSION%.%BUILD_NUMBER%_api.zip

echo Starting to build %FILE_NAME%

::BUILD
runtime\bin\python\python_mcp runtime\recompile.py %*
runtime\bin\python\python_mcp runtime\reobfuscate.py %*

::ZIP-UP
cd reobf\minecraft\
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "fluidmech"
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "hydraulic"
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "universalelectricity"
cd ..\..\
cd src\minecraft\
"..\..\..\7za.exe" a "..\..\builds\%API_NAME%" "hydraulic"
"..\..\..\7za.exe" a "..\..\builds\%FILE_NAME%" "fluidmech\resource"
cd ..\..\
::UPDATE INFO FILE
echo %PROMOTION% %FILE_NAME% %API_NAME%>>info.txt

::GENERATE FTP Script
echo open %SERVER%>ftpscript.txt
echo %USERNAME%>>ftpscript.txt
echo %PASSWORD%>>ftpscript.txt
echo binary>>ftpscript.txt
echo put "recommendedversion.txt">>ftpscript.txt
echo put "builds\%FILE_NAME%">>ftpscript.txt
echo put "builds\%API_NAME%">>ftpscript.txt
echo put info.txt>>ftpscript.txt
echo quit>>ftpscript.txt
ftp.exe -s:ftpscript.txt
del ftpscript.txt

echo Done building %FILE_NAME%

pause