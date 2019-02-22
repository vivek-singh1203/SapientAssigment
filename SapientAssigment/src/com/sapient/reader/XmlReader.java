package com.sapient.reader;

import java.io.File;

import com.sapient.constant.FileType;

public class XmlReader extends Reader implements IReader {
	
	private static XmlReader xmlReader = null ;
	
	
	private XmlReader(){}
	
	
	public static synchronized XmlReader getInstance(){
		if(xmlReader == null){
			xmlReader = new XmlReader();
		}
		return xmlReader;
	}

	@Override
	public void readTransaction(File transactionFile) {
	}

	@Override
	public IReader readFile(FileType fileType, File file) {
		return null;
	}

}
