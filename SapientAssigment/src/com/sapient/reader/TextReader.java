package com.sapient.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sapient.constant.FileType;
import com.sapient.pojo.Transaction;

public class TextReader extends Reader implements IReader {
	
	private static TextReader textReader = null ;
	
	
	private TextReader(){}
	
	
	public static synchronized TextReader getInstance(){
		if(textReader == null){
			textReader = new TextReader();
		}
		return textReader;
	}

	@Override
	public void readTransaction(File file) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {

				String[] attributes = line.split(cvsSplitBy);
				Transaction transaction = setTransaction(attributes);
				saveTransaction(transaction);

			}

		} catch (Exception ec) {

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public IReader readFile(FileType fileType, File file) {
		return null;
	}

}
