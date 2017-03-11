package com.idc.rad.app;

/**
 * @author John Vincent
 *
 */

import java.io.Serializable;

public class ServersJacl implements Serializable {
	private static final long serialVersionUID = -1788179442383781572L;
	private String jaclFile;
	private Servers.Server server;
	private String instance;

	private String rootPath;
	private String workspaceServerPath;
	private String bindingLocation;
	private String db2JavaPath;
	private String db2LibPath;
	private String db2UniversalDriverLibPath;
	private String mqJmsLibRoot;
	private String sqlserverLibPath;

	private String earName;
	private String earFile;

	public ServersJacl (AppProps appProps, String jaclFile, Servers.Server server) { 
		this.jaclFile = jaclFile;
		this.server = server;
		this.instance = appProps.getInstance();
		this.bindingLocation = appProps.getBindingLocation();
		this.rootPath = appProps.getRootPath();
		this.workspaceServerPath = appProps.getWorkspaceServerPath();
		this.sqlserverLibPath = appProps.getSqlserverLibPath();
		this.db2JavaPath = appProps.getDb2JavaPath();
		this.db2LibPath = appProps.getDb2LibPath();
		this.db2UniversalDriverLibPath = appProps.getDb2UniversalDriverLibPath();
		this.mqJmsLibRoot = appProps.getMQJmsLibRoot();
	}
	public Servers.Server getServer() {return server;}

	public String getJaclFile() {return jaclFile;}
	public String getInstance() {return instance;}
	public String getBindingLocation() {return bindingLocation;}
	public String getRootPath() {return rootPath;}
	public String getWorkspaceServerPath() {return workspaceServerPath;}
	public String getSqlserverLibPath() {return sqlserverLibPath;}
	public String getDb2JavaPath() {return db2JavaPath;}
	public String getDb2LibPath() {return db2LibPath;}
	public String getDb2UniversalDriverLibPath() {return db2UniversalDriverLibPath;}
	public String getMQJmsLibRoot() {return mqJmsLibRoot;}
	public String getEarName() {return earName;}
	public String getEarFile() {return earFile;}

	public void setJaclFile (String s) {jaclFile = s;}
	public void setBindingLocation (String bindingLocation) {this.bindingLocation = bindingLocation;}
	public void setRootPath (String rootPath) {this.rootPath = rootPath;}
	public void setWorkspaceServerPath (String workspaceServerPath) {this.workspaceServerPath = workspaceServerPath;}
	public void setSqlserverLibPath (String sqlserverLibPath) {this.sqlserverLibPath = sqlserverLibPath;}
	public void setDb2JavaPath (String db2JavaPath) {this.db2JavaPath = db2JavaPath;}
	public void setDb2LibPath (String db2LibPath) {this.db2LibPath = db2LibPath;}
	public void setDb2UniversalDriverLibPath (String db2UniversalDriverLibPath) {this.db2UniversalDriverLibPath = db2UniversalDriverLibPath;}
	public void setMQJmsLibRoot (String mqJmsLibRoot) {this.mqJmsLibRoot = mqJmsLibRoot;}

	public void setEarName (String earName) {this.earName = earName;}
	public void setEarFile (String earFile) {this.earFile = earFile;}

	public String toString() {
		return "("+getBindingLocation()+","+getRootPath()+","+getWorkspaceServerPath()+","+
			getSqlserverLibPath()+","+getDb2JavaPath()+","+getDb2LibPath()+","+
			getDb2UniversalDriverLibPath()+","+getMQJmsLibRoot()+","+getEarName()+","+getEarFile()+")";
	}
}
