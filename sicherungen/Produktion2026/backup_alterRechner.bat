echo off
mysqldump -u root -prootkeag --single-transaction --flush-logs --databases lager > "C:\Lager\Java\MySQL\backup\backup_%date%_%time:~0,2%-%time:~3,2%-%time:~6,2%.sql"
mysqldump -u root -prootkeag --single-transaction --flush-logs --databases lager > "C:\Users\elektrowerkstatt\Desktop\backup_lager_%date%_%time:~0,2%-%time:~3,2%-%time:~6,2%.sql"
