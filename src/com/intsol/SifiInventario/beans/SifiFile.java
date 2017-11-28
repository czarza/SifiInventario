package com.intsol.SifiInventario.beans;

public class SifiFile {
	private String fileName;
	private String month;	// Se guardan los cfdi creando un directorio por mes
	private String path;
	
	public SifiFile() {
		super();
	}
	
	public SifiFile(String fileName, String path) {
		super();
		this.fileName = fileName;
		this.path = path;
	}
	
	public SifiFile(String fileName, String path, String month) {
		super();
		this.fileName = fileName;
		this.path = path;
		this.month = month;
	}


	public String getFileName() { return fileName; }
	public void setFileName(String fileName) { this.fileName = fileName; }
	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }
	public String getMonth() { return month; } 
	public void setMonth(String month) { this.month = month; } 
}
