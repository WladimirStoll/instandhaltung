echo on
keytool.exe -v -genkey -alias MyLager -keystore ../dist/lib/lager.keystore -keypass lager2lager2lager2 -storepass lager3 -dname CN=Stoll
keytool -selfcert -alias MyLager -keystore ./../dist/lib/lager.keystore -storepass lager3 -keypass lager2lager2lager2
keytool -list -keystore ./../dist/lib/lager.keystore -storepass lager3 