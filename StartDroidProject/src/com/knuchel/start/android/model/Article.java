package com.knuchel.start.android.model;

public class Article {
    private Long id;
    private String barcode;
    private String barcodeFormat;
    private String name;
    private Long date;
    private Float price;

    public void setAll(String barcode, String barcodeFormat, String name,
	    Long date, Float price) {
	this.barcode = barcode;
	this.barcodeFormat = barcodeFormat;
	this.name = name;
	this.date = date;
	this.price = price;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public void setBarcode(String barcode) {
	this.barcode = barcode;
    }

    public String getBarcode() {
	return barcode;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setDate(Long date) {
	this.date = date;
    }

    public Long getDate() {
	return date;
    }

    public void setPrice(Float price) {
	this.price = price;
    }

    public Float getPrice() {
	return price;
    }

    public void setBarcodeFormat(String barcodeFormat) {
	this.barcodeFormat = barcodeFormat;
    }

    public String getBarcodeFormat() {
	return barcodeFormat;
    }
}
