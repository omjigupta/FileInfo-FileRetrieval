package com.omji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.omji.utils.FileUtil;

public class ReadDirectory {
	public static int count = 0;
	public static void main(String[] args) throws Exception {
		
		if (args.length != 2) {
			System.out.println(
					"usage <Path to input Directory> <save_location_path> . Terminating ...");
			System.exit(1);
		}

		try {
			FileUtil.checkCreateDirectory(args[1]);
		} catch (Exception e) {
			System.out.println("Unable to locate/create directory " + args[1] + ". Terminating ...");
			System.exit(1);
		}
		
		ArrayList<String> lines = new ArrayList<String>();


		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		while ((strLine = br.readLine()) != null) {
			if (lines.contains(strLine))
				continue;
			lines.add(strLine);
		}
		br.close();
		System.out.println(lines);

		for (String line : lines) {
			doCopy(line, args[1]);
		}
	}

	private static void doCopy(String line, String folder) throws Exception {
		addDirToZipArchive(new File(line), null, true, folder);
	}

	public static void addDirToZipArchive(File fileToZip, String parentDirectoryName, boolean flag, String folder)
			throws Exception {
		if (fileToZip == null || !fileToZip.exists()) {
			return;
		}

		String zipEntryName = fileToZip.getName();
		if (parentDirectoryName == null && flag) {
			for (File file : fileToZip.listFiles()) {
				addDirToZipArchive(file, null, false, folder);
			}
			return;
		}

		if (fileToZip.isDirectory()) {
			for (File file : fileToZip.listFiles())
				addDirToZipArchive(file, zipEntryName, false, folder);
		} else {
			System.out.println(fileToZip.toString());
			FileUtils.copyFileToDirectory(fileToZip, new File(folder));
		
			System.out.println("count-- "+ ++count);
		}
		
	}
}
