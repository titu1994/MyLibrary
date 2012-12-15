package com.MyLibrary.FileIO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class FileSensor {
	private static final Comparator<File> fileComparator = new Comparator<File>() {

		@Override
		public int compare(File f1, File f2) {
			return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
		}
	};
	
	public File[] listTextFiles(String dirName){
		File dir = new File(dirName);
		
		File[] arr = dir.listFiles(new FilenameFilter(){
			
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".txt");
			}
		});
		
		Arrays.sort(arr, fileComparator);
		
		return arr;
	}
	
	public File[] listAllFiles(String dirName){
		File dir = new File(dirName);
		File arr[] = dir.listFiles();
		
		Arrays.sort(arr, fileComparator);
		return arr;
	}
}
