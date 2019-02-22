package com.sapinet.testcases;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.sapient.reader.CsvReader;

public class ReaderTests {

	File file = null;

	@Before
	public void setUp() {

		file = new File("resources/sample.csv");
	}

	@Test
	public void testFileExit() {

		assertTrue(file.exists());

	}

	@Test
	public void testReadTransaction() {

		CsvReader csvInstance = CsvReader.getInstance();

		csvInstance.readTransaction(file);

		assertTrue(csvInstance.getTransactionList().isEmpty());

	}

}
