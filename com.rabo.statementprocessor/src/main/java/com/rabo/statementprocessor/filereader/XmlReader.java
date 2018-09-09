package com.rabo.statementprocessor.filereader;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.rabo.statementprocessor.validation.ValidationClass;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rabo.statementprocessor.filereader.XmlReader;
import com.rabo.statementprocessor.pojo.PojoClass;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.rabo.statementprocessorInterface.FileProcessor;

@Component
 @Primary
public class XmlReader extends DefaultHandler implements FileProcessor {

	private PojoClass read;
	private String temp;
	private ArrayList<PojoClass> accList = new ArrayList<PojoClass>();

	public void process() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException {

		SAXParserFactory spfac = SAXParserFactory.newInstance();

		SAXParser sp = spfac.newSAXParser();

		XmlReader handler = new XmlReader();

		sp.parse("input.XML", handler);

		handler.readList();
	}

	public void characters(char[] buffer, int start, int length) {
		temp = new String(buffer, start, length);
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		temp = "";
		if (qName.equalsIgnoreCase("record")) {
			read = new PojoClass();
			read.setReference(attributes.getValue("reference"));

		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("record")) {
			// add it to the list
			accList.add(read);

		} else if (qName.equalsIgnoreCase("accountNumber")) {
			read.setAccountNumber((temp));
		} else if (qName.equalsIgnoreCase("Description")) {
			read.setDescription((temp));
		} else if (qName.equalsIgnoreCase("startBalance")) {
			read.setStartBal((temp));
		} else if (qName.equalsIgnoreCase("mutation")) {
			read.setMutation((temp));
		}

		else if (qName.equalsIgnoreCase("endBalance")) {
			read.setEndBal((temp));
		}

	}

	private void readList() throws ClassNotFoundException, IOException {

		PojoClass[] xmlarr = accList.toArray(new PojoClass[accList.size()]);

		String[] input = new String[xmlarr.length];

		for (int i = 0; i < xmlarr.length; i++) {
			input[i] = xmlarr[i].getReference() + "," + xmlarr[i].getAccountNumber() + "," + xmlarr[i].getDescription()
					+ "," + xmlarr[i].getStartBal() + "," + xmlarr[i].getMutation() + "," + xmlarr[i].getEndBal();
		}

		ValidationClass.validationMethod(input);

	}

}
