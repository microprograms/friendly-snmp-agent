# friendly-snmp-agent

在项目根目录执行

mvn clean package && docker build --no-cache -f docker/Dockerfile -t friendly-snmp-agent:1.0.0 .

docker run -d -p 1161:1161/udp --name friendly-snmp-agent friendly-snmp-agent:1.0.0

docker save \
    friendly-snmp-agent:1.0.0 \
    -o friendly-snmp-agent-airgap-images-slim.tar
