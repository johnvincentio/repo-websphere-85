
package com.idc.rad.app;

/**
 * @author John Vincent
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.idc.rad.gui.App; 

public class ExecuteCommandThread extends Thread {
	private App m_app;
	private String[] m_strCmd;
	private OutputLine m_cout;
	private boolean m_bCursorBusy = true;
	private File m_cwd = null;
	public ExecuteCommandThread (App app, String[] strCmd, File cwd, OutputLine cout) {
		this (app, strCmd, cout);
		m_cwd = cwd;
		System.out.println("cwd :"+cwd+":");
	}
	public ExecuteCommandThread (App app, String[] strCmd, OutputLine cout) {
		m_app = app;
		m_strCmd = strCmd;
		m_cout = cout;
		for (int i=0; i<m_strCmd.length; i++)
			System.out.println("("+i+") value:"+m_strCmd[i]+":");
	}
	public void run() {
		BufferedReader reader = null;
		Process process;
		try {
			setBusyCursor();
			if (m_cwd == null)
				process = Runtime.getRuntime().exec(m_strCmd);
			else
				process = Runtime.getRuntime().exec(m_strCmd, null, m_cwd);

			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String lineRead = null;
			while((lineRead = reader.readLine()) != null) {
				m_cout.println(lineRead);
			}
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}
		finally {
			try {
				resetCursor();
				if (reader!= null) reader.close();
			}
			catch (IOException ex) {}
		}
	}
	public void setCursor (boolean bool) {m_bCursorBusy = bool;}
	private void setBusyCursor() {if (m_bCursorBusy) m_app.setBusyCursor();}
	private void resetCursor() {if (m_bCursorBusy) m_app.resetCursor();}
}
