package com.sapient.constant;

public enum Fee {
	
	FIVE_HUNDREAD(500),HUNDREAD(100),FIFTY(50),TEN(10);
	private int fee;
	Fee(int fee) {
		this.fee = fee;
	}
	
	public int getFee() {
		return fee;
	}

}
