
package com.idc.rad.gui;

/**
* @author John Vincent
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AppMenu extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 9084322971963535229L;
	private App m_app;
	private JMenu m_menuFile;
	private JMenu m_menuOptions;
	private JMenu m_menuNew;
	private JMenuItem m_menuItemRefresh;
	private JMenuItem m_menuItemProperties;
	private JMenuItem m_menuItemExit;
	private JMenuItem m_menuItemWorkspaces;
	private JMenuItem m_menuItemServers;
	private JMenuItem m_menuItemNewWorkspace;
	private JMenuItem m_menuItemNewServer;

	public AppMenu (App app) {
		m_app = app;
		m_menuFile = new JMenu("File");
		m_menuNew = new JMenu("New");
		m_menuFile.add(m_menuNew);
		m_menuItemNewWorkspace = new JMenuItem("Workspace");
		m_menuItemNewWorkspace.addActionListener(this);
		m_menuNew.add(m_menuItemNewWorkspace);
		m_menuItemNewServer = new JMenuItem("Server");
		m_menuItemNewServer.addActionListener(this);
		m_menuNew.add(m_menuItemNewServer);

		m_menuItemRefresh = new JMenuItem("Refresh");
		m_menuItemRefresh.addActionListener(this);
		m_menuFile.add(m_menuItemRefresh);

		m_menuItemProperties = new JMenuItem("Properties");
		m_menuItemProperties.addActionListener(this);
		m_menuFile.add(m_menuItemProperties);

		m_menuItemExit = new JMenuItem("Exit");
		m_menuItemExit.addActionListener(this);
		m_menuFile.add(m_menuItemExit);
		add(m_menuFile);

		m_menuOptions = new JMenu("Options");
		m_menuItemWorkspaces = new JMenuItem();
		m_menuItemWorkspaces.setText("Workspaces");
		m_menuItemWorkspaces.addActionListener(this);
		m_menuItemWorkspaces.setEnabled(false);
		m_menuItemServers = new JMenuItem("Servers");
		m_menuItemServers.addActionListener(this);
		m_menuOptions.add(m_menuItemWorkspaces);
		m_menuOptions.add(m_menuItemServers);
		add(m_menuOptions);
	}
	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JMenuItem) {
			if (source == m_menuItemExit) {
				m_app.doStopClient();
			}
			else if (source == m_menuItemNewWorkspace) {
				m_menuItemWorkspaces.setEnabled(false);
				m_menuItemServers.setEnabled(true);
				m_app.doNewWorkspace();
			}
			else if (source == m_menuItemNewServer) {
				System.out.println("new server");
				m_app.getAppUtils().createServer();
			}
			else if (source == m_menuItemRefresh) {
				System.out.println("doRefresh");
				m_app.doRefresh();
			}
			else if (source == m_menuItemProperties) {
				System.out.println("doProperties");
				m_app.doProperties();
			}
			else if (source == m_menuItemWorkspaces) {
				System.out.println("doWorkspace");
				m_menuItemWorkspaces.setEnabled(false);
				m_menuItemServers.setEnabled(true);
				m_app.doWorkSpace();
			}
			else if (source == m_menuItemServers) {
				System.out.println("doServers");
				m_menuItemWorkspaces.setEnabled(true);
				m_menuItemServers.setEnabled(false);
				m_app.doServers();
			}
		}
	}
}
