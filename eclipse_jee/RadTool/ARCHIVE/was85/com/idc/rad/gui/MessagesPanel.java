
package com.idc.rad.gui;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MessagesPanel extends JPanel {
	private static final long serialVersionUID = -639673347054675816L;
	private JTextArea m_messagesArea;
	public MessagesPanel (int x, int y) {
		setLayout(new BorderLayout());
		m_messagesArea = new JTextArea(x,y);
		m_messagesArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(m_messagesArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(BorderFactory.createEtchedBorder());
		add(scrollPane, BorderLayout.CENTER);
	}
	public void setMessagesArea (final String msg) {
		SwingUtilities.invokeLater (
				new Runnable() {
					public void run() {
						m_messagesArea.append(msg);
						m_messagesArea.append("\n");
						m_messagesArea.setCaretPosition(
								m_messagesArea.getText().length());                                                                                
					}
				}
		);
	}
	public void resetMessagesArea () {
		SwingUtilities.invokeLater (
				new Runnable() {
					public void run() {
						m_messagesArea.setText("");
						m_messagesArea.setCaretPosition(
								m_messagesArea.getText().length());                                                                                
					}
				}
		);
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
