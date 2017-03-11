package com.idc.rad.gui.workspace;

/**
 * @author John Vincent
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.idc.rad.app.Message;
import com.idc.rad.app.Workspaces;
import com.idc.rad.gui.App;

public class WorkspaceTreePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -385654673833292172L;
	private App m_app;
	private JTree m_tree = null;
	private DefaultMutableTreeNode m_top;

    private MouseListener m_popupListener;
    private JPopupMenu m_popupAll, m_popupNew;
    private JMenuItem m_menuItemStart;
    private JMenuItem m_menuItemNew;
   
	public WorkspaceTreePanel (App app, Workspaces workspaces) {
		System.out.println("WorkspaceTreePanel constructor");
		m_app = app;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		m_popupAll = new JPopupMenu();
		m_menuItemStart = new JMenuItem("Start");
		m_menuItemStart.addActionListener(this);
		m_popupAll.add(m_menuItemStart);

		m_popupNew = new JPopupMenu();
		m_menuItemNew = new JMenuItem("New");
		m_menuItemNew.addActionListener(this);
		m_popupNew.add(m_menuItemNew);
		
		m_top =	new DefaultMutableTreeNode("Workspaces");
		Iterator<String> iter = workspaces.getItems();
		while (iter.hasNext()) {
			String workspace = iter.next();
			m_top.add(new DefaultMutableTreeNode(workspace));
		}
		m_tree = new JTree(m_top);
		m_tree.setEditable(false);
		m_tree.setShowsRootHandles(true);
		m_tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
		m_popupListener = new PopupListener();
		m_tree.addMouseListener(m_popupListener);

	    JScrollPane scrollPane = new JScrollPane(m_tree);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(BorderFactory.createEtchedBorder());
//		scrollPane.setBounds(new Rectangle(3, 3, 239, 220));		// x,y, width, height
		add(scrollPane,BorderLayout.CENTER);
}
	public void actionPerformed(ActionEvent e) {
	    String newline = "\n";
		System.out.println("actionPerformed");
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)(e.getSource());
			String s = "Action event detected."
				+ newline
				+ "    Event source: " + source.getText()
				+ " (an instance of " + getClassName(source) + ")";
			System.out.println(s + newline);
			if (source == m_menuItemStart) {
				doStartWorkspace();
			}
			else if (source == m_menuItemNew) {
				m_app.doNewWorkspace();
			}
		}
	}
	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex+1);
	}
	private void doStartWorkspace() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) m_tree.getLastSelectedPathComponent();
		if (node == null) return;
		if (node.isLeaf()) {
			Object nodeInfo = node.getUserObject();
			System.out.println("nodeInfo :"+nodeInfo.toString());
			m_app.getAppUtils().startWorkspace (nodeInfo.toString(), 
					new Message (m_app.getWorkspacePanel().getMessagesPanel()));
		}	
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
				else if (node.isRoot())
					m_popupNew.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	public String toString() {return getClass().getName() + "[" + paramString() + "]";}
}
