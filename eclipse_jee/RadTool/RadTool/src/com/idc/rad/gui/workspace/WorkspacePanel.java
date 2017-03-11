package com.idc.rad.gui.workspace;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.idc.rad.gui.MessagesPanel;

public class WorkspacePanel extends JPanel {
	private static final long serialVersionUID = 7092755881139945506L;
	private MessagesPanel m_messagesPanel;

	public WorkspacePanel (WorkspaceTreePanel workspaceTreePanel) {
		setLayout (new BorderLayout());
		setBorder (BorderFactory.createEmptyBorder(5, 10, 5, 10));
		add (workspaceTreePanel, BorderLayout.WEST);
		m_messagesPanel = new MessagesPanel(40,40);
		m_messagesPanel.setBorder (BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add (m_messagesPanel, BorderLayout.CENTER);		
	}
	public MessagesPanel getMessagesPanel() {return m_messagesPanel;}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
