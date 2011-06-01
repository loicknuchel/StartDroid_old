package com.knuchel.start.android.model;

import com.knuchel.start.android.io.sqlite.DbAdapter;

public class Employe implements DbAdapter {
    public long id;
    public String name;
    public String job;
    public String company;

    @Override
    public long getId() {
	return id;
    }

}
