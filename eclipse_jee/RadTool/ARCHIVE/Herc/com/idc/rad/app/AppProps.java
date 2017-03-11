
package com.idc.rad.app;

/**
 * @author John Vincent
 *
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class AppProps implements Serializable {
	private static final long serialVersionUID = -2838601603264451999L;

	private int version = 7;

	private String rootPath;
	private String workspaceServerPath;

	private String instance;
	private String bindingLocation;
	private String db2JavaPath;
	private String db2LibPath;
	private String db2UniversalDriverLibPath;
	private String sqlserverLibPath;
	private String mqJmsLibRoot;

	private String editor;
	private String browser;

	private String workspaceStart;
	private String workspaceStartKey;
	private String workspaceStartValue;

	private String workspaceDir;
	private String serverDir;

	private String serverRoot;
	private String serverStart;
	private String serverStop;
	private String serverStatus;
	private String serverFirststeps;
	private String serverStartProfile;
	private String serverCreateProfile;
	private String serverDeleteProfile;
	private String serverWsadmin;

	public AppProps (String[] args) {
		if (args.length > 0) {
			if (args[0].trim().equals("85")) version = 85;
			if (args[0].trim().equals("7")) version = 7;
			if (args[0].trim().equals("6")) version = 6;
		}
		setDefaults();
		System.out.println("version "+version);
		for (int num = 0; num < args.length; num++) {
			if (args[num].length() > 8) process (args[num]);	
		}
	}
	public AppProps (int num) {
		version = num;
		setDefaults();
	}
	private void setDefaults() {
		instance = "server1";
		bindingLocation = "SINGLESERVER";
		db2JavaPath = "C:/Program Files/IBM/SQLLIB_02/java";
		db2LibPath = "C:/Program Files/IBM/SQLLIB_02/lib";
		mqJmsLibRoot = "${MQ_INSTALL_ROOT}/java/lib";

		editor = "C:/Program Files/EditPlus 2/editplus.exe";
		browser = "C:/Program Files/Internet Explorer/iexplore.exe";

		rootPath = getClasspathRootPath();
		System.out.println("rootPath "+rootPath);
		if (rootPath == null || rootPath.length() < 1) rootPath = "c:/development";
		System.out.println("(2) rootPath "+rootPath);

		if (version == 85) {
			workspaceServerPath = "c:\\tmp85";

			workspaceDir = "C:/ws85Eclipse";
			serverDir = "C:/profiles85";

			workspaceStart = "C:\\eclipse-jee\\eclipse.exe";
			workspaceStartKey = "";
			workspaceStartValue = "";

			serverStart = "startServer.bat";
			serverStop = "stopServer.bat";
			serverStatus = "serverStatus.bat";
			serverFirststeps = "firststeps.bat";
			serverRoot = "C:\\Applications\\IBM\\WebSphere\\AppServer";
			serverStartProfile = serverRoot + "\\bin\\wasprofile.bat";
			serverCreateProfile = serverRoot + "\\bin\\ProfileManagement\\pmt.bat";
			serverDeleteProfile = serverRoot + "\\bin\\manageprofiles.bat";
			serverWsadmin = "bin\\wsadmin.bat";

			db2UniversalDriverLibPath = "C:/Applications/IBM/WebSphere/AppServer/universalDriver/lib";
			sqlserverLibPath = "C:/development/Microsoft SQLServer JDBC Driver/sqljdbc_4.0/enu";
		}
		else if (version == 7) {
			workspaceServerPath = "c:\\tmp7";

			workspaceDir = "C:/irac7/wrkspc";
			serverDir = "C:/profiles61";

			workspaceStart = "C:\\Program Files\\IBM\\SDP\\eclipse.exe";
			workspaceStartKey = "-product";
			workspaceStartValue = "com.ibm.rational.rad.product.v75.ide";

			serverStart = "startServer.bat";
			serverStop = "stopServer.bat";
			serverStatus = "serverStatus.bat";
			serverFirststeps = "firststeps.bat";
			serverRoot = "C:\\Program Files\\IBM\\SDP\\runtimes\\base_v61";
			serverStartProfile = serverRoot + "\\bin\\wasprofile.bat";
			serverCreateProfile = serverRoot + "\\bin\\ProfileManagement\\pmt.bat";
			serverDeleteProfile = serverRoot + "\\bin\\manageprofiles.bat";
			serverWsadmin = "bin\\wsadmin.bat";

			db2UniversalDriverLibPath = "C:/Program Files/IBM/SDP/runtimes/base_v61/universalDriver/lib";
			sqlserverLibPath = "C:/development/Microsoft SQL Server 2000 Driver for JDBC/lib";
		}
		else {
			workspaceServerPath = "c:\\tmp";

			workspaceDir = "C:/development";
			serverDir = "C:/profiles";

			workspaceStart = "C:\\Program Files\\IBM\\Rational\\SDP\\6.0\\rationalsdp.exe";
			workspaceStartKey = "";
			workspaceStartValue = "";

			serverStart = "startServer.bat";
			serverStop = "stopServer.bat";
			serverStatus = "serverStatus.bat";
			serverFirststeps = "firststeps.bat";
			serverRoot = "C:\\Program Files\\IBM\\Rational\\SDP\\6.0\\runtimes\\base_v6";
			serverStartProfile = serverRoot + "\\bin\\wasprofile.bat";
			serverCreateProfile = serverRoot + "\\bin\\ProfileCreator\\pctWindows.exe";
			serverDeleteProfile = serverStartProfile;
			serverWsadmin = "bin\\wsadmin.bat";

			db2UniversalDriverLibPath = "C:/Program Files/IBM/Rational/SDP/6.0/runtimes/base_v6/universalDriver/lib";
			sqlserverLibPath = "C:/Program Files/Microsoft SQL Server 2000 Driver for JDBC/lib";
		}
	}
	private String getClasspathRootPath() {
		String[] paths = System.getProperty ("java.class.path").split(";");
		for (int num = 0; num < paths.length; num++) {
			System.out.println("num "+num+" paths[num] "+paths[num]);
			if (! paths[num].contains("RadTool2")) continue;
			int end = paths[num].indexOf("\\RadTool2");
			if (end < 1) continue;
			return paths[num].substring(0, end);
		}
		return null;
	}
	private void process (String file) {
		System.out.println("Properties file:"+file);
		if (file == null) return;

		Properties props = new Properties();
		try {
			props.load (new FileInputStream(file));
		} catch (IOException ioe) {
			System.err.println("Exception getting properties; "+ioe.getMessage());
		}

		String tmp;
		if (isValid(tmp = props.getProperty("root_path"))) rootPath = tmp;
		if (isValid(tmp = props.getProperty("workspace_server_path"))) workspaceServerPath = tmp;
		if (isValid(tmp = props.getProperty("server_instance"))) instance = tmp;
		if (isValid(tmp = props.getProperty("bindingLocation"))) bindingLocation = tmp;
		if (isValid(tmp = props.getProperty("db2_java_path"))) db2JavaPath = tmp;
		if (isValid(tmp = props.getProperty("db2_lib_path"))) db2LibPath = tmp;
		if (isValid(tmp = props.getProperty("db2_universal_driver_lib_path"))) db2UniversalDriverLibPath = tmp;
		if (isValid(tmp = props.getProperty("mq_jms_lib_root"))) mqJmsLibRoot = tmp;
		if (isValid(tmp = props.getProperty("sqlserver_lib_path"))) sqlserverLibPath = tmp;

		if (isValid(tmp = props.getProperty("GUI_EDITOR"))) editor = tmp;
		if (isValid(tmp = props.getProperty("BROWSER"))) browser = tmp;
		if (isValid(tmp = props.getProperty("WORKSPACE_START"))) workspaceStart = tmp;
		if (isValid(tmp = props.getProperty("WORKSPACE_START_KEY"))) workspaceStartKey = tmp;
		if (isValid(tmp = props.getProperty("WORKSPACE_START_VALUE"))) workspaceStartValue = tmp;
		if (isValid(tmp = props.getProperty("WORKSPACE_DIR"))) workspaceDir = tmp;
		if (isValid(tmp = props.getProperty("SERVER_ROOT"))) serverRoot = tmp;
		if (isValid(tmp = props.getProperty("SERVER_PROFILE"))) serverDir = tmp;
		if (isValid(tmp = props.getProperty("SERVER_START"))) serverStart = tmp;
		if (isValid(tmp = props.getProperty("SERVER_STOP"))) serverStop = tmp;
		if (isValid(tmp = props.getProperty("SERVER_STATUS"))) serverStatus = tmp;
		if (isValid(tmp = props.getProperty("SERVER_FIRST_STEPS"))) serverFirststeps = tmp;
		if (isValid(tmp = props.getProperty("SERVER_START_PROFILE"))) serverStartProfile = tmp;
		if (isValid(tmp = props.getProperty("SERVER_CREATE_PROFILE"))) serverCreateProfile = tmp;
		if (isValid(tmp = props.getProperty("SERVER_DELETE_PROFILE"))) serverDeleteProfile = tmp;
		if (isValid(tmp = props.getProperty("SERVER_WSADMIN"))) serverWsadmin = tmp;
		props = null;
	}
	private boolean isValid (String tmp) {
		if (tmp == null) return false;
		if (tmp.trim().length() < 1) return false;
		return true;
	}
	public boolean isJaclActive() {
		if (! isValid(rootPath)) return false;
		if (! isValid(workspaceServerPath)) return false;
		if (! isValid(instance)) return false;
		if (! isValid(bindingLocation)) return false;
		if (! isValid(db2JavaPath)) return false;
		if (! isValid(db2LibPath)) return false;
		if (! isValid(db2UniversalDriverLibPath)) return false;
		if (! isValid(mqJmsLibRoot)) return false;
		if (! isValid(sqlserverLibPath)) return false;
		return true;
	}

	public int getVersion() {return version;}
	public String getRootPath() {return rootPath;}
	public String getWorkspaceServerPath() {return workspaceServerPath;}
	public String getInstance() {return instance;}
	public String getBindingLocation() {return bindingLocation;}
	public String getDb2JavaPath() {return db2JavaPath;}
	public String getDb2LibPath() {return db2LibPath;}
	public String getDb2UniversalDriverLibPath() {return db2UniversalDriverLibPath;}
	public String getMQJmsLibRoot() {return mqJmsLibRoot;}
	public String getSqlserverLibPath() {return sqlserverLibPath;}
	public String getEditor() {return editor;}
	public String getBrowser() {return browser;}
	public String getWorkspaceStart() {return workspaceStart;}
	public String getWorkspaceStartKey() {return workspaceStartKey;}
	public String getWorkspaceStartValue() {return workspaceStartValue;}
	public String getWorkspaceDir() {return workspaceDir;}
	public String getServerRoot() {return serverRoot;}
	public String getServerDir() {return serverDir;}
	public String getServerStart() {return serverStart;}
	public String getServerStop() {return serverStop;}
	public String getServerStatus() {return serverStatus;}
	public String getServerFirststeps() {return serverFirststeps;}
	public String getServerStartProfile() {return serverStartProfile;}
	public String getServerCreateProfile() {return serverCreateProfile;}
	public String getServerDeleteProfile() {return serverDeleteProfile;}
	public String getServerWsadmin() {return serverWsadmin;}
	public String toString() {
		return "("+getVersion()+","+getRootPath()+","+getWorkspaceServerPath()+","+getInstance()+","+
			getBindingLocation()+","+getDb2JavaPath()+","+getDb2LibPath()+","+
			getDb2UniversalDriverLibPath()+","+getMQJmsLibRoot()+","+getSqlserverLibPath()+","+
			getEditor()+","+getBrowser()+","+
			getWorkspaceStart()+","+getWorkspaceStartKey()+","+getWorkspaceStartValue()+","+getWorkspaceDir()+","+
			getServerDir()+","+getServerRoot()+","+
			getServerStart()+","+getServerStop()+","+getServerStatus()+","+getServerFirststeps()+","+
			getServerStartProfile()+","+getServerCreateProfile()+","+getServerDeleteProfile()+","+getServerWsadmin()+")";
	}
}
