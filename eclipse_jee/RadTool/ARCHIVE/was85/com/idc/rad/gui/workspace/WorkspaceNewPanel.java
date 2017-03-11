package com.idc.rad.gui.workspace;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.idc.rad.app.Message;
import com.idc.rad.gui.App;
import com.idc.rad.gui.MessagesPanel;

public class WorkspaceNewPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1469719367308776511L;
	private App m_app;
	private MessagesPanel m_messagesPanel;
	private JTextField m_nameField;
	private JButton m_btnApp;

	public WorkspaceNewPanel (App app, WorkspaceTreePanel workspaceTreePanel) {
		m_app = app;
		setLayout(new BorderLayout());

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

        JPanel topPane = new JPanel();
		topPane.setLayout(new FlowLayout());
		topPane.add (new JLabel("New Workspace Name "));
		m_nameField = new JTextField(12);
		m_nameField.addActionListener(this);
		topPane.add (m_nameField);
		m_btnApp = new JButton("Create");
		m_btnApp.addActionListener(this);
		topPane.add(m_btnApp);

        JPanel midPane = new JPanel(new BorderLayout());
		m_messagesPanel = new MessagesPanel(37,40);
		m_messagesPanel.setBorder (BorderFactory.createEmptyBorder(0, 10, 0, 0));
		midPane.add (m_messagesPanel, BorderLayout.CENTER);
		
		rightPanel.add (topPane, BorderLayout.NORTH);
		rightPanel.add (midPane, BorderLayout.CENTER);

		add (workspaceTreePanel, BorderLayout.WEST);
		add (rightPanel, BorderLayout.CENTER);
	}

	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			System.out.println("JButton");
			if (source == m_btnApp) {
				System.out.println("btnApp");
				doCreateWorkspace();                           
			}
		}
		else if (source instanceof JTextField) {
			System.out.println("JTextField");
			if (source == m_nameField) {
				System.out.println("m_nameField");
				doCreateWorkspace();                           
			}
		}
		else
			System.out.println("else not a button");
	}
	private void doCreateWorkspace() {
		String name = m_nameField.getText();
		System.out.println("new workspace name is :"+name+":");
		if ("".equals(name)) return;
		m_app.getAppUtils().createWorkspace (name, new Message (m_messagesPanel)); 
	}
	public MessagesPanel getMessagesPanel() {return m_messagesPanel;}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
