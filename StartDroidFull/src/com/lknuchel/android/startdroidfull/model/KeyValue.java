package com.lknuchel.android.startdroidfull.model;

import com.lknuchel.android.startdroidfull.io.sqlite.DbAdapter;

// This class has to implement DbAdapter interface only for the V3 bdd or higher

public class KeyValue implements DbAdapter {
    private Long id;
    private String key;
    private String value;

    public KeyValue() {
    }

    public KeyValue(String key, String value) {
	this.setKey(key);
	this.setValue(value);
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public String getKey() {
	return key;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
