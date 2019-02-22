package com.sapient;

import java.io.File;

import com.sapient.constant.FileType;

import com.sapient.reader.CsvReader;
import com.sapient.reader.XmlReader;
import com.sapient.reader.IReader;
import com.sapient.reader.TextReader;

/*
 * TrasactionReader responsible for instantiating various utility class based 
 * on document type and redirect the request to correct utility class.
 */
public class TrasactionReader {

	
	private static TrasactionReader reader = null ;
	
	
	private TrasactionReader(){}
	
	
	public static synchronized TrasactionReader getInstance(){
		if(reader == null){
			reader = new TrasactionReader();
		}
		return reader;
	}

	private static CsvReader csvReader;

	private static TextReader textReader;

	private static XmlReader xmlReader;

	/*
	 * Instantiating the correct reader based on document type.
	 */
	public static TrasactionReader getInstanceBasedOnType(String type) {

		FileType fileType = FileType.valueOf(type);

		switch (fileType) {
		case CSV:
			csvReader = CsvReader.getInstance();
			 break;
		case TEXT:
			textReader = TextReader.getInstance();
			 break;
		case XML:
			xmlReader = XmlReader.getInstance();
			 break;

		default:
          break;
		}

		return TrasactionReader.getInstance();
	}
	
	
	/*
	 * Delegating the request to correct reader 
	 */
	public IReader readFile(FileType fileType, File file) {

		switch (fileType) {
		case CSV:
			csvReader.readTransaction(file);
			return csvReader;
		case TEXT:
			textReader.readTransaction(file);
			return textReader;
		case XML:
			xmlReader.readTransaction(file);
			return xmlReader;

		default:
			return null;
		}
	}

}
