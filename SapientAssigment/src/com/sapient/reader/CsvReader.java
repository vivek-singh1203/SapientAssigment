package com.sapient.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.sapient.TrasactionReader;
import com.sapient.constant.FileType;
import com.sapient.pojo.Transaction;

public class CsvReader extends Reader implements IReader {
	
	
	
	private static CsvReader csvReader = null ;
	
	
	private CsvReader(){}
	
	
	public List<Transaction> getTransactionList(){
		return transactions;
	}
	
	
	public static synchronized CsvReader getInstance(){
		if(csvReader == null){
			csvReader = new CsvReader();
		}
		return csvReader;
	}

	@Override
	public void readTransaction(File file) {
		FileReader filereader = null;
		String cvsSplitBy = ",";
		try {
			filereader = new FileReader(file);

			// create csvReader object passing
			// file reader as a parameter
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;

			// we are going to read data line by line

			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {

					String[] attributes = cell.split(cvsSplitBy);
					Transaction transaction = setTransaction(attributes);
					saveTransaction(transaction);

				}

			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public IReader readFile(FileType fileType, File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
