#!/bin/ksh

#------------------------------------------------------

DATEPATH=`date "+%Y%m%d"`

#------------------------------------------------------

MAIN_CLASS=tain.kr.test.realsystem.v01.RealSystem

JOB_PATH=/sas/sasv94/config/Lev1/Web/WebAppServer/SASServer_EMART/sas_webapps/sas.emartcms.war/WEB-INF

#------------------------------------------------------

JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.45.x86_64

JAVA_EXE=${JAVA_HOME}/bin/java

CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/rt.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/resources.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/jsse.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/jce.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/charsets.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/dnsns.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/localedata.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunjce_provider.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunmscapi.jar
CLASSPATH=${CLASSPATH}:${JAVA_HOME}/jre/lib/ext/sunpkcs11.jar

CLASSPATH=${CLASSPATH}:${JOB_PATH}/lib/modules.tain.0.151031.jar

#------------------------------------------------------

OPTION="-Xss256K"
# OPTION="${OPTION} -Dfile.encoding=euc-kr"
# OPTION="${OPTION} -Dfile.encoding=utf-8"
OPTION="${OPTION} -Ddev.author=Kang_Seok"
OPTION="${OPTION} -Ddev.version=1.6.0_45"
OPTION="${OPTION} -Dcatalina.base=/sas/sasv94/config/Lev1/Web/WebAppServer/SASServer_EMART"

#------------------------------------------------------

${JAVA_EXE} -cp ${CLASSPATH} ${OPTION} ${MAIN_CLASS}
