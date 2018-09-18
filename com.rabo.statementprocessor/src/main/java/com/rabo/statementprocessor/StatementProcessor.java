package com.rabo.statementprocessor;

import com.rabo.statementprocessorInterface.FileProcessor;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class StatementProcessor {

	@Autowired

	FileProcessor file;

	public FileProcessor getFile() {
		return file;
	}

	public void setFile(FileProcessor file) {
		this.file = file;
	}

	public void csvAndXmlFileReading() throws ClassNotFoundException {

		System.out.println("file reading");
		try {
			file.process();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
	}
}
