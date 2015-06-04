rm C:\Users\Administrator\Desktop\服务器\ttafs.jar
rm C:\Users\Administrator\Desktop\服务器\ttafs_latest.zip
rmdir /s/q C:\Users\Administrator\Desktop\服务器\res
copy /y target\ttafs.jar C:\Users\Administrator\Desktop\服务器\ttafs.jar
xcopy /s /e /y res C:\Users\Administrator\Desktop\服务器\res\
HaoZipC a C:\Users\Administrator\Desktop\服务器\ttafs_latest.zip -tzip C:\Users\Administrator\Desktop\服务器\res C:\Users\Administrator\Desktop\服务器\ttafs.jar C:\Users\Administrator\Desktop\服务器\server.sql C:\Users\Administrator\Desktop\服务器\upgrade.sql C:\Users\Administrator\Desktop\服务器\stopserver.jar
echo BUILD SUCCESS