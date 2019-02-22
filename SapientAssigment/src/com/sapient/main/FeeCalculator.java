package com.sapient.main;

import java.io.File;
import java.io.IOException;

import com.sapient.TrasactionReader;
import com.sapient.constant.FileType;
import com.sapient.reader.IReader;
/*
 * FeeCalculator class responsible for calculating fee for investment bank based on
 * defined rules.
 */
public class FeeCalculator {

	public static void main(String[] args) throws IOException {

		// "resources/sample.csv"

		File file = new File("resources/sample.csv");

		IReader tranction = TrasactionReader.getInstanceBasedOnType(FileType.CSV.toString()).readFile(FileType.CSV,
				file);

		tranction.displayReport();

	}

}
