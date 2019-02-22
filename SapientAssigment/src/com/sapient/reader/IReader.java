package com.sapient.reader;

import java.io.File;

import com.sapient.constant.FileType;

public interface IReader {

	public void displayReport();

	public IReader readFile(FileType fileType, File file);

}
