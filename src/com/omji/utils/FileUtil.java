package com.omji.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtil {

	// check if a directory exists

	public static boolean checkForDirectory(String fileDir) throws Exception {
		File f;
		try {
			// check for file existing
			f = new File(fileDir);
			return f.isDirectory();
		} finally {
			f = null;
		}

	}

	// check if a file exists

	public static boolean checkForFile(String fileDir) throws Exception {
		File f;
		try {
			// check for file existing
			f = new File(fileDir);
			return f.isFile();
		} finally {
			f = null;
		}
	}

	// file name from the String path for example : C:\\omji\\file\\new_file.txt
	// --- output -> new_file.txt
	public static String getFileNameFromPath(String fullFileName) {
		File f = new File(fullFileName);
		String fname = f.getName();
		f = null;
		return fname;
	}

	// checks for directory and if does not exist - creates a directory

	public static void checkCreateDirectory(String fileDir) throws Exception {
		System.out.println("CreateDirectory = " + fileDir);
		if (!checkForDirectory(fileDir))
			createDir(fileDir);
	}

	// Create a directory based on parent path and name.

	public static File createDir(File dir, String name) throws IOException {
		return createDir(dir.getAbsolutePath() + File.separator + name);
	}

	// Create a directory based on dir String and name String passed in

	public static File createDir(String dir, String name) throws IOException {
		return createDir(dir + File.separator + name);
	}

	// Create a directory based on dir String passed in

	public static File createDir(String dir) throws IOException {
		File tmpDir = new File(dir);
		if (!tmpDir.exists()) {
			if (!tmpDir.mkdirs()) {
				throw new IOException("Could not create temporary directory: " + tmpDir.getAbsolutePath());
			}
		} else {
			System.out.println("Not creating directory, " + dir + ", this directory already exists.");
		}
		return tmpDir;
	}

	// Copy a file from one directory to another directory

	public static boolean movefile(String filetoMove, String destinationFilePath, boolean haltIfFail) {
		// File (or directory) to be moved
		File file = new File(filetoMove);

		// Destination directory
		File dir = new File(destinationFilePath);

		// Move file to new directory
		boolean success = file.renameTo(new File(dir, file.getName()));
		if (!success) {
			System.err.println("The file " + filetoMove + " was not successfully moved");
			if (haltIfFail)
				System.exit(1);
		}
		return success;
	}

	// Delete the target directory and its contents.

	public static boolean deleteDirectory(String strTargetDir) {
		File fTargetDir = new File(strTargetDir);
		if (fTargetDir.exists() && fTargetDir.isDirectory()) {
			return deleteDirectory(fTargetDir);
		} else {
			return false;
		}
	}

	// Delete the target directory and its contents.
	public static boolean deleteDirectory(File dir) {
		if (dir == null)
			return true;
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (String element : children) {
				boolean success = deleteDirectory(new File(dir, element));
				if (!success) {
					System.err.println("Unable to delete file: " + new File(dir, element));
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	// deleteFile
	public static void deleteFile(String filePath) {
		File f;
		try {
			// check for file existing
			f = new File(filePath);
			if (f.isFile()) {
				f.delete();
			}
		} finally {
			f = null;
		}
	}

	// write file

	public static void writeFile(String filePath, String txtToWrite) throws IOException {
		Writer out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
		try {
			out.write(txtToWrite);
		} finally {
			out.close();
		}
	}

	public static void writeFileAppend(String filePath, String txtToWrite) throws IOException {
		Writer out = new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8");
		try {
			out.write(txtToWrite);
		} finally {
			out.close();
		}
	}

}
