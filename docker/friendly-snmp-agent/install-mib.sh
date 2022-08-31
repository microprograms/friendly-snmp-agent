#!bin/sh

mkdir -p $HOME/.snmp/mibs
cp FRIENDLY-SNMP-AGENT-MIB.TXT $HOME/.snmp/mibs/FRIENDLY-SNMP-AGENT-MIB.TXT
echo "mibs +FRIENDLY-SNMP-AGENT-MIB" >> /root/.snmp/snmp.conf
