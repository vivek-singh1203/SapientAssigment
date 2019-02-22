package com.sapient.constant;

public enum FileType {
	
	CSV(1),XML(2),TEXT(3);
	private int type;
	
	FileType(int type){
		this.type= type;
	}
}
