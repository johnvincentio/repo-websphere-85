package com.idc.rad.app;

import java.io.File;
import java.util.ArrayList;

public class AppJacl {
	private ArrayList<String> m_list = new ArrayList<String>();
	private AppProps m_appProps;
	public AppJacl (AppProps appProps) {
		m_appProps = appProps;
		handleJaclList();
	}
	public ArrayList<String> getJaclList() {return m_list;}
	private void handleJaclList() {
//		System.out.println(">>> handleJaclList");
		if (! m_appProps.isJaclActive()) return;
//		System.out.println("m_appProps "+m_appProps.getJaclRootPath());
		File dir = new File (m_appProps.getJaclRootPath());
//		System.out.println("dir "+dir.getPath());
		if (! dir.isDirectory()) return;
		File [] dirFiles = dir.listFiles();
		for (int i = 0; i < dirFiles.length; i++) {
			File subdir = dirFiles[i];
			if (! subdir.isDirectory()) continue;
//			System.out.println("subdir path "+subdir.getPath());
//			System.out.println("subdir name "+subdir.getName());
			String name = subdir.getName();
			if (! name.startsWith("jacl_")) continue;
			if (name.equalsIgnoreCase("jacl_herc")) continue;
			if (name.equalsIgnoreCase("jacl_irac")) continue;
			File [] subdirFiles = subdir.listFiles();
			for (int j = 0; j < subdirFiles.length; j++) {
				File jaclFile = subdirFiles[j];
				if (jaclFile.getName().equals("main.jacl")) {
					m_list.add (name);
					break;
				}
			}
		}
//		System.out.println("<<< handleJaclList");
	}
	public boolean isExists (String name) {
		for (String s : m_list) {
			if (s.equals(name)) return true;
		}
		return false;
	}
	public String toString() {
		StringBuffer buf = new StringBuffer();
		boolean first = true;
		buf.append ("(");
		for (String name : m_list) {
			if (! first) {
				buf.append(", ");
			}
			first = false;
			buf.append(name);
		}
		buf.append (")");
		return buf.toString();
	}
}
