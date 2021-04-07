README:-

Feature:

1. This software can be used as a store management tool.
2. It can send bill over sms and can also provide hard copy of the bill.
3. You can insert expense related data and it will calculate the earning over a given period of time.
4. In this software, you can provide points to customer based on their spending which can be redeemed to avail discount.
5. You can also send bulk promotional message using this software.




Setup:

1. This software has been tested on apache-tomcat-9.0.30 using jre1.8.0_211.
2. Import this project in eclipse and export this project as both jar and Subscriptions.war (Please use the exact name of war file as mentioned).
3. Please download apache-tomcat-9.0.30 and extract it.
4. After extracting, navigate inside the apache folder and create TOMCAT_RELEASE folder and make two sub folders as conf and lib.
5. Place the log4j2.xml file inside TOMCAT_RELEASE>conf folder.
6. Please modify the file paths mentioned log4j2.xml according to your requirement.
7. Copy all the contents of lib folder of this project inside TOMCAT_RELEASE>lib.
8. Navigate to conf folder of tomcat and edit server.xml file.
9. Change the shared loader parameter of server.xml so that it points to both TOMCAT_RELEASE>conf/lib
10. Copy the war file inside webapps of tomcat.
11. Copy dbConfig.properties file inside bin folder of tomcat.
12. Please restore the provided sql dump.

Limitations:

1. Bulk message and earning calculator has not been made functional yet.