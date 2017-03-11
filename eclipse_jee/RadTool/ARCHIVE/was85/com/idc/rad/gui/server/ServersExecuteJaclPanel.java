package com.idc.rad.gui.server;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.idc.rad.app.Message;
import com.idc.rad.app.ServersJacl;
import com.idc.rad.gui.App;
import com.idc.rad.gui.MessagesPanel;

public class ServersExecuteJaclPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3695887868159149789L;
	private App m_app;
	private ServersJacl m_serversJacl;
	private MessagesPanel m_messagesPanel;
	private JButton m_btnApp;

	public ServersExecuteJaclPanel (App app, ServerTreePanel serverTreePanel, ServersJacl serversJacl) {
		m_app = app;
		m_serversJacl = serversJacl;

		setLayout(new BorderLayout());

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		JPanel topPane = new JPanel();
		topPane.setLayout(new FlowLayout());
		topPane.add (new JLabel("Server "));
		JTextField m_serverField = new JTextField(12);
		m_serverField.setText(m_serversJacl.getServer().getName());
		m_serverField.setEditable(false);
		topPane.add (m_serverField);
		topPane.add (new JLabel("Jacl File "));
		JTextField m_fileField = new JTextField(12);
		m_fileField.setText(serversJacl.getJaclFile());
		m_fileField.setEditable(false);
		topPane.add (m_fileField);

		m_btnApp = new JButton("Execute");
		m_btnApp.addActionListener(this);
		topPane.add(m_btnApp);

		JPanel midPane = new JPanel();
		midPane.setLayout (new BorderLayout());
		m_messagesPanel = new MessagesPanel(37,60);
		midPane.add (m_messagesPanel, BorderLayout.CENTER);

		rightPanel.add (topPane,BorderLayout.NORTH);
		rightPanel.add (midPane,BorderLayout.CENTER);

		add (serverTreePanel, BorderLayout.WEST);
		add (rightPanel,BorderLayout.CENTER);
	}
	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			System.out.println("jbutton");
			if (source == m_btnApp) {
				System.out.println("btnApp");
				m_app.getAppUtils().executeJacl (m_serversJacl, new Message (m_messagesPanel));                                         
			}
		}
		else
			System.out.println("else not a button");
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
