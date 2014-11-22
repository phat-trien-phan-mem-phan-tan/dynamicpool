package vn.edu.hust.student.dynamicpool.dal.utils.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	protected String rootNodeName = "";
	private Document doc;

	public XMLReader(String path) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(path);
	}

	protected NodeList readPath(String path) {
		return this.querySelectorAll(this.doc, this.rootNodeName + (path.indexOf("/") == 0 ? "" : "/") + path);
	}

	protected NodeList querySelectorAll (Node node, String selector) {
		if (node != null && selector != null) {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			try {
				XPathExpression expr = xpath.compile(selector);
				NodeList nodeList = (NodeList) expr.evaluate(node, XPathConstants.NODESET);
				return nodeList;
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected Node querySelector(Node node, String selector) {
		if (node != null && selector != null) {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			try {
				XPathExpression expr = xpath.compile(selector);
				return (Node) expr.evaluate(node, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected String query(Node node, String selector) {
		if (node != null && selector != null) {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			try {
				XPathExpression expr = xpath.compile(selector);
				return expr.evaluate(node);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected String query(String selector) {
		if (selector != null) {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			try {
				XPathExpression expr = xpath.compile(selector);
				return expr.evaluate(this.doc);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
