package com.xxjwd.transfer;

import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

public class BuildSoapHeader {
	public static Element buildSoapHeader(String NAMESPACE) {
	    Element h = new Element().createElement(NAMESPACE, "SjbgSoapHeader");
	    Element username = new Element().createElement("", "A");
	    username.addChild(Node.TEXT, "3974");
	    h.addChild(Node.ELEMENT, username);
	    Element pass = new Element().createElement("", "P");
	    pass.addChild(Node.TEXT, "zcj");
	    h.addChild(Node.ELEMENT, pass);
	    return h;
	}
}
