# 基础镜像
FROM openjdk:11-jdk

# 修改时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone

# 切换软件源
COPY docker/etc/apt/source.mirrors.163.com.list /etc/apt/source.mirrors.163.com.list

# 安装命令行工具
RUN apt-get update && apt install -y dnsutils iputils-ping net-tools dos2unix snmp

# 复制mib到容器
COPY docker/friendly-snmp-agent/ friendly-snmp-agent/
RUN dos2unix friendly-snmp-agent/install-mib.sh

# 复制jar包到容器
COPY target/friendly-snmp-agent.jar app.jar

# 启用jvm容器支持
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=80.0 -XX:InitialRAMPercentage=75.0"

# 容器启动命令
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar
