@echo off
rem set JAVA_OPTS=%JAVA_OPTS% -DuserNumber=1 -Dduration=3 -Dserver=vmw12apxcloud01.gencos.com -DuserName=admin -Dpassword=advs

dir ..\user-files\simulations\APX_Cloud /b /a-d>Tests.txt

FOR /f "tokens=1* delims=." %%a IN (Tests.txt) DO (
  gatling.bat -s APXCloudPerfTest.%%a
)