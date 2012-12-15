package com.MyLibrary.FileIO;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android.annotation.TargetApi;
import android.os.Environment;

@TargetApi(9)
public class WriteFile {
	private static String[] filenames;
	private static File[] Files;
	private static DataOutputStream[] DOS;
	
	public static final String IOSTATE_WR_RE = "Writable and Readable";
	public static final String IOSTATE_NWR_RE = "Writable and Not Readable";
	public static final String IOSTATE_NWR = "Not Writable";
	
	private static final int VERSION = android.os.Build.VERSION.SDK_INT;
	private static final int FROYO = android.os.Build.VERSION_CODES.FROYO;
	
	private int noOfFiles;
	
	public WriteFile(String foldername, String... filename){
		noOfFiles = filename.length;
		
		DOS = new DataOutputStream[noOfFiles];
		Files = new File[noOfFiles];
		filenames = new String[noOfFiles];
		
		System.arraycopy(filename, 0, filenames, 0, noOfFiles);
		Arrays.sort(filenames);
		
		for(int i=0; i< noOfFiles; i++){
			setPath(foldername, filenames[i], i);
		}
	}
	
	private void setPath(String path, String filename, int position){
		File FilePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path + File.separator + "data");
		
		FilePath.mkdirs();
		
		Files[position] = new File(FilePath, filename + ".txt");
		
		if(!Files[position].exists()){
			try {
				Files[position].createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(VERSION > FROYO){
			if(!Files[position].canWrite()){
				Files[position].setWritable(true);
			}
		}
		
		
		try {
			DOS[position] = new DataOutputStream(new FileOutputStream(Files[position]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String isWritable(){
		String state = Environment.getExternalStorageState();
		
		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    return IOSTATE_WR_RE;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    return IOSTATE_NWR_RE;
		} else {
		    return IOSTATE_NWR;
		}
	}
	
	
	public boolean writeString(String filename, String... s) throws IOException{
		int length = s.length;
		
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
				for(int i=0; i< length; i++){
					DOS[position].write(s[i].getBytes());
					DOS[position].flush();
				}
			}
		}
		return Files[position].length() > 0;
	}

	public boolean writeInt(String filename, int... in) throws IOException{
		int length = in.length;
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
			
				for(int i=0; i< length; i++){
					DOS[position].writeInt(in[i]);
					DOS[position].flush();
				}
			}
		}
		return Files[position].length() > 0;
	}
	
	public boolean writeFloat(String filename, float... fl) throws IOException{
		int length = fl.length;
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
			
				for(int i=0; i< length; i++){
			
					DOS[position].writeFloat(fl[i]);
					DOS[position].flush();
				}
			}
		}
		
		return Files[position].length() > 0;
	}
	
	public boolean writeDouble(String filename, double... d) throws IOException{
		int length = d.length;
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
			
				for(int i=0; i< length; i++){
			
					DOS[position].writeDouble(d[i]);
					DOS[position].flush();
				}
			}
		}
		return Files[position].length() > 0;
	}
	
	public boolean writeLong(String filename, long... ln) throws IOException{
		int length = ln.length;
		
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
			
				for(int i=0; i< length; i++){
					DOS[position].writeLong(ln[i]);
					DOS[position].flush();
				}
			}
		}
		return Files[position].length() > 0;
	}
	
	public boolean writeShort(String filename, short... sh) throws IOException{
		int length = sh.length;
		
		int position = Arrays.binarySearch(filenames, filename);
		
		if(position >= 0){
			if(isWritable().equals(IOSTATE_WR_RE)){
			
				for(int i=0; i< length; i++){
					DOS[position].writeShort(sh[i]);
					DOS[position].flush();
				}
			}
		}
		return Files[position].length() > 0;
	}
	
	public void closeBuffers(){
		int noOfDOS;
		if(DOS != null){
			noOfDOS = DOS.length;
		}
		else{
			noOfDOS = 0;
		}
		
		for(int i=0; i < noOfDOS; i++){
			try {
				DOS[i].close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Files[i] = null;
		}
		
		DOS = null;
		Files = null;
	}
}