package com.a3.lfs.domain;

public class PiFile{

	private String FileGuid;
	private String InspectGuid;
	private String FileLogTime;
	private int InspectType;
	private int PatternType;
	private String PatternName;
	private int InspectCount;
	private String FileName;
	private long FileSize;
	private String FileCreatedTime;
	private String FileModifiedTime;
	private String FileAccessedTime;
	private int FileType;
	private String FilePath;

	public String getFileGuid(){

		return FileGuid;
	}

	public void setFileGuid(String fileGuid){

		FileGuid = fileGuid;
	}

	public String getInspectGuid(){

		return InspectGuid;
	}

	public void setInspectGuid(String inspectGuid){

		InspectGuid = inspectGuid;
	}

	public String getFileLogTime(){

		return FileLogTime;
	}

	public void setFileLogTime(String fileLogTime){

		FileLogTime = fileLogTime;
	}

	public int getInspectType(){

		return InspectType;
	}

	public void setInspectType(int inspectType){

		InspectType = inspectType;
	}

	public int getPatternType(){

		return PatternType;
	}

	public void setPatternType(int patternType){

		PatternType = patternType;
	}

	public String getPatternName(){

		return PatternName;
	}

	public void setPatternName(String patternName){

		PatternName = patternName;
	}

	public int getInspectCount(){

		return InspectCount;
	}

	public void setInspectCount(int inspectCount){

		InspectCount = inspectCount;
	}

	public String getFileName(){

		return FileName;
	}

	public void setFileName(String fileName){

		FileName = fileName;
	}

	public long getFileSize(){

		return FileSize;
	}

	public void setFileSize(long fileSize){

		FileSize = fileSize;
	}

	public String getFileCreatedTime(){

		return FileCreatedTime;
	}

	public void setFileCreatedTime(String fileCreatedTime){

		FileCreatedTime = fileCreatedTime;
	}

	public String getFileModifiedTime(){

		return FileModifiedTime;
	}

	public void setFileModifiedTime(String fileModifiedTime){

		FileModifiedTime = fileModifiedTime;
	}

	public String getFileAccessedTime(){

		return FileAccessedTime;
	}

	public void setFileAccessedTime(String fileAccessedTime){

		FileAccessedTime = fileAccessedTime;
	}

	public int getFileType(){

		return FileType;
	}

	public void setFileType(int fileType){

		FileType = fileType;
	}

	public String getFilePath(){

		return FilePath;
	}

	public void setFilePath(String filePath){

		FilePath = filePath;
	}
}