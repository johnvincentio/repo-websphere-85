package com.idc.rad.gui.server;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.idc.rad.gui.MessagesPanel;

public class ServersPanel extends JPanel {
	private static final long serialVersionUID = 7924755948240589729L;
	private MessagesPanel m_messagesPanel;

	public ServersPanel (ServerTreePanel serverTreePanel) {
		setLayout (new BorderLayout());
		setBorder (BorderFactory.createEmptyBorder(5, 10, 5, 10));
		add (serverTreePanel, BorderLayout.WEST);
		m_messagesPanel = new MessagesPanel(40,40);
		m_messagesPanel.setBorder (BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add (m_messagesPanel, BorderLayout.CENTER);
	}
	public MessagesPanel getMessagesPanel() {return m_messagesPanel;}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
