@echo on
c:
cd \Lager\Java\MySQL\MySQL Server 5.5\bin\
@echo off
mysqldump.exe -u root -prootkeag --single-transaction --flush-logs --databases lager > "C:\Lager\Java\MySQL\backup\backup_%date%_%time:~0,2%-%time:~3,2%-%time:~6,2%.sql"
echo Sicherung ist durchgeführt. Sichern Sie bitte die neue Datei C:\Lager\Java\MySQL\backup\backup_....
pause
