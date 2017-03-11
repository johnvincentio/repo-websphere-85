package com.idc.rad.app;

/**
 * @author John Vincent
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.File;

public class Workspaces {
	private ArrayList<String> m_list;
	public Workspaces (AppProps appProps) {
//		System.out.println("appProps.getWorkspaceDir() :"+appProps.getWorkspaceDir()+":");
		m_list = new ArrayList<String>();

		File fileWorkspaceDir = new File (appProps.getWorkspaceDir());
		if (! fileWorkspaceDir.isDirectory()) return;
		File [] allFiles = fileWorkspaceDir.listFiles();
		File aFile;
		String strFile;
		for (int i=0; i<allFiles.length; i++) {
			aFile = allFiles[i];
//			System.out.println("file "+aFile.getName());
			if (! aFile.isDirectory()) continue;
			strFile = aFile.getAbsolutePath() + File.separatorChar + ".metadata";
//			System.out.println("strFile :"+strFile+":");
			aFile = new File (strFile);
			if (! aFile.isDirectory()) continue;
			add (allFiles[i].getName());
		}
		Collections.sort(m_list);
	}
	private void add(String strName) {m_list.add(strName);}
	public Iterator<String> getItems() {return m_list.iterator();}
	public boolean isNone() {return m_list.size() < 1;}
	public int getSize() {return m_list.size();}
}
