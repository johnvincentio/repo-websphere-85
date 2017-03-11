package com.idc.rad.gui.server;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.idc.rad.app.Servers;
import com.idc.rad.app.Servers.Server;
import com.idc.rad.gui.App;

public class ServerTreePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8708929436337806416L;
	private App m_app;
	private Servers m_servers;

	private JTree m_tree = null;
	private DefaultMutableTreeNode m_top;
	private MouseListener m_popupListener;
	private JPopupMenu m_popupAll;
	private JMenuItem m_menuItemStart;
	private JMenuItem m_menuItemStop;
	private JMenuItem m_menuItemLog1;
	private JMenuItem m_menuItemLog2;
	private JMenuItem m_menuItemLog3;
	private JMenuItem m_menuItemLog4;
	private JMenuItem m_menuItemLog5;
	private JMenuItem m_menuItemCreate;
	private JMenuItem m_menuItemDelete;
	private JMenuItem m_menuItemStatus;
	private JMenuItem m_menuItemFirstSteps;
	private JMenuItem m_menuItemConfig;
	private JMenuItem m_menuItemAdmin;

	private JMenu m_menuJacl;	
	private JMenuItem m_menuItemIracJacl;

	private JMenu m_menuHercJacl;	
	private JMenuItem m_menuItemHercJacl;

	private JMenu m_menuHercDeployJacl;
	private JMenuItem m_menuItemHercDeployAdminTool;
	private JMenuItem m_menuItemHercDeployEtrieve;

	private JMenu m_menuHercUndeployJacl;
	private JMenuItem m_menuItemHercUnDeployAdminTool;
	private JMenuItem m_menuItemHercUndeployEtrieve;

	private JMenu m_menuConfig;
	private JMenu m_menuConfigCell;
	private JMenuItem m_menuItemCell1;
	private JMenuItem m_menuItemCell2;
	private JMenuItem m_menuItemCell3;
	private JMenuItem m_menuItemCell4;
	private JMenuItem m_menuItemCell5;
	private JMenuItem m_menuItemCell6;
	private JMenuItem m_menuItemCell7;

	private JMenu m_menuConfigNodes;
	private JMenuItem m_menuItemNodes1;
	private JMenuItem m_menuItemNodes2;
	private JMenuItem m_menuItemNodes3;
	private JMenuItem m_menuItemNodes4;
	private JMenuItem m_menuItemNodes5;
	private JMenuItem m_menuItemNodes6;

	private JMenu m_menuConfigServer;
	private JMenuItem m_menuItemServer1;
	private JMenuItem m_menuItemServer2;
	private JMenuItem m_menuItemServer3;

	public ServerTreePanel (App app, Servers servers) {
		System.out.println("ServerTreePanel constructor");
		m_app = app;
		m_servers = servers;
		setBackground(Color.white);
		setLayout(new BorderLayout());

		m_popupAll = new JPopupMenu();
		m_menuItemStart = new JMenuItem("Start");
		m_menuItemStart.addActionListener(this);
		m_popupAll.add(m_menuItemStart);
		m_menuItemStop = new JMenuItem("Stop");
		m_menuItemStop.addActionListener(this);
		m_popupAll.add(m_menuItemStop);
		m_menuItemStatus = new JMenuItem("Status");
		m_menuItemStatus.addActionListener(this);
		m_popupAll.add(m_menuItemStatus);
		m_popupAll.addSeparator();
/*
		m_menuItemFirstSteps = new JMenuItem("First Steps");
		m_menuItemFirstSteps.addActionListener(this);
		m_popupAll.add(m_menuItemFirstSteps);
		m_popupAll.addSeparator();
*/
		m_menuItemLog1 = new JMenuItem("StartServer.log");
		m_menuItemLog1.addActionListener(this);
		m_popupAll.add(m_menuItemLog1);
		m_menuItemLog2 = new JMenuItem("StopServer.log");
		m_menuItemLog2.addActionListener(this);
		m_popupAll.add(m_menuItemLog2);
		m_menuItemLog3 = new JMenuItem("SystemOut.log");
		m_menuItemLog3.addActionListener(this);
		m_popupAll.add(m_menuItemLog3);
		m_menuItemLog4 = new JMenuItem("SystemErr.log");
		m_menuItemLog4.addActionListener(this);
		m_popupAll.add(m_menuItemLog4);
		m_menuItemLog5 = new JMenuItem("trace.log");
		m_menuItemLog5.addActionListener(this);
		m_popupAll.add(m_menuItemLog5);
		m_popupAll.addSeparator();

		m_menuItemCreate = new JMenuItem("Create Profile");
		m_menuItemCreate.addActionListener(this);
		m_popupAll.add(m_menuItemCreate);
		m_menuItemDelete = new JMenuItem("Delete Profile");
		m_menuItemDelete.addActionListener(this);
		m_popupAll.add(m_menuItemDelete);
		m_popupAll.addSeparator();

		m_menuItemConfig = new JMenuItem("Configuration");
		m_menuItemConfig.addActionListener(this);
		m_popupAll.add(m_menuItemConfig);
		m_menuItemAdmin = new JMenuItem("Admin Console");
		m_menuItemAdmin.addActionListener(this);
		m_popupAll.add(m_menuItemAdmin);
		m_popupAll.addSeparator();

		if (m_app.isJaclActive()) {
			m_menuJacl = new JMenu ("Jacl");
			m_menuItemIracJacl = new JMenuItem ("Irac");
			m_menuItemIracJacl .addActionListener (this);
			m_menuJacl.add (m_menuItemIracJacl);

			m_menuHercJacl = new JMenu("Herc");
			m_menuJacl.add (m_menuHercJacl);

			m_menuItemHercJacl = new JMenuItem("Herc");
			m_menuItemHercJacl.addActionListener (this);
			m_menuHercJacl.add (m_menuItemHercJacl);

			m_menuHercDeployJacl = new JMenu ("Deploy");
			m_menuHercJacl.add (m_menuHercDeployJacl);

			m_menuItemHercDeployAdminTool = new JMenuItem("AdminToolEAR");
			m_menuItemHercDeployAdminTool.addActionListener(this);
			m_menuHercDeployJacl.add(m_menuItemHercDeployAdminTool);

			m_menuItemHercDeployEtrieve = new JMenuItem("EtrieveItEAR");
			m_menuItemHercDeployEtrieve.addActionListener(this);
			m_menuHercDeployJacl.add(m_menuItemHercDeployEtrieve);

			m_menuHercUndeployJacl = new JMenu ("Undeploy");
			m_menuHercJacl.add (m_menuHercUndeployJacl);

			m_menuItemHercUnDeployAdminTool = new JMenuItem("AdminToolEAR");
			m_menuItemHercUnDeployAdminTool.addActionListener(this);
			m_menuHercUndeployJacl.add(m_menuItemHercUnDeployAdminTool);

			m_menuItemHercUndeployEtrieve = new JMenuItem("EtrieveItEAR");
			m_menuItemHercUndeployEtrieve.addActionListener(this);
			m_menuHercUndeployJacl.add(m_menuItemHercUndeployEtrieve);

			m_menuHercJacl.add (m_menuHercDeployJacl);
			m_menuHercJacl.add (m_menuHercUndeployJacl);
			m_popupAll.add(m_menuJacl);
			m_popupAll.addSeparator();
		}

		m_menuConfig = new JMenu("Config");
		m_menuConfigCell = new JMenu("Cell");
		m_menuConfig.add(m_menuConfigCell);
		m_menuItemCell1 = new JMenuItem("cell.xml");
		m_menuItemCell1.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell1);
		m_menuItemCell7 = new JMenuItem("namebindings.xml");
		m_menuItemCell7.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell7);
		m_menuItemCell2 = new JMenuItem("namestore.xml");
		m_menuItemCell2.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell2);
		m_menuItemCell3 = new JMenuItem("resources.xml");
		m_menuItemCell3.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell3);
		m_menuItemCell4 = new JMenuItem("security.xml");
		m_menuItemCell4.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell4);
		m_menuItemCell5 = new JMenuItem("variables.xml");
		m_menuItemCell5.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell5);
		m_menuItemCell6 = new JMenuItem("virtualhosts.xml");
		m_menuItemCell6.addActionListener(this);
		m_menuConfigCell.add(m_menuItemCell6);

		m_menuConfigNodes = new JMenu("Nodes");
		m_menuConfig.add(m_menuConfigNodes);
		m_menuItemNodes1 = new JMenuItem("namestore.xml");
		m_menuItemNodes1.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes1);
		m_menuItemNodes2 = new JMenuItem("node.xml");
		m_menuItemNodes2.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes2);
		m_menuItemNodes3 = new JMenuItem("resources.xml");
		m_menuItemNodes3.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes3);
		m_menuItemNodes4 = new JMenuItem("serverindex.xml");
		m_menuItemNodes4.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes4);
		m_menuItemNodes5 = new JMenuItem("systemapps.xml");
		m_menuItemNodes5.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes5);
		m_menuItemNodes6 = new JMenuItem("variables.xml");
		m_menuItemNodes6.addActionListener(this);
		m_menuConfigNodes.add(m_menuItemNodes6);

		m_menuConfigServer = new JMenu("Server");
		m_menuConfig.add(m_menuConfigServer);
		m_menuItemServer1 = new JMenuItem("resources.xml");
		m_menuItemServer1.addActionListener(this);
		m_menuConfigServer.add(m_menuItemServer1);
		m_menuItemServer2 = new JMenuItem("server.xml");
		m_menuItemServer2.addActionListener(this);
		m_menuConfigServer.add(m_menuItemServer2);
		m_menuItemServer3 = new JMenuItem("variables.xml");
		m_menuItemServer3.addActionListener(this);
		m_menuConfigServer.add(m_menuItemServer3);

		m_popupAll.add(m_menuConfig);

		m_top = new DefaultMutableTreeNode("Servers");
		Iterator<Server> iter = m_servers.getItems();
		while (iter.hasNext()) {
			Server server = iter.next();
			m_top.add (new DefaultMutableTreeNode(server.getName()));
		}
		m_tree = new JTree(m_top);
		m_tree.setEditable(false);
		m_tree.setShowsRootHandles(true);
		m_tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

		m_popupListener = new PopupListener();
		m_tree.addMouseListener(m_popupListener);

		JScrollPane scrollPane = new JScrollPane(m_tree);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(BorderFactory.createEtchedBorder());
		add(scrollPane,BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed");
		String newline = "\n";
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)(e.getSource());
			String s = "Action event detected."
				+ newline
				+ "    Event source: " + source.getText()
				+ " (an instance of " + getClassName(source) + ")";
			System.out.println(s + newline);
			String strNode = getNodeName();
			System.out.println("node "+strNode);
			if (source == m_menuItemStart) {
				if (strNode != null) m_app.getAppUtils().startServer (strNode);
			}
			else if (source == m_menuItemStop) {
				if (strNode != null) m_app.getAppUtils().stopServer (strNode);
			}
			else if (source == m_menuItemStatus) {
				if (strNode != null) m_app.getAppUtils().statusServer (strNode);
			}
			else if (source == m_menuItemFirstSteps) {
				if (strNode != null) m_app.getAppUtils().firstSteps (strNode);
			}
			else if (source == m_menuItemLog1) {
				if (strNode != null) m_app.getAppUtils().log1Server (strNode);
			}
			else if (source == m_menuItemLog2) {
				if (strNode != null) m_app.getAppUtils().log2Server (strNode);
			}
			else if (source == m_menuItemLog3) {
				if (strNode != null) m_app.getAppUtils().log3Server (strNode);
			}
			else if (source == m_menuItemLog4) {
				if (strNode != null) m_app.getAppUtils().log4Server (strNode);
			}
			else if (source == m_menuItemLog5) {
				if (strNode != null) m_app.getAppUtils().log5Server (strNode);
			}
			else if (source == m_menuItemCreate) {
				if (strNode != null) m_app.getAppUtils().createServer();
			}
			else if (source == m_menuItemDelete) {
				if (strNode != null) m_app.getAppUtils().deleteServer (strNode);
			}
			else if (source == m_menuItemConfig) {
				if (strNode != null) m_app.getAppUtils().configServer (strNode);
			}
			else if (source == m_menuItemIracJacl) {
				if (strNode != null) m_app.doServersJacl ("irac.jacl", null, null, strNode);
			}
			else if (source == m_menuItemHercJacl) {
				if (strNode != null) m_app.doServersJacl ("herc.jacl", null, null, strNode);
			}

			else if (source == m_menuItemHercDeployEtrieve) {
				if (strNode != null) m_app.doServersJacl ("hercDeploy.jacl", "EtrieveIt", "EtrieveIt.ear", strNode);
			}
			else if (source == m_menuItemHercDeployAdminTool) {
				if (strNode != null) m_app.doServersJacl ("hercDeploy.jacl", "AdminToolEAR", "AdminToolEAR.ear", strNode);
			}
			
			else if (source == m_menuItemHercUndeployEtrieve) {
				if (strNode != null) m_app.doServersJacl ("hercUndeploy.jacl", "EtrieveIt", null, strNode);
			}
			else if (source == m_menuItemHercUnDeployAdminTool) {
				if (strNode != null) m_app.doServersJacl ("hercUndeploy.jacl", "AdminToolEAR", null, strNode);
			}
			else if (source == m_menuItemAdmin) {
				if (strNode != null) m_app.getAppUtils().adminConsole (strNode);
			}
			else if ((source == m_menuItemCell1) || (source == m_menuItemCell2) ||
					(source == m_menuItemCell3) || (source == m_menuItemCell4) ||
					(source == m_menuItemCell5) || (source == m_menuItemCell6) ||
					(source == m_menuItemCell7)) {
				if (strNode != null) m_app.getAppUtils().showConfigCellFile (strNode, source.getText());
			}
			else if ((source == m_menuItemNodes1) || (source == m_menuItemNodes2) ||
					(source == m_menuItemNodes3) || (source == m_menuItemNodes4) ||
					(source == m_menuItemNodes5) || (source == m_menuItemNodes6)) {
				if (strNode != null) m_app.getAppUtils().showConfigNodesFile (strNode, source.getText());
			}
			else if ((source == m_menuItemServer1) || (source == m_menuItemServer2) || (source == m_menuItemServer3)) {
				if (strNode != null) m_app.getAppUtils().showConfigServerFile (strNode, source.getText());
			}
		}
	}

	private String getNodeName() {
		String strReturn = null;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) m_tree.getLastSelectedPathComponent();
		if (node != null) {
			if (node.isLeaf()) {
				Object nodeInfo = node.getUserObject();
				strReturn = nodeInfo.toString();
			}
		}
		return strReturn;
	}
	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex+1);
	}

	class PopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {maybeShowPopup(e);}
		public void mouseReleased(MouseEvent e) {maybeShowPopup(e);}
		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) m_tree.getLastSelectedPathComponent();
				if (node == null) return;
				if (node.isLeaf())
					m_popupAll.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
