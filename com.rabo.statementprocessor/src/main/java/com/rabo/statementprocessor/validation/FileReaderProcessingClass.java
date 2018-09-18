package com.rabo.statementprocessor.validation;

import java.io.IOException;

import com.rabo.statementprocessor.pojo.*;
import com.rabo.statementprocessor.validation.RecordReferenceValidation;
/**
 * this class receives the input as CsvANDXmlPojo[] from the csvand xml reader class.
 */
public class FileReaderProcessingClass {
	/**
	 * 
	 * @param filearray
	 * @ In this method the input from the csv and xml reader class was stores into String array and used that data for 
	 * validation purpose.
	 * 
	 * @exception IOException,ClassNotFoundException On input error.
	 * @see IOException,ClassNotFoundException.
	 */
	public static void fileReader(CsvANDXmlPojo[] filearray) throws ClassNotFoundException, IOException {

		String[] cusDetails= new String[filearray.length];

		for (int i = 0; i < filearray.length; i++) {
			cusDetails[i] = filearray[i].getReference() + "," + filearray[i].getAccountNumber() + ","
					+ filearray[i].getDescription() + "," + filearray[i].getStartBal() + ","
					+ filearray[i].getMutation() + "," + filearray[i].getEndBal();

		}
		RecordReferenceValidation.validationMethod(cusDetails);

	}
}
