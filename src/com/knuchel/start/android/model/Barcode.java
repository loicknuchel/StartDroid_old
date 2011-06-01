package com.knuchel.start.android.model;

public class Barcode {
	private Long id;
	private String format;
	private String value;
	
	public Barcode(){}

	public Barcode(String format, String value){
		this.setFormat(format);
		this.setValue(value);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String toDevString(){
		return "ID : "+Long.toString(this.id)+"\nformat : "+this.format+"\nvalue : "+this.value;
	}
	
	public String toString(){
		return this.value;
	}
}
