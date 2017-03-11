package com.idc.rad.gui.server;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.idc.rad.app.AppProps;
import com.idc.rad.app.AppUtils;
import com.idc.rad.app.Servers;
import com.idc.rad.app.ServersJacl;
import com.idc.rad.gui.App;

public class ServersJaclPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6485494537402081361L;
	private App m_app;
	private ServersJacl m_serversJacl;
	private JTextField m_label1Field = new JTextField(50);
	private JTextField m_label2Field = new JTextField(50);
	private JTextField m_label3Field = new JTextField(50);
	private JTextField m_label4Field = new JTextField(50);

	private JTextField m_param1Field = new JTextField(50);
	private JTextField m_param2Field = new JTextField(50);
	private JTextField m_param3Field = new JTextField(50);
	private JTextField m_param4Field = new JTextField(50);
	private JTextField m_param5Field = new JTextField(50);
	private JTextField m_param6Field = new JTextField(50);
	private JTextField m_jaclFileField = new JTextField(50);
	private JTextField m_param7Field = new JTextField(50);
	private JTextField m_param8Field = new JTextField(50);
	private JTextField m_param9Field = new JTextField(50);
	private JButton m_btnApp;

	public ServersJaclPanel (App app, AppProps appProps, 
			ServerTreePanel serverTreePanel, 
			String jaclFile, String earName, String earFile, Servers.Server server) {
		m_app = app;
		m_serversJacl = new ServersJacl (appProps, jaclFile, server);

		setLayout(new BorderLayout());

		JPanel pane1 = new JPanel();
		pane1.setLayout(new GridLayout(0,1,0,0));
		pane1.setBorder(BorderFactory.createEtchedBorder());

		pane1.add (new JLabel("Server Name "));
		m_label1Field.setText(m_serversJacl.getServer().getName());
		m_label1Field.setEditable(false);
		pane1.add (m_label1Field);
		
		pane1.add (new JLabel(" Node Name "));
		m_label2Field.setText(m_serversJacl.getServer().getNode());
		m_label2Field.setEditable(false);
		pane1.add (m_label2Field);

		pane1.add (new JLabel(" Cell Name "));
		m_label3Field.setText(m_serversJacl.getServer().getCell());
		m_label3Field.setEditable(false);
		pane1.add (m_label3Field);

		pane1.add (new JLabel("Binding Location "));
		m_label4Field.setText(m_serversJacl.getBindingLocation());
		m_label4Field.setEditable(false);
		pane1.add (m_label4Field);

		m_jaclFileField.setText(m_serversJacl.getJaclFile());

		m_param1Field.setText(m_serversJacl.getRootPath());
		m_param2Field.setText(AppUtils.makeWorkspaceServerPath (m_serversJacl.getWorkspaceServerPath(), m_serversJacl.getServer().getName()));
		m_param3Field.setText(m_serversJacl.getSqlserverLibPath());
		m_param4Field.setText(m_serversJacl.getDb2JavaPath());
		m_param5Field.setText(m_serversJacl.getDb2LibPath());
		m_param6Field.setText(m_serversJacl.getDb2UniversalDriverLibPath());
		m_param9Field.setText(m_serversJacl.getMQJmsLibRoot());

		pane1.add (new JLabel("Jacl File "));
		pane1.add (m_jaclFileField);
		pane1.add (new JLabel("RootPath "));
		pane1.add (m_param1Field);
		pane1.add (new JLabel("WorkspaceServerPath "));
		pane1.add (m_param2Field);
		pane1.add (new JLabel("SqlserverLibPath "));
		pane1.add (m_param3Field);
		pane1.add (new JLabel("Db2JavaPath "));
		pane1.add (m_param4Field);
		pane1.add (new JLabel("Db2LibPath "));
		pane1.add (m_param5Field);
		pane1.add (new JLabel("Db2UniversalDriverLibPath "));
		pane1.add (m_param6Field);
		pane1.add (new JLabel("MQJmsLibRoot "));
		pane1.add (m_param9Field);

		if (earName != null) {
			m_param7Field.setText (earName);
			m_param7Field.setEditable(false);
			pane1.add (new JLabel("Ear Name "));
			pane1.add (m_param7Field);
		}

		if (earFile != null) {
			m_param8Field.setText (earFile);
			m_param8Field.setEditable(false);
			pane1.add (new JLabel("Ear File "));
			pane1.add (m_param8Field);
		}

		JPanel pane2 = new JPanel();
		pane2.setLayout(new BorderLayout());
		m_btnApp = new JButton("Submit");
		m_btnApp.addActionListener(this);
		pane2.add(m_btnApp);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,10,0,10);
		c.weightx = 1.0;
		c.weighty = 1.0;   //request any extra vertical space
		c.gridwidth = GridBagConstraints.REMAINDER;
		rightPanel.add (pane1, c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(15,10,10,10);
		rightPanel.add (pane2, c);
		
		add (serverTreePanel, BorderLayout.WEST);
		add (rightPanel, BorderLayout.CENTER);
	}

	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			System.out.println("jbutton");
			if (source == m_btnApp) {
				System.out.println("btnApp");
				if (! isEntryValid(m_jaclFileField)) return;
				if (! isEntryValid(m_param1Field)) return;
				if (! isEntryValid(m_param2Field)) return;
				if (! isEntryValid(m_param3Field)) return;
				if (! isEntryValid(m_param4Field)) return;
				if (! isEntryValid(m_param5Field)) return;
				if (! isEntryValid(m_param6Field)) return;
				if (! isEntryValid(m_param9Field)) return;
				m_serversJacl.setJaclFile (m_jaclFileField.getText().trim());
				m_serversJacl.setRootPath (m_param1Field.getText().trim());
				m_serversJacl.setWorkspaceServerPath (m_param2Field.getText().trim());
				m_serversJacl.setSqlserverLibPath (m_param3Field.getText().trim());
				m_serversJacl.setDb2JavaPath (m_param4Field.getText().trim());
				m_serversJacl.setDb2LibPath (m_param5Field.getText().trim());
				m_serversJacl.setDb2UniversalDriverLibPath (m_param6Field.getText().trim());
				m_serversJacl.setEarName (m_param7Field.getText().trim());
				m_serversJacl.setEarFile (m_param8Field.getText().trim());
				m_serversJacl.setMQJmsLibRoot (m_param9Field.getText().trim());

				m_app.doServersExecuteJacl (m_serversJacl);
			}
		}
		else
			System.out.println("else not a button");
	}
	private boolean isEntryValid (JTextField field) {
		if (field == null) return false;
		String value = field.getText().trim();
		if (value == null || value.length() < 5) return false;
		return true;
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
