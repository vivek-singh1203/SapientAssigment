package com.sapient.constant;

public enum TransactionType {
	BUY("BUY",1),SELL("SELL",2),DEPOSIT("DEPOSITE",3),WITHDRAW("WITHDRAW",4);
	private int type;
	private String name;
	TransactionType(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	public String getName(){
		return  name;
	}
}
