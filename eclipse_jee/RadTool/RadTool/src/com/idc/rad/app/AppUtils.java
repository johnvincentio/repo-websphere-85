package com.idc.rad.app;

import java.io.File;
import java.io.IOException;

import com.idc.rad.gui.App;
import com.idc.rad.app.Servers.Server;

/**
 * @author John Vincent
 */

public class AppUtils {
	private App m_app;
	private AppProps m_appProps;
	private Servers m_servers;
	public AppUtils (App app, AppProps appProps, Servers servers) {
		m_app = app;
		m_appProps = appProps;
		m_servers = servers;
	}

	public void createWorkspace (String strName, Message message) {
		startWorkspace (strName, message);
	}
	public void startWorkspace (String strName, Message message) {
//		System.out.println("strName :"+strName+":");
		String strFile = m_appProps.getWorkspaceDir() + File.separatorChar + strName;
//		System.out.println("strFile :"+strFile+":");
		ExecuteCommandThread exec;
		if (m_appProps.getVersion() == 6) {
			String[] strCmd = {m_appProps.getWorkspaceStart(), "-showlocation", "-data", strFile};
			exec = new ExecuteCommandThread(m_app, strCmd, message);
			exec.setCursor(false);
			exec.start();
		}
		else {
			String[] strCmd = {m_appProps.getWorkspaceStart(), 
					m_appProps.getWorkspaceStartKey(),
					m_appProps.getWorkspaceStartValue(),
					"-data", strFile,"-showlocation"};
			exec = new ExecuteCommandThread(m_app, strCmd, message);
			exec.setCursor(false);
			exec.start();
		}
	}

	public void startServer (String strName) {
		System.out.println("Start strName :"+strName+":");
		String[] strCmd = {makeServerBinName (strName, m_appProps.getServerStart()), "server1"};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void stopServer (String strName) {
		System.out.println("Stop strName :"+strName+":");
		String[] strCmd = {makeServerBinName (strName, m_appProps.getServerStop()), "server1"};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void statusServer (String strName) {
		System.out.println("Status strName :"+strName+":");
		String[] strCmd = {makeServerBinName (strName, m_appProps.getServerStatus()), "-all"};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void firstSteps (String strName) {
		System.out.println("First Steps strName :"+strName+":");
		String[] strCmd = {makeServerFirststepsName (strName, m_appProps.getServerFirststeps())};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void log1Server (String strName) {
		editFile (makeServerLogName (strName, "startServer.log"));
	}
	public void log2Server (String strName) {
		editFile (makeServerLogName (strName, "stopServer.log"));
	}
	public void log3Server (String strName) {
		editFile (makeServerLogName (strName, "SystemOut.log"));
	}
	public void log4Server (String strName) {
		editFile (makeServerLogName (strName, "SystemErr.log"));
	}
	public void log5Server (String strName) {
		editFile (makeServerLogName (strName, "trace.log"));
	}
	public void createServer() {
		System.out.println("Create Server");
		String[] strCmd = {m_appProps.getServerCreateProfile()};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void deleteServer (String strName) {
		System.out.println("Delete Server");
		String[] strCmd = {m_appProps.getServerDeleteProfile(), "-delete", "-profileName", strName};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.start();
	}
	public void configServer (String strName) {
		Message message = m_app.getServerPanelMessage();
		Server server = m_servers.getServer (strName);
		if (server == null) return;

		message.println("Server:    "+server.getName());
		message.println("Node:    "+server.getNode());
		message.println("Cell:    "+server.getCell());
		message.println("Path:    "+server.getPath());
		message.println(" ");
		message.println("Bootstrap Port:    "+server.getPortBootstrap());
		message.println("SOAP Connector Port:    "+server.getPortSoap());
		message.println("Admin Console Port:    "+server.getPortAdmin());
		message.println("Admin Console Secure Port: "+server.getPortAdminSecure());
		message.println("HTTP Port:    "+server.getPortHttp());
		message.println("HTTP Secure Port:    "+server.getPortHttps());
		message.println(" ");
		message.println("SIB End Point Address: "+server.getPortSIBEndPointAddress());
		message.println("SIB End Point Secure Address: "+server.getPortSIBEndPointSecureAddress());
		message.println("SIB MQ EndPoint Address: "+server.getPortSIBMQEndPointAddress());
		message.println("SIB MQ EndPoint Secure Address: "+server.getPortSIBMQEndPointSecureAddress());
		message.println(" ");
		message.println("SAS SSL SERVERAUTH LISTENER ADDRESS: "+server.getPortOther1());
		message.println("CSIV2 SSL SERVERAUTH LISTENER ADDRESS: "+server.getPortOther2());
		message.println("CSIV2 SSL MUTUALAUTH LISTENER ADDRESS: "+server.getPortOther3());
		message.println("DCS_UNICAST_ADDRESS:    "+server.getPortOther4());
		message.println("ORB_LISTENER_ADDRESS:    "+server.getPortOther5());
	}
	public void adminConsole (String strName) {
		Server server = m_servers.getServer (strName);
		if (server == null) return;
		String strURL = "http://localhost:"+server.getPortAdmin()+"/ibm/console/";
		String[] strCmd = {m_appProps.getBrowser(), strURL};
		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, m_app.getServerPanelMessage());
		exec.setCursor(false);
		exec.start();
	}
	public void executeJacl (ServersJacl serversJacl, Message message) {
		System.out.println(">>> Servers::executeJacl; ");
		String cwd = serversJacl.getJaclRootPath() + makeName(serversJacl.getJaclDir());
		String str1 = serversJacl.getEarName();
		if (str1 == null) str1 = "nothing";
		String str2 = serversJacl.getEarFile();
		if (str2 == null) str1 = "nothing";
		String[] strCmd = {makeServerWsadminName(serversJacl.getServer(), m_appProps.getServerWsadmin()), 
				"-f", serversJacl.getJaclFile(), 
				"-conntype", "SOAP", 
				"-port", serversJacl.getServer().getPortSoap(),
				"nothing",
				serversJacl.getInstance(),
				serversJacl.getServer().getNode(),
				serversJacl.getServer().getCell(),
				serversJacl.getBindingLocation(),
				jaclDir (serversJacl.getJaclRootPath()),
				jaclDir (serversJacl.getWorkspaceServerPath()),
				serversJacl.getSqlserverLibPath(),
				serversJacl.getMySQLLibPath(),
				serversJacl.getDb2JavaPath(),
				serversJacl.getDb2LibPath(),
				serversJacl.getDb2UniversalDriverLibPath(),
				str1, str2, serversJacl.getMQJmsLibRoot()
		};

		boolean bool = (new File (serversJacl.getWorkspaceServerPath()).mkdir());
		System.out.println("Created WorkspaceServerPath; Success = "+bool);

		ExecuteCommandThread exec = new ExecuteCommandThread(m_app, strCmd, new File(cwd), message);
		exec.setCursor(false);
		exec.start();
		System.out.println("<<< Servers::executeJacl; ");
	}

	public void showConfigCellFile (String strName, String strFile) {
		System.out.println("showConfigCellFile; "+strName+","+strFile);
		Server server = m_servers.getServer (strName);
		if (server == null) return;
		String strTmp = makeServerCellName (server, strFile);
		System.out.println("file :"+strTmp+":");
		if ((new File(strTmp)).isFile()) editFile(strTmp);
	}
	public void showConfigNodesFile (String strName, String strFile) {
		System.out.println("showConfigNodesFile; "+strName+","+strFile);
		Server server = m_servers.getServer (strName);
		if (server == null) return;
		String strTmp = makeServerNodeName (server, strFile);
		System.out.println("file :"+strTmp+":");
		if ((new File(strTmp)).isFile()) editFile(strTmp);
	}
	public void showConfigServerFile (String strName, String strFile) {
		System.out.println("showConfigServerFile; "+strName+","+strFile);
		Server server = m_servers.getServer (strName);
		if (server == null) return;
		String strTmp = makeServerServerName (server, strFile);
		System.out.println("file :"+strTmp+":");
		if ((new File(strTmp)).isFile()) editFile(strTmp);
	}
	private void editFile (final String strFile) {
		String[] strCmd = {m_appProps.getEditor(), strFile};
		System.out.println("strCmd :"+strCmd+":");
		try {
			Runtime.getRuntime().exec(strCmd);
		}
		catch (IOException e) {
			System.out.println("cannot run command "+strCmd);
		}
	}

	private String makeServerLogName (String s1, String s2) {
		Server server = m_servers.getServer (s1);
		if (server == null) return "";
		return server.getPath() + makeName("logs") + makeName("server1") + makeName(s2);
	}
	private String makeServerBinName (String s1, String s2) {
		Server server = m_servers.getServer (s1);
		if (server == null) return "";
		return server.getPath() + makeName("bin") + makeName(s2);
	}
    private String makeServerFirststepsName (String s1, String s2) {
		Server server = m_servers.getServer (s1);
		if (server == null) return "";
    	return server.getPath() + makeName("firststeps") + makeName(s2);
    }
	private String makeServerCellName (Server server, String file) {
		return server.getPath() + makeName("config") + makeName("cells") + makeName(server.getCell()) + makeName(file);
	}
	private String makeServerNodeName (Server server, String file) {
		return server.getPath() + makeName("config") + makeName("cells") + makeName(server.getCell()) + makeName("nodes") +
				makeName(server.getNode()) + makeName(file);
	}
	private String makeServerServerName (Server server, String file) {
		return server.getPath() + makeName("config") + makeName("cells") + makeName(server.getCell()) + makeName("nodes") +
				makeName(server.getNode()) + makeName("servers") + makeName("server1") + makeName(file);
	}
	private String makeServerWsadminName (Server server, String file) {
		return server.getPath() + makeName(file);
	}
	private String jaclDir (String path) {return path.replace('\\', '/');}

	public static String makeName (String s1) {return File.separatorChar + s1;}
	public static String makeWorkspaceServerPath (String s1, String s2) {
		return s1 + File.separatorChar + "ws_" + s2;
	}
}
