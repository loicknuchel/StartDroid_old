package com.lknuchel.android.startdroidlight.io.sqlite;

public final class DbConstants {
    public static final int VERSION_BDD = 1;
    public static final String NOM_BDD = "startdroid.db";

    public static final String KEYVALUE_TABLE = "keyValueTable";
    public static final String KEYVALUE_COL_ID = "id";
    public static final String KEYVALUE_COL_KEY = "key";
    public static final String KEYVALUE_COL_VALUE = "value";
    public static final int KEYVALUE_NUM_COL_ID = 0;
    public static final int KEYVALUE_NUM_COL_KEY = 1;
    public static final int KEYVALUE_NUM_COL_VALUE = 2;

    public static final String KEYNAME_TABLE = "keyNameTable";
    public static final String KEYNAME_COL_ID = "id";
    public static final String KEYNAME_COL_KEY = "key";
    public static final String KEYNAME_COL_NAME = "name";
    public static final int KEYNAME_NUM_COL_ID = 0;
    public static final int KEYNAME_NUM_COL_KEY = 1;
    public static final int KEYNAME_NUM_COL_NAME = 2;
}
