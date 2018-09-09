package com.rabo.statementprocessor;
import com.rabo.statementprocessorInterface.FileProcessor;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class File {

	@Autowired
	
	FileProcessor file;
	
	public FileProcessor getFile() {
		return file;
	}

	public void setFile(FileProcessor file) {
		this.file = file;
	}

	public void reading() throws ClassNotFoundException  {
		
		System.out.println("file reading");
		try {
			file.process();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 