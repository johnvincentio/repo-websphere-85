package com.idc.rad.app;

/**
 * @author John Vincent
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Comparator;

public class Servers {
	private ArrayList<Server> m_list = new ArrayList<Server>();
	public Servers (AppProps appProps) {
		if (appProps.getVersion() == 7)
			doServers61 (appProps);
		else
			doServers60 (appProps);
	}
	private void doServers60 (AppProps appProps) {
		File aFile = new File (appProps.getServerDir());
		if (! aFile.isDirectory()) return;
		File [] allFiles1 = aFile.listFiles();
		File [] allFiles2, allFiles3;

		String strServer, strCell, strNode, strFile;
		for (int i=0; i<allFiles1.length; i++) {
			aFile = allFiles1[i];
//			System.out.println("file "+aFile.getName());
			if (! aFile.isDirectory()) continue;
			strServer = aFile.getName();
//			System.out.println("strServer "+strServer);
			String strPath = appProps.getServerDir() + AppUtils.makeName(strServer);

			strFile = strPath + AppUtils.makeName("config") + AppUtils.makeName("cells");
			aFile = new File (strFile);
			if (! aFile.isDirectory()) continue;
			allFiles2 = aFile.listFiles();
			if (allFiles2 == null) continue;
			if (allFiles2.length < 1) continue;
			aFile = allFiles2[0];
			strCell = aFile.getName();
//			System.out.println("strCell "+strCell);

			strFile = strPath + AppUtils.makeName("config") + 
					AppUtils.makeName("cells") + AppUtils.makeName(strCell) + AppUtils.makeName("nodes");
			aFile = new File (strFile);
			if (! aFile.isDirectory()) continue;
			allFiles3 = aFile.listFiles();
			if (allFiles3 == null) continue;
			if (allFiles3.length < 1) continue;
			aFile = allFiles3[0];
			strNode = aFile.getName();
//			System.out.println("strNode "+strNode);

			Server server = new Server (strServer, strPath, strCell, strNode);
			m_list.add (server);

			strFile += AppUtils.makeName(strNode) + AppUtils.makeName("serverindex.xml");
//			System.out.println("strFile "+strFile);
			JVxml jvxml = new JVxml();
			File file = new File(strFile);
			Map<String, String> map = jvxml.parse(file);

			server.setPortBootstrap (map.get("BOOTSTRAP_ADDRESS"));
			server.setPortSoap  (map.get("SOAP_CONNECTOR_ADDRESS"));
			server.setPortAdmin (map.get("WC_adminhost"));
			server.setPortAdminSecure (map.get("WC_adminhost_secure"));
			server.setPortHttp (map.get("WC_defaulthost"));
			server.setPortHttps (map.get("WC_defaulthost_secure"));

			server.setPortSIBEndPointAddress (map.get("SIB_ENDPOINT_ADDRESS"));
			server.setPortSIBEndPointSecureAddress (map.get("SIB_ENDPOINT_SECURE_ADDRESS"));
			server.setPortSIBMQEndPointAddress (map.get("SIB_MQ_ENDPOINT_ADDRESS"));
			server.setPortSIBMQEndPointSecureAddress (map.get("SIB_MQ_ENDPOINT_SECURE_ADDRESS"));

			server.setPortOther1 (map.get("SAS_SSL_SERVERAUTH_LISTENER_ADDRESS"));
			server.setPortOther2 (map.get("CSIV2_SSL_SERVERAUTH_LISTENER_ADDRESS"));
			server.setPortOther3 (map.get("CSIV2_SSL_MUTUALAUTH_LISTENER_ADDRESS"));
			server.setPortOther4 (map.get("DCS_UNICAST_ADDRESS"));
			server.setPortOther5 (map.get("ORB_LISTENER_ADDRESS"));
		}
		Collections.sort(m_list, new SortServersAsc());
	}
	private void doServers61 (AppProps appProps) {
		String registryStrFile = appProps.getServerRoot() + AppUtils.makeName("properties") + AppUtils.makeName("profileRegistry.xml");
//		System.out.println("strFile "+strFile);
		ProfileRegistryXml profileRegistryXml = new ProfileRegistryXml();
		Map<String, String> profileRegistryMap = profileRegistryXml.parse (new File(registryStrFile));
		Iterator<Map.Entry<String, String>> iter = profileRegistryMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> me = iter.next();
			String strServer = me.getKey();
			String strPath = me.getValue();
			System.out.println("strServer "+strServer);
			System.out.println("strPath "+strPath);

			String strFile = strPath + AppUtils.makeName("config") + AppUtils.makeName("cells");
			System.out.println("strFile "+strFile);
			File aFile = new File (strFile);
			if (! aFile.isDirectory()) continue;
			File[] allFiles2 = aFile.listFiles();
			if (allFiles2 == null) continue;
			if (allFiles2.length < 1) continue;
			aFile = allFiles2[0];
			String strCell = aFile.getName();
			System.out.println("strCell "+strCell);

			strFile = strPath + AppUtils.makeName("config") + 
					AppUtils.makeName("cells") + AppUtils.makeName(strCell) + AppUtils.makeName("nodes");
			aFile = new File (strFile);
			if (! aFile.isDirectory()) continue;
			File[] allFiles3 = aFile.listFiles();
			if (allFiles3 == null) continue;
			if (allFiles3.length < 1) continue;
			aFile = allFiles3[0];
			String strNode = aFile.getName();
			System.out.println("strNode "+strNode);

			Server server = new Server (strServer, strPath, strCell, strNode);

			strFile += AppUtils.makeName(strNode) + AppUtils.makeName("serverindex.xml");
			System.out.println("strFile "+strFile);
			JVxml jvxml = new JVxml();
			File file = new File(strFile);
			Map<String, String> map = jvxml.parse(file);

			server.setPortBootstrap (map.get("BOOTSTRAP_ADDRESS"));
			server.setPortSoap  (map.get("SOAP_CONNECTOR_ADDRESS"));
			server.setPortAdmin (map.get("WC_adminhost"));
			server.setPortAdminSecure (map.get("WC_adminhost_secure"));
			server.setPortHttp (map.get("WC_defaulthost"));
			server.setPortHttps (map.get("WC_defaulthost_secure"));

			server.setPortSIBEndPointAddress (map.get("SIB_ENDPOINT_ADDRESS"));
			server.setPortSIBEndPointSecureAddress (map.get("SIB_ENDPOINT_SECURE_ADDRESS"));
			server.setPortSIBMQEndPointAddress (map.get("SIB_MQ_ENDPOINT_ADDRESS"));
			server.setPortSIBMQEndPointSecureAddress (map.get("SIB_MQ_ENDPOINT_SECURE_ADDRESS"));

			server.setPortOther1 (map.get("SAS_SSL_SERVERAUTH_LISTENER_ADDRESS"));
			server.setPortOther2 (map.get("CSIV2_SSL_SERVERAUTH_LISTENER_ADDRESS"));
			server.setPortOther3 (map.get("CSIV2_SSL_MUTUALAUTH_LISTENER_ADDRESS"));
			server.setPortOther4 (map.get("DCS_UNICAST_ADDRESS"));
			server.setPortOther5 (map.get("ORB_LISTENER_ADDRESS"));
			m_list.add (server);
		}
		Collections.sort(m_list, new SortServersAsc());
	}
	public Iterator<Server> getItems() {return m_list.iterator();}
	public boolean isNone() { return m_list.size() < 1; }
	public int getSize() { return m_list.size(); }

	public Server getServer (String name) {
		Server current;
		Iterator<Server> iter = getItems();
		while (iter.hasNext()) {
			current = iter.next();
			if (current.getName().equals(name)) {
				return current;
			}
		}
		return null;
	}

	public class Server {
		String name;
		String path;
		String cell;
		String node;
		String portBootstrap;		 		 		 		// BOOTSTRAP_ADDRESS
		String portSoap;		 		 		 		 	// SOAP_CONNECTOR_ADDRESS
		String portAdmin;		 		 		 		 	// WC_adminhost
		String portAdminSecure;		 		 		 		// WC_adminhost_secure
		String portHttp;		 		 		 		 	// WC_defaulthost
		String portHttps;		 		 		 		 	// WC_defaulthost_secure
		String portSIBEndPointAddress;		 		 		// SIB_ENDPOINT_ADDRESS
		String portSIBEndPointSecureAddress;		 		// SIB_ENDPOINT_SECURE_ADDRESS
		String portSIBMQEndPointAddress;		 		 	// SIB_MQ_ENDPOINT_ADDRESS
		String portSIBMQEndPointSecureAddress;		 		// SIB_MQ_ENDPOINT_SECURE_ADDRESS
		String portOther1;		 		 		 		 	// SAS_SSL_SERVERAUTH_LISTENER_ADDRESS
		String portOther2;		 		 		 		 	// CSIV2_SSL_SERVERAUTH_LISTENER_ADDRESS
		String portOther3;		 		 		 		 	// CSIV2_SSL_MUTUALAUTH_LISTENER_ADDRESS
		String portOther4;		 		 		 		 	// DCS_UNICAST_ADDRESS
		String portOther5;		 		 		 		 	// ORB_LISTENER_ADDRESS

		public Server (String name, String path, String cell, String node) {
			this.name = name;
			this.path = path;
			this.cell = cell;
			this.node = node;
		}
		public String getName() {return name;}
		public String getPath() {return path;}
		public String getCell() {return cell;}
		public String getNode() {return node;}
		public String getPortBootstrap() {return portBootstrap;}
		public String getPortSoap() {return portSoap;}
		public String getPortAdmin() {return portAdmin;}
		public String getPortAdminSecure() {return portAdminSecure;}
		public String getPortHttp() {return portHttp;}
		public String getPortHttps() {return portHttps;}
		public String getPortSIBEndPointAddress() {return portSIBEndPointAddress;}
		public String getPortSIBEndPointSecureAddress() {return portSIBEndPointSecureAddress;}
		public String getPortSIBMQEndPointAddress() {return	portSIBMQEndPointAddress;}
		public String getPortSIBMQEndPointSecureAddress() {return portSIBMQEndPointSecureAddress;}
		public String getPortOther1() {return portOther1;}
		public String getPortOther2() {return portOther2;}
		public String getPortOther3() {return portOther3;}
		public String getPortOther4() {return portOther4;}
		public String getPortOther5() {return portOther5;}

		public void setName (String name) {this.name = name;}
		public void setCell (String cell) {this.cell = cell;}
		public void setNode (String node) {this.node = node;}
		public void setPortBootstrap (String portBootstrap)	{this.portBootstrap = portBootstrap;}
		public void setPortSoap (String portSoap) {this.portSoap = portSoap;}
		public void setPortAdmin (String portAdmin) {this.portAdmin = portAdmin;}
		public void setPortAdminSecure (String portAdminSecure) {this.portAdminSecure = portAdminSecure;}
		public void setPortHttp (String portHttp) {this.portHttp = portHttp;}
		public void setPortHttps (String portHttps) {this.portHttps = portHttps;}
		public void setPortSIBEndPointAddress (String portSIBEndPointAddress) {this.portSIBEndPointAddress = portSIBEndPointAddress;}
		public void setPortSIBEndPointSecureAddress (String portSIBEndPointSecureAddress) {this.portSIBEndPointSecureAddress = portSIBEndPointSecureAddress;}
		public void setPortSIBMQEndPointAddress (String portSIBMQEndPointAddress) {this.portSIBMQEndPointAddress = portSIBMQEndPointAddress;}
		public void setPortSIBMQEndPointSecureAddress (String portSIBMQEndPointSecureAddress) {this.portSIBMQEndPointSecureAddress = portSIBMQEndPointSecureAddress;}
		public void setPortOther1 (String portOther1) {this.portOther1 = portOther1;}
		public void setPortOther2 (String portOther2) {this.portOther2 = portOther2;}
		public void setPortOther3 (String portOther3) {this.portOther3 = portOther3;}
		public void setPortOther4 (String portOther4) {this.portOther4 = portOther4;}
		public void setPortOther5 (String portOther5) {this.portOther5 = portOther5;}

		public String toString() {
			return
				"("+getName()+","+getPath()+","+getCell()+","+getNode()+","+
				getPortBootstrap()+","+getPortSoap()+","+getPortAdmin()+","+
				getPortAdminSecure()+","+getPortHttp()+","+getPortHttps()+","+
				getPortSIBEndPointAddress()+","+getPortSIBEndPointSecureAddress()+","+
				getPortSIBMQEndPointAddress()+","+getPortSIBMQEndPointSecureAddress()+","+
				getPortOther1()+","+getPortOther2()+","+getPortOther3()+","+getPortOther4()+","+getPortOther5()+")";
		}
	}
	private class SortServersAsc implements Comparator<Server> {
		public int compare (Server a, Server b) {
			return a.getName().compareTo (b.getName());
		}
	}
}
