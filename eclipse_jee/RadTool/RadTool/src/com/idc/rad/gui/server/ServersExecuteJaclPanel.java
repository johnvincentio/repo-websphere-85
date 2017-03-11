package com.idc.rad.gui.server;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.idc.rad.app.Message;
import com.idc.rad.app.ServersJacl;
import com.idc.rad.gui.App;
import com.idc.rad.gui.MessagesPanel;

public class ServersExecuteJaclPanel extends JPanel {
	private static final long serialVersionUID = -3695887868159149789L;
	private App m_app;
	private ServersJacl m_serversJacl;
	private MessagesPanel m_messagesPanel;

	public ServersExecuteJaclPanel (App app, ServerTreePanel serverTreePanel, ServersJacl serversJacl) {
		m_app = app;
		m_serversJacl = serversJacl;

		setLayout(new BorderLayout());

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout (new BorderLayout());

		JPanel topPane = new JPanel();
		topPane.setLayout(new GridLayout(0,1,0,0));
		topPane.setBorder(BorderFactory.createEtchedBorder());
		topPane.add (new JLabel("Server Name "));
		JTextField m_serverField = new JTextField(12);
		m_serverField.setText(m_serversJacl.getServer().getName());
		m_serverField.setEditable(false);
		topPane.add (m_serverField);

		topPane.add (new JLabel("Jacl File "));
		JTextField m_fileField = new JTextField(12);
		m_fileField.setText(serversJacl.getJaclFile());
		m_fileField.setEditable(false);
		topPane.add (m_fileField);

		topPane.add (new JLabel("Jacl Directory "));
		JTextField m_dirField = new JTextField(15);
		m_dirField.setText(serversJacl.getJaclDir());
		m_dirField.setEditable(false);
		topPane.add (m_dirField);

		topPane.add (new JLabel("Jacl Root Path "));
		JTextField m_rootPathField = new JTextField(40);
		m_rootPathField.setText(serversJacl.getJaclRootPath());
		m_rootPathField.setEditable(false);
		topPane.add (m_rootPathField);

		JPanel midPane = new JPanel();
		midPane.setLayout (new BorderLayout());
		m_messagesPanel = new MessagesPanel(37,60);
		midPane.add (m_messagesPanel, BorderLayout.CENTER);

		rightPanel.add (topPane,BorderLayout.NORTH);
		rightPanel.add (midPane,BorderLayout.CENTER);

		add (serverTreePanel, BorderLayout.WEST);
		add (rightPanel,BorderLayout.CENTER);
	}
	public void doit() {
		m_app.getAppUtils().executeJacl (m_serversJacl, new Message (m_messagesPanel));
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
