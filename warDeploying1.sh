/usr/local/apache-tomcat-6.0.35/bin/shutdown.sh &
rm -r /usr/local/apache-tomcat-6.0.35/webapps/LifeExposure &
rm /usr/local/apache-tomcat-6.0.35/webapps/LifeExposure.war &
cd /home/saima/IdeaProjects/ssProject\(6.uploadImgFrmBrowser\)\(TEST\) &
mvn compile &
mvn package &
cp target/LifeExposure.war /usr/local/apache-tomcat-6.0.35/webapps/ &
/usr/local/apache-tomcat-6.0.35/bin/startup.sh

