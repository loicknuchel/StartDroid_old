package com.knuchel.start.android.model;

import com.knuchel.start.android.io.sqlite.DbAdapter;

public class Uri implements DbAdapter {
    public long id;
    public long date;
    public String uri;
    public String type;
    public String content;
    
    public long getId(){
	return this.id;
    }
}
