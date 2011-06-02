package com.lknuchel.start.android.model;

import com.lknuchel.start.android.io.sqlite.DbAdapter;

public class Employe implements DbAdapter {
    public Long id;
    public String name;
    public String job;
    public String company;

    @Override
    public Long getId() {
	return id;
    }

}
