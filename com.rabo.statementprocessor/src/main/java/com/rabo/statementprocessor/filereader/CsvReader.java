package com.rabo.statementprocessor.filereader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.rabo.statementprocessor.validation.ValidationClass;
import com.rabo.statementprocessorInterface.FileProcessor;
import com.rabo.statementprocessor.pojo.PojoClass;

@Component
//@Primary
public class CsvReader implements FileProcessor {

	ArrayList<PojoClass> csvList = new ArrayList<PojoClass>();

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void process() throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException {
		CSVReader csvreader = null;
		try {
			csvreader = new CSVReader(new FileReader("input.csv"), ',', '"', 1);

			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(PojoClass.class);
			String[] csv = { "reference", "accountNumber", "description", "startBal", "mutation", "endBal" };
			strategy.setColumnMapping(csv);

			@SuppressWarnings("rawtypes")
			CsvToBean ctb = new CsvToBean();
			@SuppressWarnings("rawtypes")
			ArrayList csvList = (ArrayList) ctb.parse(strategy, csvreader);
			PojoClass[] csvarr = (PojoClass[]) csvList.toArray(new PojoClass[csvList.size()]);

			String[] value = new String[csvarr.length];

			for (int i = 0; i < csvarr.length; i++) {
				value[i] = csvarr[i].getReference() + "," + csvarr[i].getAccountNumber() + ","
						+ csvarr[i].getDescription() + "," + csvarr[i].getStartBal() + "," + csvarr[i].getMutation()
						+ "," + csvarr[i].getEndBal();
				// System.out.println(value[i].toString());
			}

			ValidationClass.validationMethod(value);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		
		 finally
			{
				try
				{
					//closing the reader
					csvreader.close();
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
			}

	}

}