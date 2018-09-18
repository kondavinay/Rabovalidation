package com.rabo.statementprocessor.pojo;

public class CsvANDXmlPojo {
	String reference;

	private String accountNumber;
	private String Description;
	private String StartBal;
	private String mutation;
	private String endBal;

	public CsvANDXmlPojo() {

	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getStartBal() {
		return StartBal;
	}

	public void setStartBal(String startBal) {
		StartBal = startBal;
	}

	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	public String getEndBal() {
		return endBal;
	}

	public void setEndBal(String endBal) {
		this.endBal = endBal;
	}

	public CsvANDXmlPojo(String reference, String accountNumber, String description, String startBal, String mutation,
			String endBal) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		Description = description;
		StartBal = startBal;
		this.mutation = mutation;
		this.endBal = endBal;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(getReference() + "," + getAccountNumber() + "," + getDescription() + "," + getMutation() + ","
				+ getEndBal());
		return sb.toString();
	}

}
