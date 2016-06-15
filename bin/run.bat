@echo off
dir ..\user-files\simulations\APX_Cloud /b /a-d>Tests.txt

FOR /f "tokens=1* delims=." %%a IN (Tests.txt) DO (
  gatling.bat -s APXCloudPerfTest.%%a
)