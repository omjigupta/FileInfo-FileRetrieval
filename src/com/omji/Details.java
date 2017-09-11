package com.omji;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

import com.omji.utils.GeneralUtil;

public class Details {

	public static void fileInfo(File fileName) throws IOException {
		Path file = null;
		
		file = Paths.get(fileName.toString());
		
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

		System.out.println("file name: " + file.getFileName());
		System.out.println("file path: " + file.getParent().toString());
		System.out.println("file ext: " + GeneralUtil.getFileExtension(file.getFileName().toString()));
		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());
		
		
		try {
		    DosFileAttributes attr1 =
		        Files.readAttributes(file, DosFileAttributes.class);
		    System.out.println("isReadOnly is " + attr1.isReadOnly());
		    System.out.println("isHidden is " + attr1.isHidden());
		    System.out.println("isArchive is " + attr1.isArchive());
		    System.out.println("isSystem is " + attr1.isSystem());
		} catch (UnsupportedOperationException x) {
		    System.err.println("DOS file" +
		        " attributes not supported:" + x);
		}
		
		try{
		PosixFileAttributes attr1 =
			    Files.readAttributes(file, PosixFileAttributes.class);
			System.out.format("%s %s %s%n",
			    attr1.owner().getName(),
			    attr1.group().getName(),
			    PosixFilePermissions.toString(attr1.permissions()));
		} catch (UnsupportedOperationException x) {
		    System.err.println("DOS file" +
		        " attributes not supported:" + x);
		}
			
		FileStore store = Files.getFileStore(file);
		long total = store.getTotalSpace() / 1024;
		long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
		long avail = store.getUsableSpace() / 1024;
		
		System.out.println(total + " " + used + " " + avail);
	}

}
