package com.lknuchel.android.startdroidfull.model;

import com.lknuchel.android.startdroidfull.io.sqlite.DbAdapter;

// This class has to implement DbAdapter interface only for the V3 bdd or higher

public class KeyName implements DbAdapter {
    private Long id;
    private String key;
    private String name;

    public KeyName() {
    }

    public KeyName(String key, String name) {
	this.setKey(key);
	this.setName(name);
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

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
