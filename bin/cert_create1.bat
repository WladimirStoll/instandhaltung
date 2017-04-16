echo on
keytool.exe -v -genkey -alias MyLager -keystore ../dist/lib/lager.keystore -keypass lager2lager2lager2 -storepass lager3 -dname CN=Stoll -validity 3600
keytool -list -keystore ./../dist/lib/lager.keystore -storepass lager3 
  