package com.github.microprograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.friendlysnmp.FException;
import org.friendlysnmp.FriendlyAgent;
import org.snmp4j.smi.OID;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.microprograms.OctetStringFriendlyRoMib.OctetStringFriendlyRoScalar;

@SpringBootApplication
public class FriendlySnmpAgentApplication {
	private static final String TITLE = "UECM inspector";
	private static final String VERSION = "Build 20220829";
	private static final String PROP_FILE = "friendly-agent.properties";

	private static final OID OID_ENTERPRISE_FRIENDLY_SNMP = new OID("1.3.6.1.4.1.29091");
	private static final OID OID_FRIENDLY_SNMP_AGENT = new OID(OID_ENTERPRISE_FRIENDLY_SNMP).append(10);

	public static void main(String[] args) throws IOException, FException {
		Properties prop = FriendlyAgent.loadProps(FriendlySnmpAgentApplication.class, PROP_FILE);
		FriendlyAgent agent = new FriendlyAgent(TITLE, VERSION, prop);
		agent.addMIB(new OctetStringFriendlyRoMib(agentWorker -> {
			List<OctetStringFriendlyRoScalar> list = new ArrayList<>();

			list.add(new OctetStringFriendlyRoScalar("coredns", new OID(OID_FRIENDLY_SNMP_AGENT).append("1.1.0"),
					agentWorker, x -> x.setValueEx("CoreDNS running")));

			list.add(new OctetStringFriendlyRoScalar("tidb", new OID(OID_FRIENDLY_SNMP_AGENT).append("3.1.0"),
					agentWorker, x -> x.setValueEx("TiDB running")));
			list.add(new OctetStringFriendlyRoScalar("rabbitmq", new OID(OID_FRIENDLY_SNMP_AGENT).append("3.2.0"),
					agentWorker, x -> x.setValueEx("RabbitMQ running")));
			list.add(new OctetStringFriendlyRoScalar("redis", new OID(OID_FRIENDLY_SNMP_AGENT).append("3.3.0"),
					agentWorker, x -> x.setValueEx("Redis running")));
			list.add(new OctetStringFriendlyRoScalar("mongodb", new OID(OID_FRIENDLY_SNMP_AGENT).append("3.4.0"),
					agentWorker, x -> x.setValueEx("MongoDB running")));

			list.add(new OctetStringFriendlyRoScalar("gateway", new OID(OID_FRIENDLY_SNMP_AGENT).append("4.1.0"),
					agentWorker, x -> x.setValueEx("gateway running")));

			return list;
		}));
		agent.init();
		agent.start();
	}

}
