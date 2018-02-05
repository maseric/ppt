#!/usr/bin/bash
cd /u001/dvp/www/spi_jer/reporting/ppt

CLASSPATH=/u001/dvp/www/spi_jer/reporting/ppt/bin
CLASSPATH=$CLASSPATH:/u001/dvp/www/spi_jer/reporting/ppt/lib/commons-logging-1.1.jar
CLASSPATH=$CLASSPATH:/u001/dvp/www/spi_jer/reporting/ppt/lib/junit-3.8.1.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/mail.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/activation.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/entbase.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/entjavaxcrypto.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/entjsse.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/entssl.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/xml4j.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/WapiClient.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/log4j.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/log4j-core.jar
CLASSPATH=$CLASSPATH:C:\i2\scc_int\6.1.11\Spoke/lib/xerces.jar
export CLASSPATH


java -classpath ;;/u001/dvp/www/spi_jer/reporting/ppt/lib/log4j-1.2.13.jar;/u001/dvp/www/spi_jer/reporting/ppt/lib/mysql.jar;/u001/dvp/www/spi_jer/reporting/ppt/lib/poi-3.1-FINAL-20080629.jar;/u001/dvp/www/spi_jer/reporting/ppt/lib/poi-scratchpad-3.1-FINAL-20080629.jar generator.XmlReporter
