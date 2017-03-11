package com.idc.rad.gui;

/**
 * @author John Vincent
 */

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.idc.rad.app.AppProps;
import com.idc.rad.app.AppUtils;
import com.idc.rad.app.Message;
import com.idc.rad.app.Servers;
import com.idc.rad.app.ServersJacl;
import com.idc.rad.app.Workspaces;
import com.idc.rad.gui.server.ServerTreePanel;
import com.idc.rad.gui.server.ServersExecuteJaclPanel;
import com.idc.rad.gui.server.ServersJaclPanel;
import com.idc.rad.gui.server.ServersPanel;
import com.idc.rad.gui.workspace.WorkspaceNewPanel;
import com.idc.rad.gui.workspace.WorkspacePanel;
import com.idc.rad.gui.workspace.WorkspaceTreePanel;

public class App extends JFrame {
	private static final long serialVersionUID = -6885813381304957198L;
	private AppProps m_appProps;
	private AppUtils m_appUtils;
	private boolean m_bWorkspacesActive = true;
	private WorkspacePanel m_workspacePanel;
	private ServersPanel m_serversPanel;

	public App (String msg, String[] args) {
		super (msg);
		m_appProps = new AppProps (args);
		System.out.println("m_appProps :"+m_appProps+":");

		makeContentPane();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				doStopClient();
			}
		});

		setJMenuBar ((new AppMenu (this)));
		setSize (new Dimension(900,700));
		pack();
		setVisible(true);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle window = getBounds();
		setLocation ((screen.width - window.width) / 2, (screen.height - window.height) / 2);
	}
	private void makeContentPane() {
		m_workspacePanel = new WorkspacePanel (new WorkspaceTreePanel (this, new Workspaces (m_appProps)));
		Servers servers = new Servers (m_appProps);
		m_appUtils = new AppUtils (this, m_appProps, servers);
		m_serversPanel = new ServersPanel (new ServerTreePanel(this, servers));
		if (m_bWorkspacesActive)
			getContentPane().add(m_workspacePanel);
		else
			getContentPane().add(m_serversPanel);
	}
	public static void main(String[] args) {new App("RAD Tool", args);}
	public void doStopClient() {System.exit(0);}
	public AppUtils getAppUtils() {return m_appUtils;}
	public WorkspacePanel getWorkspacePanel() {return m_workspacePanel;}
	public ServersPanel getServersPanel() {return m_serversPanel;}
	public boolean isJaclActive() {return m_appProps.isJaclActive();}

	public void doWorkSpace() {
		System.out.println("App:doWorkspace");
		m_bWorkspacesActive = true;
		getContentPane().removeAll();
		getContentPane().add(getWorkspacePanel());
		pack();
		validate();
		repaint();
	}
	public void doServers() {
		System.out.println("App:doServers");
		m_bWorkspacesActive = false;
		getContentPane().removeAll();
		getContentPane().add(getServersPanel());
		pack();
		validate();
		repaint();
	}
	public Message getServerPanelMessage() {
		System.out.println("App:getServerPanelMessage");
		m_bWorkspacesActive = false;
		getContentPane().removeAll();
		getContentPane().add(getServersPanel());
		pack();
		validate();
		repaint();
		return new Message (getServersPanel().getMessagesPanel());
	}
	public void doNewWorkspace() {
		System.out.println("App:doNewWorkspace");
		WorkspaceNewPanel pane = new WorkspaceNewPanel (this, new WorkspaceTreePanel(this, new Workspaces (m_appProps)));
		m_bWorkspacesActive = true;
		getContentPane().removeAll();
		getContentPane().add(pane);
		pack();
		validate();
		repaint();
	}
	public void doServersJacl (String jaclFile, String earName, String earFile, String node) {
		System.out.println("App:doServersJacl; node :"+node+":");
		Servers servers = new Servers (m_appProps);
		ServersJaclPanel pane = new ServersJaclPanel (this, 
				m_appProps, new ServerTreePanel (this, servers), jaclFile, earName, earFile, servers.getServer(node));
		m_bWorkspacesActive = false;
		getContentPane().removeAll();
		getContentPane().add(pane);
		pack();
		validate();
		repaint();
	}
	public void doServersExecuteJacl(ServersJacl serversJacl) {
		System.out.println("App:doServersExecuteJacl");
		Servers servers = new Servers (m_appProps);
		ServersExecuteJaclPanel pane = new ServersExecuteJaclPanel (this, new ServerTreePanel(this, servers), serversJacl);
		m_bWorkspacesActive = false;
		getContentPane().removeAll();
		getContentPane().add(pane);
		pack();
		validate();
		repaint();
	}
	public void doRefresh() {
		System.out.println("App:doRefresh");
		getContentPane().removeAll();
		makeContentPane();
		pack();
		validate();
		repaint();
	}
	public void doProperties() {
		System.out.println("App:doProperties");
		getContentPane().removeAll();
		getContentPane().add (new PropertiesPanel (m_appProps));
		pack();
		validate();
		repaint();
	}
	public void setBusyCursor() {
		Cursor busyCursor = new Cursor(Cursor.WAIT_CURSOR);
		this.setCursor(busyCursor);
	}
	public void resetCursor() {
		Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		this.setCursor(defaultCursor);
	}
}
/*
	private void testFrame(String note) {
		System.out.println(">>> testFrame; "+note);
		JRootPane rootPane = getRootPane();
		Container container = rootPane.getContentPane();
		Component[] components = container.getComponents();
		for (int num = 0; num < components.length; num++) {
			Component component = components[num];
			if (component instanceof JPanel) {
				System.out.println("Jpanel found");
				JPanel panel = (JPanel) component;
				if (panel instanceof com.idc.rad.gui.workspace.WorkspacePanel) {
					System.out.println("found WorkspacePanel");
				}
				if (panel instanceof com.idc.rad.gui.server.ServersPanel) {
					System.out.println("found ServersPanel");
				}
			}
			component.list();
//			System.out.println("name :"+component.getName()+":");
			System.out.println("<<< testFrame; "+note);
		}
	}
*/
