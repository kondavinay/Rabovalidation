package com.rabo.statementprocessor.filereader;

import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.rabo.statementprocessor.validation.RecordReferenceValidation;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rabo.statementprocessor.filereader.XmlReader;
import com.rabo.statementprocessor.pojo.CsvANDXmlPojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.rabo.statementprocessorInterface.FileProcessor;
import com.rabo.statementprocessor.validation.*;
/**
 * 
 * this class reads the Xml file and converts each customer records into string and store that into array for further validation.
 */
@Component
//@Primary
public class XmlReader extends DefaultHandler implements FileProcessor {

	private CsvANDXmlPojo read;
	private String temp;
	private ArrayList<CsvANDXmlPojo> xmlList = new ArrayList<CsvANDXmlPojo>();
	/**
	   * This is the Method implemented from the FileProcessor Interface,in this the Xml file will be read by 
	   * by SAXParser
	   * @param args Unused.
	   
	   * @exception IOException,SAXException,ParserConfigurationException,ClassNotFoundException,on input error
	   * @see IOException,SAXException,ParserConfigurationException,ClassNotFoundException
	   */

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
			read = new CsvANDXmlPojo();
			read.setReference(attributes.getValue("reference"));

		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("record")) {
			// add it to the list
			xmlList.add(read);

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

		CsvANDXmlPojo[] xmlarr = xmlList.toArray(new CsvANDXmlPojo[xmlList.size()]);

		FileReaderProcessingClass.fileReader(xmlarr);

	}

}
