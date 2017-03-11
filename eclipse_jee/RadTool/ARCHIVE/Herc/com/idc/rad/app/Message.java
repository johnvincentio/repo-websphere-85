package com.idc.rad.app;

/**
 * @author John Vincent
 */

import com.idc.rad.gui.MessagesPanel;

public class Message implements OutputLine {
	private MessagesPanel m_panel;
	public Message (MessagesPanel panel) {
		m_panel = panel;
		m_panel.resetMessagesArea();
	}
	public void println(String msg) {
		m_panel.setMessagesArea(msg);
	}
}
