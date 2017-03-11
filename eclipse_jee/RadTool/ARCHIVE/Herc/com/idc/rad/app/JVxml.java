package com.idc.rad.app;

/**
 * @author John Vincent
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;

public class JVxml {
	public Map<String, String> parse(String strXML) {
		InputSource source = new InputSource(new StringReader(strXML));
		return handle (source);
	}
	public Map<String, String> parse (File file) {
		InputSource source = null;
		try {
			source = new InputSource(new FileInputStream(file));
		}
		catch (FileNotFoundException ex) {
			System.out.println("--- FileNotFoundException ---:");
			System.out.println(ex.getMessage());
		}
		return handle (source);
	}
	private Map<String, String> handle(InputSource source) {
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document doc;
		Map<String, String> map = new HashMap<String, String>();
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(false);
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(source);   
			doc.getDocumentElement().normalize();

			Element elem1 = doc.getDocumentElement();
//			System.out.println ("(1) nodeName "+elem1.getNodeName());
			if (! elem1.getNodeName().equals("serverindex:ServerIndex")) {
				throw new Exception ("Not a serverindex:ServerIndex");
			}

			NodeList nodeList2 = elem1.getChildNodes();
			int len2 = (nodeList2 != null) ? nodeList2.getLength() : 0;
			for (int i2=0; i2<len2; i2++) {
				if (nodeList2 == null) break;
				Node node2 = nodeList2.item(i2);
				if (node2.getNodeType() == Node.ELEMENT_NODE && node2.getNodeName().equals("serverEntries")) {
//					System.out.println("has child (2) node "+node2.getNodeName());

					NodeList nodeList3 = node2.getChildNodes();
					int len3 = (nodeList3 != null) ? nodeList3.getLength() : 0;
					for (int i3=0; i3<len3; i3++) {
						if (nodeList3 == null) break;
						Node node3 = nodeList3.item(i3);
						if (node3.getNodeType() == Node.ELEMENT_NODE && node3.getNodeName().equals("specialEndpoints")) {
//							System.out.println("has child (3) node "+node3.getNodeName());
							String strPointName = getAttrValue ("endPointName", node3);
//							System.out.println("point name "+strPointName);

							NodeList nodeList4 = node3.getChildNodes();
							int len4 = (nodeList4 != null) ? nodeList4.getLength() : 0;
							for (int i4=0; i4<len4; i4++) {
								if (nodeList4 == null) break;
								Node node4 = nodeList4.item(i4);
								if (node4.getNodeType() == Node.ELEMENT_NODE && node4.getNodeName().equals("endPoint")) {
//									System.out.println("has child (4) node "+node4.getNodeName());
									String strPort = getAttrValue ("port", node4);
//									System.out.println("port "+strPort);
									map.put (strPointName, strPort);
								}
							}
						}
					}
				}
			}
		}
		catch (Exception ex) {
			System.out.println("Exception "+ex.getMessage());
		}
		return map;
	}
	private String getAttrValue (String name, Node node) {
		String strReturn = null;
		NamedNodeMap attrs = node.getAttributes();
		int attrCount = (attrs != null) ? attrs.getLength() : 0;
		for (int i=0; i<attrCount; i++) {
			if (attrs == null) break;
			Node attr = attrs.item(i);
//			System.out.println("Name "+attr.getNodeName()+" value "+attr.getNodeValue());
			if (name.equals(attr.getNodeName())) {
				strReturn = attr.getNodeValue();
				break;
			}
		}
		return strReturn;
	}
}
