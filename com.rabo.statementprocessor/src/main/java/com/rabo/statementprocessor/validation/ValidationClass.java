package com.rabo.statementprocessor.validation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;



public class ValidationClass {
	
	public static void validationMethod(String[] sf) throws IOException, ClassNotFoundException {
		
	   File file = new File("C:\\Users\\Vinay Konda\\Desktop\\New folder (3)\\1\\folder\\myOutput9999.csv");
		FileOutputStream filestraem = new FileOutputStream(file);
		ObjectOutputStream objectstream = new ObjectOutputStream(filestraem);
		ArrayList<String> validrecords = new ArrayList<String>(Arrays.asList(sf));

		Arrays.sort(sf);
		for (int i = 1; i < sf.length; i++) {
			if (sf[i].substring(0, 6).equals(sf[i - 1].substring(0, 6))) {
				validrecords.remove(sf[i]);

			}
		}
		System.out.println("unique record references");
		for (String fg : validrecords) {

			System.out.println(fg);

		}

		ArrayList<String> failedvalidations = new ArrayList<String>();
		String value2[] = validrecords.toArray(new String[validrecords.size()]);
		System.out.println("\nsuccess validation records");
		objectstream.writeChars("\n Endbalance matched records");
		for (String mack : value2) {
			String[] value = mack.split(",");
			
			BigDecimal startbal = new BigDecimal(value[3]);
			BigDecimal mutationbal= new BigDecimal(value[4]);
			BigDecimal  balancetovalidate= startbal.add(mutationbal);
			BigDecimal endbal = new BigDecimal(value[5]);

			if (balancetovalidate.equals(endbal)) {

				System.out.println("\nRecord Transaction Reference : " + value[0]);

				System.out.println("Description: " + value[2]);
				objectstream.writeChars("\nRecord \nTransaction Reference : " +
						 value[0]+"\n"+"Description: " + value[2]);

			} else if (balancetovalidate != endbal) {

				failedvalidations.add("\nRecord\nTransaction Reference : " + value[0] + "\nDescription  : " + value[2]);

			}

		}
		objectstream.writeObject("\nEndBalancefailed records");
		System.out.println("\nfailed  validation records");
		for (String s1 : failedvalidations) {

			System.out.println(s1);
			objectstream.writeObject("\n"+s1);
		}
		objectstream.close();
		filestraem.close();
	}
}
