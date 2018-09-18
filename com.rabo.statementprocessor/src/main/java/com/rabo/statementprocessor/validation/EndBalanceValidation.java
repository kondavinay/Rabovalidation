package com.rabo.statementprocessor.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import com.rabo.statementprocessor.validation.RecordReferenceValidation;
/**
 * this class validated the end balances by checking the addition of start balance and mutation
 * which should equal to the end balance.
 */
public class EndBalanceValidation {
	static ArrayList<String> failedvalidations = new ArrayList<String>();
	/**
	 * 
	 * @param balance
	 * @ In this method the end balance of customer details were validated and the success validations and failed validations
	 * were seperated by adding failedvalidations to the list of failedvalidations.
	 * @exception IOException On input error.
	 * @see IOException.
	 */
	public static void details(String[] balance) throws IOException {

		File file = new File("EndBalanceValidation.csv");
		FileOutputStream filestraem1 = new FileOutputStream(file);
		ObjectOutputStream objectstream1 = new ObjectOutputStream(filestraem1);
		System.out.println("\nsuccess EndBalance validation records");
		objectstream1.writeObject("\n Endbalance matched records");
		for (String mack : balance) {
			String[] balValidation = mack.split(",");

			BigDecimal startbal = new BigDecimal(balValidation[3]);
			BigDecimal mutationbal = new BigDecimal(balValidation[4]);
			BigDecimal balancetovalidate = startbal.add(mutationbal);
			BigDecimal endbal = new BigDecimal(balValidation[5]);

			if (balancetovalidate.equals(endbal)) {

				System.out.println("\nRecord Transaction Reference : " + balValidation[0]);

				System.out.println("Description: " + balValidation[2]);
				objectstream1.writeObject("\nRecord \nTransaction Reference : " + balValidation[0] + "\n"
						+ "Description: " + balValidation[2]);

			} else if (balancetovalidate != endbal) {

				failedvalidations.add("\nRecord\nTransaction Reference : " + balValidation[0] + "\nDescription  : "
						+ balValidation[2]);

			}

		}
		objectstream1.writeObject("\nEndBalancefailed records");
		System.out.println("\nfailed  EndBalance validation records");
		for (String endBalFailedRecords : failedvalidations) {

			System.out.println(endBalFailedRecords);
			objectstream1.writeObject("\n" + endBalFailedRecords);
			
		}
	}

}
