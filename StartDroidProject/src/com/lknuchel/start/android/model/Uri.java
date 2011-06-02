package com.lknuchel.start.android.model;

import com.lknuchel.start.android.io.sqlite.DbAdapter;

public class Uri implements DbAdapter {
    public Long id;
    public long date;
    public String uri;
    public String type;
    public String content;
    
    public Long getId(){
	return this.id;
    }
}
