set JAVA_OPTS=%JAVA_OPTS% -DuserNumber=20 -Dduration=15 -Dserver=vmw12r2apxapi.gencos.com -DuserName=admin -Dpassword=advs

@echo off
dir ..\user-files\simulations\APX_Cloud /b /a-d>Tests.txt

FOR /f "tokens=1* delims=." %%a IN (Tests.txt) DO (
  gatling.bat -s APXCloudPerfTest.%%a
)