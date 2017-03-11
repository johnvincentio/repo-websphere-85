package com.idc.rad.gui;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.idc.rad.app.AppProps;
import com.idc.rad.app.Message;

public class PropertiesPanel extends JPanel {
	private static final long serialVersionUID = -7505477785112382920L;
	private MessagesPanel m_messagesPanel;

	public PropertiesPanel (AppProps appProps) {
		setLayout (new BorderLayout());
		setBorder (BorderFactory.createEmptyBorder(5, 10, 5, 10));
		m_messagesPanel = new MessagesPanel(50,60);
		m_messagesPanel.setBorder (BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add (m_messagesPanel, BorderLayout.CENTER);

		AppProps defProps = new AppProps (appProps.getVersion());
		Message message = new Message (m_messagesPanel);
		message.println("The following properties can be set with a properties file, the name of ");
		message.println("which should be passed to RadTool as a parameter.");
		message.println("");
		message.println("	Version of Rad RadTool is currently using.");
		message.println("	value: "+appProps.getVersion());
		message.println("	default: "+defProps.getVersion());
		message.println("root_path ");
		message.println("	Root directory of irac development.");
		message.println("	value: "+appProps.getRootPath());
		message.println("	default: "+defProps.getRootPath());
		message.println("");
		message.println("workspace_server_path");
		message.println("	Directory to be used by the servers for internal usage.");
		message.println("	value: "+appProps.getWorkspaceServerPath());
		message.println("	default: "+defProps.getWorkspaceServerPath());
		message.println("");
		message.println("server_instance");
		message.println("	Internal server name.");
		message.println("	value: "+appProps.getInstance());
		message.println("	default: "+defProps.getInstance());
		message.println("");
		message.println("bindingLocation");
		message.println("	Internal server usage.");
		message.println("	value: "+appProps.getBindingLocation());
		message.println("	default: "+defProps.getBindingLocation());
		message.println("");
		message.println("db2_java_path");
		message.println("	Path to java db2 jars.");
		message.println("	value: "+appProps.getDb2JavaPath());
		message.println("	default: "+defProps.getDb2JavaPath());
		message.println("");
		message.println("db2_lib_path");
		message.println("	Path to db2 jars.");
		message.println("	value: "+appProps.getDb2LibPath());
		message.println("	default: "+defProps.getDb2LibPath());
		message.println("");
		message.println("db2_universal_driver_lib_path");
		message.println("	Path to db2 universal driver jars.");
		message.println("	value: "+appProps.getDb2UniversalDriverLibPath());
		message.println("	default: "+defProps.getDb2UniversalDriverLibPath());
		message.println("");
		message.println("mq_jms_lib_root");
		message.println("	Path to mq_jms_lib_root.");
		message.println("	value: "+appProps.getMQJmsLibRoot());
		message.println("	default: "+defProps.getMQJmsLibRoot());
		message.println("");
		message.println("sqlserver_lib_path");
		message.println("	Path to SQLServer jars.");
		message.println("	value: "+appProps.getSqlserverLibPath());
		message.println("	default: "+defProps.getSqlserverLibPath());

		message.println("");
		message.println("WORKSPACE_DIR: .");
		message.println("	Root directory of RAD workspaces.");
		message.println("	value: "+appProps.getWorkspaceDir());
		message.println("	default: "+defProps.getWorkspaceDir());
		message.println("");
		message.println("SERVER_PROFILE: .");
		message.println("	Root directory of WebSphere server profiles.");
		message.println("	value: "+appProps.getServerDir());
		message.println("	default: "+defProps.getServerDir());

		message.println("");
		message.println("WORKSPACE_START: .");
		message.println("	File used to start the workspace.");
		message.println("	value: "+appProps.getWorkspaceStart());
		message.println("	default: "+defProps.getWorkspaceStart());
		message.println("");
		message.println("WORKSPACE_START_KEY: .");
		message.println("	RAD Internal usage.");
		message.println("	value: "+appProps.getWorkspaceStartKey());
		message.println("	default: "+defProps.getWorkspaceStartKey());
		message.println("");
		message.println("WORKSPACE_START_VALUE: .");
		message.println("	RAD Internal usage.");
		message.println("	value: "+appProps.getWorkspaceStartValue());
		message.println("	default: "+defProps.getWorkspaceStartValue());

		message.println("");
		message.println("SERVER_ROOT: .");
		message.println("	Server root directory.");
		message.println("	value: "+appProps.getServerRoot());
		message.println("	default: "+defProps.getServerRoot());
		message.println("");
		message.println("SERVER_START: .");
		message.println("	File used to start the server.");
		message.println("	value: "+appProps.getServerStart());
		message.println("	default: "+defProps.getServerStart());
		message.println("");
		message.println("SERVER_STOP: .");
		message.println("	File used to stop the server.");
		message.println("	value: "+appProps.getServerStop());
		message.println("	default: "+defProps.getServerStop());
		message.println("");
		message.println("SERVER_STATUS: .");
		message.println("	File used to status the server.");
		message.println("	value: "+appProps.getServerStatus());
		message.println("	default: "+defProps.getServerStatus());
		message.println("");
		message.println("SERVER_START_PROFILE: .");
		message.println("	File used to delete the server.");
		message.println("	value: "+appProps.getServerStartProfile());
		message.println("	default: "+defProps.getServerStartProfile());
		message.println("");
		message.println("SERVER_CREATE_PROFILE: .");
		message.println("	File used to create a server.");
		message.println("	value: "+appProps.getServerCreateProfile());
		message.println("	default: "+defProps.getServerCreateProfile());
		message.println("");
		message.println("SERVER_DELETE_PROFILE: .");
		message.println("	File used to delete a server.");
		message.println("	value: "+appProps.getServerDeleteProfile());
		message.println("	default: "+defProps.getServerDeleteProfile());
		message.println("");
		message.println("SERVER_WSADMIN: .");
		message.println("	File used to run wsadmin on a server.");
		message.println("	value: "+appProps.getServerWsadmin());
		message.println("	default: "+defProps.getServerWsadmin());
		message.println("");
		message.println("BROWSER: .");
		message.println("	Browser to use.");
		message.println("	value: "+appProps.getBrowser());
		message.println("	default: "+defProps.getBrowser());
		message.println("");
		message.println("GUI_EDITOR: .");
		message.println("	Editor to use.");
		message.println("	value: "+appProps.getEditor());
		message.println("	default: "+defProps.getEditor());
	}
	public MessagesPanel getMessagesPanel() {return m_messagesPanel;}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
